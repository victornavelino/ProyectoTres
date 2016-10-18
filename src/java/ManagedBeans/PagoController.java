package ManagedBeans;

import Entidades.Caja.Ingreso;
import Entidades.Pago.Mes;
import Entidades.Pago.Pago;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.PagoFacade;

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

@Named("pagoController")
@SessionScoped
public class PagoController implements Serializable {

    @EJB
    private Facades.PagoFacade ejbFacade;
    @EJB
    private Facades.IngresoFacade cajaFacade;
    @EJB
    private Facades.TipoDeIngresoFacade ingresoFacade;
    @Inject
    private UsuarioLogerBean usuarioLogerBean;
    private List<Pago> items = null;
    private Pago selected;

    public PagoController() {
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
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePago").getString("PagoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            Ingreso caja = new Ingreso();
            caja.setFechaOperacion(new Date());
            caja.setFecha(selected.getFechaPago());
            caja.setDescripcion("Pago, "
                    + selected.getMedico().getPersona()
                    + ", Cuota " + selected.getMes() + " " + selected.getAnio());
            caja.setTipo(ingresoFacade.find(1L));
            caja.setImporte(selected.getImporte());
            caja.setUsuario(usuarioLogerBean.getUsuario());
            cajaFacade.create(caja);
            items = null;    // Invalidate list of items to trigger re-query.
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

    public Mes[] getMeses() {
        return Mes.values();
    }

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
