/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Facades.UsuarioFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author vouilloz
 */
@ManagedBean
@RequestScoped
public class NavegarBean {

   @ManagedProperty(value = "#{usuarioLstBean}")
   private UsuarioLstBean usuarioLstBean;
//    @EJB
//    private UsuarioFacade usuarioFacade;

    public UsuarioLstBean getUsuarioLstBean() {
        return usuarioLstBean;
    }

    public void setUsuarioLstBean(UsuarioLstBean usuarioLstBean) {
        this.usuarioLstBean = usuarioLstBean;
    }

    /**
     * Creates a new instance of NavegarBean
     */
    public NavegarBean() {
    usuarioLstBean = new UsuarioLstBean();
    }

    public String frmUsuario() {
        return "usuario.xhtml?faces-redirect=true";
    }

    public String frmPagosMedicos() {
        return "pagoMedico.xhtml?faces-redirect=true";
    }

}
