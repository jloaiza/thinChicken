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
    
    public void guiSetExitText(String pExit){
        _startWindow.setExitText(pExit);
    }

    public void guiSetProductionsText(String pText){
        _startWindow.setProdText(pText);
    }

    public void guiAddState(String pID, boolean pIsFinal){
        _startWindow.getAutoHandler().newState(pID, pIsFinal);
    }
    
    public void guiSetStartState(String pID){
        _startWindow.getAutoHandler().setStartState(pID);
    }
    
    public void guiSetStateCount(int pCount){
        _startWindow.getAutoHandler().setItemsCountToLoad(pCount);
    }
    
    public void guiAddConnection(String pStartState, String pFinalState, String pKey){
        _startWindow.getAutoHandler().addConnection(pStartState, pFinalState, pKey);
    }
    
    public void loadAutomata(){
        //_automata.clear();
        LoadAutomata load = new LoadAutomata();
        load.setDirection(PathRegister.AUTOMATA_PATH);
        load.load();
        _automata.showMe();
    }
    
    public String loadEntry(){
        LoadFile lf = new LoadFile();
        return lf.readFile(PathRegister.ENTRY_PATH);
    }
    
    public void evaluateEntry(boolean pDebugMode){
        _automata.setOnDebugMode(pDebugMode);
        new Thread(new Runnable() {

            @Override
            public void run() {
                AssessAutomata evaluate = new AssessAutomata();
                evaluate.setDirection(PathRegister.ENTRY_PATH);
                String exit = evaluate.start();
                _startWindow.setExitText(exit);
        // FALTAN COSAS
            }
        }).start();
    }
    
    public void setProdPath(String pPath){
        PathRegister.PRODUCTIONS_PATH = pPath;
    }
    
    public void setExitPath(String pPath){
        PathRegister.EXIT_PATH = pPath;
    }
    
    public void setAutomataPath(String pPath){
        PathRegister.AUTOMATA_PATH = pPath;
    }
    
    public void setEntryPath(String pPath){
        PathRegister.ENTRY_PATH = pPath;
    }
}
