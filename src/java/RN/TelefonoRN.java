/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Persona.Telefono;
import Facades.TelefonoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author vouilloz
 */
@Stateless
public class TelefonoRN implements TelefonoRNLocal {

    @EJB
    private TelefonoFacade telefonoFacade;

    @Override
    public void create(Telefono telefono) throws Exception {
        telefonoFacade.create(telefono);
    }

    @Override
    public void edit(Telefono telefono) throws Exception {
        telefonoFacade.edit(telefono);
    }

    @Override
    public void remove(Telefono telefono) throws Exception {
        telefonoFacade.remove(telefono);
    }

    @Override
    public List<Telefono> findAll() throws Exception {
        return telefonoFacade.findAll();
    }

    @Override
    public Telefono findById(Long id) throws Exception {
        return telefonoFacade.find(id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
