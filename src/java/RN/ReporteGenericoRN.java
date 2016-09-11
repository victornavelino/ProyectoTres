/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RN;

import Entidades.Reporte.ReporteGenerico;
import Facades.ReporteGenericoFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author diego
 */
@Stateless
public class ReporteGenericoRN implements ReporteGenericoRNLocal {

    @EJB
    private ReporteGenericoFacadeLocal reporteGenericoFacadeLocal;
    
    @Override
    public void create(ReporteGenerico reporteGenerico) throws Exception {
        this.validar(reporteGenerico);
        reporteGenericoFacadeLocal.create(reporteGenerico);
    }

    @Override
    public void edit(ReporteGenerico reporteGenerico) throws Exception {
        this.validar(reporteGenerico);
        reporteGenericoFacadeLocal.edit(reporteGenerico);
    }

    @Override
    public void remove(ReporteGenerico reporteGenerico) throws Exception {
        reporteGenericoFacadeLocal.remove(reporteGenerico);
    }

    @Override
    public List<ReporteGenerico> findLikeByReporteGenerico(String cadena) throws Exception {
        return reporteGenericoFacadeLocal.findLikeByNombre(cadena);
    }
    
    private void validar(ReporteGenerico reporteGenerico) throws Exception {
        if (reporteGenerico.getNombre().isEmpty()) {
            throw new Exception("Debe ingresar el nombre del reporte");
        }//fin if
        
        if(reporteGenerico.getConsulta().isEmpty()){
            throw new Exception("Debe ingresar la consulta del reporte");
        }
        
        //verificar consulta
        if(!this.reporteGenericoFacadeLocal.validarConsultaReporteGenerico(reporteGenerico)){
            throw new Exception("Hay un error en la consulta. Verificar");
        }//fin if
        
        if(reporteGenerico.getColumnas().isEmpty()){
            throw new Exception("Debe ingresar los nombres de las columnas separadas por una coma.");
        }
        
        

        if(this.reporteGenericoFacadeLocal.findByNombre(reporteGenerico.getNombre(), reporteGenerico.getId())){
            throw new Exception("Ya existe el reporte.");
        }//fin if
    }

    @Override
    public List<ReporteGenerico> findAll() {
        return reporteGenericoFacadeLocal.findAll();
    }

    @Override
    public List<Object[]> findLstConsultaReporteGenerico(Long idReporte, List<Object> lstValorParametro) throws Exception {
        return reporteGenericoFacadeLocal.findLstConsultaReporteGenerico(idReporte, lstValorParametro);
    }
}
