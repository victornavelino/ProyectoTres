package ManagedBeans;

import Entidades.Reporte.ReporteGenerico;
import RN.ReporteGenericoRNLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named(value = "reporteGenericoLstBean")
@SessionScoped
public class ReporteGenericoLstBean implements Serializable {

    private List<ReporteGenerico> lstReporteGenerico;
    
    private List<SelectItem> SIReporteGenerico;
    @EJB
    private ReporteGenericoRNLocal reporteGenericoRNLocal;

    public ReporteGenericoLstBean() {
    }

    public List<SelectItem> getSIReporteGenerico() {
        return SIReporteGenerico;
    }

    public void setSIReporteGenerico(List<SelectItem> SIReporteGenerico) {
        this.SIReporteGenerico = SIReporteGenerico;
    }
        
    public List<ReporteGenerico> getLstReporteGenerico() {
        return lstReporteGenerico;
    }

    public void setLstReporteGenerico(List<ReporteGenerico> lstReporteGenerico) {
        this.lstReporteGenerico = lstReporteGenerico;
    }
    
    public void cargarSIReporteGenerico(){
        this.setSIReporteGenerico(new ArrayList<SelectItem>());
        this.setLstReporteGenerico(this.reporteGenericoRNLocal.findAll());
        
        for(ReporteGenerico tp : this.getLstReporteGenerico()){

            SelectItem si = new SelectItem(tp, tp.getDescripcion());
            this.getSIReporteGenerico().add(si);
        }//fin for
    }//fin cargarSIPantallas

    public void findLikeByReporteGenerico(String cadena) throws Exception {
        this.setLstReporteGenerico(reporteGenericoRNLocal.findLikeByReporteGenerico(cadena.trim().toLowerCase()));
    }

}//fin clase
