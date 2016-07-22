/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Persona.CorreoElectronico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vouilloz
 */
@Local
public interface CorreoElectronicoRNLocal {

    public void create(CorreoElectronico correoElectronico) throws Exception;

    public void edit(CorreoElectronico correoElectronico) throws Exception;

    public void remove(CorreoElectronico correoElectronico) throws Exception;

    public List<CorreoElectronico> findAll() throws Exception;

    public CorreoElectronico findById(Long id) throws Exception;

}
