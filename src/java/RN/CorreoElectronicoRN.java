/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;
import Entidades.Persona.CorreoElectronico;
import Facades.CorreoElectronicoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author vouilloz
 */
@Stateless
public class CorreoElectronicoRN implements CorreoElectronicoRNLocal {

    @EJB
    private CorreoElectronicoFacade correoElectronicoFacade;

    @Override
    public void create(CorreoElectronico correoElectronico) throws Exception {
        correoElectronicoFacade.create(correoElectronico);
    }

    @Override
    public void edit(CorreoElectronico correoElectronico) throws Exception {
        correoElectronicoFacade.edit(correoElectronico);
    }

    @Override
    public void remove(CorreoElectronico correoElectronico) throws Exception {
        correoElectronicoFacade.remove(correoElectronico);
    }

    @Override
    public List<CorreoElectronico> findAll() throws Exception {
        return correoElectronicoFacade.findAll();
    }

    @Override
    public CorreoElectronico findById(Long id) throws Exception {
        return correoElectronicoFacade.find(id);
    }

}
