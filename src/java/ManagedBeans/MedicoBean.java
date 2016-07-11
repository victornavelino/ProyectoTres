/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Medico;
import Recursos.Encrypter;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;

/**
 *
 * @author nago
 */
@Named(value = "medicoBean")
@RequestScoped
public class MedicoBean {

    /**
     * Creates a new instance of MedicoBean
     */
    private Medico medico;
    private Boolean bCamposSoloLectura;
    private String tipoOperacion;
    private CommandButton cbAction;

    public MedicoBean() {
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

        } else if (btnSelect.getId().equals("cbDelete")) {
            this.setTipoOperacion("Borrado");

            this.setbCamposSoloLectura(true);
            this.getCbAction().setValue("Eliminar");

        } else if (btnSelect.getId().equals("cbEdit")) {

            this.setTipoOperacion("Modificación");
            this.getCbAction().setValue("Modificar");

        }

    }//fin setBtnSelect

    public void create() {
        String sMensaje = "";
        FacesMessage fm;
        FacesMessage.Severity severity = null;
        try {

            this.getUsuario().setPassword(Encrypter.encriptar(this.getUsuario().getPassword()));

//validar si ingreso contrasenas y si son iguales
            this.validarContrasena(this.getUsuario().getPassword());
            /*Creo un usuario de forma manual
             usuario.setApellido("Aguirre");
             usuario.setNombre("Franco");
             usuario.setUsuario("vouilloz");
             usuario.setPassword("123");*/
            usuarioFacade.create(this.getUsuario());
            sMensaje = "El Usuario fue guardado";
            severity = FacesMessage.SEVERITY_INFO;
            //agregar a la lista
            // this.getUsuarioLstBean().getLstUsuario().add(this.getUsuario());
            //limíar campos
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
            //valida si el password no es vacion y si el password y la confirmacion so iguales
            this.validarContrasena(Encrypter.encriptar(this.getUsuario().getPassword()));
            this.getUsuario().setPassword(Encrypter.encriptar(this.getUsuario().getPassword()));
            usuarioFacade.edit(this.getUsuario());

            sMensaje = "El dato fue modificado";
            severity = FacesMessage.SEVERITY_INFO;

            //elimino y agrego  a la lista
            int iPos = this.getUsuarioLstBean().getLstUsuario().indexOf(this.getUsuario());

            this.getUsuarioLstBean().getLstUsuario().remove(iPos);
            this.getUsuarioLstBean().getLstUsuario().add(iPos, this.getUsuario());

            this.getCbAction().setValue("Editar");
            this.getCbAction().setDisabled(true);

        } catch (Exception ex) {

            if (ex.getMessage().trim().toLowerCase().equals("transaction aborted")) {
                sMensaje = "Error: No se puede eliminar";
            } else {
                sMensaje = "Error: " + ex.getMessage();
            }

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

            usuarioFacade.remove(this.getUsuario());

            sMensaje = "El dato fue eliminado";
            severity = FacesMessage.SEVERITY_INFO;

            //remover de la lista
            this.getUsuarioLstBean().getLstUsuario().remove(this.getUsuario());

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
}
