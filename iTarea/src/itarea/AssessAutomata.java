
package itarea;

import automata.Automata;
import structures.Queue;

/**
 * Clase encarga de iniciar las evaluaciones de las entradas en el autómata.
 * @author Yeison
 */

public class AssessAutomata 
{
    private LoadFile _entryFile = new LoadFile();
    private SaveFile _exitFile = new SaveFile();
    private Queue _queue = new Queue();
    private String _readFile;
    private String _direction;
   
    /**
     * Función que iniciar la evaluación de cada entrada en el autómata.
     * @return: El resultado de la evaluación.
     * @throws InterruptedException 
     */
    public synchronized String start() throws InterruptedException
    {
        loadFile();
        startAssess(_readFile);
        String toReturn = getExitFile();
        resetAll();
        return toReturn;
    }
    
    /**
     * Función que resetea todas las variables para una futura reevaluación.
     */
    private void resetAll()
    {
        _queue.clear();
        _entryFile.resetRead();
    }
    
    /**
     * Función que carga el archivo desde donde se leeran las entradas.
     */
    private void loadFile()
    {
        _readFile = _entryFile.readFile(_direction,"NULL");
    }
    
    /**
     * Función que envia entrada por entrada al autómata para su evaluación.
     * @param pRead: Variable desde donde se tomarán los datos a leer.
     * @throws InterruptedException 
     */
    private void startAssess(String pRead) throws InterruptedException
    {
        boolean send = true;
        String[] assess = pRead.split("\n");
        for (int i = 1; i < assess.length; i++) 
        {
            send = Automata.getInstance().evaluate(assess[i]);
            if (send == true)
            {
                _queue.enqueue("OK");
            }
            else
            {
                _queue.enqueue(":-c");
            }
        }
    }
    
    /**
     * Función que entrega el resultado de la evaluación final.
     * @return: Resultado de la evaluación.
     */
    private String getExitFile()
    {
        String finalassess = "";
        while(!_queue.isEmpty())
        {
            finalassess = finalassess + _queue.dequeue() + "\n";
        }
        return finalassess;
    }
    
    /**
     * Función que setea la dirección del archivo de entradas.
     * @param pDirection: Dirección del archivo de entradas.
     */
    public void setDirection(String pDirection)
    {
        _direction = pDirection;
    }
}
