/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itarea;

import automata.Automata;
import structures.Queue;

/**
 *
 * @author Yeison
 */

public class AssessAutomata 
{
    private LoadFile _entryFile = new LoadFile();
    private SaveFile _exitFile = new SaveFile();
    private Queue _queue = new Queue();
    private String _readFile;
    private String _direction;
   
    public synchronized void start()
    {
        loadFile();
        startAssess(_readFile);
        writeExitFile();
        resetAll();
    }
    
    private void resetAll()
    {
        _queue.clear();
        _entryFile.resetRead();
    }
    
    private void loadFile()
    {
        _readFile = _entryFile.readFile(_direction);
    }
    
    private void startAssess(String pRead)
    {
        boolean send = true;
        System.out.println("LECTURA: " + pRead);
        String[] assess = pRead.split("\n");
        for (int i = 1; i < assess.length; i++) 
        {
            System.out.println("ENVIADO: " + assess[i]);
            send = Automata.getInstance().evaluate(assess[i]);
            if (send == true)
            {
                System.out.println("TRUE");
                _queue.enqueue("OK");
            }
            else
            {
                System.out.println("FALSE");
                _queue.enqueue(":-c");
            }
        }
    }
    
    private void writeExitFile()
    {
        String finalassess = "";
        while(!_queue.isEmpty())
        {
            finalassess = finalassess + _queue.dequeue() + "\n";
        }
        _exitFile.writeFile("/root/Desktop/Proyecto/salida.txt", finalassess);
        //NO
        System.out.println(finalassess);
        //NO
    }
    
    public void setDirection(String pDirection)
    {
        _direction = pDirection;
    }
}
