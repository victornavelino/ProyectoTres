/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Facades.*;
import Entidades.Medico.Especialidad;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author franco
 */
@Stateless
public class EspecialidadRN implements EspecialidadRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @EJB
    private EspecialidadFacade facade;

    @Override
    public List<Especialidad> findAll() {
        return facade.findAll();
    }

    @Override
    public List<Especialidad> buscarEspecialidad(String especialidad) {
        Query q = null;
        q = em.createNamedQuery("Especialidad.buscar");
        q.setParameter("especialidad", "%" + especialidad + "%");
        return q.getResultList();
    }

}
