package ManagedBeans;


import Entidades.Reporte.TipoParametro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named(value = "reporteGenericoTipoParametroLstBean")
@SessionScoped
public class ReporteGenericoTipoParametroLstBean implements Serializable {

    private List <SelectItem> lstSiTipo;

    public ReporteGenericoTipoParametroLstBean() {
    }

    public List<SelectItem> getLstSiTipo() {
        return lstSiTipo;
    }

    public void setLstSiTipo(List<SelectItem> lstSiTipo) {
        this.lstSiTipo = lstSiTipo;
    }

    
    
    public void cargarSITipo(){
        lstSiTipo = new ArrayList();
        
        
        for(TipoParametro tp :  TipoParametro.values()){
            lstSiTipo.add(new SelectItem(tp, tp.toString()));
        }
    }//fin cargarSISexo
    
}//fin clase
