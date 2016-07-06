/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Especializacion;
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
public class EspecializacionFacade extends AbstractFacade<Especializacion> {
    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecializacionFacade() {
        super(Especializacion.class);
    }
    public List<Especializacion> buscarPorEspecialidad(Especialidad especialidad){
        Query q=em.createQuery("SELECT e FROM Especializacion e Where e.especialidad =:especialidad");
        q.setParameter("especialidad", especialidad);
        return q.getResultList();
        
    }
}
