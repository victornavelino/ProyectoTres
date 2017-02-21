/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Medico.Recertificacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author franco
 */
@Local
public interface RecertificacionRNLocal {


    public List<Object[]> cantidadVigente();

    public List<Recertificacion> getRecertificacionesActivos();

}
