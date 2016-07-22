/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Persona.Telefono;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vouilloz
 */
@Local
public interface TelefonoRNLocal {

    public void create(Telefono telefono) throws Exception;

    public void edit(Telefono telefono) throws Exception;

    public void remove(Telefono telefono) throws Exception;

    public List<Telefono> findAll() throws Exception;

    public Telefono findById(Long id) throws Exception;

}
