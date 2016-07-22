/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Medico.Especialidad;
import Entidades.Medico.Especializacion;
import Facades.EspecialidadFacade;
import Facades.EspecializacionFacade;
import RN.EspecializacionRNLocal;
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
@Named(value = "listaEspecializacionBean")
@RequestScoped
public class ListaEspecializacionBean {

    /**
     * Creates a new instance of ListaEspecialidadBean
     */
    @EJB
    private EspecializacionRNLocal especializacionFacade;
    private List<Especializacion> especializaciones;
    private List<SelectItem> lstSIEspecializacion;

    public List<Especializacion> getEspecializaciones() {
        return especializaciones;
    }

    public void setEspecializaciones(List<Especializacion> especializaciones) {
        this.especializaciones = especializaciones;
    }

    public List<SelectItem> getLstSIEspecializacion() {
        return lstSIEspecializacion;
    }

    public void setLstSIEspecializacion(List<SelectItem> lstSIEspecializacion) {
        this.lstSIEspecializacion = lstSIEspecializacion;
    }

    public ListaEspecializacionBean() {
    }

    @PostConstruct
    private void inicializar() {
        this.especializaciones = new ArrayList<>();
        this.cargarEspecializaciones();
    }

    public void cargarEspecializaciones() {
        this.setEspecializaciones(especializacionFacade.findAll());
    }

    public void buscarEspecializaciones(Especialidad especialidad) {
        this.setEspecializaciones(especializacionFacade.buscarPorEspecialidad(especialidad));
    }
}
