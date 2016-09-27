/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Especialidad;
import Facades.EspecialidadFacade;
import RN.EspecialidadRNLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author nago
 */
@Named(value = "listaEspecialidadBean")
@RequestScoped
public class ListaEspecialidadBean {

    /**
     * Creates a new instance of ListaEspecialidadBean
     */
    @EJB
    private EspecialidadRNLocal especialidadFacade;
    private List<Especialidad> especialidades;
    private List <SelectItem> lstSIEspecialidad;

    public List<SelectItem> getLstSIEspecialidad() {
        return lstSIEspecialidad;
    }

    public void setLstSIEspecialidad(List<SelectItem> lstSIEspecialidad) {
        this.lstSIEspecialidad = lstSIEspecialidad;
    }
    

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }
    
    
    
    public ListaEspecialidadBean() {
    }
    
    @PostConstruct
    private void inicializar(){
        this.especialidades= new ArrayList<>();
        this.cargarEspecialidades();
    }

    public void cargarEspecialidades() {
        this.setEspecialidades(especialidadFacade.findAll());
    }
    public void buscarEspecialidades(String especialidad){
        this.setEspecialidades(especialidadFacade.buscarEspecialidad(especialidad));
    }
}
