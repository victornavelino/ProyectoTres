/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans.util;

import Entidades.Base.Archivo;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author franco
 */
@Named("imagePreviaBean")
@SessionScoped
public class ImagePreviaBean implements Serializable{

    private StreamedContent image;

    private Archivo archivo;

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    @PostConstruct
    public void init() {

        if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {

            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            image = new DefaultStreamedContent();
        } else {

            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            if (this.getArchivo() != null) {
                byte[] imagen = this.getArchivo().getContenidoArchivo();
                if (imagen != null) {
                    image = new DefaultStreamedContent(new ByteArrayInputStream(imagen));
                }//fin if
            }
        }
    }

    public StreamedContent getImage() {
        return image;
    }

}
