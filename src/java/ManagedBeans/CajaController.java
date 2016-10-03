package ManagedBeans;

import Entidades.Caja.Caja;
import Entidades.Caja.MovimientoCaja;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.CajaFacade;
import RN.CajaRNLocal;
import RN.MovimientoCajaRNLocal;
import com.sun.tools.xjc.reader.Ring;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.primefaces.context.RequestContext;

@Named("cajaController")
@SessionScoped
public class CajaController implements Serializable {

    @EJB
    private Facades.CajaFacade ejbFacade;
    @EJB
    private CajaRNLocal cajaRNLocal;
    @EJB
    private MovimientoCajaRNLocal movimientoCajaRNLocal;
    private List<Caja> items = null;
    private Caja selected;
    private boolean isCajaAbierta;
    private String sMensaje = "";
    private BigDecimal cajaInicial;
    BigDecimal saldoFinalCaja = new BigDecimal("0.00");
    BigDecimal totalIngresosCaja = new BigDecimal("0.00");
    BigDecimal totalEgresosCaja = new BigDecimal("0.00");
    BigDecimal cajaArqueo = new BigDecimal("0.00");

    public CajaController() {
    }

    public BigDecimal getCajaArqueo() {
        return cajaArqueo;
    }

    public void setCajaArqueo(BigDecimal cajaArqueo) {
        this.cajaArqueo = cajaArqueo;
    }
    
    public MovimientoCajaRNLocal getMovimientoCajaRNLocal() {
        return movimientoCajaRNLocal;
    }

    public void setMovimientoCajaRNLocal(MovimientoCajaRNLocal movimientoCajaRNLocal) {
        this.movimientoCajaRNLocal = movimientoCajaRNLocal;
    }

    public BigDecimal getSaldoFinalCaja() {
        return saldoFinalCaja;
    }

    public void setSaldoFinalCaja(BigDecimal saldoFinalCaja) {
        this.saldoFinalCaja = saldoFinalCaja;
    }

    public BigDecimal getTotalIngresosCaja() {
        return totalIngresosCaja;
    }

    public void setTotalIngresosCaja(BigDecimal totalIngresosCaja) {
        this.totalIngresosCaja = totalIngresosCaja;
    }

    public BigDecimal getTotalEgresosCaja() {
        return totalEgresosCaja;
    }

    public void setTotalEgresosCaja(BigDecimal totalEgresosCaja) {
        this.totalEgresosCaja = totalEgresosCaja;
    }

    public BigDecimal getCajaInicial() {
        return cajaInicial;
    }

    public void setCajaInicial(BigDecimal cajaInicial) {
        this.cajaInicial = cajaInicial;
    }

    public boolean isIsCajaAbierta() {
        return isCajaAbierta;
    }

    public void setIsCajaAbierta(boolean isCajaAbierta) {
        this.isCajaAbierta = isCajaAbierta;
    }

    public String getsMensaje() {
        return sMensaje;
    }

    public void setsMensaje(String sMensaje) {
        this.sMensaje = sMensaje;
    }

    public CajaRNLocal getCajaRNLocal() {
        return cajaRNLocal;
    }

    public void setCajaRNLocal(CajaRNLocal cajaRNLocal) {
        this.cajaRNLocal = cajaRNLocal;
    }

    public Caja getSelected() {
        return selected;
    }

    public void setSelected(Caja selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CajaFacade getFacade() {
        return ejbFacade;
    }

    public Caja prepareCreate() {
        selected = new Caja();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        isCajaAbierta = true;
        sMensaje = "";
        selected.setFechaInicio(new Date());
        System.out.println("ManagedBeans.CajaController.create()");

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCaja").getString("CajaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCaja").getString("CajaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCaja").getString("CajaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Caja> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCaja").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCaja").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Caja getCaja(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Caja> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Caja> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Caja abrirCaja() {
        //selected = new Caja();
        if (hayCajaAbierta()) {
            System.out.println("ya se encuentra caja abierta");
            selected = cajaRNLocal.getCajaAbierta();
            sMensaje = "Ya se encuentra abierta una Caja del usuario: " + selected.getUsuario();
            isCajaAbierta = true;
            RequestContext.getCurrentInstance().update(":frmPri:EgresoListForm,:frmPri:IngresoListForm");
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya se encuentra una caja Abierta", null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);

        } else {
            selected = new Caja();
            System.out.println("nueva caja");
            RequestContext.getCurrentInstance().update(":frmPri:CajaAbreForm,:frmPri:EgresoListForm");
            RequestContext.getCurrentInstance().execute("PF('CajaAbreDlg').show();");

        }
        return selected;
    }

    public void verificarEstadoCaja() {
        if (!isCajaAbierta) {
            JsfUtil.addSuccessMessage("La Caja esta Cerrada, no puede realizar movimientos");

        }
    }

    public void calcularSaldo() {
        if (isCajaAbierta) {

            selected = cajaRNLocal.getCajaAbierta();
            selected.setMovimientosCaja(movimientoCajaRNLocal.getMovimientosdesdeFecha(selected.getFechaInicio()));
            for (MovimientoCaja movimientoCaja : selected.getMovimientosCaja()) {

                switch (movimientoCaja.getClass().getSimpleName()) {
                    case "Ingreso":
                        totalIngresosCaja = totalIngresosCaja.add(movimientoCaja.getImporte());
                        break;
                    case "Egreso":
                        totalEgresosCaja = totalEgresosCaja.add(movimientoCaja.getImporte());
                        break;
                }

            }
            //calculamos el saldo
            System.out.println("entro calcular sadlo");
            saldoFinalCaja = selected.getCajaInicial().add(totalIngresosCaja).subtract(totalEgresosCaja);
            //RequestContext.getCurrentInstance().update(":frmPri:CajaCierreForm");
            RequestContext.getCurrentInstance().execute("PF('CajaCierreDlg').show();");
        } else {
        }
    }

    public void cerrarCaja() {
        if (selected != null) {
            System.out.println("entro cerrar caja");
            selected.setFechaFin(new Date());
            selected.setCajaFinal(cajaArqueo);
            update();
            isCajaAbierta = false;
            selected = null;
            RequestContext.getCurrentInstance().update(":frmPri:EgresoListForm,:frmPri:IngresoListForm");
            JsfUtil.addSuccessMessage("La caja se cerró exitosamente");
             System.out.println("pasoooo cerrar caja");
        } else {
            JsfUtil.addSuccessMessage("La caja esta Cerrada");
        }

    }

    public boolean hayCajaAbierta() {
        boolean respuesta = false;
        List listCajasAbiertas = cajaRNLocal.buscarCajaAbierta();
        if (listCajasAbiertas.size() > 0) {
            respuesta = true;
        }
        return respuesta;
    }

//    public void altaAbrirCaja() {
//
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
//        DiagAbrirCaja diagAbrirCaja = new DiagAbrirCaja(null, true);
//        diagAbrirCaja.setLocation(Comunes.centrarDialog(diagAbrirCaja));
//        diagAbrirCaja.setVisible(true);
//        String cadenaCajaInicial = diagAbrirCaja.getCajaInicial();
//        if (cadenaCajaInicial != null) {
//            while (!Comunes.validarBigDecimal(cadenaCajaInicial)) {
//                //revisas que solo sea numero
//                JOptionPane.showMessageDialog(null, "Escriba solamente números y . de separador", "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
//                cadenaCajaInicial = JOptionPane.showInputDialog("Ingrese la Caja Inicial");
//            }
//            BigDecimal cajaInicial = new BigDecimal(cadenaCajaInicial);
//            Caja caja = new Caja();
//            Date fechaInicio = Comunes.obtenerFechaActualDesdeDB();
//            //se me hace un quilombo el exportador al excel para compara la fecha con milisegundos
//            String fechaInicioSinMilisegundos = format.format(fechaInicio);
//            try {
//                caja.setFechaInicio(format.parse(fechaInicioSinMilisegundos));
//            } catch (ParseException ex) {
//                Logger.getLogger(CajaFacade.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            caja.setUsuario(usuario);
//            caja.setSucursal(sucursal);
//            caja.setCajaInicial(cajaInicial);
//
//            alta(caja);
//
//        }
//    }
    public void alta(Caja caja) {
        cajaInicial = selected.getCajaInicial();
        selected.setFechaInicio(new Date());
        getFacade().create(caja);
    }

    @PostConstruct
    public void inicializar() {
        isCajaAbierta = false;
        if (hayCajaAbierta()) {
            System.out.println("ya se encuentra caja abierta");
            selected = cajaRNLocal.getCajaAbierta();
            sMensaje = "Ya se encuentra abierta una Caja del usuario: " + selected.getUsuario();
            isCajaAbierta = true;
            RequestContext.getCurrentInstance().update(":frmPri:EgresoListForm,:frmPri:IngresoListForm");
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya se encuentra una caja Abierta", null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
            JsfUtil.addSuccessMessage(sMensaje);

        }
    }

    @FacesConverter(forClass = Caja.class)
    public static class CajaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CajaController controller = (CajaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cajaController");
            return controller.getCaja(getKey(value));
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
            if (object instanceof Caja) {
                Caja o = (Caja) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Caja.class.getName()});
                return null;
            }
        }

    }

}
