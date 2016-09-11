package ManagedBeans;


import Entidades.Reporte.ReporteGenerico;
import RN.ReporteGenericoRNLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "reporteGenericoBean")
@RequestScoped
public class ReporteGenericoBean {

    private ReporteGenerico reporteGenerico;
    @EJB
    private ReporteGenericoRNLocal reporteGenericoRNLocal;

    public ReporteGenericoBean() {
        reporteGenerico = new ReporteGenerico();
    }

    public ReporteGenerico getReporteGenerico() {
        return reporteGenerico;
    }

    public void setReporteGenerico(ReporteGenerico reporteGenerico) {
        this.reporteGenerico = reporteGenerico;
    }

    public void create() throws Exception {
        reporteGenericoRNLocal.create(this.getReporteGenerico());
    }

    public void edit() throws Exception {
        reporteGenericoRNLocal.edit(this.getReporteGenerico());
    }

    public void remove() throws Exception {
        reporteGenericoRNLocal.remove(this.getReporteGenerico());
    }
}
