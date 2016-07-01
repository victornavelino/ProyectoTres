/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conversores;

/**
 * Clase usada para convertir un tipo string a una clase de cualquier tipo
 * dependiendo el uso
 *
 * @author hugo
 */
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "ConversorGenerico")
public class ConversorGenerico implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent componente, String valor) {
        if (valor != null) {
            return this.getAttributesFrom(componente).get(valor);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent componente, Object valor) {

        if (valor != null && !"".equals(valor)) {

            String chavePk = null;
            this.addAttribute(componente, valor);
            //chavePk = ((List) valor).get(0).toString();	
            chavePk = valor.toString();

            if (chavePk != null) {
                return String.valueOf(chavePk);
            }
        }
        return (String) valor;
    }

    protected void addAttribute(UIComponent component, Object obj) {
        //String key = ((List) obj).get(0).toString();
        String key = obj.toString();
        this.getAttributesFrom(component).put(key, obj);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}
