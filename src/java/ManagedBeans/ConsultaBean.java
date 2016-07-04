/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Medico;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
//import javax.enterprise.context.RequestScoped;

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
    private String especialidad;
    @ManagedProperty("#{listaEspecialidadBean}")
    private ListaEspecialidadBean listaEspecialidadBean;

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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public ConsultaBean() {
    }

    public void buscarEspecialidades() {
     
    }

}
