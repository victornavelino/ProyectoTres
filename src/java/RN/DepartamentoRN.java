/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Localidad.Departamento;
import Entidades.Localidad.Provincia;
import Facades.DepartamentoFacade;
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
public class DepartamentoRN implements DepartamentoRNLocal {
    
    @PersistenceContext(unitName = "ProyectoColegioPU")
    private EntityManager em;
    @EJB
    private DepartamentoFacade departamentoFacade;

    @Override
    public void create(Departamento departamento) throws Exception {
        //Agregar validaciones
        departamentoFacade.create(departamento);
    }

    @Override
    public void edit(Departamento departamento) throws Exception {
        departamentoFacade.edit(departamento);
    }

    @Override
    public void remove(Departamento departamento) throws Exception {
        departamentoFacade.remove(departamento);
    }

    @Override
    public List<Departamento> findAll() throws Exception {
        return departamentoFacade.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Departamento buscarDepartamento(Departamento departamento) {
        return departamentoFacade.find(departamento.getId());
    }

    @Override
    public List<Departamento> buscarDptoProvincia(Provincia provincia) {
        Query q = null;
        q = em.createQuery("SELECT d From Departamento d WHERE d.provincia=:provincia");
        q.setParameter("provincia", provincia);
        return q.getResultList();
    }

}
