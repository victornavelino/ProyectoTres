package ManagedBeans;


import Entidades.Reporte.Parametro;
import Entidades.Reporte.ReporteGenerico;
import RN.ReporteGenericoRNLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "reporteGenericoParametroBean")
@RequestScoped
public class ReporteGenericoParametroBean {

    private Parametro parametro;
    @EJB
    private ReporteGenericoRNLocal reporteGenericoRNLocal;

    public ReporteGenericoParametroBean() {
        parametro = new Parametro();
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }
}
