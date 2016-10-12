/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Pago.CuotaPlanPago;
import Entidades.Pago.PlanPago;
import Facades.PlanPagoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hugo
 */
@Stateless
public class PlanPagoRN implements PlanPagoRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;
    @EJB
    private PlanPagoFacade ejbFacade;

    @Override
    public void create(PlanPago planPago) {
        //Agregar validaciones
        ejbFacade.create(planPago);
        em.getEntityManagerFactory().getCache().evict(CuotaPlanPago.class);

    }

    @Override
    public void edit(PlanPago planPago) {
        ejbFacade.edit(planPago);
        em.getEntityManagerFactory().getCache().evict(CuotaPlanPago.class);

    }

    @Override
    public void remove(PlanPago planPago) {
        ejbFacade.remove(planPago);
        em.getEntityManagerFactory().getCache().evict(CuotaPlanPago.class);

    }

    @Override
    public List<PlanPago> findAll() {
        return ejbFacade.findAll();
    }

    @Override
    public PlanPago find(Long id) {
        return ejbFacade.find(id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
