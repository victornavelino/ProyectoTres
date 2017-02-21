package ManagedBeans;

import Entidades.Medico.Recertificacion;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.RecertificacionFacade;
import RN.RecertificacionRNLocal;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "recertificacionController")
@SessionScoped
public class RecertificacionController implements Serializable {

    @EJB
    private Facades.RecertificacionFacade ejbFacade;
    private List<Recertificacion> items = null;
    private Recertificacion selected;
    @EJB
    private RecertificacionRNLocal recertificacionRNLocal;

    public RecertificacionController() {
    }

    public Recertificacion getSelected() {
        return selected;
    }

    public void setSelected(Recertificacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RecertificacionFacade getFacade() {
        return ejbFacade;
    }

    public Recertificacion prepareCreate() {
        selected = new Recertificacion();
        initializeEmbeddableKey();
        return selected;
    }

    public boolean esVencida(Date fecha) {
        try {
            return fecha.before(new Date());
        } catch (Exception e) {
            return false;
        }

    }

    public void create() {
        persist(PersistAction.CREATE, "Se ha creado la Recertificación");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Se ha modificado la Recertificación");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Se ha elimando la Recertificación");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Recertificacion> getItems() {
            items = getFacade().findAll();
        return items;
    }

    public List<Recertificacion> getRecertificacionesActivos() {
             items = recertificacionRNLocal.getRecertificacionesActivos();
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public void calcularVencimiento(SelectEvent event) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(selected.getFechaRecertificacion());
        cal.add(Calendar.YEAR, 5);
        selected.setFechaVencimiento(cal.getTime());
        System.out.println("FECHA VENCIMIENTO: " + selected.getFechaVencimiento());
    }

    public List<Recertificacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Recertificacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Recertificacion.class)
    public static class RecertificacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RecertificacionController controller = (RecertificacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "recertificacionController");
            return controller.getFacade().find(getKey(value));
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
            if (object instanceof Recertificacion) {
                Recertificacion o = (Recertificacion) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Recertificacion.class.getName()});
                return null;
            }
        }

    }

}
