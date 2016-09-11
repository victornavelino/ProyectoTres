package ManagedBeans;

import ManagedBeans.util.MensajeBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;


@Named(value = "reporteGenericoPagBean")
@RequestScoped
public class ReporteGenericoPagBean {

    private String cadena;
    @Inject
    private ReporteGenericoLstBean reporteGenericoLstBean;
    
    @Inject
    private MensajeBean mensajeBean;

    public ReporteGenericoPagBean() {
    }

    public MensajeBean getMensajeBean() {
        return mensajeBean;
    }

    public void setMensajeBean(MensajeBean mensajeBean) {
        this.mensajeBean = mensajeBean;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public ReporteGenericoLstBean getReporteGenericoLstBean() {
        return reporteGenericoLstBean;
    }

    public void setReporteGenericoLstBean(ReporteGenericoLstBean reporteGenericoLstBean) {
        this.reporteGenericoLstBean = reporteGenericoLstBean;
    }

    public void buscarByReporteGenerico() {
        try {
            this.reporteGenericoLstBean.findLikeByReporteGenerico(this.getCadena());
            
           
            if(this.getReporteGenericoLstBean().getLstReporteGenerico().isEmpty()){

                this.getMensajeBean().setTipoIcono('I');
                this.getMensajeBean().setPagCerrar("");
                this.getMensajeBean().setMensaje("No se encontraron coincidencias");
                RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dMensaje");
                RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show();");
                
            }
        } catch (Exception ex) {
            this.getMensajeBean().setTipoIcono('E');
            this.getMensajeBean().setPagCerrar("");
            this.getMensajeBean().setMensaje("Se ha producido un error al buscar los datos");
            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dMensaje");
            RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show();");
        }

    }
    
    public void generarReporte(){
        try {
            //this.reporteGenericoLstBean.findLikeByReporteGenerico(this.getCadena());
            
           
            /*if(this.getReporteGenericoLstBean().getLstReporteGenerico().isEmpty()){

                this.getMensajeBean().setTipoIcono('I');
                this.getMensajeBean().setPagCerrar("");
                this.getMensajeBean().setMensaje("No se encontraron coincidencias");
                RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dMensaje");
                RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show();");
                
            }*/
        } catch (Exception ex) {
            this.getMensajeBean().setTipoIcono('E');
            this.getMensajeBean().setPagCerrar("");
            this.getMensajeBean().setMensaje("Error: " + ex.getMessage());
            RequestContext.getCurrentInstance().update("ReporteGenericoViewForm:dMensaje");
            RequestContext.getCurrentInstance().execute("PF('dlgMensaje').show();");
        }
    }//fin generarReporte
    
    public String entrar(){
        this.getReporteGenericoLstBean().setLstReporteGenerico(null);
        return "reporteGenerico.xhtml?faces-redirect=true";
    }
}
