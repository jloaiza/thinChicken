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
    public static final int FIRST_CONNECTION = 0;
    public static final int SECOND_CONNECTION = 1;
    private static final int LBL_RELATIVE_LOCATION= 15;
    
    private String _id;
    private JArrow _arrow;
    private JLabel _label;
    private int _type;
    
    public JConnection(String pStartState, String pEndState, String pKey, int pConnectionType, int pX1, int pY1, int pX2, int pY2){
        _id = pStartState + "->" + pEndState;
        _arrow = new JArrow(pX1, pY1, pX2, pY2);
        _type = pConnectionType;
        initLabel(pKey, pX1, pY1, pX2, pY2);
    }
    
    private void initLabel(String pKey, int pX1, int pY1, int pX2, int pY2){
        _label = new JLabel(pKey);
        _label.setForeground(_arrow.getColor());
        int x = pX1>pX2?pX2:pX1 + Math.abs(pX1-pX2)/2 
                + (_type==FIRST_CONNECTION?-LBL_RELATIVE_LOCATION:LBL_RELATIVE_LOCATION/2);
        int y = pY1>pY2?pY2:pY1 + Math.abs(pY1-pY2)/2 
                + (_type==FIRST_CONNECTION?-LBL_RELATIVE_LOCATION:LBL_RELATIVE_LOCATION/2);
        _label.setEnabled(true);
        _label.setVisible(true);
        _label.setBounds(x, y, pKey.length()*15, 15);
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
