/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Medico;
import Entidades.Persona.DocumentoIdentidad;
import Entidades.Persona.Domicilio;
import Entidades.Persona.Persona;
import Facades.MedicoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.component.commandbutton.CommandButton;

/**
 *
 * @author nago
 */
@Named(value = "medicoBean")
@RequestScoped
public class MedicoBean implements Serializable{

    /**
     * Creates a new instance of MedicoBean
     */
    private Medico medico;
    private Boolean bCamposSoloLectura;
    private String tipoOperacion;
    private CommandButton cbAction;
    @EJB
    private MedicoFacade medicoFacade;
    @Inject
    private ListaMedicoBean listaMedicoBean;
    @Inject
    private DomicilioBean domicilioBean;
    @Inject
    private PersonaBean personaBean;

    public MedicoBean() {

    }

    public PersonaBean getPersonaBean() {
        return personaBean;
    }

    public void setPersonaBean(PersonaBean personaBean) {
        this.personaBean = personaBean;
    }

    public DomicilioBean getDomicilioBean() {
        return domicilioBean;
    }

    public void setDomicilioBean(DomicilioBean domicilioBean) {
        this.domicilioBean = domicilioBean;
    }

    public MedicoFacade getMedicoFacade() {
        return medicoFacade;
    }

    public void setMedicoFacade(MedicoFacade medicoFacade) {
        this.medicoFacade = medicoFacade;
    }

    public ListaMedicoBean getListaMedicoBean() {
        return listaMedicoBean;
    }

    public void setListaMedicoBean(ListaMedicoBean listaMedicoBean) {
        this.listaMedicoBean = listaMedicoBean;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Boolean getbCamposSoloLectura() {
        return bCamposSoloLectura;
    }

    public void setbCamposSoloLectura(Boolean bCamposSoloLectura) {
        this.bCamposSoloLectura = bCamposSoloLectura;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
    }

    @PostConstruct
    private void inicializar() {
        medico = new Medico();
        medico.setPersona(new Persona());
        medico.getPersona().setDomicilio(new Domicilio());
        medico.getPersona().setDocumentoIdentidad(new DocumentoIdentidad());
    }

    public void actionBtn() {

        switch (this.getTipoOperacion()) {
            case "Alta":
                create();
                break;
            case "Modificación":
                this.edit();
                break;
            case "Borrado":
                //borra el campo
                this.delete();
                //this.delete(Boolean.TRUE);
                break;
        }//fin switch

    }//fin actionBtn

    public void setBtnSelect(ActionEvent e) {
        CommandButton btnSelect = (CommandButton) e.getSource();

        System.out.println("boton select: " + btnSelect.getId());

        //activo el boton
        this.getCbAction().setDisabled(false);

        if (btnSelect.getId().equals("cbCreate")) {
            this.setTipoOperacion("Alta");
            this.getCbAction().setValue("Guardar");
//            this.setMedico(new Medico());
//            this.getMedico().setPersona(new Persona());
//            this.getMedico().getPersona().setDomicilio(new Domicilio());

        } else if (btnSelect.getId().equals("cbDelete")) {
            this.setTipoOperacion("Borrado");

            this.setbCamposSoloLectura(true);
            this.getCbAction().setValue("Eliminar");

        } else if (btnSelect.getId().equals("cbEdit")) {
//            System.out.println("PERSONAAA : "+this.getMedico().getPersona());
//            System.out.println("MEDICOID : "+this.getMedico().getId());
            this.getPersonaBean().setPersona(this.getMedico().getPersona());
            this.setTipoOperacion("Modificación");
            this.getCbAction().setValue("Modificar");

        }

    }//fin setBtnSelect

    public void create() {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        System.out.println("ENTRO CREAR ALTA MEDICO");
        try {
            this.getMedico().setPersona(personaBean.getPersona());
            this.getMedico().getPersona().setDomicilio(domicilioBean.getDomicilio());
            medicoFacade.create(this.getMedico());
            sMensaje = "Insertado correctamente";
            severity = FacesMessage.SEVERITY_INFO;
            this.limpiar();

        } catch (Exception ex) {

            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin create

    public void edit() {

        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {
            //this.getMedico().setPersona(personaBean.getPersona());
            this.getMedico().getPersona().setDomicilio(domicilioBean.getDomicilio());
            medicoFacade.edit(this.getMedico());

            sMensaje = "El dato fue modificado";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego  a la lista
//            int iPos = this.getListaMedicoBean().getMedicos().indexOf(this.getMedico());
//
//            this.getListaMedicoBean().getMedicos().remove(iPos);
//            this.getListaMedicoBean().getMedicos().add(iPos, this.getMedico());
            this.getListaMedicoBean().cargarMedicos();

            this.getCbAction().setValue("Editar");
            this.getCbAction().setDisabled(true);

        } catch (Exception ex) {
            sMensaje = "Error: No se puede modificar";

            severity = FacesMessage.SEVERITY_ERROR;

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin edit

    public void delete() {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

            medicoFacade.remove(this.getMedico());

            sMensaje = "El dato fue eliminado";
            severity = FacesMessage.SEVERITY_INFO;

            //remover de la lista
            this.getListaMedicoBean().getMedicos().remove(this.getMedico());

            this.getCbAction().setValue("Eliminar");
            this.getCbAction().setDisabled(true);

        } catch (Exception ex) {

            severity = FacesMessage.SEVERITY_ERROR;
            sMensaje = "Error: " + ex.getMessage();

        } finally {
            fm = new FacesMessage(severity, sMensaje, null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin delete

    public void limpiar() {
        this.setMedico(new Medico());
        this.personaBean.setPersona(new Persona());
        this.setbCamposSoloLectura(false);
    }//fin limpiar

}
