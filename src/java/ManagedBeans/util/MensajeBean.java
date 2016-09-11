/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans.util;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author AFerSor
 */
@Named(value = "mensajeBean")
@SessionScoped
public class MensajeBean implements Serializable {

    private String mensaje;
    private String pagCerrar;
    private char tipoIcono;

    public MensajeBean() {
    }

    public char getTipoIcono() {
        return tipoIcono;
    }

    public void setTipoIcono(char tipoIcono) {
        this.tipoIcono = tipoIcono;
    }

    public String getPagCerrar() {
        return pagCerrar;
    }

    public void setPagCerrar(String pagCerrar) {
        this.pagCerrar = pagCerrar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {

        this.mensaje = mensaje;
    }

    public String icono() {
        String nombreIcono = "";
        switch (this.getTipoIcono()) {

            case 'I': //informacion

            case 'A': //advertencia

            case 'E': //error
                nombreIcono = "../recursos/imagenes/error.png";
                break;

        }//fin switch
        return nombreIcono;
    }//icono

    public void cerrar() {
        RequestContext.getCurrentInstance().execute("PF('dlgMensaje').hide();");

        if (this.getPagCerrar() != null && !this.getPagCerrar().isEmpty()) {

            RequestContext.getCurrentInstance().execute(this.getPagCerrar());
        }//fin if

    }//fin cerrar

}
