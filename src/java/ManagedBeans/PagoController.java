package ManagedBeans;

import Entidades.Caja.Caja;
import Entidades.Caja.Ingreso;
import Entidades.Medico.Medico;
import Entidades.Pago.Mes;
import Entidades.Pago.Pago;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.PagoFacade;
import RN.CajaRNLocal;
import RN.MedicoRNLocal;
import RN.PagoRNLocal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("pagoController")
@SessionScoped
public class PagoController implements Serializable {

    @EJB
    private Facades.PagoFacade ejbFacade;
    @EJB
    private Facades.IngresoFacade cajaFacade;
    @EJB
    private Facades.TipoDeIngresoFacade ingresoFacade;
    @EJB
    private CajaRNLocal cajaRNLocal;
    @EJB
    private MedicoRNLocal medicoRNLocal;
    @Inject
    private UsuarioLogerBean usuarioLogerBean;
    private List<Pago> items = null;
    private Pago selected;
    private int cantidadCuotas;
    @EJB
    private PagoRNLocal pagoRNLocal;
    @Inject
    private CajaController cajaController;

    public PagoController() {
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public Pago getSelected() {
        return selected;
    }

    public void setSelected(Pago selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PagoFacade getFacade() {
        return ejbFacade;
    }

    public Pago prepareCreate() {
        selected = new Pago();
        cantidadCuotas = 1;
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (cajaController.hayCajaAbierta()) {
            int mes = selected.getMes();
            int anio = selected.getAnio();
            StringBuilder mensaje = new StringBuilder("");
//        try {
            for (int i = 0; i < cantidadCuotas; i++) {
                if (!pagoRNLocal.existePago(selected.getMedico(), anio, mes)) {

                    selected.setMes(mes);
                    selected.setAnio(anio);
                    persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePago").getString("PagoCreated"));
                    if (selected.getMedico().getTipoSocio().getId() == 1L) {
                        Ingreso caja = new Ingreso();
                        caja.setFecha(new Date());
                        caja.setFechaOperacion(selected.getFechaPago());
                        caja.setDescripcion("Pago, "
                                + selected.getMedico().getPersona()
                                + ", Cuota " + selected.getMes() + " " + selected.getAnio());
                        caja.setTipo(ingresoFacade.find(5L));
                        caja.setImporte(selected.getImporte());
                        caja.setMedico(selected.getMedico());
                        try {
                            caja.setNroComprobante(Integer.parseInt(selected.getNroRecibo()));
                        } catch (NumberFormatException numberFormatException) {
                        }
                        caja.setUsuario(usuarioLogerBean.getUsuario());
                        cajaFacade.create(caja);
                        Caja cajaAbierta = cajaRNLocal.getCajaAbierta();
                        cajaAbierta.getMovimientosCaja().add(caja);
                        cajaRNLocal.edit(cajaAbierta);
                        Medico medico = selected.getMedico();
                        if (medico != null) {
                            medico.getMovimientoCajas().add(caja);
                            medico.getPagos().add(selected);
                            medicoRNLocal.edit(medico);
                        }
                        cajaRNLocal.edit(cajaAbierta);
                    }
                    if (!JsfUtil.isValidationFailed()) {
                        items = null;    // Invalidate list of items to trigger re-query.
                    }
                    if (mes == 12) {
                        mes = 1;
                        anio++;
                    } else {
                        mes++;
                    }
                } else {
                    mensaje = mensaje.append(mes).append("/").append(anio).append("; ");
                }

            }
            if (mensaje != null) {
                JsfUtil.addErrorMessage("Las Cuotas: " + mensaje + " YA se encuentran abonadas");
            }
//        } catch (Exception e) {
//            System.out.println("Error creando Pago: " + e);
//        }
        } else {
            JsfUtil.addErrorMessage("La caja se encuentra cerrada no puede realizar Pagos!");
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePago").getString("PagoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePago").getString("PagoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

//    public Mes[] getMeses() {
//        return Mes.values();
//    }
    public List<Pago> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePago").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePago").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Pago getPago(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Pago> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pago> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Pago.class)
    public static class PagoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PagoController controller = (PagoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pagoController");
            return controller.getPago(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pago) {
                Pago o = (Pago) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pago.class.getName()});
                return null;
            }
        }

    }

}
