/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;


import Entidades.Localidad.Pais;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hugo
 */
@Local
public interface PaisRNLocal {

    public void create(Pais pais) throws Exception;

    public void edit(Pais pais) throws Exception;

    public void remove(Pais pais) throws Exception;

    public List<Pais> findAll() throws Exception;

}
