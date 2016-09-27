package ManagedBeans;

import Entidades.Medico.Recertificacion;
import Facades.RecertificacionFacade;
import RN.RecertificacionRNLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@Named("reporteRecertificacionBean")
@javax.faces.view.ViewScoped
public class ReporteRecertificacionBean implements Serializable {

    @EJB
    private Facades.RecertificacionFacade ejbFacade;
    @EJB
    private RecertificacionRNLocal recertificacionRNLocal;
    private List<Recertificacion> items = null;
    private Recertificacion selected;
    private PieChartModel pieModel1;

    public RecertificacionFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(RecertificacionFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Recertificacion> getItems() {
        return items;
    }

    public void setItems(List<Recertificacion> items) {
        this.items = items;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    private void createPieModels() {
        createPieModel1();
    }

    @PostConstruct
    public void init() {
        createPieModels();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        List<Object[]> cantidadMedicos = getRecertificacionRNLocal().cantidadVigente();
        for (Object[] row : cantidadMedicos) {
            pieModel1.set((String) row[1], (Long) row[0]);
        }
        pieModel1.setShadow(true);
        pieModel1.setTitle("Recertificaciones");
        pieModel1.setLegendPosition("e");
        pieModel1.setShowDataLabels(true);
        pieModel1.setMouseoverHighlight(true);
        pieModel1.setLegendRows(20);
        pieModel1.setLegendCols(1);
        pieModel1.setFill(true);

    }

    public ReporteRecertificacionBean() {
    }

    public Recertificacion getSelected() {
        return selected;
    }

    public void setSelected(Recertificacion selected) {
        this.selected = selected;
    }

    public RecertificacionRNLocal getRecertificacionRNLocal() {
        return recertificacionRNLocal;
    }

    public void setRecertificacionRNLocal(RecertificacionRNLocal recertificacionRNLocal) {
        this.recertificacionRNLocal = recertificacionRNLocal;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RecertificacionFacade getFacade() {
        return ejbFacade;
    }

}
