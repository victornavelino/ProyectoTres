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
    private Integer mes;
    private Integer anio;
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

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public ConsultaBean() {

    }

    @PostConstruct
    public void init() {
        this.listaMedicoBean.setMedicosDeudores(medicoRNLocal.buscarMedicosDeudores(Calendar.getInstance().get(Calendar.MONTH)+1, Calendar.getInstance().get(Calendar.YEAR)));
    }

    public void buscarEspecialidad() {
        this.listaEspecialidadBean.buscarEspecialidades(StringEspecialidad);

    }

    public void buscarEspecializaciones() {
        this.listaEspecializacionBean.buscarEspecializaciones(especialidad);
    }

    public void buscarMedicosDeudores() {
        System.out.println("añooo: " + anio);
        this.listaMedicoBean.setMedicosDeudores(medicoRNLocal.buscarMedicosDeudores(mes, anio));

    }

    public void iniciar() {
        //en el calendario java mes va de 0 a 11 por eso le sumo
        //para que se muestre bien en a vista
        mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
        anio = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println("mes y año: " + mes + "  " + anio);
        spinnerAnio.setValue(anio);
    }
}
