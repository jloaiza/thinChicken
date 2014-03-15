/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itarea;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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
    private LoadFile _exitFile = new LoadFile();
    private LoadFile _productionsFile = new LoadFile();
    private LoadFile _automataFile = new LoadFile();
    
    public synchronized void start()
    {
        createInputString();
        resetBuffers();
        createAlphabet();
        startAssess();
        sendFile();
        resetVariables();
    }
    
    private void resetVariables()
    {
        _entrySplitString = null;
        _exitSplitString = null;
        _entrySplitString = null;
        _readFile = null;
        _inputSplitString = null;
        _finalGenerate = "";
    }
    
    private void resetBuffers()
    {
        _automataFile.resetRead();
        _entryFile.resetRead();
        _exitFile.resetRead();
        _productionsFile.resetRead();
    }
    
    private void createInputString()
    {
        String input = "";
        _readFile = _automataFile.readFile(_directionAutomata, "NULL");
        _entrySplitString = _entryFile.readFile(_directionEntry,"NULL").split("\n");
        _exitSplitString = _exitFile.readFile(_directionExit,"NULL").split("\n");
        _splitProductions = _productionsFile.readFile(_directionProductions, "NULL").split("\n");
        System.out.println("INICIO DE TEST");
        System.out.println("READ FILE: " + _readFile);
        System.out.println("ENTRY SPLIT: " + _entrySplitString[0] + " " + _entrySplitString[1] + " " + _entrySplitString[2] + " " + _entrySplitString[3]);
        System.out.println("EXIT SPLIT STRING: " + _exitSplitString[0] + " " + _exitSplitString[1] + " " + _exitSplitString[2] + " " + _exitSplitString[3]);
        System.out.println("FIN DE TEST");
        for(int i = 1; i < _entrySplitString.length; i++)
        {
            if("OK".equals(_exitSplitString[i]))
            {
                System.out.println("INDICE: " + i);
                input = input + _entrySplitString[i] + "\n";
            }
        }
        _inputSplitString = input.split("\n");
        //NO
        System.out.println("EL INPUT ES EL SIGUIENTE: " + input);
        //NO
    }
    
    private void startAssess()
    {
        for(int i = 0; i < _inputSplitString.length; i++)
        {
            System.out.println("CAMBIAMOS DE LINEA DE ENTRADA A: " + _inputSplitString[i]);
            String auxgenerate = "";
            String auxcharacter = "";
            int after = 0;
            for(int j = 0; j < _inputSplitString[i].length(); j++)
            {
                int nextindex = giveMeAWord(_inputSplitString[i],j);
                //NO
                System.out.println("INDEX");
                System.out.println(nextindex);
                System.out.println(j);
                //NO
                int advancement = 0;
                String character = "";
                character = _inputSplitString[i].substring(j, nextindex);
                advancement = nextindex - j;
                j = nextindex;
                System.out.println(advancement);
                auxcharacter = auxcharacter + character;
                //NO
                System.out.println("AUX");
                System.out.println(auxcharacter);
                System.out.println("AUX");
                //NO
                String[] auxsplit = null;
                auxsplit = extractProduction(auxcharacter);
                if (isEmptyArray(auxsplit))
                {
                    String finalproduction = extractProductionArray(_temporaryProductions);
                    if(!"".equals(finalproduction))
                    {
                        System.out.println("J VALE: " + j);
                        String subauxcharacter = _inputSplitString[i].substring(after, j - advancement);
                        System.out.println("SUBCHARACTER: " + subauxcharacter);
                        System.out.println("PRODUCCION ENVIADDA: " + finalproduction);
                        if(subauxcharacter.length() < giveMeProduction(finalproduction).length())
                        {
                            System.out.println("ENTRO CARAJO");
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
                        System.out.println("AFTER: " + after);
                    }
                    else
                    {
                        System.out.println("TEST: " + auxgenerate);
                        auxgenerate = auxgenerate + "e";
                        break;
                    }
                    auxcharacter = "";
                }
                _temporaryProductions = auxsplit;
                //NO
                System.out.println("SEMIFINAL");
                System.out.println(auxgenerate);
                System.out.println("SEMIFINAL");
                //NO
                j--;
            }
            auxgenerate = auxgenerate + ultimateProduction(auxcharacter);
            //NO
            System.out.println("AUXCHARACTER: " + auxcharacter);
            //NO
            _finalGenerate = _finalGenerate + auxgenerate + "\n";
        }
        //NO
        System.out.println("FINAL");
        System.out.println(_finalGenerate);
        System.out.println("FINAL");
        //NO
    }
    
    private String[] extractProduction(String pCharacter)
    {
        String[] splitproductions = null;
        String productions = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            //NO
            System.out.println(pCharacter);
            System.out.println(counter);
            //NO
            String auxcomparision = "";
            if(counter + pCharacter.length() <= _splitProductions[i].length())
            {
                auxcomparision = _splitProductions[i].substring(counter, counter + pCharacter.length());
            }
            //NO
            System.out.println(auxcomparision);
            //NO
            if(auxcomparision.equals(pCharacter))
            {
                productions = productions + _splitProductions[i].substring(0 , counter - 2) + "/";
            }
            //NO
            System.out.println("PRODU");
            System.out.println(productions);
            System.out.println("PRODU");
            //NO
        }
        splitproductions = productions.split("/");
        return splitproductions;
    }
    
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
                System.out.println("ALPHABETO: " + alphabet);
                alphabet = alphabet + "\n";
                System.out.println(alphabet);
            }
            else
            {
                alphabet = alphabet + Character.toString(_readFile.charAt(i));
            }
        }
        alphabet = alphabet + "\n";
        System.out.println("ALFABETO FINAL: " + alphabet);
        _alphabetSplit = alphabet.split("\n");
    }
    
    private int giveMeAWord(String pChain, int pIndex)
    {
        System.out.println("ENTRO CON " + pIndex);
        System.out.println("LA LINEA ES: " + pChain + " Con una longitud: " + pChain.length());
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
            System.out.println("EL AUX ES: " + aux);
        }
        if(!isInAlphabet(aux))
        {
            String remove = removeWords(aux);
            System.out.println("FUNCIONA: " + remove);
            index = pIndex + remove.length() +1;
        }
        return index;
    }
    
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
    
    private String limitControl(String pCharacter)
    {
        System.out.println("ESTAMOS EN LIMIT CONTROL CON: " + pCharacter);
        String finalproduction = "";
        String midproduction = "";
        for(int i = 1; i < _splitProductions.length; i++)
        {
            int counter = approachTheCharacter(_splitProductions[i],">");
            if(_splitProductions[i].length() < counter + pCharacter.length())
            {
                String assess = pCharacter.substring(0,giveMeAWord(pCharacter, 0));
                System.out.println("ASSESS: " + assess);
                String production = _splitProductions[i];
                System.out.println("PRODUCTION: " + production);
                boolean serieflag = true;
                int repeatcounter = 0;
                for(int j = counter; j<_splitProductions[i].length();j++)
                {
                    System.out.println("COUNTEEER: " + counter);
                    if(_splitProductions[i].substring(counter, giveMeAWord(production, counter)).equals(assess))
                    {
                        repeatcounter++;
                        counter = giveMeAWord(production, counter);
                        j = counter;
                        System.out.println("J ESSSSSS: " + j);
                    }
                    else
                    {
                        counter = giveMeAWord(production, counter);
                        j= counter;
                        System.out.println("J ESSSSSS: " + j);
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
            System.out.println("MID PRODUCTION");
            System.out.println(midproduction);
        }
        String[] midproductionsplit = midproduction.split("/");
        System.out.println(midproductionsplit.length);
        if(midproductionsplit.length == 1 && "".equals(midproductionsplit[0]))
        {
            return "e";
        }
        else
        {
            midproductionsplit = accommodateWords(midproductionsplit);
            System.out.println("PALABRAS ACOMODADAS: " + midproductionsplit[0]);
            int numberofwords = numberOfWords(pCharacter);
            finalproduction = aplyModule(midproductionsplit, numberofwords);
            return finalproduction; 
        }  
    }
    
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
        System.out.println("FINAL PRODUCTION: " + finalproduction);
        return finalproduction;
    }
    
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
    
    private int[] investArray(int[] pArray)
    {
        int[]auxArray = new int[pArray.length];
        for(int i = 0; i < pArray.length; i++)
        {
            auxArray[(pArray.length - 1) - i] = pArray[i];
        }
        return auxArray;
    }
    
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
    
    private String aplyModule(String[] pModules, int pNumber)
    {
        String auxfinal = "";
        for(int i = 0; i < pModules.length; i++)
        {
            int index = approachTheCharacter(pModules[i], "=");
            int value = Integer.parseInt(pModules[i].substring(index, pModules[i].length()));
            System.out.println("pNumber: " + pNumber + " Y value: " + value);
            int amount = pNumber/value;
            System.out.println("AMOUNT: " + amount);
            pNumber = pNumber - (value * amount);
            for(int j = 0; j < amount; j++)
            {
                auxfinal = auxfinal + pModules[i].substring(0,index-1);
            }
        }
        System.out.println("AUXFINAL:  " + auxfinal);
        return auxfinal;
        
    }
    
    public void setDirectionAutomata(String pDirectionAutomata) 
    {
        _directionAutomata = pDirectionAutomata;
    }

    public void setDirectionEntry(String pDirectionEntry) 
    {
        _directionEntry = pDirectionEntry;
    }

    public void setDirectionExit(String pDirectionExit) 
    {
        _directionExit = pDirectionExit;
    }

    public void setDirectionProductions(String pDirectionProductions) 
    {
        _directionProductions = pDirectionProductions;
    }
    
    private void sendFile()
    {
        //enviar;
    }
}
