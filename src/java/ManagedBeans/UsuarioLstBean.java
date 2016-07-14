/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Usuario.Usuario;
import Facades.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author vouilloz
 */
@Named(value = "usuarioLstBean")
@SessionScoped
public class UsuarioLstBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;//hacemos la referencia para poder utilizar el metodo findall
    private List<Usuario> lstUsuario; //Cargamos la lista de Usuarios retornada po el metodo findAll del usuarioRNLocal
    private List<SelectItem> lstSIUsuario;//Aca se guarda el item seleccionado de la lista

    /**
     * Creates a new instance of UsuarioLstBean
     */
    public UsuarioLstBean() {
    }

    @PostConstruct
    private void init() {
        this.cargarUsuario();
    }


    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public List<SelectItem> getLstSIUsuario() {
        return lstSIUsuario;
    }

    public void setLstSIUsuario(List<SelectItem> lstSIUsuario) {
        this.lstSIUsuario = lstSIUsuario;
    }

    public void cargarUsuario() {
        System.out.println("entroo cargar usuariso");
        try {
            this.setLstUsuario(usuarioFacade.findAll());
            System.out.println("usuarios"+ usuarioFacade.findAll());
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin cargarUsuario

}
