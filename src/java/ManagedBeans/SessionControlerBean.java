/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vouilloz
 */
@ManagedBean
@RequestScoped
public class SessionControlerBean {

    private int iTimeSession;

    /**
     * Creates a new instance of SessionControlerBean
     */
    public SessionControlerBean() {
    }

    public int getiTimeSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return session.getMaxInactiveInterval();
    }

    public void setiTimeSession(int iTimeSession) {
        this.iTimeSession = iTimeSession;
    }

    /**
     * Cierra la session del usuario
     *
     * @return url de la pagina
     */
    public String cerrarSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
            return "./login.xhtml?faces-redirect=true";
        }//fin if

        return "";
    }//fin cerrarSession

    /**
     * Crea una session nueva
     *
     * @return url de la pagina
     */
    public String createSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        /*session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
         if(session != null){
         session.invalidate();
         }//fin */

        return "./login.xhtml?faces-redirect=true";
    }//fin abrirSession

}
