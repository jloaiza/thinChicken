
package itarea;

import automata.Automata;

/**
 * Clase encargada de pasar los datos del autómata en el archivo
 * a la clase destinada para ello.
 * @author Yeison
 */
public class LoadAutomata 
{
    private LoadFile _automataFile = new LoadFile();
    private String _readFile;
    private String _direction;
    private int _counter = 3;
    
    /**
     * Función encargada de iniciar la carga del archivo autómata.
     */
    public synchronized void load()
    {
        loadFile();
        createBeenAndAlphabetAndFinal(_readFile,_counter,"Been");
        createBeenAndAlphabetAndFinal(_readFile,_counter,"Alphabet");
        setInitialBeen(_readFile);
        setTransition(_readFile);
        createBeenAndAlphabetAndFinal(_readFile,_counter = _counter + 3,"Final");
        resetAll();
    }
    
    /**
     * Función encargada de resetear todas las variables importantes
     * para una futura carga de algún otr autómata.
     */
    private void resetAll()
    {
        _counter = 3;
        _automataFile.resetRead();
    }
    
    /**
     * Función que carga el archivo autómata.
     */
    private void loadFile()
    {
        _readFile = _automataFile.readFile(_direction, "NULL");
    }
    
    /**
     * Función encargada de crear los estados el alfabeto y los estados finales en el autómata.
     * @param pRead: Archivo desde donde se leen los datos.
     * @param pCounter: Contador importante en la lógica.
     * @param pOperation: Tipo de función a realizar (Estado, alfabeto o estado final).
     */
    private void createBeenAndAlphabetAndFinal(String pRead,int pCounter, String pOperation)
    {
        String estate = "";
        for(int i = pCounter; i < pRead.length(); i++)
        {
            if("}".equals(Character.toString(pRead.charAt(_counter))) && !",".equals(Character.toString(pRead.charAt(_counter-1))))
            {
                break;
            }
            else if (",".equals(Character.toString(pRead.charAt(_counter))))
            {
                if(!",".equals(Character.toString(pRead.charAt(_counter-1))))
                {
                    add(pOperation,estate);
                    estate = "";
                    _counter++;
                }
                else
                {
                    while(",".equals(Character.toString(pRead.charAt(_counter))))
                    {
                        estate = estate + Character.toString(pRead.charAt(_counter));
                        _counter++;
                    }
                    estate = estate.substring(0, estate.length() - 1);
                    add(pOperation,estate);
                    estate = "";
                }
            }
            else
            {
                estate = estate + Character.toString(pRead.charAt(_counter));
                _counter++;
            }
        }
        add(pOperation,estate);
        _counter = _counter + 3;
    }
    
    /**
     * Función encarga de enviar al autómata el estado el alfabeto o estado final.
     * @param pOperation: Tipo de dato a agregar.
     * @param pState: Dato a agregar.
     */
    private void add(String pOperation, String pState)
    {
        switch (pOperation)
        {
            case "Been":
                Automata.getInstance().newState(pState);
                break;
            case "Alphabet":
                Automata.getInstance().addWordToAlphabet(pState);
                break;
            case "Final":
                Automata.getInstance().setFinalState(pState);
                break;
        }
    }
    
    /**
     * Función encargada de setear los estados iniciales.
     * @param pRead: Variable desde donde se leen los datos.
     */
    private void setInitialBeen(String pRead)
    {
        _counter--;
        String initialstate = "";
        if(",".equals(Character.toString(pRead.charAt(_counter))))
        {
            while(",".equals(Character.toString(pRead.charAt(_counter))))
            {
                initialstate = initialstate + Character.toString(pRead.charAt(_counter));
                _counter++;
            }
            initialstate = initialstate.substring(0, initialstate.length()-1);
            _counter--;
        }
        else
        {
            while(!",".equals(Character.toString(pRead.charAt(_counter))))
            {
                initialstate = initialstate + Character.toString(pRead.charAt(_counter));
                _counter++;
            }
        }
        Automata.getInstance().setInitialState(initialstate); 
    }
    
    /**
     * Función encarga de setear las transiciones en el autómata.
     * @param pRead: Variable desde donde se leen los datos.
     */
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
                  boolean flag = false;
                  for(int i = _counter; i < pRead.length(); i++)
                  {
                      if(")".equals(Character.toString(pRead.charAt(_counter))) && !")".equals(Character.toString(pRead.charAt(_counter+1))))
                      {
                          break;
                      }
                      else if (",".equals(Character.toString(pRead.charAt(_counter))))
                      {
                          if(!",".equals(Character.toString(pRead.charAt(_counter-1))))
                          {
                              beenone = aux;
                              aux = "";
                              _counter++;
                              if("".equals(beenone))
                              {
                                  flag = true;
                              }
                          }
                          else
                          {
                              while(",".equals(Character.toString(pRead.charAt(_counter))))
                              {
                                  aux = aux + Character.toString(pRead.charAt(_counter));
                                  _counter++;
                              }
                              if(flag == true)
                              {
                                  beenone = aux.substring(0, aux.length());
                                  aux = "";
                              }
                          }
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
                while(!"(".equals(Character.toString(pRead.charAt(_counter))) && !"}".equals(Character.toString(pRead.charAt(_counter))))
                {
                    beentwo = beentwo + Character.toString(pRead.charAt(_counter));
                    _counter++;
                }
                if(!"}".equals(Character.toString(pRead.charAt(_counter))))
                {
                    beentwo = beentwo.substring(0, beentwo.length()-1);
                }
                _counter--;
                Automata.getInstance().addTransition(beenone, beentwo, value);
                beenone = "";
                beentwo = "";
                value = "";
                _counter--;
            }
            _counter++;
        }
    }
    
    /**
     * Función encarga de setear la dirección del archivo desde donde se leeran los datos.
     * @param pDirection: Dirección del archivo.
     */
    public void setDirection(String pDirection)
    {
        _direction = pDirection;
    }
}
