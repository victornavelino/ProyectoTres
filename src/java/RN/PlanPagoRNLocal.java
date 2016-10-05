/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;
import Entidades.Pago.PlanPago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hugo
 */
@Local
public interface PlanPagoRNLocal {

    public void create(PlanPago planPago);

    public void edit(PlanPago planPago);

    public void remove(PlanPago planPago);

    public List<PlanPago> findAll();
    
    public PlanPago find(Long id);

}
