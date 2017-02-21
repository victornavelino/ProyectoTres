/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Recertificacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author franco
 */
@Stateless
public class RecertificacionRN implements RecertificacionRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @Override
    public List<Object[]> cantidadVigente() {
        Query q = null;
        q = em.createNamedQuery("Recertificacion.cantidadVigente");
        q.setParameter("today", new Date());
        try {
            return q.getResultList();
        } catch (Exception e) {
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Recertificacion> getRecertificacionesActivos() {
        Query q = null;
        q = em.createNamedQuery("Recertificacion.getRecertificacionesActivos");
        try {
            return q.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
