package ManagedBeans;

import Entidades.Medico.Recertificacion;
import Facades.RecertificacionFacade;
import RN.RecertificacionRNLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
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
    private BarChartModel barModel;

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

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

    private void createModels() {
        createPieModel1();
        createBarModel();
    }

    @PostConstruct
    public void init() {
        createModels();
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

    private void createBarModel() {

        BarChartModel model = new BarChartModel();

        ChartSeries especialidades = new ChartSeries();
        especialidades.setLabel("Especialidades");
        List<Object[]> cantidadMedicos = getRecertificacionRNLocal().cantidadVigente();
        for (Object[] row : cantidadMedicos) {
            especialidades.set((String) row[1], (Long) row[0]);
        }

        model.addSeries(especialidades);

        barModel = model;

        barModel.setTitle("Recertificaciones Vigentes");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Especialidades");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidades");
        yAxis.setMin(0);
        yAxis.setMax(200);
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
