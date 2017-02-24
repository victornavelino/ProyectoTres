/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Medico;
import RN.MedicoRNLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;

/**
 *
 * @author hugo
 */
@Named("consultaBean")
@SessionScoped
public class ConsultaBean implements Serializable {

    /**
     * Creates a new instance of ConsultaBean
     */
    private List<Medico> medicosDeudores;

    @EJB
    private MedicoRNLocal medicoRNLocal;

    @Inject
    private MedicoController medicoController;

    private Spinner spinnerAnio;

    public Spinner getSpinnerAnio() {
        return spinnerAnio;
    }

    public List<Medico> getMedicosDeudores() {
        return medicosDeudores;
    }

    public void setMedicosDeudores(List<Medico> medicosDeudores) {
        this.medicosDeudores = medicosDeudores;
    }

    public MedicoRNLocal getMedicoRNLocal() {
        return medicoRNLocal;
    }

    public void setMedicoRNLocal(MedicoRNLocal medicoRNLocal) {
        this.medicoRNLocal = medicoRNLocal;
    }

    public void setSpinnerAnio(Spinner spinnerAnio) {
        this.spinnerAnio = spinnerAnio;
    }

    public ConsultaBean() {

    }

    @PostConstruct
    public void init() {
        this.setMedicosDeudores(new ArrayList<Medico>());
    }

    public void buscarMedicosDeudores(int mes) {
        this.setMedicosDeudores(medicoRNLocal.buscarMedicosDeudores(obtenerMesRestado(mes), obtenerAniRestado(mes)));
        medicoController.setSelected(null);

    }

    public void buscarMedicosDeudores(int mes, int mes2) {
        this.setMedicosDeudores(medicoRNLocal.buscarMedicosDeudores(obtenerMesRestado(mes), obtenerAniRestado(mes), obtenerMesRestado(mes2), obtenerAniRestado(mes2)));
        medicoController.setSelected(null);

    }

    public void iniciar(int i) {
        //en el calendario java mes va de 0 a 11 por eso le sumo
        //para que se muestre bien en a vista
        if (i == 1) {
            buscarMedicosDeudores(0, 6);
        } else if (i == 2) {
            buscarMedicosDeudores(6, 12);
        } else if (i == 3) {
            buscarMedicosDeudores(12, 24);
        } else if (i == 4) {
            buscarMedicosDeudores(24);
        }
        RequestContext.getCurrentInstance().update("consultaForm:dtConsultaMedico");
        RequestContext.getCurrentInstance().update("consultaForm:pagos");

    }

    public int obtenerMesRestado(int mes) {
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MONTH, -mes);
        int month = c.get(Calendar.MONTH) + 1;
        return month;
    }

    public int obtenerAniRestado(int mes) {
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MONTH, -mes);
        int year = c.get(Calendar.YEAR);
        return year;
    }

}
