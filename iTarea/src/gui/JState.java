/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Point;
import javax.swing.JLabel;

/**
 *
 * @author jmloaiza
 */
public class JState implements Comparable<JState>{
    public static final int STATE_SIZE = 58;
    private JLabel _label;
    private Point _clickPoint;
    private boolean _isFinal;
    
    public JState(String pName, boolean pIsFinal, int pX, int pY){
        _label = new JLabel(pName);
        _isFinal = pIsFinal;
        _label.setEnabled(true);
        _label.setVisible(true);
        _label.setHorizontalTextPosition(JLabel.CENTER);
        _label.setVerticalTextPosition(JLabel.CENTER);
        if (!pIsFinal){
            _label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/figStateUn.png")));
        } else {
            _label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/figFinalStateUn.png")));
        }
        
        _label.setBounds(pX, pY, STATE_SIZE, STATE_SIZE);
        _clickPoint = new Point(0, 0);
        addListener();
    }
    
    public void moveMe(int pX, int pY){
        _label.setLocation(pX, pY);        
    }
    
    public void setCheckStateColor(){
        if (_isFinal){
            _label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/figFinalStateCh.png")));
        } else {
            _label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/figStateCh.png")));
        }
        
    }
    
    public void setDefaultStateColor(){
        if (_isFinal){
            _label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/figFinalStateUn.png")));
        } else {
            _label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/figStateUn.png")));
        }
    }
    
    public void setBadStateColor(){
        if (_isFinal){
            _label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/figFinalStateBad.png")));
        } else {
            _label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/figStateBad.png")));
        }
    }
    
    public void setCorrectStateColor(){
        _label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/figFinalStateGood.png")));
    }
    
    public boolean isFinal(){
        return _isFinal;
    }
    
    public JLabel getLabel(){
        return _label;
    }
    
    @Override
    public int compareTo(JState pState) {
        return _label.getText().compareTo(pState.getLabel().getText());
    }
    
    private void addListener(){
        _label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelMouseClicked(evt);
            }
        });
        _label.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    labelMouseDragged(evt);
                }
            });
    }
    
    
    private void labelMouseClicked(java.awt.event.MouseEvent evt){
        _clickPoint = new Point(evt.getPoint());
        System.out.println("sdkjd");
    }
    
    private void labelMouseDragged(java.awt.event.MouseEvent evt) {                                      
        System.out.println("drag relative: " + evt.getPoint().toString());
        //evt.translatePoint(_label.getX(), _label.getY());
        //int posY = _label.getY()+evt.getY();
        int posX = _label.getX()+evt.getX()-(int)_clickPoint.getX();
        int posY = _label.getY()+evt.getY()-(int)_clickPoint.getY();
        _label.setLocation(posX, posY);
    }      
    
}
