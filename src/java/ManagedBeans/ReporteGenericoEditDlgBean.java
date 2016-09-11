package ManagedBeans;


import Entidades.Reporte.Parametro;
import Entidades.Reporte.ReporteGenerico;
import ManagedBeans.util.MensajeBean;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;


@Named(value = "reporteGenericoEditDlgBean")
@RequestScoped
public class ReporteGenericoEditDlgBean {

    private String textBoton;
    private char accionBtn;
    private Boolean campoEditable;
    private String icono;
    @Inject
    private ReporteGenericoBean reporteGenericoBean;
    
    @Inject
    private ReporteGenericoLstBean reporteGenericoLstBean;
    
    @Inject
    private ReporteGenericoParametroBean reporteGenericoParametroBean;
    
    @Inject
    private ReporteGenericoParametroLstBean reporteGenericoParametroLstBean;
    
    @Inject
    private ReporteGenericoTipoParametroLstBean reporteGenericoTipoParametroLstBean;
    
    @Inject
    private MensajeBean mensajeBean;

    public ReporteGenericoEditDlgBean() {
    }

    public ReporteGenericoTipoParametroLstBean getReporteGenericoTipoParametroLstBean() {
        return reporteGenericoTipoParametroLstBean;
    }

    public void setReporteGenericoTipoParametroLstBean(ReporteGenericoTipoParametroLstBean reporteGenericoTipoParametroLstBean) {
        this.reporteGenericoTipoParametroLstBean = reporteGenericoTipoParametroLstBean;
    }
    
    public ReporteGenericoParametroBean getReporteGenericoParametroBean() {
        return reporteGenericoParametroBean;
    }

    public void setReporteGenericoParametroBean(ReporteGenericoParametroBean reporteGenericoParametroBean) {
        this.reporteGenericoParametroBean = reporteGenericoParametroBean;
    }

    public ReporteGenericoParametroLstBean getReporteGenericoParametroLstBean() {
        return reporteGenericoParametroLstBean;
    }

    public void setReporteGenericoParametroLstBean(ReporteGenericoParametroLstBean reporteGenericoParametroLstBean) {
        this.reporteGenericoParametroLstBean = reporteGenericoParametroLstBean;
    }
    
    public ReporteGenericoLstBean getReporteGenericoLstBean() {
        return reporteGenericoLstBean;
    }

    public void setReporteGenericoLstBean(ReporteGenericoLstBean reporteGenericoLstBean) {
        this.reporteGenericoLstBean = reporteGenericoLstBean;
    }
    

    public MensajeBean getMensajeBean() {
        return mensajeBean;
    }

    public void setMensajeBean(MensajeBean mensajeBean) {
        this.mensajeBean = mensajeBean;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public ReporteGenericoBean getReporteGenericoBean() {
        return reporteGenericoBean;
    }

    public void setReporteGenericoBean(ReporteGenericoBean reporteGenericoBean) {
        this.reporteGenericoBean = reporteGenericoBean;
    }

    public Boolean isCampoEditable() {
        return campoEditable;
    }

    public void setCampoEditable(Boolean campoEditable) {
        this.campoEditable = campoEditable;
    }

    public String getTextBoton() {
        return textBoton;
    }

    public void setTextBoton(String textBoton) {
        this.textBoton = textBoton;
    }

    public char getAccionBtn() {
        return accionBtn;
    }

    public void setAccionBtn(char accionBtn) {
        this.accionBtn = accionBtn;
    }

    public void setBtnSelect(ActionEvent e) {
        CommandButton btnSelect = (CommandButton) e.getSource();
        
        //cargar los tipos de parametros
        
        this.getReporteGenericoTipoParametroLstBean().cargarSITipo();
        this.getReporteGenericoParametroLstBean().setLstParametros(new ArrayList<Parametro>());
        
        if (btnSelect.getId().equals("cbReporteGenericoCreate")) {
            
            this.setAccionBtn('C');
            this.setTextBoton("Guardar");
            this.getReporteGenericoBean().setReporteGenerico(new ReporteGenerico());
            RequestContext.getCurrentInstance().update(":ReporteGenericoViewForm:dReporteGenericoEditDlg");
        } else if (btnSelect.getId().equals("cbReporteGenericoDelete")) {
            this.setAccionBtn('D');
            this.setCampoEditable(Boolean.FALSE);
            this.setTextBoton("Eliminar");
        } else if (btnSelect.getId().equals("cbReporteGenericoEdit")) {
            this.setAccionBtn('M');
            this.setTextBoton("Modificar");
        }
        
        RequestContext.getCurrentInstance().execute("PF('dlgReporteGenericoEditDlg').show();");
        this.getMensajeBean().setPagCerrar("");
    }

    public void actionBtn() {
        switch (this.getAccionBtn()) {
            case 'C':
                this.create();
                break;
            case 'M':
                this.edit();
                break;
            case 'D':
                this.delete();
                break;
        }
    }

    public void create() {
        String mensaje = "";
        try {
            
            //parametros
            
            if(!this.getReporteGenericoParametroLstBean().getLstParametros().isEmpty()){
                for(Parametro para : this.getReporteGenericoParametroLstBean().getLstParametros()){
                    para.setReporteGenerico(this.getReporteGenericoBean().getReporteGenerico());
                }//fin for
            }//fin if
  
            this.getReporteGenericoBean().getReporteGenerico().setLstParametros(
                    this.getReporteGenericoParametroLstBean().getLstParametros());
            
            this.getReporteGenericoBean().create();
            

            
            if(this.getReporteGenericoLstBean().getLstReporteGenerico() == null){
                this.getReporteGenericoLstBean().setLstReporteGenerico(new ArrayList<ReporteGenerico>());
            }//fin if
            
            //actualizar la tabla
            this.getReporteGenericoLstBean().getLstReporteGenerico().add(this.getReporteGenericoBean().getReporteGenerico());
            
            mensaje = "Los datos fueron guardados";
            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dtReporteGenerico");
            this.getMensajeBean().setTipoIcono('I');
            this.getMensajeBean().setPagCerrar("PF('dlgReporteGenericoEditDlg').hide();");
        } catch (Exception ex) {
            this.getMensajeBean().setTipoIcono('E');
            mensaje = "Error: "+ex.getMessage();
        } finally {
            this.getMensajeBean().setMensaje(mensaje);
            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dMensaje");
            RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show();");
        }
    }

    public void edit() {
        String mensaje = "";
        try {
            
            System.out.println("Entro al edit");
            //parametros
            
            if(!this.getReporteGenericoParametroLstBean().getLstParametros().isEmpty()){
                for(Parametro para : this.getReporteGenericoParametroLstBean().getLstParametros()){
                    para.setReporteGenerico(this.getReporteGenericoBean().getReporteGenerico());
                }//fin for
            }//fin if
  
            this.getReporteGenericoBean().getReporteGenerico().setLstParametros(
                    this.getReporteGenericoParametroLstBean().getLstParametros());
            
            this.getReporteGenericoBean().edit();
            
            //actualizar la tabla
            int pos = this.getReporteGenericoLstBean().getLstReporteGenerico().indexOf(this.getReporteGenericoBean().getReporteGenerico());
            this.getReporteGenericoLstBean().getLstReporteGenerico().remove(this.getReporteGenericoBean().getReporteGenerico());
            this.getReporteGenericoLstBean().getLstReporteGenerico().add(pos, this.getReporteGenericoBean().getReporteGenerico());
            
            mensaje = "Los datos fueron modificados";
            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dtReporteGenerico");
            this.getMensajeBean().setTipoIcono('I');
            this.getMensajeBean().setPagCerrar("PF('dlgReporteGenericoEditDlg').hide();");

        } catch (Exception ex) {
            this.getMensajeBean().setTipoIcono('E');
            mensaje = "Error: " + ex.getMessage();
        } finally {
            this.getMensajeBean().setMensaje(mensaje);
            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dMensaje");
            RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show();");
        }
    }

    public void delete() {
        String mensaje = "";
        try {
            
            //parametros
            
            if(!this.getReporteGenericoParametroLstBean().getLstParametros().isEmpty()){
                for(Parametro para : this.getReporteGenericoParametroLstBean().getLstParametros()){
                    para.setReporteGenerico(this.getReporteGenericoBean().getReporteGenerico());
                }//fin for
            }//fin if
  
            this.getReporteGenericoBean().getReporteGenerico().setLstParametros(
                    this.getReporteGenericoParametroLstBean().getLstParametros());
            
            this.getReporteGenericoBean().remove();

           
            //actualizar la tabla
            this.getReporteGenericoLstBean().getLstReporteGenerico().remove(this.getReporteGenericoBean().getReporteGenerico());
            
           
            
            
            mensaje = "Los datos fueron borrados";
            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dtReporteGenerico");
            this.getMensajeBean().setTipoIcono('I');
            this.getMensajeBean().setPagCerrar("PF('dlgReporteGenericoEditDlg').hide();");
        } catch (Exception ex) {
            this.getMensajeBean().setTipoIcono('E');
            mensaje = "Error: " + ex.getMessage();
        } finally {
            this.getMensajeBean().setMensaje(mensaje);
            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dMensaje");
            RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show();");
        }
    }
    
     public void agregarParametro(){
        String mensaje = "";
        try {
            
            if(this.getReporteGenericoParametroBean().getParametro().getNombre().isEmpty()){
                throw new Exception("Debe ingresar el nombre");
            }

            if (this.getReporteGenericoParametroBean().getParametro().getTipoParametro() == null) {
                throw new Exception("Debe seleccionar el tipo");
            }

            
            //verificar si ya esta cargado 
            for(Parametro par : this.getReporteGenericoParametroLstBean().getLstParametros()){
                
                if(this.getReporteGenericoParametroBean().getParametro().getNombre().trim().toLowerCase().equals(
                        par.getNombre().trim().toLowerCase())){
                    throw new Exception("Ya existe un parámetro con el nombre ingresado");
                }//fin if
            }//fin for
            
            this.getReporteGenericoParametroLstBean().getLstParametros().add(this.getReporteGenericoParametroBean().getParametro());

            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dtParametrosRGD");


        } catch (Exception ex) {
            this.getMensajeBean().setTipoIcono('E');
            mensaje = "Error: " + ex.getMessage();
            this.getMensajeBean().setMensaje(mensaje);
            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dMensaje");
            RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show();");
        } 
        
        
        
    }//fin agregarArticulo
    
    public void quitarParametro(Parametro parametro){
        

        int tamanio = this.getReporteGenericoParametroLstBean().getLstParametros().size();

        for(int i=0; i < tamanio; i++){
            
            if(this.getReporteGenericoParametroLstBean().getLstParametros().
                    get(i).equals(parametro)){
                
                this.getReporteGenericoParametroLstBean().getLstParametros().remove(i);
                i=tamanio;
            }//fin if
        }//fin for
                
        RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dtParametrosRGD");
    }//fin quitarArticulo

}
