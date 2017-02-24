/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Medico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author franco
 */
@Local
public interface MedicoRNLocal {

    public List<Medico> buscarMedicosEspecialidad(Especialidad especialidad);

    public List<Medico> buscarTodos();

    public List<Medico> buscarXApellido(String apellido);

    public List<Medico> buscarXMatricula(String matriculaProfesional);

    public List<Medico> buscarMedicosDeudores(Integer mes, Integer anio);

    public int buscarUltimaMatricula();

    public List<Medico> buscarTodosActivos();

    public void edit(Medico medico);

    public List<Medico> buscarMedicosDeudores(Integer mes, Integer mes2, Integer anio, Integer anio2);

}
