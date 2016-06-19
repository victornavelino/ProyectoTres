/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author hugo
 */
@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent uIComponent, Object value) throws ValidatorException {

        System.out.println("valiteror: hay un error en la direccion de Email " + value);
        if (!value.equals("")) {
            System.out.println("Value = vacio");
            Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w+");
            Matcher matcher = pattern.matcher((CharSequence) value);
            HtmlInputText htmlInputText = (HtmlInputText) uIComponent;
            String label;
            if (htmlInputText.getLabel() == null || htmlInputText.getLabel().trim().equals("")) {
                label = htmlInputText.getId();
            } else {
                label = htmlInputText.getLabel();
            }
            if (!matcher.matches()) {
                FacesMessage faceMessage = new FacesMessage(label + ": formato de mail no valido");
                throw new ValidatorException(faceMessage);
            }

        }
    }//fin if

}
