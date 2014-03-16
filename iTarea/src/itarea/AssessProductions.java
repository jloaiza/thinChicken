
package itarea;

/**
 * Clase que inicia la evalución de las entradas permitidas con las producciones.
 * @author Yeison
 */
public class AssessProductions 
{
    private String _readFile;
    private String _finalGenerate = "";
    private String _directionAutomata;
    private String _directionEntry;
    private String _directionExit;
    private String _directionProductions;
    private String[] _alphabetSplit;
    private String[] _entrySplitString;  
    private String[] _exitSplitString;
    private String[] _inputSplitString;
    private String[] _splitProductions;
    private String[] _temporaryProductions;
    private LoadFile _entryFile = new LoadFile();
    private LoadFile _productionsFile = new LoadFile();
    private LoadFile _automataFile = new LoadFile();
    
    /**
     * Función que inicia la evaluación de las entradas.
     */
    public synchronized String start()
    {
        createInputString();
        resetBuffers();
        createAlphabet();
        startAssess();
        String temp = _finalGenerate;
        resetVariables();
        return temp;
    }
    
    /**
     * Función que resetea todas las variables utilizadas para una futura evaluación.
     */
    private void resetVariables()
    {
        _entrySplitString = null;
        _exitSplitString = null;
        _entrySplitString = null;
        _readFile = null;
        _inputSplitString = null;
        _finalGenerate = "";
    }
    
    /**
     * Función que resetea todos los buffers de lectura para los archivos 
     * por si hubiera la necesidad de volver a leer alguno.
     */
    private void resetBuffers()
    {
        _automataFile.resetRead();
        _entryFile.resetRead();
        _productionsFile.resetRead();
    }
    
    /**
     * Función que crea todas las variables junto con sus datos correspondientes
     * para iniciar las evaluaciones.
     */
    private void createInputString()
    {
        String input = "";
        _readFile = _automataFile.readFile(_directionAutomata, "NULL");
        _entrySplitString = _entryFile.readFile(_directionEntry,"NULL").split("\n");
        _exitSplitString = _directionExit.split("\n");
        _splitProductions = _productionsFile.readFile(_directionProductions, "NULL").split("\n");
        for(int i = 1; i < _entrySplitString.length; i++)
        {
            if("OK".equals(_exitSplitString[i]))
            {
                input = input + _entrySplitString[i] + "\n";
            }
        }
        _inputSplitString = input.split("\n");
    }
    
    /**
     * Función que inicia la evaluación una por una junto con las producciones.
     */
    private void startAssess()
    {
        for(int i = 0; i < _inputSplitString.length; i++)
        {
            String auxgenerate = "";
            String auxcharacter = "";
            int after = 0;
            for(int j = 0; j < _inputSplitString[i].length(); j++)
            {
                int nextindex = giveMeAWord(_inputSplitString[i],j);
                int advancement = 0;
                String character = "";
                character = _inputSplitString[i].substring(j, nextindex);
                advancement = nextindex - j;
                j = nextindex;
                auxcharacter = auxcharacter + character;
                String[] auxsplit = null;
                auxsplit = extractProduction(auxcharacter);
                if (isEmptyArray(auxsplit))
                {
                    String finalproduction = extractProductionArray(_temporaryProductions);
                    if(!"".equals(finalproduction))
                    {
                        String subauxcharacter = _inputSplitString[i].substring(after, j - advancement);
                        if(subauxcharacter.length() < giveMeProduction(finalproduction).length())
                        {
                            finalproduction = limitControl(subauxcharacter);
                        }
                        if("e".equals(finalproduction))
                        {
                             auxgenerate = auxgenerate + finalproduction;
                             break;
                        }
                        else
                        {
                             auxgenerate = auxgenerate + finalproduction;
                        }
                        j = j - advancement;
                        after = j;
                    }
                    else
                    {
                        auxgenerate = auxgenerate + "e";
                        break;
                    }
                    auxcharacter = "";
                }
                _temporaryProductions = auxsplit;
                j--;
            }
            auxgenerate = auxgenerate + ultimateProduction(auxcharacter);
            _finalGenerate = _finalGenerate + auxgenerate + "\n";
        }
    }
    
    /**
     * Función que dada una palabra retorna los posibles caminos que puede tomar
     * a partir de las producciones (Backtracking).
     * @param pCharacter: Palabra a evaluar.
     * @return: Retorna el resultado de la evaluación.
     */
    private String[] extractProduction(String pCharacter)
    {
        String[] splitproductions = null;
        String productions = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            String auxcomparision = "";
            if(counter + pCharacter.length() <= _splitProductions[i].length())
            {
                auxcomparision = _splitProductions[i].substring(counter, counter + pCharacter.length());
            }
            if(auxcomparision.equals(pCharacter))
            {
                productions = productions + _splitProductions[i].substring(0 , counter - 2) + "/";
            }
        }
        splitproductions = productions.split("/");
        return splitproductions;
    }
    
    /**
     * Función que dado un arreglo retorna si el mismo esta vacio o no.
     * @param pArray: Recibe un arreglo de strings.
     * @return: Valor de verdad, true si es vacio o false sino lo esta.
     */
    private boolean isEmptyArray(String[] pArray)
    {
        boolean text = false;
        int i=0;
        while (!text && ( i < pArray.length))
        {
            if ((pArray[i]!= null) && (!pArray[i].equals("")))
            {
                text = true;
            }
        
            i++;
        }
        return !text;
    }
    
    /**
     * Función que dado un arreglo de producciones retorna la menor que se encuentra
     * dentro del mismo.
     * @param pArray: Arreglo de producciones.
     * @return: La producción menor dentro del arreglo.
     */
    private String extractProductionArray(String[] pArray)
    {
        if(isEmptyArray(pArray))
        {
            return "";
        }
        else
        {
            for(int j = 0; j < pArray.length; j++)
            {
                pArray[j] = giveMeProduction(pArray[j]);
            }
            String aux = pArray[0];
            for(int i = 0; i < pArray.length; i++)
            {
                if(pArray[i].length() < aux.length())
                {
                    aux = pArray[i];
                }
            }
            return giveMeValueProduction(aux);
        }
    }
    
    /**
     * Función que dado una palabra retorna su equivalente a la izquierda de -> 
     * en la producción.
     * @param pLine: Palabra a comparar.
     * @return: La producción.
     */
    private String ultimateProduction(String pLine)
    {
        String finalproduction = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            String aux = _splitProductions[i].substring(counter, _splitProductions[i].length());
            if(aux.equals(pLine))
            {
                finalproduction = _splitProductions[i].substring(0, counter - 2);
                break;
            }
        }
        return finalproduction;   
    }
    
    /**
     * Función que se encarga de crear un alfabeto para poder comparar.
     */
    private void createAlphabet()
    {
        int counter = 3;
        String alphabet = "";
        while(!"{".equals(Character.toString(_readFile.charAt(counter))))
        {
            counter++;
        }
        counter++;
        for(int i = counter; i < _readFile.length(); i++)
        {
            if("}".equals(Character.toString(_readFile.charAt(i))) && !",".equals(Character.toString(_readFile.charAt(i-1))))
            {
                break;
            }
            else if (",".equals(Character.toString(_readFile.charAt(i))))
            {
                if(!",".equals(Character.toString(_readFile.charAt(i-1))))
                {
                    alphabet = alphabet + "\n";
                }
                else
                {
                    while(",".equals(Character.toString(_readFile.charAt(i))))
                    {
                        alphabet = alphabet + Character.toString(_readFile.charAt(i));
                        i++;
                    }
                    alphabet = alphabet.substring(0, alphabet.length() - 1);
                    i--;
                    alphabet = alphabet + "\n";
                }
            }
            else
            {
                alphabet = alphabet + Character.toString(_readFile.charAt(i));
            }
        }
        alphabet = alphabet + "\n";
        _alphabetSplit = alphabet.split("\n");
    }
    
    /**
     * Función que apartir de un index va recorriendo una cadena y retorna la siguiente palabra
     * en la cadena que pertenezca al alfabeto.
     * @param pChain: Cadena donde se itera.
     * @param pIndex: Index desde donde se comienza a iterar.
     * @return: La siguiente palabra en la cadena.
     */
    private int giveMeAWord(String pChain, int pIndex)
    {
        String aux = "";
        int index = 0;
        for(int i = pIndex; i <= pChain.length(); i++)
        {
            if(isInAlphabet(aux))
            {
                index = i;
                break;
            }
            else if(i < pChain.length())
            {
                aux = aux + Character.toString(pChain.charAt(i));
            }
        }
        if(!isInAlphabet(aux))
        {
            String remove = removeWords(aux);
            index = pIndex + remove.length() +1;
        }
        return index;
    }
    
    /**
     * Función que dada una cadena, le quita todas las palabras
     * que se encuentren en el alfabeto y retorna la cadena sin ellas.
     * @param pWord: Palabra desde donde se remueven las palabras del alfabeto.
     * @return: Cadena con las palabras removidas.
     */
    private String removeWords(String pWord)
    {
        for(int i = 0; i < _alphabetSplit.length; i++)
        {
            if(pWord.contains(_alphabetSplit[i]))
            {
                pWord = pWord.replaceAll(_alphabetSplit[i], "");
            }
        }
        return pWord;
    }
    
    /**
     * Retorna un valor de verdad si la palabra recibida se encuentra
     * en el alfabeto o no.
     * @param pWord: Palabra a buscar en el alfabeto.
     * @return: Valor de verdad, true si se encuentra, false sino lo esta.
     */
    private boolean isInAlphabet(String pWord)
    {
        boolean aux = false;
        for(int i = 0; i < _alphabetSplit.length; i++)
        {
            if(_alphabetSplit[i].equals(pWord))
            {
                aux = true;
                break;
            }
        }
        return aux;
    }
    
    /**
     * Función que controla si una entrada contiene palabras repetidas,
     * y comprueba contra las producciones si esto es valido en alguna.
     * @param pCharacter: Palabra a comprobar.
     * @return: Palabra final luego de ser comprobada.
     */
    private String limitControl(String pCharacter)
    {
        String finalproduction = "";
        String midproduction = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            if(_splitProductions[i].length() < counter + pCharacter.length())
            {
                String assess = pCharacter.substring(0,giveMeAWord(pCharacter, 0));
                String production = _splitProductions[i];
                boolean serieflag = true;
                int repeatcounter = 0;
                for(int j = counter; j<_splitProductions[i].length();j++)
                {
                    if(_splitProductions[i].substring(counter, giveMeAWord(production, counter)).equals(assess))
                    {
                        repeatcounter++;
                        counter = giveMeAWord(production, counter);
                        j = counter;
                    }
                    else
                    {
                        counter = giveMeAWord(production, counter);
                        j= counter;
                        serieflag = false;
                        break;
                    }
                    j--;
                }
                if(serieflag == true)
                {
                    midproduction = midproduction + _splitProductions[i].substring(0, approachTheCharacter(production,">")-2) + "=" + Integer.toString(repeatcounter) + "/";
                }
            }
        }
        String[] midproductionsplit = midproduction.split("/");
        if(midproductionsplit.length == 1 && "".equals(midproductionsplit[0]))
        {
            return "e";
        }
        else
        {
            midproductionsplit = accommodateWords(midproductionsplit);
            int numberofwords = numberOfWords(pCharacter);
            finalproduction = aplyModule(midproductionsplit, numberofwords);
            return finalproduction; 
        }  
    }
    
    /**
     * Función que aproxima el index de una producción hasta -> mas uno.
     * @param pProduction: Producción a aproximar.
     * @param pCharacter: Caracter a aproximar.
     * @return: Index hasta la aproximación.
     */
    public int approachTheCharacter(String pProduction, String pCharacter)
    {
        int counter = 0;
        for(int j = 0; j < pProduction.length(); j++)
        {
            if(pCharacter.equals(Character.toString(pProduction.charAt(j))))
            {
                counter++;
                break;
            }
            else
            {
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * Función que dado que retorna el equivalente a una producción, lo que esta luego de ->.
     * @param pProduction: Producción a extraer el equivalente.
     * @return: El equivalente.
     */
    private String giveMeProduction(String pProduction)
    {
        String finalproduction = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            if(_splitProductions[i].substring(0, counter-2).equals(pProduction))
            {
                finalproduction = _splitProductions[i].substring(counter, _splitProductions[i].length());
            }
        }
        return finalproduction;
    }
    
    /**
     * Función que dada una palabra, retorna su equivalente en la producción, es decir
     * lo que esta a la izquierda de ->
     * @param pValue: Palabra a utilizar.
     * @return: El equivalente.
     */
    private String giveMeValueProduction(String pValue)
    {
        String finalvalue = "";
        for(int i = 0; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            if(_splitProductions[i].substring(counter, _splitProductions[i].length()).equals(pValue))
            {
                finalvalue = _splitProductions[i].substring(0, counter-2);
            }
        }
        return finalvalue;
    }
    
    /**
     * Función que acomoda un arreglo de string desde la palabra mas grande a la 
     * más pequeña.
     * @param pWords: Arreglo a acomodar.
     * @return: Arreglo acomodado.
     */
    private String[] accommodateWords(String[] pWords)
    {
        int[] intarray = new int[pWords.length];
        for(int i = 0; i < pWords.length; i++)
        {
            int initiation = approachTheCharacter(pWords[i],"=");
            String value = pWords[i].substring(initiation,pWords[i].length());
            int valueint = Integer.parseInt(value);
            intarray[i] = valueint;
            
        }
        intarray = investArray(sortArray(intarray));
        
        return makeTupla(intarray, pWords).split("/");
    }
    
    /**
     * Función que invierte un arreglo.
     * @param pArray: Arreglo a invertir.
     * @return: Arreglo invertido.
     */
    private int[] investArray(int[] pArray)
    {
        int[]auxArray = new int[pArray.length];
        for(int i = 0; i < pArray.length; i++)
        {
            auxArray[(pArray.length - 1) - i] = pArray[i];
        }
        return auxArray;
    }
    
    /**
     * Función que hace un tupla entre una producción y su equivalente en numeros enteros (B,5).
     * @param pArrayInt: Arreglo de enteros.
     * @param pArrayString: Arreglo de String.
     * @return: El string en forma de tupla.
     */
    private String makeTupla(int[] pArrayInt, String[] pArrayString)
    {
        String aux = "";
        for(int i = 0; i < pArrayInt.length; i++)
        {
            int value = pArrayInt[i];
            for(int j = 0; j< pArrayString.length; j++)
            {
                int index = approachTheCharacter(pArrayString[i], "=");
                String valuestring = pArrayString[i].substring(index, pArrayString[i].length());
                if(valuestring.equals(Integer.toString(value)))
                {
                    aux = aux + pArrayString[i].substring(0, index-1) + "=" + Integer.toString(value) + "/";
                    break;
                }
            }
        }
        return aux;
    }
    
    /**
     * Función que acomoda un arreglo de enteros de menor a mayor.
     * @param pArray: Arreglo de enteros a ordenar.
     * @return: Arreglo ordenado.
     */
    private int[] sortArray(int[] pArray)
    {
        int aux;
        for (int i = 0; i < pArray.length - 1; i++) 
        {
            for (int x = i + 1; x < pArray.length; x++) 
            {
                if (pArray[x] > pArray[i]) 
                {
                    aux = pArray[i];
                    pArray[i] = pArray[x];
                    pArray[x] = aux;
                }
            }
        }
        return pArray;
    }
    
    /**
     * Función que dada una cadena me retorna el número de palabras en el alfabeto
     * que tiene dentro de ella.
     * @param pCharacter: Palabra a evaluar.
     * @return: Número de palabras.
     */
    private int numberOfWords(String pCharacter)
    {
        int counter = 0;
        for(int i = 0; i < pCharacter.length(); i++)
        {
            i = giveMeAWord(pCharacter, i);
            counter++;
            i--;
        }
        return counter;
    }
    
    /**
     * Función que aplica un modulo a una palabra dependiendo de su cantidad de caracteres.
     * @param pModules: Arreglo de donde se sacan las palabras para aplicar módulo.
     * @param pNumber: Módulo a aplicar.
     * @return: Devuelve la palabra con el módulo aplicado.
     */
    private String aplyModule(String[] pModules, int pNumber)
    {
        String auxfinal = "";
        for(int i = 0; i < pModules.length; i++)
        {
            int index = approachTheCharacter(pModules[i], "=");
            int value = Integer.parseInt(pModules[i].substring(index, pModules[i].length()));
            int amount = pNumber/value;
            pNumber = pNumber - (value * amount);
            for(int j = 0; j < amount; j++)
            {
                auxfinal = auxfinal + pModules[i].substring(0,index-1);
            }
        }
        return auxfinal;
        
    }
    
    /**
     * Función que setea la dirección del archivo autómata.
     * @param pDirectionAutomata: Dirección del archivo autómata.
     */
    public void setDirectionAutomata(String pDirectionAutomata) 
    {
        _directionAutomata = pDirectionAutomata;
    }

    /**
     * Función que setea la dirección del archivo de entradas.
     * @param pDirectionEntry: Dirección del archivo de entradas.
     */
    public void setDirectionEntry(String pDirectionEntry) 
    {
        _directionEntry = pDirectionEntry;
    }

    /**
     * Función que setea el string con las salidas ya evaluadas.
     * @param pDirectionExit: String con la evaluación.
     */
    public void setExit(String pDirectionExit) 
    {
        _directionExit = "\n" + pDirectionExit + "\n";
    }

    /**
     * Función que setea la dirección del archivo de producciones.
     * @param pDirectionProductions: Dirección del archivo de producciones..
     */
    public void setDirectionProductions(String pDirectionProductions) 
    {
        _directionProductions = pDirectionProductions;
    }
}
