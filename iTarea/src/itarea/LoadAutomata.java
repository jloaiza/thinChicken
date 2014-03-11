/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itarea;

import automata.Automata;

/**
 *
 * @author Yeison
 */
public class LoadAutomata 
{
    private LoadFile _automataFile = new LoadFile();
    private String _readFile;
    private String _direction;
    private int _counter = 3;
    
    public void load()
    {
        loadFile();
        createBeenAndAlphabetAndFinal(_readFile,_counter,"Been");
        createBeenAndAlphabetAndFinal(_readFile,_counter,"Alphabet");
        setInitialBeen(_readFile);
        setTransition(_readFile);
        createBeenAndAlphabetAndFinal(_readFile,_counter = _counter + 3,"Final");
        resetAll();
    }
    
    private void resetAll()
    {
        _counter = 3;
        _automataFile.resetRead();
    }
    
    private void loadFile()
    {
        _readFile = _automataFile.readFile(_direction);
    }
    
    private void createBeenAndAlphabetAndFinal(String pRead,int pCounter, String pOperation)
    {
        String estate = "";
        for(int i = pCounter; i < pRead.length(); i++)
        {
            System.out.println("LETRA: " + pRead.charAt(_counter) + " CONTADOR: " + _counter);
            if("}".equals(Character.toString(pRead.charAt(_counter))) && !",".equals(Character.toString(pRead.charAt(_counter-1))))
            {
                break;
            }
            else if (",".equals(Character.toString(pRead.charAt(_counter))))
            {
                add(pOperation,estate);
                System.out.println(estate);
                estate = "";
                _counter++;
            }
            else
            {
                estate = estate + Character.toString(pRead.charAt(_counter));
                _counter++;
            }
        }
        add(pOperation,estate);
        _counter = _counter + 3;
        System.out.println(estate);
    }
    
    private void add(String pOperation, String pState)
    {
        switch (pOperation)
        {
            case "Been":
                Automata.getInstance().newState(pState);
            case "Alphabet":
                Automata.getInstance().addWordToAlphabet(pState);
            case "Final":
                Automata.getInstance().setFinalState(pState);
        }
    }
    
    private void setInitialBeen(String pRead)
    {
        _counter--;
        String initialstate = "";
        while(!",".equals(Character.toString(pRead.charAt(_counter))))
        {
            initialstate = initialstate + Character.toString(pRead.charAt(_counter));
            _counter++;
        }
        Automata.getInstance().setInitialState(initialstate); 
        //NO
        System.out.println("ESTADO INICIAL: " + initialstate);
        System.out.println("LETRA: " + pRead.charAt(_counter) + " CONTADOR: " + _counter);
        System.out.println(_counter);
        //NO
    }
    
    private void setTransition(String pRead)
    {
        String aux = "";
        String beenone = "";
        String beentwo = "";
        String value = "";
        while(!"}".equals(Character.toString(pRead.charAt(_counter))))
        {
            if ("(".equals(Character.toString(pRead.charAt(_counter))))
            {
                  _counter++;
                  for(int i = _counter; i < pRead.length(); i++)
                  {
                      if(")".equals(Character.toString(pRead.charAt(_counter))) && !")".equals(Character.toString(pRead.charAt(_counter+1))))
                      {
                          break;
                      }
                      else if (",".equals(Character.toString(pRead.charAt(_counter))))
                      {
                          System.out.println("AUXILIAR: " + aux);
                          beenone = aux;
                          aux = "";
                          _counter++;
                      }
                      else
                      {
                          aux = aux + Character.toString(pRead.charAt(_counter));
                          _counter++;
                      }
                  }
                  value = aux;
                  aux = "";
                _counter = _counter + 2;
                while(!",".equals(Character.toString(pRead.charAt(_counter))) && !"}".equals(Character.toString(pRead.charAt(_counter))))
                {
                    beentwo = beentwo + Character.toString(pRead.charAt(_counter));
                    _counter++;
                }
                //NO
                System.out.println(beenone);
                System.out.println(beentwo);
                System.out.println(value);
                System.out.println("LETRA: " + pRead.charAt(_counter) + " CONTADOR: " + _counter);
                //NO
                Automata.getInstance().addTransition(beenone, beentwo, value);
                beenone = "";
                beentwo = "";
                value = "";
                _counter--;
            }
            _counter++;
        }
        System.out.println("SALI TRANSITION");
        System.out.println("LETRA: " + pRead.charAt(_counter) + " CONTADOR: " + _counter);
        System.out.println("SALI TRANSITION");
    }
    
    public void setDirection(String pDirection)
    {
        _direction = pDirection;
    }
}
