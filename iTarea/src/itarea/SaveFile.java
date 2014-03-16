
package itarea;

import java.io.*;

/**
 * Función que guarda los archivos en el disco duro.
 * @author Yeison
 */
public class SaveFile 
{
    private File _file;
    private FileWriter _fileWriter;
    private BufferedWriter _writerBuffer;
    private PrintWriter _writer; 
    
    /**
     * Función que dado una cadena la convierte en un archivo y la guarda.
     * @param pDirection: Dirección donde se guardara el archivo.
     * @param pText: Texto a escribir en el archivo.
     */
    public void writeFile(String pDirection, String pText)
    {
        try
        {
            _file = new File(pDirection);
            _fileWriter = new FileWriter(_file);
            _writerBuffer = new BufferedWriter(_fileWriter);
            _writer = new PrintWriter(_writerBuffer);
            _writer.write(pText);
            _writer.close();
            _writerBuffer.close();
            _fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error:" + e.getMessage());
        }  
        
    }
}
