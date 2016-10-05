package ManagedBeans;

import Entidades.Caja.Egreso;
import Entidades.Caja.FormaDePago;
import Entidades.Caja.TipoDeEgreso;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.EgresoFacade;

import java.io.Serializable;
import java.util.Arrays;
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

@Named("egresoController")
@SessionScoped
public class EgresoController implements Serializable {

    @EJB
    private Facades.EgresoFacade ejbFacade;
    private List<Egreso> items = null;
    private List<FormaDePago> listaFormaDePago=null;
    private Egreso selected;

    public EgresoController() {
    }

    public List<FormaDePago> getListaFormaDePago() {
        return listaFormaDePago;
    }

    public void setListaFormaDePago(List<FormaDePago> listaFormaDePago) {
        this.listaFormaDePago = listaFormaDePago;
    }

    public Egreso getSelected() {
        return selected;
    }

    public void setSelected(Egreso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EgresoFacade getFacade() {
        return ejbFacade;
    }

    public Egreso prepareCreate() {
        selected = new Egreso();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleEgreso").getString("EgresoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleEgreso").getString("EgresoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleEgreso").getString("EgresoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Egreso> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleEgreso").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleEgreso").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Egreso getEgreso(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Egreso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Egreso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Egreso.class)
    public static class EgresoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EgresoController controller = (EgresoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "egresoController");
            return controller.getEgreso(getKey(value));
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
            if (object instanceof Egreso) {
                Egreso o = (Egreso) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Egreso.class.getName()});
                return null;
            }
        }

    }

    public List<FormaDePago> listaFormaDePago() {
        if (listaFormaDePago == null) {
            listaFormaDePago = Arrays.asList(FormaDePago.values());
        }
        return listaFormaDePago;
    }

}
