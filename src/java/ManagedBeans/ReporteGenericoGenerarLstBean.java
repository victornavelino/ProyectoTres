package ManagedBeans;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;
import org.primefaces.component.inputtext.InputText;


@Named(value = "reporteGenericoGenerarLstBean")
@SessionScoped
public class ReporteGenericoGenerarLstBean implements Serializable{

    
    private List<Object[]> lstRs;
    
    private List<ColumnModel> columns = new ArrayList<ColumnModel>();  
    
    private List<Object> lstValorParametro;
    
  
    public ReporteGenericoGenerarLstBean() {

    }

    public List<Object> getLstValorParametro() {
        return lstValorParametro;
    }

    public void setLstValorParametro(List<Object> lstValorParametro) {
        this.lstValorParametro = lstValorParametro;
    }
   
    
    
    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }
    
    public List<Object[]> getLstRs() {
        return lstRs;
    }

    public void setLstRs(List<Object[]> lstRs) {
        this.lstRs = lstRs;
    }
    
    public void createDynamicColumns(String columnas) {  
        String[] columnKeys = columnas.split(",");  
        columns.clear();  
  
        int pos = 0;
        for (String columnKey : columnKeys) {  
            //String key = columnKey.trim();  
  
            //if(VALID_COLUMN_KEYS.contains(key)){  
            columns.add(new ColumnModel(columnKey.toUpperCase(), pos));  
            pos++;
            //}  
        }  
    }
    
    static public class ColumnModel implements Serializable {
 
        private String header;
        private int posicion;
 
        public ColumnModel(String header, int posicion) {
            this.header = header;
            this.posicion = posicion;
        }
 
        public String getHeader() {
            return header;
        }
 
        public int getPosicion() {
            return posicion;
        }
    }
    
    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet hoja = wb.getSheetAt(0);
        
        HSSFRow fila = hoja.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
        for(int i=0; i < fila.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = fila.getCell(i);
             
            cell.setCellStyle(cellStyle);
        }
    }
    
    public void preProcessPDF(Object document) {
      Document pdf = (Document) document;
      pdf.setPageSize(PageSize.LETTER.rotate());
      
      //pdf.setPageSize(PageSize.A4.rotate());
    }
    
    public void postProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        pdf.open();
        

        Chunk titulo = new Chunk(formatter.format(new Date()), FontFactory.getFont(FontFactory.COURIER, 20, Font.UNDERLINE, Color.BLACK));

            
            //Anexamos el texto a un objeto Pharagraph
            //Paragraph fecha=new Paragraph(formatter.format(new Date()),FontFactory.getFont(FontFactory.TIMES_ROMAN,12,Font.BOLD, Color.BLACK));
                    
            //Escribimos sobre el
            pdf.add(titulo);
            pdf.add(Chunk.NEWLINE);
            
            
            
 
        /*ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";
         
        pdf.add(Image.getInstance(logo));*/
    }

}
