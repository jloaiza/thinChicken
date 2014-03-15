/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Component;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import structures.trees.BinarySearchTree;

/**
 *
 * @author jmloaiza
 */
public class GUIAutoHandler {
    private static final int STATE_SPACE = 120;
    private static final int RAND_BORDER = 20;
    
    public static final int DEFAULT_STATE_COLOR = 0;
    public static final int CHECK_STATE_COLOR = 1;
    public static final int BAD_STATE_COLOR = 2;
    public static final int GOOD_STATE_COLOR = 3;
    
    private JLayeredPane _autoPanel;
    private JLabel _startLabel;
    
    private BinarySearchTree<JState> _states;
    private BinarySearchTree<JConnection> _connections;
    
    private int _maxWidthSates;
    private Point _lastState;

    public GUIAutoHandler(JLayeredPane pAutoPanel) {
        _autoPanel = pAutoPanel;
        _states = new BinarySearchTree<>();
        _connections = new BinarySearchTree<>();
        _maxWidthSates = 0;
        _lastState = new Point(-1, 0);
        initStartLabel();
    }
    
    private void initStartLabel(){
        _startLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/gui/images/iconInitState.png")));
        _startLabel.setBounds(0, 0, 28, 19);
        _startLabel.setEnabled(true);
        _startLabel.setVisible(false);
        addToPane(_startLabel, 1);
    }
    
    public void setStartState(String pStateID){
        JState dummy = new JState(pStateID, false, 0, 0);
        JLabel starter = _states.search(dummy).getValue().getLabel();
        _startLabel.setLocation(
                starter.getX()-_startLabel.getWidth(),
                starter.getY()+starter.getHeight()/2-_startLabel.getHeight()/2);
        _startLabel.setVisible(true);
    }
    
    public void newState(String pID, boolean pIsFinal, int pX, int pY){
        JState state = new JState(pID, pIsFinal, pX, pY);
        _states.insert(state);
        addToPane(state.getLabel(), 2);
    }
    
    private void addToPane(Component pComponent, int pLayer){
        _autoPanel.add(pComponent, new Integer(pLayer));
    }
    
    public void setItemsCountToLoad(int pCount){
        double widthStates = Math.sqrt(5*pCount/3);
        double heightStates = pCount/widthStates;
        if (widthStates%1 != 0){
            widthStates++;
        }
        if (heightStates%1 != 0){
            heightStates++;
        }
        
        _maxWidthSates = (int)widthStates;
        //System.out.println(_maxWidthSates+"x"+_maxHeightSates+"="+String.valueOf(_maxHeightSates*_maxWidthSates));
    }
    
    public void addConnection(String pStartState, String pFinalState, String pKey){
        connectTwoStates(pStartState, pFinalState, pKey);
    }
    
    private void connectTwoStates(String pStartState, String pFinalState, String pKey){
        JState start = _states.search(new JState(pStartState, false,  -1, -1)).getValue();
        JState end = _states.search(new JState(pFinalState, false, -1, -1)).getValue();
        
        int x1 = start.getLabel().getX();
        int y1 = start.getLabel().getY();
        int x2 = end.getLabel().getX();
        int y2 = end.getLabel().getY();
        
        int x1Add = start.getLabel().getWidth()/4;
        int y1Add = start.getLabel().getHeight()/4;
        int x2Add = end.getLabel().getWidth()/4;
        int y2Add = end.getLabel().getHeight()/4;
        
        x1 += x1<x2?3*x1Add:x1Add;
        y1 += y1<y2?3*y1Add:y1Add;
        x2 += x2<x1?3*x2Add:x2Add;
        y2 += y2<y1?3*y2Add:y2Add;
        
        JConnection connection = new JConnection(pStartState, pFinalState, pKey, x1, y1, x2, y2);
        _connections.insert(connection);
        addToPane(connection.getArrow(), 3);
        addToPane(connection.getLabel(), 3);
    }
    
    public void newState(String pID, boolean pIsFinal){
        int posX = (int)(Math.random()*(STATE_SPACE - JState.STATE_SIZE - RAND_BORDER));
        int posY = (int)(Math.random()*(STATE_SPACE - JState.STATE_SIZE - RAND_BORDER));
        
        System.out.println("new state gui:"+posX+","+posY);
        
        if (_lastState.getX() == _maxWidthSates){
            _lastState.setLocation(0, _lastState.getY()+1);
        } else {
            _lastState.setLocation(_lastState.getX()+1, _lastState.getY());
        }
        
        if (_lastState.getX() == 0){
            posX += _lastState.getX()*STATE_SPACE + RAND_BORDER;
        } else {
            posX += _lastState.getX()*STATE_SPACE;
        }
        
        posY += _lastState.getY()*STATE_SPACE;
        
        
        newState(pID, pIsFinal, posX, posY);
    }
    
    public void setStateColor(String pState, int pColorType){
        JState dummy = new JState(pState, false, 0, 0);
        JState state = _states.search(dummy).getValue();
        if (pColorType == DEFAULT_STATE_COLOR){
            state.setDefaultStateColor();
        } else if (pColorType == CHECK_STATE_COLOR){
            state.setCheckStateColor();
        } else if (pColorType == BAD_STATE_COLOR){
            state.setBadStateColor();
        } else if (pColorType == GOOD_STATE_COLOR){
            state.setCorrectStateColor();
        }
    }
    
}
