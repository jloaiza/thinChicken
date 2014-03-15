/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javax.swing.JLabel;

/**
 *
 * @author jmloaiza
 */
public class JConnection implements Comparable<JConnection>{
    private static final int CHAR_SPACE = 15;
    private String _id;
    private JArrow _arrow;
    private JLabel _label;
    
    public JConnection(String pStartState, String pEndState, String pKey, int pX1, int pY1, int pX2, int pY2){
        _id = pStartState + "->" + pEndState;
        if (pStartState.compareTo(pEndState) == 0){
            _arrow = new JAutoArrow(pX1, pY1);
            initLabel(pKey, _arrow._x1, _arrow._y1, _arrow._x2, _arrow._y2);
        } else {
            _arrow = new JArrow(pX1, pY1, pX2, pY2);
            initLabel(pKey, pX1, pY1, pX2, pY2);
        }
        
    }
    
    private void initLabel(String pKey, int pX1, int pY1, int pX2, int pY2){
        _label = new JLabel(pKey);
        _label.setForeground(_arrow.getColor());
        int x = pX1>pX2?pX2:pX1 + Math.abs(pX1-pX2)/2 - pKey.length()*CHAR_SPACE/2;
        int y = pY1>pY2?pY2:pY1 + Math.abs(pY1-pY2)/2 - CHAR_SPACE/2;
        _label.setEnabled(true);
        _label.setVisible(true);
        _label.setBounds(x, y, pKey.length()*CHAR_SPACE, CHAR_SPACE);
    }

    public String getID(){
        return _id;
    }
    
    public JArrow getArrow(){
        return _arrow;
    }
    
    public JLabel getLabel(){
        return _label;
    }
    
    @Override
    public int compareTo(JConnection pConnection) {
        return _id.compareTo(pConnection.getID());
    }
}
