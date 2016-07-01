/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Medico;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    public ConsultaBean() {
    }
   
    
}
