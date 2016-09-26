package ManagedBeans;

import Entidades.Medico.Especializacion;
import Facades.EspecializacionFacade;
import RN.EspecializacionRNLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@Named("reporteEspecializacionBean")
@javax.faces.view.ViewScoped
public class ReporteEspecializacionBean implements Serializable {

    @EJB
    private Facades.EspecializacionFacade ejbFacade;
    @EJB
    private EspecializacionRNLocal especializacionRNLocal;
    private List<Especializacion> items = null;
    private Especializacion selected;
    private PieChartModel pieModel1;

    public EspecializacionFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(EspecializacionFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Especializacion> getItems() {
        return items;
    }

    public void setItems(List<Especializacion> items) {
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

        List<Object[]> cantidadMedicos = getEspecializacionRNLocal().cantidadEspecializacion();
        for (Object[] row : cantidadMedicos) {
            pieModel1.set((String) row[1], (int) row[0]);
        }

        pieModel1.setTitle("Especializaciones");
        pieModel1.setLegendPosition("w");
    }

    public ReporteEspecializacionBean() {
    }

    public Especializacion getSelected() {
        return selected;
    }

    public void setSelected(Especializacion selected) {
        this.selected = selected;
    }

    public EspecializacionRNLocal getEspecializacionRNLocal() {
        return especializacionRNLocal;
    }

    public void setEspecializacionRNLocal(EspecializacionRNLocal especializacionRNLocal) {
        this.especializacionRNLocal = especializacionRNLocal;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EspecializacionFacade getFacade() {
        return ejbFacade;
    }

}
