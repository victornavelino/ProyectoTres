/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Pago;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author hugo
 */
@Entity
@Table(name = "pago_cuota_plan_pago")
public class CuotaPlanPago implements Serializable, Comparable<CuotaPlanPago> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(scale = 2, precision = 12)
    private BigDecimal importe;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPago;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVencimiento;
    private int cuota;
    @ManyToOne
    private PlanPago planPago;
    private String nroRecibo;

    public String getNroRecibo() {
        return nroRecibo;
    }

    public void setNroRecibo(String nroRecibo) {
        this.nroRecibo = nroRecibo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public PlanPago getPlanPago() {
        return planPago;
    }

    public void setPlanPago(PlanPago planPago) {
        this.planPago = planPago;
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
        if (!(object instanceof CuotaPlanPago)) {
            return false;
        }
        CuotaPlanPago other = (CuotaPlanPago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        try {
            return "" + cuota;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public int compareTo(CuotaPlanPago o) {
        return this.getCuota() - o.getCuota(); // use your logic
    }

}
