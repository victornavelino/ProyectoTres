/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;


import Entidades.Usuario.Usuario;
import Facades.UsuarioFacade;
import Recursos.Encrypter;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.commandbutton.CommandButton;

/**
 *
 * @author AFerSor
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @Inject
    private UsuarioLstBean usuarioLstBean;
    private Usuario usuario;
    private String sConfirmarContrasena;
    private Boolean bCamposSoloLectura;
    private int iActionBtnSelect;
    private CommandButton cbAction;

    public UsuarioBean() {
        usuario = new Usuario();
    }

    /*   public UsuarioLstBean getUsuarioLstBean() {
     return usuarioLstBean;
     }*/

    /*   public void setUsuarioLstBean(UsuarioLstBean usuarioLstBean) {
     this.usuarioLstBean = usuarioLstBean;
     }*/
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getbCamposSoloLectura() {
        return bCamposSoloLectura;
    }

    public void setbCamposSoloLectura(Boolean bCamposSoloLectura) {
        this.bCamposSoloLectura = bCamposSoloLectura;
    }

    public int getiActionBtnSelect() {
        return iActionBtnSelect;
    }

    public void setiActionBtnSelect(int iActionBtnSelect) {
        this.iActionBtnSelect = iActionBtnSelect;
    }

    public CommandButton getCbAction() {
        return cbAction;
    }

    public void setCbAction(CommandButton cbAction) {
        this.cbAction = cbAction;
    }

    public String getsConfirmarContrasena() {
        return sConfirmarContrasena;
    }

    public void setsConfirmarContrasena(String sConfirmarContrasena) {
        this.sConfirmarContrasena = sConfirmarContrasena;
    }

    public UsuarioLstBean getUsuarioLstBean() {
        return usuarioLstBean;
    }

    public void setUsuarioLstBean(UsuarioLstBean usuarioLstBean) {
        this.usuarioLstBean = usuarioLstBean;
    }

    public void actionBtn() {

        switch (this.getiActionBtnSelect()) {
            case 0:
                System.out.println("ususario"+usuario);
                System.out.println("ENTRO CREATE USUARIO");
                create();
                break;
            case 1:
                this.edit();
                break;
            case 2:
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
            this.setiActionBtnSelect(0);
            this.getCbAction().setValue("Guardar");

        } else if (btnSelect.getId().equals("cbDelete")) {
            this.setiActionBtnSelect(2);

            this.setbCamposSoloLectura(true);
            this.getCbAction().setValue("Eliminar");

        } else if (btnSelect.getId().equals("cbEdit")) {

            this.setiActionBtnSelect(1);
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

    public void limpiar() {
        this.setUsuario(new Usuario());
        this.setbCamposSoloLectura(false);
    }//fin limpiar

    private void validarContrasena(String contrasena) throws Exception {

        if (contrasena.isEmpty()) {
            throw new Exception("No ingreso la contraseña");
        }//fin if

        System.out.println("metodo validar password: " + contrasena + " " + this.getsConfirmarContrasena());

        if (!contrasena.equals(Encrypter.encriptar(this.getsConfirmarContrasena()))) {
            throw new Exception("La contraseña y la confirmacion no son iguales");
        }
    }//fin validarContrasena
}
