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
   
    public synchronized String start() throws InterruptedException
    {
        loadFile();
        startAssess(_readFile);
        String toReturn = getExitFile();
        resetAll();
        return toReturn;
    }
    
    private void resetAll()
    {
        _queue.clear();
        _entryFile.resetRead();
    }
    
    private void loadFile()
    {
        _readFile = _entryFile.readFile(_direction,"NULL");
    }
    
    private void startAssess(String pRead) throws InterruptedException
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
    
    private String getExitFile()
    {
        String finalassess = "";
        while(!_queue.isEmpty())
        {
            finalassess = finalassess + _queue.dequeue() + "\n";
        }
        return finalassess;
    }
    
    public void setDirection(String pDirection)
    {
        _direction = pDirection;
    }
}
