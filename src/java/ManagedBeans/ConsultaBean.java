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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.primefaces.component.spinner.Spinner;

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
    private Medico medico;
//    private Especialidad especialidad;
    private String StringEspecialidad;
    private Especialidad especialidad;
    @Inject
    private ListaEspecialidadBean listaEspecialidadBean;
    @Inject
    private ListaEspecializacionBean listaEspecializacionBean;
    @Inject
    private ListaMedicoBean listaMedicoBean;
    @EJB
    private MedicoRNLocal medicoRNLocal;
    private Spinner spinnerAnio;

    public Spinner getSpinnerAnio() {
        return spinnerAnio;
    }

    public void setSpinnerAnio(Spinner spinnerAnio) {
        this.spinnerAnio = spinnerAnio;
    }

    public ListaMedicoBean getListaMedicoBean() {
        return listaMedicoBean;
    }

    public void setListaMedicoBean(ListaMedicoBean listaMedicoBean) {
        this.listaMedicoBean = listaMedicoBean;
    }

    public ConsultaBean() {

    }

    @PostConstruct
    public void init() {
    }

    public void buscarEspecialidad() {
        this.listaEspecialidadBean.buscarEspecialidades(StringEspecialidad);

    }

    public void buscarEspecializaciones() {
        this.listaEspecializacionBean.buscarEspecializaciones(especialidad);
    }

    public void buscarMedicosDeudores(int mes) {
        this.listaMedicoBean.setMedicosDeudores(medicoRNLocal.buscarMedicosDeudores(obtenerMesRestado(mes),obtenerAniRestado(mes)));

    }

    public void buscarMedicosDeudores(int mes, int mes2) {
        this.listaMedicoBean.setMedicosDeudores(medicoRNLocal.buscarMedicosDeudores(obtenerMesRestado(mes),obtenerAniRestado(mes),obtenerMesRestado(mes2),obtenerAniRestado(mes2)));

    }
    public void iniciar() {
        //en el calendario java mes va de 0 a 11 por eso le sumo
        //para que se muestre bien en a vista
    }

    public int obtenerMesRestado(int mes) {
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MONTH, -mes);
        int month = c.get(Calendar.MONTH);
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
