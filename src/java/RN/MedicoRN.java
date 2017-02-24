/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Especialidad;
import Facades.*;
import Entidades.Medico.Medico;
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
public class MedicoRN implements MedicoRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @EJB
    private MedicoFacade facade;

    @Override
    public List<Medico> buscarMedicosEspecialidad(Especialidad especialidad) {
        Query q = null;
        q = em.createNamedQuery("Medico.buscarMedicosEspecialidad");
        q.setParameter("especialidad", especialidad);
        return q.getResultList();
    }

    @Override
    public List<Medico> buscarTodos() {
        return facade.findAll();
    }

    @Override
    public void edit(Medico medico) {
        facade.edit(medico);
    }

    @Override
    public List<Medico> buscarXApellido(String apellido) {
        Query q = null;
        q = em.createNamedQuery("Medico.buscarXApellido");
        q.setParameter("apellido", "%" + apellido + "%");
        if (q.getResultList() != null) {
            return q.getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Medico> buscarXMatricula(String matriculaProfesional) {
        Query q = null;
        q = em.createNamedQuery("Medico.buscarXMatricula");
        q.setParameter("matriculaProfesional", matriculaProfesional);
        if (q.getResultList() != null) {
            return q.getResultList();
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Medico> buscarMedicosDeudores(Integer mes, Integer anio) {
        Query q = null;
        q = em.createNamedQuery("Medico.buscarMedicosDeudores");
        q.setParameter("mes", mes);
        q.setParameter("anio", anio);
        if (q.getResultList() != null) {
            return q.getResultList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Medico> buscarMedicosDeudores(Integer mes, Integer mes2, Integer anio, Integer anio2) {
        Query q = null;
        q = em.createNamedQuery("Medico.buscarMedicosDeudoresEntre");
        q.setParameter("mes", mes);
        q.setParameter("anio", anio);
        q.setParameter("mes2", mes2);
        q.setParameter("anio2", anio2);
        if (q.getResultList() != null) {
            return q.getResultList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public int buscarUltimaMatricula() {
        Query q = null;
        q = em.createNamedQuery("Medico.buscarUltimaMatricula");
        try {
            return (Integer) q.getSingleResult();
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public List<Medico> buscarTodosActivos() {
        Query q = null;
        q = em.createNamedQuery("Medico.buscarTodosActivos");
        if (q.getResultList() != null) {
            return q.getResultList();
        } else {
            return new ArrayList<>();
        }

    }

}
