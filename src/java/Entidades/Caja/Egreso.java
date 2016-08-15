/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Caja;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity
@Table(name = "caja_movimientocaja_egreso")
public class Egreso extends MovimientoCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @OneToOne
    private TipoDeEgreso tipoDeEgreso;
    @Basic(optional = false)
    @Column(scale = 3, precision = 12)
    private BigDecimal importe;
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoDeEgreso getTipoDeEgreso() {
        return tipoDeEgreso;
    }

    public void setTipoDeEgreso(TipoDeEgreso tipoDeEgreso) {
        this.tipoDeEgreso = tipoDeEgreso;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
