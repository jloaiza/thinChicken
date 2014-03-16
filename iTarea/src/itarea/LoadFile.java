
package itarea;

import java.io.*;


/**
 * Clase encargada de cargar los archivos.
 * @author Yeison
 */
public class LoadFile 
{
    private File _file;
    private FileReader _fileRead;
    private BufferedReader _readBuffer;
    private String _read = "";
    
    /**
     * Función que lee un archivo a partir de una dirección absoluta.
     * @param pDirection: Dirección del archivo.
     * @param pEntry: Tipo de función a tratar.
     * @return: Lectura del archivo.
     */
    public String readFile(String pDirection,String pEntry)
    {
        try
        {
            //Creamos el objeto con el archivo que leeremos.
            _file = new File(pDirection);

            //Creamos el objeto FileReader que nos dara el flujo de datos para poder leer.
            _fileRead = new FileReader(_file);

            //Creamos un lector en buffer para recopilar datos a travez del flujo de FileReader.
             _readBuffer = new BufferedReader(_fileRead);

            String aux = "";/*variable auxiliar*/

            while(aux != null)
            //Este repite el proceso de lectura por si el archivo viene en varias lineas.
            {
                _read = _read + removeSpace(aux) + "\n";
                aux = _readBuffer.readLine();
                //Si la variable aux aún tiene datos se guardan sino se sale del ciclo.
            }
            //Se cierran los buffers.
            _readBuffer.close();
            _fileRead.close();

        }
        catch(IOException e)
        {
            System.out.println("Error:" + e.getMessage());
        }
        if("Entry".equals(pEntry))
        {
            return _read.substring(1, _read.length()-1);
        }
        else
        {
            return _read;
        }
    }
    
    /**
     * Función que remueve cualquier espacio innecesario en la carga del archivo.
     * @param pWord: Palabra de la cual remover espacios.
     * @return: Palabra sin espacios.
     */
    private String removeSpace(String pWord)
    {
        String aux = "";
        for(int i = 0; i < pWord.length(); i++)
        {
            if(!" ".equals(Character.toString(pWord.charAt(i))))
            {
                aux = aux + Character.toString(pWord.charAt(i));
            }
        }
        return aux;
    }
    
    /**
     * Función que resetea la última lectura realizada de un archivo.
     */
    public void resetRead()
    {
        _read = "";
    }
}
