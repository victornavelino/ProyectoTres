/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades.Persona;

import Entidades.Localidad.Localidad;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Embeddable
public class Domicilio implements Serializable {

    private String calle;
    private String numero;
    private String piso;
    private String dpto;
    private String entreCalles;
    private String referencia;
    private Localidad localidad;
    private String barrio;
    private String codigoPostal;

 

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getEntreCalles() {
        return entreCalles;
    }

    public void setEntreCalles(String entreCalles) {
        this.entreCalles = entreCalles;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }


    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    @Override
    public String toString() {
        return calle + " "+ numero + " "+localidad;
    }

}
