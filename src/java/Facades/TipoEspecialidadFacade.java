/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entidades.Medico.TipoEspecialidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author franco
 */
@Stateless
public class TipoEspecialidadFacade extends AbstractFacade<TipoEspecialidad> {
    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEspecialidadFacade() {
        super(TipoEspecialidad.class);
    }
    
}
