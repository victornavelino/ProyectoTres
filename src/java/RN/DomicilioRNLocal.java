/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Persona.Domicilio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nago
 */
@Local
public interface DomicilioRNLocal {

    public void create(Domicilio domicilio) throws Exception;

    public void edit(Domicilio domicilio) throws Exception;

    public void remove(Domicilio domicilio) throws Exception;

    public List<Domicilio> findAll() throws Exception;

    public Domicilio buscarDomicilio(Domicilio domicilio);
}
