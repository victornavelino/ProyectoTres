/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;
import Entidades.Localidad.Departamento;
import Entidades.Localidad.Localidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hugo
 */
@Local
public interface LocalidadRNLocal {

    public void create(Localidad localidad) throws Exception;

    public void edit(Localidad localidad) throws Exception;

    public void remove(Localidad localidad) throws Exception;

    public List<Localidad> findAll() throws Exception;

    public Localidad buscarLocalidad(Localidad localidad);

    public List<Localidad> buscarLocalidadesDepto(Departamento depto) throws Exception;

}
