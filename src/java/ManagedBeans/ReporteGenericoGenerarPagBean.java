package ManagedBeans;


import Entidades.Reporte.Parametro;
import Entidades.Reporte.ReporteGenerico;
import Entidades.Reporte.TipoParametro;
import ManagedBeans.util.MensajeBean;
import RN.ReporteGenericoRNLocal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;


@Named(value = "reporteGenericoGenerarPagBean")
@RequestScoped
public class ReporteGenericoGenerarPagBean {

    private ReporteGenerico reporteGenerico;
    private List<Object[]> lstRs;
    int posParametro = 0;
    
    @Inject
    private ReporteGenericoLstBean reporteGenericoLstBean;
    
    @Inject
    private ReporteGenericoGenerarLstBean reporteGenericoGenerarLstBean;
    
    @Inject
    private MensajeBean mensajeBean;

    @EJB
    private ReporteGenericoRNLocal reporteGenericoRNLocal;
    
    public ReporteGenericoGenerarPagBean() {
        reporteGenerico = new ReporteGenerico();
    }

    public int getPosParametro() {
        posParametro++;
        System.out.println("PosParametro: " + posParametro);
        return posParametro;
    }

    

    public ReporteGenericoGenerarLstBean getReporteGenericoGenerarLstBean() {
        return reporteGenericoGenerarLstBean;
    }

    public void setReporteGenericoGenerarLstBean(ReporteGenericoGenerarLstBean reporteGenericoGenerarLstBean) {
        this.reporteGenericoGenerarLstBean = reporteGenericoGenerarLstBean;
    }
    
    public List<Object[]> getLstRs() {
        return lstRs;
    }

    public void setLstRs(List<Object[]> lstRs) {
        this.lstRs = lstRs;
    }
    
    public ReporteGenerico getReporteGenerico() {
        return reporteGenerico;
    }

    public void setReporteGenerico(ReporteGenerico reporteGenerico) {
        this.reporteGenerico = reporteGenerico;
    }
    
    public MensajeBean getMensajeBean() {
        return mensajeBean;
    }

    public void setMensajeBean(MensajeBean mensajeBean) {
        this.mensajeBean = mensajeBean;
    }

    public ReporteGenericoLstBean getReporteGenericoLstBean() {
        return reporteGenericoLstBean;
    }

    public void setReporteGenericoLstBean(ReporteGenericoLstBean reporteGenericoLstBean) {
        this.reporteGenericoLstBean = reporteGenericoLstBean;
    }
    
    public void generarReporte(){
        try {
            //this.reporteGenericoLstBean.findLikeByReporteGenerico(this.getCadena());
            
            if(this.getReporteGenerico().getId()==null){
                throw new Exception("No selecciono el reporte");
            }//fin if
            
                        
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            
            //String txtProperty = request.getParameter("ReporteGenericoViewForm:itParametro0");
            
            List <String> componentes = Collections.list(request.getParameterNames());

            if(componentes != null){
                this.getReporteGenericoGenerarLstBean().setLstValorParametro(new ArrayList<Object>());
            }//fin if
            
            for (String nombreComponente : componentes) {
               if(nombreComponente.lastIndexOf("itParametro")>0){
                   this.getReporteGenericoGenerarLstBean().getLstValorParametro().add(String.valueOf(request.getParameter(nombreComponente)).trim());
                    System.out.println("parametros valor: " + request.getParameter(nombreComponente));
                    
               }//fin if
            }//fin for
            
            
            

            //recuperar los datos
            this.getReporteGenericoGenerarLstBean().setLstRs(
                reporteGenericoRNLocal.findLstConsultaReporteGenerico(this.getReporteGenerico().getId(), 
                        this.getReporteGenericoGenerarLstBean().getLstValorParametro()));
            
            this.getReporteGenericoGenerarLstBean().createDynamicColumns(
                    this.getReporteGenerico().getColumnas());
            
            
            /*for(Object[] objeto : this.getReporteGenericoGenerarLstBean().getLstRs()){
                
            }//fin for
           
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
        reporteGenericoGenerarLstBean.setLstRs(new ArrayList<Object[]>());
        return "reporteGenericoGenerar.xhtml?faces-redirect=true";
    }
    
    public void parametroValue(ValueChangeEvent e){
        System.out.println(" dato " + e.getNewValue());
    }//fin
}
