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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    private Date fechaVencimiento;
    @ManyToOne
    private Medico medico;
    @OneToOne
    private TipoPlanPago tipoPlanPago;
    @OneToMany(mappedBy = "planPago", cascade = CascadeType.ALL)
    private List<CuotaPlanPago> cuotas;

    public List<CuotaPlanPago> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<CuotaPlanPago> cuotas) {
        this.cuotas = cuotas;
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public TipoPlanPago getTipoPlanPago() {
        return tipoPlanPago;
    }

    public void setTipoPlanPago(TipoPlanPago tipoPlanPago) {
        this.tipoPlanPago = tipoPlanPago;
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
