/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Medico;
import RN.MedicoRNLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
//import javax.enterprise.context.RequestScoped;

/**
 *
 * @author hugo
 */
@ManagedBean
@Named(value = "listaMedicoBean")
@RequestScoped
public class ListaMedicoBean {

    /**
     * Creates a new instance of ConsultaBean
     */
    private List<Medico> medicos;
    private Especialidad especialidad;
    @EJB
    private MedicoRNLocal medicoFacade;

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    
    public ListaMedicoBean() {
    }
    @PostConstruct
    private void inicializar(){
        cargarMedicos();
        
    }
    public void buscarMedicoXEspecialidad() {
        this.medicos = medicoFacade.buscarMedicosEspecialidad(especialidad);

    }
    public void cargarMedicos(){
        System.out.println("entroo cargar medicoos");
    this.setMedicos(medicoFacade.buscarTodos());
        System.out.println("MEDICOS: "+this.getMedicos());
}
}
