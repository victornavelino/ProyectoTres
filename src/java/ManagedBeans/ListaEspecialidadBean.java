/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Especialidad;
import Facades.EspecialidadFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author nago
 */
@ManagedBean(name = "listaEspecialidadBean")
@RequestScoped
public class ListaEspecialidadBean {

    /**
     * Creates a new instance of ListaEspecialidadBean
     */
    @EJB
    private EspecialidadFacade especialidadFacade;
    private List<Especialidad> especialidades;

    public EspecialidadFacade getEspecialidadFacade() {
        return especialidadFacade;
    }

    public void setEspecialidadFacade(EspecialidadFacade especialidadFacade) {
        this.especialidadFacade = especialidadFacade;
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
        this.cargarEspecialidades();
    }

    public void cargarEspecialidades() {
        this.setEspecialidades(especialidadFacade.findAll());
    }
    public void buscarEspecialidades(String especialidad){
        this.setEspecialidades(especialidadFacade.findAll());
    }
}
