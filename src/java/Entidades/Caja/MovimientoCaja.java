/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Caja;

import Entidades.Base.Base;
import Entidades.Medico.Medico;
import Entidades.Usuario.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author root
 */
@Entity
@Table(name = "caja_movimientocaja")
@NamedQueries({
    @NamedQuery(name = "MovimientoCaja.getAbiertos",
            query = "SELECT e FROM MovimientoCaja e WHERE  e.cerrado = False "
            + " ORDER BY e.id DESC")
})
public class MovimientoCaja extends Base implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaOperacion;
    @Basic(optional = false)
    @Column(scale = 3, precision = 12)
    private BigDecimal importe;
    private String descripcion;
    @OneToOne
    private Usuario usuario;
    private boolean cerrado;

    private int nroComprobante;

    @ManyToOne
    private Medico medico;

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
   
    public int getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(int nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public String getClase() {
        return this.getClass().getSimpleName();
    }

    public String getTipoMovimiento() {
        try {
            switch (getClase()) {
                case "Ingreso":
                    return ((Ingreso) this).getTipo().getDescripcion();
                case "Egreso":
                    return ((Egreso) this).getTipo().getDescripcion();
            }
        } catch (Exception e) {
            return "";
        }
        return "";
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
        if (!(object instanceof MovimientoCaja)) {
            return false;
        }
        MovimientoCaja other = (MovimientoCaja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
