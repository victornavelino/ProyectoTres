/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;
import Entidades.Localidad.Pais;
import Entidades.Localidad.Provincia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hugo
 */
@Local
public interface ProvinciaRNLocal {

    public void create(Provincia provincia) throws Exception;

    public void edit(Provincia provincia) throws Exception;

    public void remove(Provincia provincia) throws Exception;

    public List<Provincia> findAll() throws Exception;

    public Provincia buscarProvincia(Provincia provincia);

    public List<Provincia> buscarProvinciasPais(Pais pais);

}
