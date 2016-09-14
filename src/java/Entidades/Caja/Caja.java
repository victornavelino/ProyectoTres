/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Caja;

import Entidades.Usuario.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author hugo
 */
@Entity
@Table(name = "caja")
public class Caja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        @OneToOne
    private Usuario usuario;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(scale = 3, precision = 12)
    private BigDecimal cajaInicial;
    @Column(scale = 3, precision = 12)
    private BigDecimal cajaFinal;
    @OneToMany
    private List<MovimientoCaja> movimientosCaja;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getCajaInicial() {
        return cajaInicial;
    }

    public void setCajaInicial(BigDecimal cajaInicial) {
        this.cajaInicial = cajaInicial;
    }

    public BigDecimal getCajaFinal() {
        return cajaFinal;
    }

    public void setCajaFinal(BigDecimal cajaFinal) {
        this.cajaFinal = cajaFinal;
    }

    public List<MovimientoCaja> getMovimientosCaja() {
        return movimientosCaja;
    }

    public void setMovimientosCaja(List<MovimientoCaja> movimientosCaja) {
        this.movimientosCaja = movimientosCaja;
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
        if (!(object instanceof Caja)) {
            return false;
        }
        Caja other = (Caja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Caja.Caja[ id=" + id + " ]";
    }
    
}
