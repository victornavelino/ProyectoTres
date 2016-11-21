/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import Entidades.Caja.Caja;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hugo
 */
@Local
public interface CajaRNLocal {

    void create(Caja caja);

    void edit(Caja caja);

    void remove(Caja caja);

    Caja find(Object id);

    List<Caja> findAll();

    List<Caja> findRange(int[] range);

    int count();

    public List buscarCajaAbierta();

    public Caja getCajaAbierta();
    
    public boolean hayCajaAbierta();
    
}
