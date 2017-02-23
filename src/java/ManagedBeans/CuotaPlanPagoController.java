package ManagedBeans;

import Entidades.Caja.Caja;
import Entidades.Caja.Ingreso;
import Entidades.Caja.MovimientoCaja;
import Entidades.Medico.Medico;
import Entidades.Pago.CuotaPlanPago;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.CuotaPlanPagoFacade;
import Facades.MovimientoCajaFacade;
import RN.CajaRNLocal;
import RN.MedicoRNLocal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Named("cuotaPlanPagoController")
@SessionScoped
public class CuotaPlanPagoController implements Serializable {

    @EJB
    private Facades.CuotaPlanPagoFacade ejbFacade;
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
    private List<CuotaPlanPago> items = null;
    private CuotaPlanPago selected;

    public CuotaPlanPagoController() {
    }

    public CuotaPlanPago getSelected() {
        return selected;
    }

    public void setSelected(CuotaPlanPago selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CuotaPlanPagoFacade getFacade() {
        return ejbFacade;
    }

    public Date getNow() {
        return new Date();
    }

    public String estiloTabla(CuotaPlanPago cpp) {
        if (cpp.getFechaPago() == null && cpp.getFechaVencimiento().compareTo(getNow()) >= 0) {
            return "old";
        } else {
            return null;
        }
    }

    public CuotaPlanPago prepareCreate() {
        selected = new CuotaPlanPago();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCuotaPlan").getString("CuotaPlanPagoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCuotaPlan").getString("CuotaPlanPagoUpdated"));
        Ingreso caja = new Ingreso();
        caja.setFecha(new Date());
        caja.setFechaOperacion(selected.getFechaPago());
        caja.setDescripcion("Plan de Pago, "
                + selected.getPlanPago().getMedico().getPersona()
                + ", Cuota " + selected.getCuota());
        caja.setTipo(ingresoFacade.find(2L));
        caja.setImporte(selected.getImporte());
        caja.setUsuario(usuarioLogerBean.getUsuario());
        try {
            caja.setNroComprobante(Integer.parseInt(selected.getNroRecibo()));
        } catch (NumberFormatException numberFormatException) {
        }
        cajaFacade.create(caja);
        Caja cajaAbierta = cajaRNLocal.getCajaAbierta();
        cajaAbierta.getMovimientosCaja().add(caja);
        Medico medico = selected.getPlanPago().getMedico();
        if (medico != null) {
            medico.getMovimientoCajas().add(caja);
            medicoRNLocal.edit(medico);
        }
        cajaRNLocal.edit(cajaAbierta);
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCuotaPlan").getString("CuotaPlanPagoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CuotaPlanPago> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCuotaPlan").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCuotaPlan").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CuotaPlanPago getCuotaPlanPago(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<CuotaPlanPago> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CuotaPlanPago> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CuotaPlanPago.class)
    public static class CuotaPlanPagoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CuotaPlanPagoController controller = (CuotaPlanPagoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cuotaPlanPagoController");
            return controller.getCuotaPlanPago(getKey(value));
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
            if (object instanceof CuotaPlanPago) {
                CuotaPlanPago o = (CuotaPlanPago) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CuotaPlanPago.class.getName()});
                return null;
            }
        }

    }

}
