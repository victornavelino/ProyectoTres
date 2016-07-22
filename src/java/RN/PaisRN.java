/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;


import Entidades.Localidad.Pais;
import Facades.PaisFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author hugo
 */
@Stateless
public class PaisRN implements PaisRNLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private PaisFacade paisFacade;

    @Override
    public void create(Pais pais) throws Exception {

        paisFacade.create(pais);
    }

    @Override
    public void edit(Pais pais) throws Exception {
        paisFacade.edit(pais);
    }

    @Override
    public void remove(Pais pais) throws Exception {
        paisFacade.remove(pais);
    }

    @Override
    public List<Pais> findAll() throws Exception {
        return paisFacade.findAll();
    }

}
