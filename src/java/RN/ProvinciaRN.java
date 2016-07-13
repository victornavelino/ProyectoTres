/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Localidad.Pais;
import Entidades.Localidad.Provincia;
import Facades.ProvinciaFacade;
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
public class ProvinciaRN implements ProvinciaRNLocal {

    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;
    @EJB
    private ProvinciaFacade provinciaFacade;

    @Override
    public void create(Provincia provincia) throws Exception {
        //Agregar validaciones
        provinciaFacade.create(provincia);
    }

    @Override
    public void edit(Provincia provincia) throws Exception {
        provinciaFacade.edit(provincia);
    }

    @Override
    public void remove(Provincia provincia) throws Exception {
        provinciaFacade.remove(provincia);
    }

    @Override
    public List<Provincia> findAll() throws Exception {
        return provinciaFacade.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Provincia buscarProvincia(Provincia provincia) {
        return provinciaFacade.find(provincia.getId());
    }

    @Override
    public List<Provincia> buscarProvinciasPais(Pais pais) {
        Query q= null; 
        q = em.createQuery("SELECT p From Provincia p WHERE p.pais=:pais");
        q.setParameter("pais", pais);
        return q.getResultList();
    }

}
