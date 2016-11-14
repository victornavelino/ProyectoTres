/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Localidad.Departamento;
import Entidades.Localidad.Localidad;
import Entidades.Localidad.Pais;
import Entidades.Localidad.Provincia;
import Entidades.Persona.Domicilio;
import RN.DepartamentoRNLocal;
import RN.LocalidadRNLocal;
import RN.PaisRNLocal;
import RN.ProvinciaRNLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;

/**
 *
 * @author hugo
 */
@Named(value = "domicilioBean")
@SessionScoped
public class DomicilioBean implements Serializable {

    @EJB
    private PaisRNLocal paisRNLocal;
    @EJB
    private ProvinciaRNLocal provinciaRNLocal;
    @EJB
    private DepartamentoRNLocal departamentoRNLocal;
    @EJB
    private LocalidadRNLocal localidadRNLocal;

    private List<SelectItem> listaPais;
    private List<SelectItem> listaProvincias;
    private List<SelectItem> listaDepartamentos;
    private List<SelectItem> listaLocalidades;

    private Domicilio domicilio;
    private Pais pais;
    private Provincia provincia;
    private Departamento departamento;
    private Localidad localidad;
    private String actualizar;
    CommandButton btnSelect;
    int iTipoBoton;
    //RESIDENCIA
    private Localidad residencia;

    public Localidad getResidencia() {
        return residencia;
    }

    public void setResidencia(Localidad residencia) {
        this.residencia = residencia;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public List<SelectItem> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<SelectItem> listaPais) {
        this.listaPais = listaPais;
    }

    public List<SelectItem> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List<SelectItem> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<SelectItem> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<SelectItem> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<SelectItem> getListaLocalidades() {
        return listaLocalidades;
    }

    public void setListaLocalidades(List<SelectItem> listaLocalidades) {
        this.listaLocalidades = listaLocalidades;
    }

    public int getiTipoBoton() {
        return iTipoBoton;
    }

    public void setiTipoBoton(int iTipoBoton) {
        this.iTipoBoton = iTipoBoton;
    }

    /**
     * Creates a new instance of domicilioBean
     */
    public DomicilioBean() {
    }

    @PostConstruct
    private void inicializarComponentes() {
        domicilio = new Domicilio();
        residencia = new Localidad();
        cargarPaises();
        cargarProvincias();
        cargarDepartamentos();
    }

    public void cargarProvincias() {
        try {

            listaProvincias = new ArrayList<SelectItem>();
            List<Provincia> provincias = provinciaRNLocal.findAll();
            for (Provincia prov : provincias) {
                listaProvincias.add(new SelectItem(prov, prov.toString()));
            }
        } catch (Exception ex) {

            Logger.getLogger(DomicilioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarDepartamentos() {

        try {
            listaDepartamentos = new ArrayList<>();

            for (Departamento depto : departamentoRNLocal.buscarDptoProvincia(provincia)) {
                listaDepartamentos.add(new SelectItem(depto, depto.toString()));

            }
        } catch (Exception ex) {
            Logger.getLogger(DomicilioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarLocalidades() {

        try {
            listaLocalidades = new ArrayList<>();
            for (Localidad loc : localidadRNLocal.buscarLocalidadesDepto(departamento)) {
                listaLocalidades.add(new SelectItem(loc, loc.toString()));

            }
        } catch (Exception ex) {
            Logger.getLogger(DomicilioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarPaises() {

        try {
            listaPais = new ArrayList<SelectItem>();
            List<Pais> paises = paisRNLocal.findAll();
            for (Pais pa : paises) {
                listaPais.add(new SelectItem(pa, pa.toString()));
            }
        } catch (Exception ex) {
            Logger.getLogger(DomicilioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarProvPais() {
        listaProvincias = new ArrayList<SelectItem>();

        for (Provincia prov : provinciaRNLocal.buscarProvinciasPais(pais)) {
            listaProvincias.add(new SelectItem(prov, prov.toString()));

        }
        //para anular los Departamentos y localidades
        if (listaProvincias.isEmpty()) {
            listaDepartamentos = null;
            listaLocalidades = null;
        }
    }

    public void definirActionBoton(ActionEvent e) {
        btnSelect = (CommandButton) e.getSource();

    }

    public void validarLocalidad(ActionEvent e) {
        // Para validar que haya seleccionado la localidad en
        // cualquiera de los dialogos (LugarNacimiento y DOmicilio)

        //   if (this.getPersona().getLugarNacimiento() != null) {
        //this.setsDireccion(this.getPersona().getLugarNacimiento().getDepartamento().getDescripcion() + ", " + this.getPersona().getLugarNacimiento().getDescripcion());
        //System.out.println(actualizarPanel);
        RequestContext.getCurrentInstance().update("MedicoCreateForm:pnDomicilio");
        RequestContext.getCurrentInstance().execute("PF('dgDomicilioProf').hide();");
    }
        public void validarLocalidadResidencia(ActionEvent e) {
        // Para validar que haya seleccionado la localidad en
        // cualquiera de los dialogos (LugarNacimiento y DOmicilio)

        //   if (this.getPersona().getLugarNacimiento() != null) {
        //this.setsDireccion(this.getPersona().getLugarNacimiento().getDepartamento().getDescripcion() + ", " + this.getPersona().getLugarNacimiento().getDescripcion());
        //System.out.println(actualizarPanel);
        RequestContext.getCurrentInstance().update("MedicoCreateForm:pnResidencia");
        RequestContext.getCurrentInstance().execute("PF('dgResidenciaProf').hide();");
    }

    public void afterPhase(PhaseEvent PhaseEvent) {
        UIComponent component = findComponentInRoot("pnDomicilio");
        if (component != null) {
            System.out.println("found the component = " + component.getId() + " class: " + component);
        }
    }

    public static UIComponent findComponentInRoot(String id) {
        UIComponent component = null;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            UIComponent root = facesContext.getViewRoot();
            component = findComponent(root, id);
        }

        return component;
    }

    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId())) {
            return base;
        }

        UIComponent kid = null;
        UIComponent result = null;
        Iterator kids = base.getFacetsAndChildren();
        while (kids.hasNext() && (result == null)) {
            kid = (UIComponent) kids.next();
            if (id.equals(kid.getId())) {
                result = kid;
                break;
            }
            result = findComponent(kid, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }

}
