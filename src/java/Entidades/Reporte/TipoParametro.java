/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades.Reporte;

/**
 *
 * @author Carlos
 */

public enum TipoParametro  {

   FECHA("FECHA"),
   TEXTO("TEXTO"),
   ENTERO("ENTERO"),
   LONG("LONG"),
   DECIMAL("DECIMAL");

    private String name;

    private TipoParametro(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
//para llenar combo box
        return name;
    }
}
