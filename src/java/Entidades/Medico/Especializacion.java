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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author nago
 */
@Entity
@Table(name = "medico_especializacion")
@NamedQueries({
    @NamedQuery(name = "Especializacion.buscarEspecialidad",
            query = "SELECT e FROM Especializacion e WHERE e.especialidad=:especialidad "
            + " ORDER BY e.id DESC"),
    @NamedQuery(name = "Especializacion.buscarPorProfesional",
            query = "SELECT e FROM Especializacion e WHERE e.medico.persona.apellido=:medico "
            + " ORDER BY e.id DESC")

})
public class Especializacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Especialidad especialidad;
    private String matriculaEspecialidad;
    @OneToOne
    private Medico medico;
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
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
        if (!(object instanceof Especializacion)) {
            return false;
        }
        Especializacion other = (Especializacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return especialidad + " , " + matriculaEspecialidad;
    }

}
