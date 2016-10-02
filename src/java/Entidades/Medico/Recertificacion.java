/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Medico;

import Entidades.Pago.Cuenta;
import Entidades.Usuario.Usuario;
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
 * @author hugo
 */
@Entity
@Table(name = "recertificacion")
@NamedQueries({
    @NamedQuery(name = "Recertificacion.cantidadVigente",
            query = "SELECT count(r.especializacion.especialidad),r.especializacion.especialidad.descripcion FROM Recertificacion r where r.fechaVencimiento > :today group by r.especializacion.especialidad ORDER by count(r.especializacion.especialidad) desc")
})
public class Recertificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Medico medico;
    @OneToOne
    private Cuenta cuenta;
    @OneToOne
    private Especializacion especializacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRecertificacion;
    private String nroActa;
    private String nroResolucion;
    private String libro;
    private String folio;
    private String observaciones;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVencimiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Especializacion getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(Especializacion especializacion) {
        this.especializacion = especializacion;
    }

    public Date getFechaRecertificacion() {
        return fechaRecertificacion;
    }

    public void setFechaRecertificacion(Date fechaRecertificacion) {
        this.fechaRecertificacion = fechaRecertificacion;
    }

    public String getNroActa() {
        return nroActa;
    }

    public void setNroActa(String nroActa) {
        this.nroActa = nroActa;
    }

    public String getNroResolucion() {
        return nroResolucion;
    }

    public void setNroResolucion(String nroResolucion) {
        this.nroResolucion = nroResolucion;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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
        if (!(object instanceof Recertificacion)) {
            return false;
        }
        Recertificacion other = (Recertificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return medico.getPersona().getApellido() + ", "
                    + medico.getPersona().getNombre() + " - "
                    + especializacion.getEspecialidad().getNombreEspecialidad();
        } catch (Exception e) {
            return "";
        }
    }

}
