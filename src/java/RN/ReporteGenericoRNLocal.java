/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import Entidades.Reporte.ReporteGenerico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author diego
 */
@Local
public interface ReporteGenericoRNLocal {
    void create(ReporteGenerico reporteGenerico) throws Exception;

    void edit(ReporteGenerico reporteGenerico) throws Exception;

    void remove(ReporteGenerico reporteGenerico) throws Exception;
    
    public List<ReporteGenerico> findAll();
    
    List<ReporteGenerico> findLikeByReporteGenerico(String cadena) throws Exception;
    List<Object[]> findLstConsultaReporteGenerico(Long idReporte, List<Object> lstValorParametro) throws Exception;
}
