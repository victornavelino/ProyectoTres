/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;


import ManagedBeans.util.MensajeBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlInputText;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;


/**
 *
 * @author diego
 */
@Named(value = "reporteGenericoFindDlgBean")
@RequestScoped
public class ReporteGenericoFindDlgBean {

    private int pantalla;
    
    @Inject
    private ReporteGenericoBean reporteGenericoBean;
    
    @Inject
    private ReporteGenericoLstBean reporteGenericoLstBean;
    
    @Inject
    private ReporteGenericoGenerarPagBean reporteGenericoGenerarPagBean;
    
    @Inject
    private ReporteGenericoGenerarLstBean reporteGenericoGenerarLstBean;
    
    @Inject
    private MensajeBean mensajeBean;
    
    public ReporteGenericoFindDlgBean() {
    }

    public ReporteGenericoGenerarLstBean getReporteGenericoGenerarLstBean() {
        return reporteGenericoGenerarLstBean;
    }

    public void setReporteGenericoGenerarLstBean(ReporteGenericoGenerarLstBean reporteGenericoGenerarLstBean) {
        this.reporteGenericoGenerarLstBean = reporteGenericoGenerarLstBean;
    }
    
    public ReporteGenericoGenerarPagBean getReporteGenericoGenerarPagBean() {
        return reporteGenericoGenerarPagBean;
    }

    public void setReporteGenericoGenerarPagBean(ReporteGenericoGenerarPagBean reporteGenericoGenerarPagBean) {
        this.reporteGenericoGenerarPagBean = reporteGenericoGenerarPagBean;
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
        
    public int getPantalla() {
        return pantalla;
    }

    public void setPantalla(int pantalla) {
        this.pantalla = pantalla;
    }
    
    public ReporteGenericoBean getReporteGenericoBean() {
        return reporteGenericoBean;
    }

    public void setReporteGenericoBean(ReporteGenericoBean reporteGenericoBean) {
        this.reporteGenericoBean = reporteGenericoBean;
    }
    
    
    public void entrar(int pantalla){
        this.setPantalla(pantalla);
        
        switch(pantalla){
            case 1: //Pantalla generar reporte
                RequestContext.getCurrentInstance().update("ReporteGenericoGenerarViewForm:dFindReporteGenerico");
                break;
            
        }//fin switch
        
        
        RequestContext.getCurrentInstance().execute("PF('dlgFindReporteGenerico').show()");
    }
    
    public void aceptar(){
        
        System.out.println("Aceptar: " + pantalla);
        try {
            
            if (this.getReporteGenericoBean().getReporteGenerico().getId() == null) {
                throw new Exception("Debe seleccionar el reporte");
            }//fin if
            

            switch (pantalla) {
                case 1: //Pantalla generar reporte
                 
                    System.out.println("Entro");
                    this.getReporteGenericoGenerarPagBean().setReporteGenerico(this.getReporteGenericoBean().getReporteGenerico());

                    RequestContext.getCurrentInstance().update("ReporteGenericoGenerarViewForm:pgReporteRGG");
                    RequestContext.getCurrentInstance().update("ReporteGenericoGenerarViewForm:pgParametrosRGG");
                    

                    
                    break;
            }//fin switch

            RequestContext.getCurrentInstance().execute("PF('dlgFindReporteGenerico').hide();");


        } catch (Exception ex) {
          
           this.getMensajeBean().setTipoIcono('E');
           this.getMensajeBean().setMensaje(ex.getMessage());
           
           switch(pantalla){
               case 1: //Pantalla generar reporte
                   RequestContext.getCurrentInstance().update("ReporteGenericoGenerarViewForm:dMensaje");
                   break;
           }//fin switch
           
           RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show();");
        }

        
        /*RequestContext.getCurrentInstance().update("frmAdmin:dMensaje");
            RequestContext.getCurrentInstance().execute("dlgMensaje.show()");*/
    }//fin salir
    
}
