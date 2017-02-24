package ManagedBeans;

import Entidades.Base.Archivo;
import Entidades.Caja.MovimientoCaja;
import Entidades.Localidad.Localidad;
import Entidades.Medico.Medico;
import Entidades.Persona.CorreoElectronico;
import Entidades.Persona.DocumentoIdentidad;
import Entidades.Persona.Domicilio;
import Entidades.Persona.Persona;
import Entidades.Persona.Telefono;
import Entidades.Persona.TipoDocumento;
import Facades.CorreoElectronicoFacade;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import Facades.MedicoFacade;
import Facades.TelefonoFacade;
import RN.MedicoRNLocal;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    private List<Medico> medicosActivos = null;
    private Medico selected;
    @Inject
    private ListadoTelefonosBean listadoTelefonosBean;
    @Inject
    private ListadoEmailBean listadoEmailBean;
    @Inject
    private DomicilioBean domicilioBean;
    @EJB
    private MedicoRNLocal medicoRNLocal;

    public MedicoController() {
    }

    public List<Medico> getMedicosActivos() {
        return medicosActivos;
    }

    public void setMedicosActivos(List<Medico> medicosActivos) {
        this.medicosActivos = medicosActivos;
    }

    public MedicoFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(MedicoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public TelefonoFacade getTelefonoFacade() {
        return telefonoFacade;
    }

    public void setTelefonoFacade(TelefonoFacade telefonoFacade) {
        this.telefonoFacade = telefonoFacade;
    }

    public CorreoElectronicoFacade getCorreoElectronicoFacade() {
        return correoElectronicoFacade;
    }

    public void setCorreoElectronicoFacade(CorreoElectronicoFacade correoElectronicoFacade) {
        this.correoElectronicoFacade = correoElectronicoFacade;
    }

    public DomicilioBean getDomicilioBean() {
        return domicilioBean;
    }

    public void setDomicilioBean(DomicilioBean domicilioBean) {
        this.domicilioBean = domicilioBean;
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

    public Date getDate() {
        return new Date();
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
        selected.setArchivo(new Archivo());
        listadoTelefonosBean.setLstTelefonos(new ArrayList<Telefono>());
        listadoEmailBean.setLstCorreoElectronico(new ArrayList<CorreoElectronico>());
        domicilioBean.setDomicilio(new Domicilio());
        domicilioBean.setResidencia(new Localidad());
        calcularNroMatricula();
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareUpdate() {
        listadoTelefonosBean.setLstTelefonos(selected.getPersona().getTelefonos());
        listadoEmailBean.setLstCorreoElectronico(selected.getPersona().getCorreosElectronicos());
        domicilioBean.setDomicilio(selected.getPersona().getDomicilio());
        domicilioBean.setLocalidad(selected.getPersona().getLugarNacimiento());
        if (selected.getArchivo() == null) {
            selected.setArchivo(new Archivo());
        }
    }

    public void create() {
        selected.getPersona().setNombre(selected.getPersona().getNombre().toUpperCase());
        selected.getPersona().setApellido(selected.getPersona().getApellido().toUpperCase());
        selected.setTitulo(selected.getTitulo().toUpperCase());
        selected.getPersona().setDomicilio(domicilioBean.getDomicilio());
        selected.getPersona().setLugarNacimiento(domicilioBean.getLocalidad());
        selected.getPersona().setTelefonos(listadoTelefonosBean.getLstTelefonos());
        selected.getPersona().setCorreosElectronicos(listadoEmailBean.getLstCorreoElectronico());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MedicoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.getPersona().setNombre(selected.getPersona().getNombre().toUpperCase());
        selected.getPersona().setApellido(selected.getPersona().getApellido().toUpperCase());
        selected.setTitulo(selected.getTitulo().toUpperCase());
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

    public List<Medico> getActivos() {
        return medicoRNLocal.buscarTodosActivos();
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

    private void calcularNroMatricula() {
        selected.setMatriculaProfesional(medicoRNLocal.buscarUltimaMatricula() + 1);
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

    public void handleFileUpload(FileUploadEvent event) {
        try {

            if (selected.getArchivo() == null) {
                selected.setArchivo(new Archivo());
            }
            selected.getArchivo().setNombre(event.getFile().getFileName());
            selected.getArchivo().setContenidoArchivo(
                    this.getFileContents(event.getFile().getInputstream()));

        } catch (IOException ex) {
            //Logger.getLogger(CategoriaEditDlgBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private byte[] getFileContents(InputStream in) {
        byte[] bytes = null;
        try {
            // write the inputStream to a FileOutputStream            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int read = 0;
            bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
            bytes = bos.toByteArray();
            in.close();
            in = null;
            bos.flush();
            bos.close();
            bos = null;
            //logger.debug("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bytes;
    }//fin getFileContents

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);

        return String.valueOf(i);
    }

    public void oncapture(CaptureEvent captureEvent) {

        byte[] data = captureEvent.getData();

        if (selected.getArchivo() == null) {
            selected.setArchivo(new Archivo());
        }
        selected.getArchivo().setNombre(getRandomImageName());

        selected.getArchivo().setContenidoArchivo(data);

    }

    public StreamedContent getImage() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
                // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
                return new DefaultStreamedContent();
            } else {
                // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
                return new DefaultStreamedContent(new ByteArrayInputStream(selected.getArchivo().getContenidoArchivo()));
            }
        } catch (Exception e) {
            return new DefaultStreamedContent();
        }
    }
}
