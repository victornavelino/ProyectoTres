/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vouilloz
 */
@ManagedBean(name = "usuarioLogerBean")
@SessionScoped
public class UsuarioLogerBean implements Serializable {

    private Usuario usuario;

    /**
     * Creates a new instance of UsuarioLogerBean
     */
    public UsuarioLogerBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void navigationStatus() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        if (usuario == null) {
            response.sendRedirect("login.xhtml?faces-redirect=true");
        }
    }
}
