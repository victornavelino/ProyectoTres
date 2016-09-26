/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Especializacion;
import Entidades.Medico.Medico;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.Query;

/**
 *
 * @author franco
 */
@Local
public interface EspecializacionRNLocal {

    public List<Especializacion> findAll();

    public List<Especializacion> buscarPorEspecialidad(Especialidad especialidad);

    public List<Especializacion> buscarPorProfesional(String medico);

    public List<Especializacion> buscarPorMedico(Medico medico);

    public List<Object[]> cantidadEspecializacion();

}
