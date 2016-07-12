/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author franco
 */
public class Importador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        importar();
    }

    public static void importar() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                boolean flag = true;
                InputStream in = null;
                in = new FileInputStream(selectedFile);

                Workbook workbook = Workbook.getWorkbook(in);
                Sheet sheet = workbook.getSheet(0);
                for (int i = 1; i < sheet.getRows(); i++) {
                    Cell celda1 = sheet.getCell(0, i);
                    celda1.getContents();
                    Cell celda2 = sheet.getCell(1, i);
                    if (!celda2.getContents().equals("")) {
                    }
                    Cell celda3 = sheet.getCell(2, i);
                    if (!celda3.getContents().equals("")) {
                    }
                    Cell celda4 = sheet.getCell(3, i);
                    if (!celda4.getContents().equals("")) {
                    }
                    Cell celda5 = sheet.getCell(4, i);
                    if (!celda5.getContents().equals("")) {
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Importador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Importador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                Logger.getLogger(Importador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public String quitarComas(String entrada) {
        String aux = entrada.replace("" + "$" + "", "");
        String d = aux.replace(".", "");
        String e = d.replace(",", ".");
        //String f=e.trim();
        //if(f.contains("$")){

        String aux1 = e.replace('"', ' ');
        String aux2 = aux1.trim();
        //  return aux2;
        //}

        return aux2;
    }
}
