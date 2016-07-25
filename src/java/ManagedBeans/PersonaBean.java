/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Persona.Persona;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author nago
 */
@Named(value = "personaBean")
@RequestScoped
public class PersonaBean implements Serializable{

    /**
     * Creates a new instance of PersonaBean
     */
    private Persona persona;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public PersonaBean() {
        persona=new Persona();
    }
//    @PostConstruct
//    private void inicializar(){
//        persona=new Persona();
//    }
}
