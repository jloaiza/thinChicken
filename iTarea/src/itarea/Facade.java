/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package itarea;

import gui.StartWindow;
import gui.GUIAutoHandler;
import automata.Automata;

/**
 *
 * @author jmloaiza
 */
public class Facade {
    private StartWindow _startWindow;
    private Automata _automata;
    
    private static Facade _instance = null;
    
    private Facade(){
        _startWindow = new StartWindow();
        _automata = Automata.getInstance();
        _startWindow.setVisible(true);
    }
    
    public static Facade getInstance(){
        if (_instance == null){
            _instance = new Facade();
        }
        return _instance;
    }
    
    public static void startAll(){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Facade.getInstance();
            }
        });
    }
    
    public void guiSetStateCountToLoad(int pCount){
        _startWindow.getAutoHandler().setItemsCountToLoad(pCount);
    }
    
    public void guiSetStartState(String pStateID){
        _startWindow.getAutoHandler().setStartState(pStateID);
    }
    
    public void guiNewState(String pID, boolean pIsFinal){
        _startWindow.getAutoHandler().newState(pID, pIsFinal);
    }
    
    public void guiAddConnection(String pStartState, String pEndState, String pKey, boolean pIsSecondConnection){
        _startWindow.getAutoHandler().addConnection(pStartState, pEndState, pKey, pIsSecondConnection);
    }
    
    public void guiCheckStateColor(String pID){
        _startWindow.getAutoHandler().setStateColor(pID, GUIAutoHandler.CHECK_STATE_COLOR);
    }
    
    public void guiDefaultStateColor(String pID){
        _startWindow.getAutoHandler().setStateColor(pID, GUIAutoHandler.DEFAULT_STATE_COLOR);
    }
    
    public void guiBadStateColor(String pID){
        _startWindow.getAutoHandler().setStateColor(pID, GUIAutoHandler.BAD_STATE_COLOR);
    }
    
    public void guiGoodStateColor(String pID){
        _startWindow.getAutoHandler().setStateColor(pID, GUIAutoHandler.GOOD_STATE_COLOR);
    }
    
    public void guiSetEntryText(String pEntry){
        _startWindow.setEntryText(pEntry);
    }
    
    public void guiSetExitText(String pExit){
        _startWindow.setExitText(pExit);
    }
}
