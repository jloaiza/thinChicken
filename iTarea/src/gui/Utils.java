
package gui;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/* Utils.java is used by FileChooserDemo2.java. */
public class Utils { 
    
    public static FileFilter getTxtFilter(){
        return new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()){
                    return true;
                }
                
                return getExtension(pathname).compareTo("txt") == 0;
            }
            
            //The description of this filter
            @Override
            public String getDescription() {
                return "Archivo de texto *.txt";
            }
        };
    }
    
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
    public static File loadFilePathDialog(String pTittle, FileFilter pFilter, String pFileName, boolean pIsSaveDialog, Component pParent){
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(pFilter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setDialogTitle(pTittle);
        fc.setSelectedFile(new File(pFileName));
        
        int returnVal;
        if (pIsSaveDialog){
            returnVal = fc.showSaveDialog(pParent);
        } else {
            returnVal = fc.showOpenDialog(pParent);
        }
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        } else {
            return null;
        } 
    }
    
}