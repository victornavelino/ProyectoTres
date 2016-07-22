/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Entidades.Persona.CorreoElectronico;
import RN.CorreoElectronicoRNLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

/**
 *
 * @author vouilloz
 */
@ManagedBean(name = "listadoEmailBean")
@SessionScoped
public class ListadoEmailBean implements Serializable{

    @EJB
    private CorreoElectronicoRNLocal listadoEmailRNLocal;
    private List<CorreoElectronico> lstCorreoElectronico;
    private int iTipoBoton;
    private CommandButton CbActionEmail;
    private CorreoElectronico correoElectronico;
    private DataTable dtEmail;

    CommandButton btnSelect;

    public List<CorreoElectronico> getLstCorreoElectronico() {
        return lstCorreoElectronico;
    }

    public void setLstCorreoElectronico(List<CorreoElectronico> lstCorreoElectronico) {
        this.lstCorreoElectronico = lstCorreoElectronico;
    }

    public int getiTipoBoton() {
        return iTipoBoton;
    }

    public void setiTipoBoton(int iTipoBoton) {
        this.iTipoBoton = iTipoBoton;
    }

    public CommandButton getCbActionEmail() {
        return CbActionEmail;
    }

    public void setCbActionEmail(CommandButton CbActionEmail) {
        this.CbActionEmail = CbActionEmail;
    }

    public DataTable getDtEmail() {
        return dtEmail;
    }

    public void setDtEmail(DataTable dtEmail) {
        this.dtEmail = dtEmail;
    }

    public CorreoElectronico getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(CorreoElectronico correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Creates a new instance of ListadoEmailBean
     */
    public ListadoEmailBean() {
    }

    @PostConstruct
    private void init() {
        this.setLstCorreoElectronico(new ArrayList<CorreoElectronico>());
        cargarCorreosElectronicos();
        correoElectronico = new CorreoElectronico();
        lstCorreoElectronico.clear();
    }

    private void cargarCorreosElectronicos() {
        try {
            this.setLstCorreoElectronico(listadoEmailRNLocal.findAll());

        } catch (Exception ex) {

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error al cargar los telefonos: " + ex,
                    null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

    }//fin cargar Correos

    public void definirActionBotonEmail(ActionEvent e) {

        //System.out.println("Entro al evento definirActionBotonEmail");
        //System.out.println("boton : " + e);
        //System.out.println("boton 2 : " + e.getSource());
        btnSelect = (CommandButton) e.getSource();

        //System.out.println("boton id: " + btnSelect.getId());
        //this.getCbAction().setDisabled(false);
        if (btnSelect.getId().equals("btnEdit")) {
            this.getCbActionEmail().setValue("Editar");
            this.setiTipoBoton(1);
        }
        if (btnSelect.getId().equals("btnDelete")) {
            this.getCbActionEmail().setValue("Eliminar");
            this.setiTipoBoton(2);
        }

        if (btnSelect.getId().equals("btnCreateEmailDocentes") || (btnSelect.getId().equals("btnCreateEmailAlumnos"))) {
            //System.out.println("Entro al if : " + this.getPersona());
            //   this.getCbAction().setValue("Guardar");
            this.setCorreoElectronico(new CorreoElectronico());
            this.setiTipoBoton(0);

        }//fin if

        //System.out.println("termino el definir: " + this.getPersona());
    }

    //INICIO AREA E MAIL
    public void actionButtonEmail() {

        //System.out.println("Entro al actionButtonEmail" + this.getListadoEmailBean().getiTipoBoton());
        //Integer iBoton = this.getListadoEmailBean().getiTipoBoton();
        //System.out.println("boton select: " + iBoton);
        //0 guardar
        //1 modificar
        //2 eliminar
        System.out.println("tipo boton" + iTipoBoton);
        switch (this.iTipoBoton) {
            case 0:
                cargarTablaEmail();
                break;
            case 1:
                // modificar();
                break;
            case 2:
                //eliminar();
                break;
        }//fin switch

    }//fin actionButton

    public void cargarTablaEmail() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (lstCorreoElectronico == null) {
            lstCorreoElectronico = new ArrayList<>();
        }
        lstCorreoElectronico.add(correoElectronico);
        if (btnSelect.getId().equals("btnCreateEmailDocentes")) {
            //Entra al if si es llamado por Docente.xhtml
            context.update("frmPri:dtEmailDocentes");
            context.execute("PF('dlgEmail').hide();");

        }
        if (btnSelect.getId().equals("btnCreateEmailAlumnos")) {
            //Entra al else si es llamado por Alumno.xhtml
            context.update("frmPri:dtEmailAlumnos");
            context.execute("PF('dlgEmail').hide();");
        }
    }

    //ESTE METODO NO LO USO POR QUE REQUIERE QUE LA TABLA HAGA UN BINDING A UN DATATABLE EN EL BEAN Y ESO 
    //GENERA CONFLICTO CUANDO VARIAS TABLAS HACEN BINDING AL MISMO DATATABLE EN EL BEAN
    public void quitarEmail() {
        CorreoElectronico correo = (CorreoElectronico) this.getDtEmail().getRowData();
        lstCorreoElectronico.remove(correo);
    }

    //ESTE ES EL METODO USADO
    public void borrarEmail(CorreoElectronico correo) {
        this.lstCorreoElectronico.remove(correo);
    }
}
