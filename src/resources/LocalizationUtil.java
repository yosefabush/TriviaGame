/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author irit
 */
public class LocalizationUtil 
{
    public static ResourceBundle localizedResourceBundle;
    
    static
    {
        // gets the bundle for the default locale used
        localizedResourceBundle = ResourceBundle.getBundle("resources.uimessages", new Locale("en"));
    }
    
    
}
