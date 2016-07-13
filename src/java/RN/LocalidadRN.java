/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Localidad.Departamento;
import Entidades.Localidad.Localidad;
import Facades.LocalidadFacade;
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
public class LocalidadRN implements LocalidadRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;

    @EJB
    private LocalidadFacade localidadFacade;

    @Override
    public void create(Localidad localidad) throws Exception {
        //Agregar validaciones
        localidadFacade.create(localidad);
    }

    @Override
    public void edit(Localidad localidad) throws Exception {
        localidadFacade.edit(localidad);
    }

    @Override
    public void remove(Localidad localidad) throws Exception {
        localidadFacade.remove(localidad);
    }

    @Override
    public List<Localidad> findAll() throws Exception {
        return localidadFacade.findAll();
    }

    @Override
    public List<Localidad> buscarLocalidadesDepto(Departamento depto) {
        Query q = null;
        q = em.createQuery("SELECT l From Localidad l WHERE l.departamento=:depto");
        q.setParameter("depto", depto);
        return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Localidad buscarLocalidad(Localidad localidad) {
        return localidadFacade.find(localidad.getId());
    }

}
