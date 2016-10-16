/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Caja;

import Entidades.Pago.*;

/**
 *
 * @author hugo
 */
public enum FormaDePago {

    EFECTIVO("EFECTIVO"),
    CHEQUE("CHEQUE"),
    TRANSFERENCIA("TRANSFERENCIA"),
    OTRO("OTRO");

    private String name;

    private FormaDePago(String name) {
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
