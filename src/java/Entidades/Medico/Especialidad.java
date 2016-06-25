/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Medico;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author nago
 */
@Entity
@Table(name="medico_especialidad")
public class Especialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private TipoEspecialidad tipoEspecialidad;
    private String nombreEspecialidad;
    private String matriculaEspecialidad;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaMatriculacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVencimiento;
    private String libro;
    private String Folio;
    @OneToOne
    private UnidadFormadora unidadFormadora;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRevision;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVencimientoRevision;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoEspecialidad getTipoEspecialidad() {
        return tipoEspecialidad;
    }

    public void setTipoEspecialidad(TipoEspecialidad tipoEspecialidad) {
        this.tipoEspecialidad = tipoEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public String getMatriculaEspecialidad() {
        return matriculaEspecialidad;
    }

    public void setMatriculaEspecialidad(String matriculaEspecialidad) {
        this.matriculaEspecialidad = matriculaEspecialidad;
    }

    public Date getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(Date fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String Folio) {
        this.Folio = Folio;
    }

    public UnidadFormadora getUnidadFormadora() {
        return unidadFormadora;
    }

    public void setUnidadFormadora(UnidadFormadora unidadFormadora) {
        this.unidadFormadora = unidadFormadora;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public Date getFechaVencimientoRevision() {
        return fechaVencimientoRevision;
    }

    public void setFechaVencimientoRevision(Date fechaVencimientoRevision) {
        this.fechaVencimientoRevision = fechaVencimientoRevision;
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
        if (!(object instanceof Especialidad)) {
            return false;
        }
        Especialidad other = (Especialidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.medico.Especialidad[ id=" + id + " ]";
    }
    
}
