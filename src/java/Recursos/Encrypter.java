/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase usada para encriptar las contraseñas
 *
 * @author AFerSor
 */
public class Encrypter {

    /**
     * Encripta la contraseña
     *
     * @param password contraseña
     * @return cadena con la contraseña encriptada
     */
    public static String encriptar(String password) throws Exception {
        try {

            if (password.isEmpty()) {
                return "";
            }//fin if

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            /*//convert the byte to hex format method 1
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < byteData.length; i++) {
             sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
             }*/
            //convert the byte to hex format method 2
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException ex) {
            throw new Exception("Se ha producido un error al guardar el usuario");
        }
    }//fin encriptar
}//FIN CLASE
