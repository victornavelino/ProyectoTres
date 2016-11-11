/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Medico;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hugo
 */
@Entity
@Table(name = "tipomedico")
public class TipoMedico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private boolean factura;
    private boolean generalistado;
    private String observaciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isFactura() {
        return factura;
    }

    public void setFactura(boolean factura) {
        this.factura = factura;
    }

    public boolean isGeneralistado() {
        return generalistado;
    }

    public void setGeneralistado(boolean generalistado) {
        this.generalistado = generalistado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMedico)) {
            return false;
        }
        TipoMedico other = (TipoMedico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public TipoMedico() {
    }

    public TipoMedico(Long id, String descripcion, boolean factura, boolean generalistado, String observaciones) {
        this.id = id;
        this.descripcion = descripcion.toUpperCase();
        this.factura = factura;
        this.generalistado = generalistado;
        this.observaciones = observaciones.toUpperCase();
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
