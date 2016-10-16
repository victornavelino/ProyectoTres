package ManagedBeans;

import Entidades.Caja.Egreso;
import Entidades.Caja.Ingreso;
import Entidades.Caja.MovimientoCaja;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.MovimientoCajaFacade;

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

@Named("movimientoCajaController")
@SessionScoped
public class MovimientoCajaController implements Serializable {

    @EJB
    private Facades.MovimientoCajaFacade ejbFacade;
    private List<MovimientoCaja> items = null;
    private MovimientoCaja selected;
    @Inject
    private UsuarioLogerBean usuarioLogerBean;

    public MovimientoCajaController() {
    }

    public MovimientoCaja getSelected() {
        return selected;
    }

    public void setSelected(MovimientoCaja selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MovimientoCajaFacade getFacade() {
        return ejbFacade;
    }

    public MovimientoCaja prepareCreate() {
        selected = new MovimientoCaja();
        selected.setFecha(new Date());
        initializeEmbeddableKey();
        return selected;
    }
      public MovimientoCaja prepareCreateI() {
        selected = new Ingreso();
        selected.setFecha(new Date());
        initializeEmbeddableKey();
        return selected;
    }
        public MovimientoCaja prepareCreateE() {
        selected = new Egreso();
        selected.setFecha(new Date());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setUsuario(usuarioLogerBean.getUsuario());
        selected.setFecha(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("MovimientoCajaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("MovimientoCajaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("MovimientoCajaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MovimientoCaja> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleMovimientoCaja").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MovimientoCaja getMovimientoCaja(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<MovimientoCaja> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MovimientoCaja> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MovimientoCaja.class)
    public static class MovimientoCajaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovimientoCajaController controller = (MovimientoCajaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movimientoCajaController");
            return controller.getMovimientoCaja(getKey(value));
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
            if (object instanceof MovimientoCaja) {
                MovimientoCaja o = (MovimientoCaja) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovimientoCaja.class.getName()});
                return null;
            }
        }

    }

}
