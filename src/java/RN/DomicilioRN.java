/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Persona.Domicilio;
import Facades.DomicilioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author nago
 */
@Stateless
public class DomicilioRN implements DomicilioRNLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private DomicilioFacade domicilioFacade;

    @Override
    public void create(Domicilio domicilio) throws Exception {
        //Agregar validaciones
        domicilioFacade.create(domicilio);
    }

    @Override
    public void edit(Domicilio domicilio) throws Exception {
        domicilioFacade.edit(domicilio);
    }

    @Override
    public void remove(Domicilio domicilio) throws Exception {
        domicilioFacade.remove(domicilio);
    }

    @Override
    public List<Domicilio> findAll() throws Exception {
        return domicilioFacade.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Domicilio buscarDomicilio(Domicilio domicilio) {
        return domicilioFacade.find(domicilio.getId());
    }
}
