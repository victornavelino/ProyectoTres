/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Caja.Caja;
import Facades.CajaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hugo
 */
@Stateless
public class CajaRN implements CajaRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;
    @EJB
    private CajaFacade cajaFacade;

    @Override
    public void create(Caja caja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Caja caja) {
        cajaFacade.edit(caja);
    }

    @Override
    public void remove(Caja caja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Caja find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Caja> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Caja> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Caja> buscarCajaAbierta() {
        Query q = null;
        q = em.createQuery("SELECT c FROM Caja c  WHERE c.fechaFin IS NULL");
        return q.getResultList();

    }

    @Override
    public Caja getCajaAbierta() {
        Caja caja = null;
        Query q = null;
        q = em.createQuery("SELECT c FROM Caja c  WHERE c.fechaFin IS NULL");
        List listaCajasAbiertas = q.getResultList();
        if (listaCajasAbiertas.size() > 0) {
            caja = (Caja) listaCajasAbiertas.get(listaCajasAbiertas.size() - 1);
        }
        return caja;
    }

    @Override
    public boolean hayCajaAbierta() {
        Caja caja = null;
        Query q = null;
        boolean respuesta = false;
        q = em.createQuery("SELECT c FROM Caja c  WHERE c.fechaFin IS NULL");
        List listaCajasAbiertas = q.getResultList();
        if (listaCajasAbiertas.size() > 0) {
            respuesta = true;
        }
        return respuesta;
    }

}
