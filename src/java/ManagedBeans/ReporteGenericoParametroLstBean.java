package ManagedBeans;

import Entidades.Reporte.Parametro;
import Entidades.Reporte.ReporteGenerico;
import RN.ReporteGenericoRNLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named(value = "reporteGenericoParametroLstBean")
@SessionScoped
public class ReporteGenericoParametroLstBean implements Serializable {

    private List<Parametro> lstParametros;
    

    public ReporteGenericoParametroLstBean() {
    }

    public List<Parametro> getLstParametros() {
        return lstParametros;
    }

    public void setLstParametros(List<Parametro> lstParametros) {
        this.lstParametros = lstParametros;
    }
    
}//fin clase
