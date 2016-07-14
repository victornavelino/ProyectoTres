/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Medico;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author hugo
 */
@Named(value = "consultaBean")
@RequestScoped
public class ConsultaBean {

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

    public String getStringEspecialidad() {
        return StringEspecialidad;
    }

    public void setStringEspecialidad(String StringEspecialidad) {
        this.StringEspecialidad = StringEspecialidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    public ListaEspecializacionBean getListaEspecializacionBean() {
        return listaEspecializacionBean;
    }

    public void setListaEspecializacionBean(ListaEspecializacionBean listaEspecializacionBean) {
        this.listaEspecializacionBean = listaEspecializacionBean;
    }
    
    public ListaEspecialidadBean getListaEspecialidadBean() {
        return listaEspecialidadBean;
    }

    public void setListaEspecialidadBean(ListaEspecialidadBean listaEspecialidadBean) {
        this.listaEspecialidadBean = listaEspecialidadBean;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }


    public ConsultaBean() {
    }

    public void buscarEspecialidad() {
        this.listaEspecialidadBean.buscarEspecialidades(StringEspecialidad);

    }

    public void buscarEspecializaciones() {
        this.listaEspecializacionBean.buscarEspecializaciones(especialidad);
    }

}
