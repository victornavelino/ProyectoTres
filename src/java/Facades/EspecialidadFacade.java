/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entidades.Medico.Especialidad;
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
public class EspecialidadFacade extends AbstractFacade<Especialidad> {
    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecialidadFacade() {
        super(Especialidad.class);
    }
    public List<Especialidad> buscarEspecialidad(String especialidad){
        Query q =em.createQuery("SELECT e FROM Especialidad e WHERE e.nombreEspecialidad LIKE :especialidad");
        q.setParameter("especialidad","%"+especialidad+"%");
        return q.getResultList();
    }
}
