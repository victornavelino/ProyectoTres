package ManagedBeans;

import Entidades.Medico.Especializacion;
import Entidades.Medico.Medico;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.EspecializacionFacade;
import RN.EspecializacionRNLocal;
import RN.MedicoRNLocal;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("especializacionController")
@SessionScoped
public class EspecializacionController implements Serializable {

    @EJB
    private Facades.EspecializacionFacade ejbFacade;
    private EspecializacionRNLocal especializacionRNLocal;
    @EJB
    private MedicoRNLocal medicoRNLocal;
    private List<Especializacion> items = null;
    private Especializacion selected;
    private Medico medico;
    private String apellido;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public EspecializacionController() {
    }

    public Especializacion getSelected() {
        return selected;
    }

    public void setSelected(Especializacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EspecializacionFacade getFacade() {
        return ejbFacade;
    }

    public Especializacion prepareCreate() {
        selected = new Especializacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EspecializacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EspecializacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EspecializacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Especializacion> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Especializacion getEspecializacion(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Especializacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Especializacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Especializacion.class)
    public static class EspecializacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EspecializacionController controller = (EspecializacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "especializacionController");
            return controller.getEspecializacion(getKey(value));
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
            if (object instanceof Especializacion) {
                Especializacion o = (Especializacion) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Especializacion.class.getName()});
                return null;
            }
        }

    }

    public List<Medico> completeText(String apellido) {

        return medicoRNLocal.buscarXApellido(apellido);
    }

    public List<Medico> completeMedico(String apellido) {
        List<Medico> medicosFiltrados = new ArrayList<>();
        for (Medico med:medicoRNLocal.buscarTodos()) {
            if(med.getPersona().getApellido().startsWith(apellido.toUpperCase())){
                medicosFiltrados.add(med);
            }
        }
        return medicosFiltrados;
    }
}
