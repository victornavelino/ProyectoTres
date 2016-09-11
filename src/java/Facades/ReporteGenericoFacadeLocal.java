/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Entidades.Reporte.ReporteGenerico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author diego
 */
@Local
public interface ReporteGenericoFacadeLocal {

    void create(ReporteGenerico reporteGenerico);

    void edit(ReporteGenerico reporteGenerico);

    void remove(ReporteGenerico reporteGenerico);

    ReporteGenerico find(Object id);

    List<ReporteGenerico> findAll();

    List<ReporteGenerico> findRange(int[] range);

    int count();
    
    List<ReporteGenerico> findLikeByNombre(String cadena) throws Exception;
    
    Boolean findByNombre(String nombre, Long id) throws Exception;
    
    List<Object[]> findLstConsultaReporteGenerico(Long idReporte, List<Object> lstValorParametro)throws Exception;
    
    Boolean validarConsultaReporteGenerico(ReporteGenerico reporteGenerico)throws Exception;
    
}
