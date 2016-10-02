/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Pago;

import Entidades.Medico.Medico;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author hugo
 */
@Entity
@Table(name = "pago_plan_pago")
public class PlanPago implements Serializable {

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
    @OneToOne
    private Medico medico;
    @OneToOne
    private TipoPlanPago tipoPlanPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPlanPago getTipoPlanPago() {
        return tipoPlanPago;
    }

    public void setTipoPlanPago(TipoPlanPago tipoPlanPago) {
        this.tipoPlanPago = tipoPlanPago;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
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

    public void setCuota(int cuotas) {
        this.cuota = cuotas;
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
        if (!(object instanceof PlanPago)) {
            return false;
        }
        PlanPago other = (PlanPago) object;
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
                    + tipoPlanPago.getDescripcion();
        } catch (Exception e) {
            return "";
        }
    }

}
