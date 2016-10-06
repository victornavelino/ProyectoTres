package ManagedBeans;

import Entidades.Caja.TipoDeIngreso;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.TipoDeIngresoFacade;

import java.io.Serializable;
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

@Named("tipoDeIngresoController")
@SessionScoped
public class TipoDeIngresoController implements Serializable {

    @EJB
    private Facades.TipoDeIngresoFacade ejbFacade;
    private List<TipoDeIngreso> items = null;
    private TipoDeIngreso selected;

    public TipoDeIngresoController() {
    }

    public TipoDeIngreso getSelected() {
        return selected;
    }

    public void setSelected(TipoDeIngreso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoDeIngresoFacade getFacade() {
        return ejbFacade;
    }

    public TipoDeIngreso prepareCreate() {
        selected = new TipoDeIngreso();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleTipoDeIngreso").getString("TipoDeIngresoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleTipoDeIngreso").getString("TipoDeIngresoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleTipoDeIngreso").getString("TipoDeIngresoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TipoDeIngreso> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleTipoDeIngreso").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleTipoDeIngreso").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TipoDeIngreso getTipoDeIngreso(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<TipoDeIngreso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoDeIngreso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TipoDeIngreso.class)
    public static class TipoDeIngresoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoDeIngresoController controller = (TipoDeIngresoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoDeIngresoController");
            return controller.getTipoDeIngreso(getKey(value));
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
            if (object instanceof TipoDeIngreso) {
                TipoDeIngreso o = (TipoDeIngreso) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoDeIngreso.class.getName()});
                return null;
            }
        }

    }

}
