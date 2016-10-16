
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Caja;

import Entidades.Medico.Medico;
import Entidades.Pago.Mes;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "caja_movimientocaja_ingreso")
public class Ingreso extends MovimientoCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @OneToOne
    private TipoDeIngreso tipoDeIngreso;

    public TipoDeIngreso getTipoDeIngreso() {
        return tipoDeIngreso;
    }

    public void setTipoDeIngreso(TipoDeIngreso tipoDeIngreso) {
        this.tipoDeIngreso = tipoDeIngreso;
    }

    @Override
    public String toString() {
        return super.getDescripcion();
    }

}
