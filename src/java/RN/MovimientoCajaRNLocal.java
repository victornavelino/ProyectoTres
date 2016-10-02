/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Caja.MovimientoCaja;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nago
 */
@Local
public interface MovimientoCajaRNLocal {

    public void create(MovimientoCaja movimientoCaja) throws Exception;

    public void edit(MovimientoCaja movimientoCaja) throws Exception;

    public void remove(MovimientoCaja movimientoCaja) throws Exception;

    public List<MovimientoCaja> findAll() throws Exception;

    public List<MovimientoCaja> getMovimientosdesdeFecha(Date fechaInicio);
}
