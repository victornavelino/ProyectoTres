package ManagedBeans;

import Entidades.Medico.Medico;
import Entidades.Persona.CorreoElectronico;
import Entidades.Persona.DocumentoIdentidad;
import Entidades.Persona.Domicilio;
import Entidades.Persona.Persona;
import Entidades.Persona.Telefono;
import Entidades.Persona.TipoDocumento;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.MedicoFacade;
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
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@Named("medicoController")
@SessionScoped
public class MedicoController implements Serializable {

    @EJB
    private Facades.MedicoFacade ejbFacade;
    @EJB
    private Facades.TelefonoFacade telefonoFacade;
    @EJB
    private Facades.CorreoElectronicoFacade correoElectronicoFacade;
    private List<Medico> items = null;
    private Medico selected;
    @Inject
    private ListadoTelefonosBean listadoTelefonosBean;
    @Inject
    private ListadoEmailBean listadoEmailBean;
    @Inject
    private DomicilioBean domicilioBean;

    public MedicoController() {
    }

    public ListadoEmailBean getListadoEmailBean() {
        return listadoEmailBean;
    }

    public void setListadoEmailBean(ListadoEmailBean listadoEmailBean) {
        this.listadoEmailBean = listadoEmailBean;
    }

    public ListadoTelefonosBean getListadoTelefonosBean() {
        return listadoTelefonosBean;
    }

    public void setListadoTelefonosBean(ListadoTelefonosBean listadoTelefonosBean) {
        this.listadoTelefonosBean = listadoTelefonosBean;
    }

    public Medico getSelected() {
        return selected;
    }

    public void setSelected(Medico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MedicoFacade getFacade() {
        return ejbFacade;
    }

    public Medico prepareCreate() {
        selected = new Medico();
        selected.setPersona(new Persona());
        selected.getPersona().setDocumentoIdentidad(new DocumentoIdentidad());
        selected.getPersona().getDocumentoIdentidad().setTipoDocumento(new TipoDocumento());
        listadoTelefonosBean.setLstTelefonos(new ArrayList<Telefono>());
        listadoEmailBean.setLstCorreoElectronico(new ArrayList<CorreoElectronico>());
        domicilioBean.setDomicilio(new Domicilio());
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareUpdate() {
        listadoTelefonosBean.setLstTelefonos(selected.getPersona().getTelefonos());
        listadoEmailBean.setLstCorreoElectronico(selected.getPersona().getCorreosElectronicos());
    }

    public void create() {
        selected.getPersona().setDomicilio(domicilioBean.getDomicilio());
        selected.getPersona().setTelefonos(listadoTelefonosBean.getLstTelefonos());
        selected.getPersona().setCorreosElectronicos(listadoEmailBean.getLstCorreoElectronico());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MedicoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MedicoUpdated"));
    }

    public void destroy() {
        
        selected.getPersona().setTelefonos(null);
        selected.getPersona().setCorreosElectronicos(null);
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MedicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Medico> getItems() {
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

    public Medico getMedico(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Medico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Medico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Medico.class)
    public static class MedicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MedicoController controller = (MedicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "medicoController");
            return controller.getMedico(getKey(value));
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
            if (object instanceof Medico) {
                Medico o = (Medico) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Medico.class.getName()});
                return null;
            }
        }

    }

}
