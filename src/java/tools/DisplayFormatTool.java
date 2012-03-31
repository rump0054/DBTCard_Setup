package tools;

import org.apache.velocity.tools.config.DefaultKey;

@DefaultKey("displayformat")
public class DisplayFormatTool
{    
    public String uniqueid(Object id)
    { 
        if(id == null)
        {
            return null;
        }
        
        // Create string to work with from id param
        // Convert string to all lowercase
        // Remove all non-letter characters including spaces
        String string = String.valueOf(id);
        string = string.toLowerCase();
        string = string.replaceAll("[^a-z]", "");
        
       return string;    
    }
}
