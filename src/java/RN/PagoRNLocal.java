/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Medico;
import Entidades.Pago.Pago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nago
 */
@Local
public interface PagoRNLocal {
    
    void create(Pago pago);

    void edit(Pago pago);

    void remove(Pago pago);

    Pago find(Object id);

    List<Pago> findAll();
    
    public boolean existePago(Medico medico, int anio, int mes);
}
