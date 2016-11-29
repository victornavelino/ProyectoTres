/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Medico;
import Entidades.Pago.Pago;
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
public class PagoRN implements PagoRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @Override
    public void create(Pago pago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Pago pago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Pago pago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pago find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pago> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean existePago(Medico medico, int anio, int mes) {
        Pago pago = null;
        Query q = null;
        boolean respuesta = false;
        q = em.createQuery("SELECT p FROM Pago p WHERE p.medico=:medico AND p.anio=:anio AND p.mes=:mes");
        q.setParameter("medico", medico);
        q.setParameter("anio", anio);
        q.setParameter("mes", mes);
        List listaPagos = q.getResultList();
        if (listaPagos.size() > 0) {
            respuesta = true;
        }
        return respuesta; //To change body of generated methods, choose Tools | Templates.
    }

}
