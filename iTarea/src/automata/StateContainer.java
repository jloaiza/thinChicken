/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automata;

/**
 * Description. This class is used in the evaluation stack by the Automata class.
 * It relate the state to the index of the next word that has to be read when it was added
 * and the parent of the state.
 * 
 * @author jmloaiza
 */
public class StateContainer {
    private final int _index;
    private final State _state;
    private final State _parent;
    
    public StateContainer(int pIndex, State pState, State pParent){
        _index = pIndex;
        _state = pState;
        _parent = pParent;
    }
    
    public int getIndex(){
        return _index;
    }
    
    public State getState(){
        return _state;
    }
    
    public State getParent(){
        return _parent;
    }
    
    
    
}
