/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Especializacion;
import Facades.*;
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
    public List<Especializacion> findAll() {
        return facade.findAll();
    }

    @Override
    public List<Especializacion> buscarPorEspecialidad(Especialidad especialidad) {
        Query q = null;
        q = em.createNamedQuery("Especializacion.buscarEspecialidad");
        q.setParameter("especialidad", especialidad);
        return q.getResultList();
    }

}
