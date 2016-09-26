/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Especializacion;
import Entidades.Medico.Medico;
import Facades.*;
import java.util.ArrayList;
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
public class EspecializacionRN implements EspecializacionRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @EJB
    private EspecializacionFacade facade;

    @Override
    public List<Especializacion> buscarPorEspecialidad(Especialidad especialidad) {
        Query q = null;
        q = em.createNamedQuery("Especializacion.buscarEspecialidad");
        q.setParameter("especialidad", especialidad);
        return q.getResultList();
    }

    @Override
    public List<Especializacion> buscarPorProfesional(String medico) {
        Query q = null;
        q = em.createNamedQuery("Especializacion.buscarPorProfesional");
        q.setParameter("medico", medico);
        return q.getResultList();
    }

    @Override
    public List<Especializacion> buscarPorMedico(Medico medico) {
        Query q = null;
        q = em.createNamedQuery("Especializacion.buscarPorMedico");
        q.setParameter("medico", medico);
        return q.getResultList();
    }

   @Override
    public List<Object[]> cantidadEspecializacion() {
              Query q = null;
        q = em.createNamedQuery("Especializacion.cantidadEspecializacion");
        try {
            return q.getResultList();
        } catch (Exception e) {
            return new ArrayList<Object[]>();
        }
    }
    
     @Override
    public List<Especializacion> findAll() {
        Query q = null;
        q = em.createNamedQuery("Especializacion.findAll");
        return q.getResultList();
    }
    

}
