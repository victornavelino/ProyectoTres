/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Persona.Telefono;
import Entidades.Persona.TipoTelefono;
import Facades.TipoTelefonoFacade;
import RN.TelefonoRNLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

/**
 *
 * @author vouilloz
 */
@Named("listadoTelefonosBean")
@SessionScoped
public class ListadoTelefonosBean implements Serializable {

    @EJB
    private TelefonoRNLocal telefonosRNLocal;
    @EJB
    private TipoTelefonoFacade tipoTelefonoFacade;
    private List<Telefono> lstTelefonos;
    private int iTipoBoton;
    private List<SelectItem> listaTipoTelefonos;
    private Telefono telefono;
    private DataTable dtTelefono;
    private TipoTelefono tipotelefono;
    private CommandButton cbActionTelefonos;
    CommandButton btnSelect;

    public TelefonoRNLocal getTelefonosRNLocal() {
        return telefonosRNLocal;
    }

    public void setTelefonosRNLocal(TelefonoRNLocal telefonosRNLocal) {
        this.telefonosRNLocal = telefonosRNLocal;
    }

    public TipoTelefonoFacade getTipoTelefonoFacade() {
        return tipoTelefonoFacade;
    }

    public void setTipoTelefonoFacade(TipoTelefonoFacade tipoTelefonoFacade) {
        this.tipoTelefonoFacade = tipoTelefonoFacade;
    }

    public int getiTipoBoton() {
        return iTipoBoton;
    }

    public void setiTipoBoton(int iTipoBoton) {
        this.iTipoBoton = iTipoBoton;
    }

    /**
     * Creates a new instance of listadoTelefonosBean
     */
    public ListadoTelefonosBean() {

    }

    @PostConstruct
    public void init() {
        this.setLstTelefonos(new ArrayList<Telefono>());
        //cargarTelefonos();
        cargarTipoTelefonos();
        telefono = new Telefono();
        // lstTelefonos.clear();
    }

    public List<Telefono> getLstTelefonos() {
        //System.out.println("get telefono: " + lstTelefonos.size());
        return lstTelefonos;
    }

    public void setLstTelefonos(List<Telefono> lstTelefonos) {
        this.lstTelefonos = lstTelefonos;
    }

    public List<SelectItem> getListaTipoTelefonos() {
        return listaTipoTelefonos;
    }

    public void setListaTipoTelefonos(List<SelectItem> listaTipoTelefonos) {
        this.listaTipoTelefonos = listaTipoTelefonos;
    }

    public DataTable getDtTelefono() {
        return dtTelefono;
    }

    public void setDtTelefono(DataTable dtTelefono) {
        this.dtTelefono = dtTelefono;
    }

    public TipoTelefono getTipotelefono() {
        return tipotelefono;
    }

    public void setTipotelefono(TipoTelefono tipotelefono) {
        this.tipotelefono = tipotelefono;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public CommandButton getCbActionTelefonos() {
        return cbActionTelefonos;
    }

    public void setCbActionTelefonos(CommandButton cbActionTelefonos) {
        this.cbActionTelefonos = cbActionTelefonos;
    }

    private void cargarTelefonos() {
        try {
            this.setLstTelefonos(telefonosRNLocal.findAll());

        } catch (Exception ex) {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error al cargar los telefonos: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin cargar Telefonos

    public void cargarTipoTelefonos() {
        listaTipoTelefonos = new ArrayList<>();
        for (TipoTelefono tt : tipoTelefonoFacade.findAll()) {
            listaTipoTelefonos.add(new SelectItem(tt, tt.toString()));
        }
    }

    public void prepararDialogTelefonos(ActionEvent e) {

        //System.out.println("Entro al evento definirActionBoton");
        //System.out.println("boton : " + e);
        //System.out.println("boton 2 : " + e.getSource());
        btnSelect = (CommandButton) e.getSource();

        System.out.println("boton id: " + btnSelect.getId());
        //this.getCbAction().setDisabled(false);
        if (btnSelect.getId().equals("btnEdit")) {
            this.getCbActionTelefonos().setValue("Editar");
            this.setiTipoBoton(1);
        }
        if (btnSelect.getId().equals("btnDelete")) {
            this.getCbActionTelefonos().setValue("Eliminar");
            this.setiTipoBoton(2);
        }

        if (btnSelect.getId().equals("btnCreateTelefonosDocentes")) {
            this.setTelefono(new Telefono());
            this.getCbActionTelefonos().setValue("Guardar");
            this.setiTipoBoton(0);
        }//fin if

        if (btnSelect.getId().equals("btnCreateTelefonosProfesional")) {
            this.setTelefono(new Telefono());
            this.getCbActionTelefonos().setValue("Guardar");
            this.setiTipoBoton(0);
            System.out.println("OKOKOK");
        }//fin if
                if (btnSelect.getId().equals("btnCreateTelefonosProfesionalUpdate")) {
            this.setTelefono(new Telefono());
            this.getCbActionTelefonos().setValue("Guardar");
            this.setiTipoBoton(0);
            System.out.println("OKOKOK");
        }//fin if

        //System.out.println("termino el definir: " + this.getPersona());
    }

    public void actionButton() {

        // System.out.println("Entro al actionButton TipoBoton = "+this.getListadoTelefonosBean().getiTipoBoton());
        //Integer iBoton = this.getListadoTelefonosBean().getiTipoBoton();
        //System.out.println("boton select: " + iBoton);
        //System.out.println("Entro al actionButton dsddsdsd"+this.tipoOperacionTelefono);
        //0 guardar
        //1 modificar
        //2 eliminar
        switch (this.iTipoBoton) {
            case 0:
                System.out.println("Case cargar Tabla Telefonos");
                cargarTablaTelefonos(this.iTipoBoton);
                break;
            case 1:
                // modificar();
                break;
            case 2:
                //eliminar();
                break;
            case 3:
                cargarTablaTelefonos(this.iTipoBoton);
                break;
        }//fin switch

    }//fin actionButton

    public void cargarTablaTelefonos(int tipoBoton) {

        //context.update(":frmPri:pTelefonos");
        if (lstTelefonos == null) {
            lstTelefonos = new ArrayList<>();
        }

        telefono.setTipoTelefono(tipotelefono);
        System.out.println("Telegonods_ " + telefono);
        lstTelefonos.add(telefono);
        //this.setTelefonios(new Telefono());
        System.out.println("Ejecuta new telefono");
        //telefono.setNumero("");
        System.out.println(btnSelect.getId());
        if (btnSelect.getId().equals("btnCreateTelefonosProfesional")) {
            System.out.println("guardar btnCreateTelefonosMedicos" + this.getLstTelefonos());
            //Entra al if si es llamado por Docente.xhtml
            RequestContext.getCurrentInstance().update("MedicoCreateForm:dtTelefonosProf");
            RequestContext.getCurrentInstance().execute("PF('dlgTel').hide();");

        }
        if (btnSelect.getId().equals("btnCreateTelefonosProfesionalUpdate")) {
            System.out.println("guardar btnCreateTelefonosMedicos" + this.getLstTelefonos());
            //Entra al if si es llamado por Docente.xhtml
            RequestContext.getCurrentInstance().update("MedicoEditForm:dtTelefonosProf");
            RequestContext.getCurrentInstance().execute("PF('dlgTel').hide();");
        }

        //System.out.println("Entro al cargar telefonos: " + telefono.getNumero());
        // this.getListadoTelefonosBean().getLstTelefonos().add(telefono);
    }

    //ESTE METODO NO LO USO POR QUE REQUIERE QUE LA TABLA HAGA UN BINDING A UN DATATABLE EN EL BEAN Y ESO 
    //GENERA CONFLICTO CUANDO VARIAS TABLAS HACEN BINDING AL MISMO DATATABLE EN EL BEAN
    public void quitarTelefono() {
        Telefono tel = (Telefono) this.getDtTelefono().getRowData();
        lstTelefonos.remove(tel);
    }

    //ESTE ES EL METODO USADO
    public void borrarTelefono(Telefono telefono) {
        this.lstTelefonos.remove(telefono);
    }
}
