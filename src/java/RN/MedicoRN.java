/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Especialidad;
import Facades.*;
import Entidades.Medico.Medico;
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

}
