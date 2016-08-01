/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Pago;

/**
 *
 * @author hugo
 */
public enum Mes {
    
ENERO ("ENERO"),
FEBRERO ("FEBRERO"),
MARZO ("MARZO"),
ABRIL ("ABRIL"),
MAYO ("MAYO"),
JUNIO ("JUNIO"),
JULIO ("JULIO"),
AGOSTO ("AGOSTO"),
SEPTIEMBRE ("SEPTIEMBRE"),
OCTUBRE ("OCTUBRE"),
NOVIEMBRE ("NOVIEMBRE"),
DICIEMBRE ("DICIEMBRE");

private String name ;

    private Mes(String name) {
        this.name = name;
    }


public String getName() {
return name;
}

@Override
public String toString(){
//para llenar combo box
return name;
}

}
