/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Caja.MovimientoCaja;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nago
 */
@Stateless
public class MovimientoCajaRN implements MovimientoCajaRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @Override
    public void create(MovimientoCaja movimientoCaja) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(MovimientoCaja movimientoCaja) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(MovimientoCaja movimientoCaja) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MovimientoCaja> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<MovimientoCaja> getMovimientosdesdeFecha(Date fechaInicio) {
        Query q = em.createQuery("SELECT c FROM MovimientoCaja c WHERE c.fecha >= :fechaInicio");
        q.setParameter("fechaInicio", fechaInicio);
        return q.getResultList();
    }
    
    @Override
    public List<MovimientoCaja> getAbiertos() {
        Query q = em.createNamedQuery("MovimientoCaja.getAbiertos");
        try {
            return q.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
