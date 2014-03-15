/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import itarea.Facade;
import itarea.LoadFile;
import itarea.PathRegister;
import itarea.SaveFile;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author jmloaiza
 */
public final class StartWindow extends javax.swing.JFrame {
    
    private static final int SLIDER_MOVE_SIZE = 10; 
    private static final int HORIZONTAL_SLIDER = 0;
    private static final int VERTICAL_SLIDER = 1;
    
    
    private boolean _onEditMode;
    private boolean _onScroll;
    
    private int _scrollValue;
    
    private final panPalette _palette;
    private GUIAutoHandler _guiAutoHandler;
    
    /**
     * Creates new form StartWindow
     */
    public StartWindow() {
        initComponents();
        _guiAutoHandler = new GUIAutoHandler(panAutomata);
        _onEditMode = false;
        _onScroll = false;
        _scrollValue = 0;
        _palette = new panPalette(this);
        _palette.setBounds(660, 25, _palette.getPreferredSize().width, _palette.getPreferredSize().height);
        jLayeredPane1.add(_palette);
        jLayeredPane1.setLayer(_palette, javax.swing.JLayeredPane.PALETTE_LAYER);        
        
        onEditMode(false);
        
        this.setLocationRelativeTo(null);
        
        lblViewerAuto.setVisible(false);
        
        panAutomata.setSize(500, 500);
    }          
    
    private void showAutoSliders(){
        slider1.setVisible(true);
        slider1.setEnabled(true);
        slider2.setVisible(true);
        slider2.setEnabled(true);
        bttUpS2.setVisible(true);
        bttUpS2.setEnabled(true);
        bttDownS2.setVisible(true);
        bttDownS2.setEnabled(true);
        bttLeftS1.setVisible(true);
        bttLeftS1.setEnabled(true);
        bttRightS1.setVisible(true);
        bttRightS1.setEnabled(true);
        bttSlider1.setVisible(true);
        bttSlider1.setEnabled(true);
        bttSlider2.setVisible(true);
        bttSlider2.setEnabled(true);
    }
    
    private void hideAutoSliders(){
        slider1.setVisible(false);
        slider1.setEnabled(false);
        slider2.setVisible(false);
        slider2.setEnabled(false);
        bttUpS2.setVisible(false);
        bttUpS2.setEnabled(false);
        bttDownS2.setVisible(false);
        bttDownS2.setEnabled(false);
        bttLeftS1.setVisible(false);
        bttLeftS1.setEnabled(false);
        bttRightS1.setVisible(false);
        bttRightS1.setEnabled(false);
        bttSlider1.setVisible(false);
        bttSlider1.setEnabled(false);
        bttSlider2.setVisible(false);
        bttSlider2.setEnabled(false);
    }
    
    protected void onEditMode(boolean pOnEdit){
        if (pOnEdit){
            _onEditMode = true;
            _palette.setVisible(true);
            _palette.setEnabled(true);
            _palette.requestFocus();
            
            scrollEntry.setVisible(false);
            scrollExit.setVisible(false);
            scrollProd.setVisible(false);
        } else {
            _onEditMode = false;
            _palette.setVisible(false);
            _palette.setEnabled(false);
            
            scrollEntry.setVisible(true);
            scrollExit.setVisible(true);
            scrollProd.setVisible(true);
            
            this.requestFocus();
        }
            
    }
    
    public void setExitText(String pExit){
        textExit.setText("");
        textExit.insert(pExit, 0);
    }
    
    public void setProdText(String pProd){
        textProd.setText("");
        textProd.insert(pProd, 0);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        bttSaveProd = new javax.swing.JLabel();
        bttSaveExit = new javax.swing.JLabel();
        bttOpenEntry = new javax.swing.JLabel();
        bttSaveEntry = new javax.swing.JLabel();
        bttOpenAuto = new javax.swing.JLabel();
        bttEditAuto = new javax.swing.JLabel();
        bttCleanAuto = new javax.swing.JLabel();
        bttSaveAuto = new javax.swing.JLabel();
        bttCompile = new javax.swing.JLabel();
        lblEntry = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        lblAutomata = new javax.swing.JLabel();
        bttSlider2 = new javax.swing.JLabel();
        bttSlider1 = new javax.swing.JLabel();
        bttDownS2 = new javax.swing.JLabel();
        bttUpS2 = new javax.swing.JLabel();
        bttRightS1 = new javax.swing.JLabel();
        bttAutoViewer = new javax.swing.JLabel();
        bttLeftS1 = new javax.swing.JLabel();
        slider2 = new javax.swing.JLabel();
        slider1 = new javax.swing.JLabel();
        debugMode = new javax.swing.JCheckBox();
        scrollProd = new javax.swing.JScrollPane();
        textProd = new javax.swing.JTextArea();
        scrollExit = new javax.swing.JScrollPane();
        textExit = new javax.swing.JTextArea();
        scrollEntry = new javax.swing.JScrollPane();
        textEntry = new javax.swing.JTextArea();
        containerAutomata = new javax.swing.JPanel();
        lblViewerAuto = new javax.swing.JLabel();
        panAutomata = new javax.swing.JLayeredPane();
        bkg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MiniCompilador");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(950, 544));

        bttSaveProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bttSave.png"))); // NOI18N
        bttSaveProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttSaveProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttSaveProdMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttSaveProd);
        bttSaveProd.setBounds(920, 385, 27, 24);

        bttSaveExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bttSave.png"))); // NOI18N
        bttSaveExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttSaveExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttSaveExitMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttSaveExit);
        bttSaveExit.setBounds(448, 385, 27, 24);

        bttOpenEntry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bttOpen.png"))); // NOI18N
        bttOpenEntry.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttOpenEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttOpenEntryMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttOpenEntry);
        bttOpenEntry.setBounds(869, 41, 27, 24);

        bttSaveEntry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bttSave.png"))); // NOI18N
        bttSaveEntry.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttSaveEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttSaveEntryMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttSaveEntry);
        bttSaveEntry.setBounds(920, 41, 27, 24);

        bttOpenAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bttOpen.png"))); // NOI18N
        bttOpenAuto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttOpenAuto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttOpenAutoMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttOpenAuto);
        bttOpenAuto.setBounds(564, 41, 27, 24);

        bttEditAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bttEdit.png"))); // NOI18N
        bttEditAuto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttEditAuto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttEditAutoMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttEditAuto);
        bttEditAuto.setBounds(513, 41, 27, 24);

        bttCleanAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bttClean.png"))); // NOI18N
        bttCleanAuto.setToolTipText("");
        bttCleanAuto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttCleanAuto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttCleanAutoMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttCleanAuto);
        bttCleanAuto.setBounds(468, 41, 27, 24);

        bttSaveAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bttSave.png"))); // NOI18N
        bttSaveAuto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLayeredPane1.add(bttSaveAuto);
        bttSaveAuto.setBounds(613, 41, 27, 24);

        bttCompile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bttCompile.png"))); // NOI18N
        bttCompile.setToolTipText("");
        bttCompile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttCompile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttCompileMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttCompile);
        bttCompile.setBounds(150, 43, 91, 20);

        lblEntry.setForeground(new java.awt.Color(255, 255, 255));
        lblEntry.setText("Entrada");
        jLayeredPane1.add(lblEntry);
        lblEntry.setBounds(660, 42, 100, 21);

        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.setText("Salida");
        jLayeredPane1.add(lblExit);
        lblExit.setBounds(10, 387, 150, 21);

        lblAutomata.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblAutomata.setForeground(new java.awt.Color(255, 255, 255));
        lblAutomata.setText("Automata");
        jLayeredPane1.add(lblAutomata);
        lblAutomata.setBounds(10, 42, 110, 21);

        bttSlider2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/slider2btt.png"))); // NOI18N
        bttSlider2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttSlider2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttSlider2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bttSlider2MouseReleased(evt);
            }
        });
        bttSlider2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bttSlider2MouseDragged(evt);
            }
        });
        jLayeredPane1.add(bttSlider2);
        bttSlider2.setBounds(626, 210, 11, 22);

        bttSlider1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/slider1btt.png"))); // NOI18N
        bttSlider1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttSlider1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttSlider1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bttSlider1MouseReleased(evt);
            }
        });
        bttSlider1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bttSlider1MouseDragged(evt);
            }
        });
        jLayeredPane1.add(bttSlider1);
        bttSlider1.setBounds(300, 359, 22, 11);

        bttDownS2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/slider2D.png"))); // NOI18N
        bttDownS2.setToolTipText("");
        bttDownS2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttDownS2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttDownS2MouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttDownS2);
        bttDownS2.setBounds(620, 354, 20, 20);

        bttUpS2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/slider2U.png"))); // NOI18N
        bttUpS2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttUpS2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttUpS2MouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttUpS2);
        bttUpS2.setBounds(620, 70, 20, 19);

        bttRightS1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/slider1R.png"))); // NOI18N
        bttRightS1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttRightS1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttRightS1MouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttRightS1);
        bttRightS1.setBounds(600, 355, 18, 18);

        bttAutoViewer.setText("Viewer");
        bttAutoViewer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttAutoViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttAutoViewerMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttAutoViewer);
        bttAutoViewer.setBounds(400, 41, 46, 21);

        bttLeftS1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/slider1L.png"))); // NOI18N
        bttLeftS1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttLeftS1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttLeftS1MouseClicked(evt);
            }
        });
        jLayeredPane1.add(bttLeftS1);
        bttLeftS1.setBounds(5, 355, 18, 18);

        slider2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/slider2.png"))); // NOI18N
        jLayeredPane1.add(slider2);
        slider2.setBounds(620, 69, 20, 304);

        slider1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/slider1.png"))); // NOI18N
        jLayeredPane1.add(slider1);
        slider1.setBounds(5, 355, 613, 18);

        debugMode.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        debugMode.setText("Paso a paso");
        jLayeredPane1.add(debugMode);
        debugMode.setBounds(250, 41, 89, 22);

        scrollProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        textProd.setEditable(false);
        textProd.setBackground(java.awt.Color.white);
        textProd.setColumns(20);
        textProd.setRows(5);
        textProd.setDisabledTextColor(java.awt.Color.white);
        scrollProd.setViewportView(textProd);

        jLayeredPane1.add(scrollProd);
        scrollProd.setBounds(475, 409, 475, 111);

        scrollExit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        textExit.setEditable(false);
        textExit.setBackground(java.awt.Color.white);
        textExit.setColumns(20);
        textExit.setRows(5);
        textExit.setDisabledTextColor(java.awt.Color.white);
        scrollExit.setViewportView(textExit);

        jLayeredPane1.add(scrollExit);
        scrollExit.setBounds(0, 409, 475, 111);

        textEntry.setBackground(java.awt.Color.white);
        textEntry.setColumns(20);
        textEntry.setRows(5);
        textEntry.setDisabledTextColor(java.awt.Color.white);
        scrollEntry.setViewportView(textEntry);

        jLayeredPane1.add(scrollEntry);
        scrollEntry.setBounds(652, 65, 298, 314);

        containerAutomata.setBackground(java.awt.Color.white);
        containerAutomata.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblViewerAuto.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblViewerAuto.setForeground(java.awt.Color.lightGray);
        lblViewerAuto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblViewerAuto.setText("Visualizador activado");
        containerAutomata.add(lblViewerAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 130, 620, -1));

        panAutomata.setBackground(new java.awt.Color(255, 255, 255));
        panAutomata.setOpaque(true);
        containerAutomata.add(panAutomata, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 290));

        jLayeredPane1.add(containerAutomata);
        containerAutomata.setBounds(0, 65, 621, 290);

        bkg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/bkg.jpg"))); // NOI18N
        bkg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bkgMouseClicked(evt);
            }
        });
        jLayeredPane1.add(bkg);
        bkg.setBounds(0, 0, 950, 544);

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bkgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bkgMouseClicked
        requestFocus();
    }//GEN-LAST:event_bkgMouseClicked

    private void bttSaveEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSaveEntryMouseClicked
        File file;
        if (evt.isShiftDown() || PathRegister.ENTRY_PATH.compareTo("") == 0){
            file = Utils.loadFilePathDialog("Guardar entrada", Utils.getTxtFilter(), "entrada.txt", true, this);
        } else {
            file = new File(PathRegister.ENTRY_PATH);
        }
        if (file != null){
            if (file.exists()){
                Object[] options = { "Aceptar", "Cancelar" };
                int selection = JOptionPane.showOptionDialog(null, 
                        "El archivo ya existe, ¿desea sobrescribirlo?", 
                        "Atención", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, options, options[0]);
                if (selection == 0){
                    SaveFile save = new SaveFile();
                    save.writeFile(file.getAbsolutePath(), textEntry.getText());
                    PathRegister.ENTRY_PATH = file.getAbsolutePath();
                }
            } else {
                SaveFile save = new SaveFile();
                save.writeFile(file.getAbsolutePath(), textEntry.getText());
                PathRegister.ENTRY_PATH = file.getAbsolutePath();
            }
            
        }
        
    }//GEN-LAST:event_bttSaveEntryMouseClicked

    private void bttEditAutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttEditAutoMouseClicked
        onEditMode(true);
    }//GEN-LAST:event_bttEditAutoMouseClicked

    private void bttCleanAutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttCleanAutoMouseClicked
        
    }//GEN-LAST:event_bttCleanAutoMouseClicked

    private void bttLeftS1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttLeftS1MouseClicked
        moveHorizontalAutomata(-SLIDER_MOVE_SIZE);
    }//GEN-LAST:event_bttLeftS1MouseClicked

    private void bttRightS1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttRightS1MouseClicked
        moveHorizontalAutomata(SLIDER_MOVE_SIZE);
    }//GEN-LAST:event_bttRightS1MouseClicked

    private void bttDownS2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttDownS2MouseClicked
        moveVerticalAutomata(SLIDER_MOVE_SIZE);
    }//GEN-LAST:event_bttDownS2MouseClicked

    private void bttUpS2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttUpS2MouseClicked
        moveVerticalAutomata(-SLIDER_MOVE_SIZE);        
    }//GEN-LAST:event_bttUpS2MouseClicked

    private void bttSlider2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSlider2MouseDragged
        if ((bttSlider2.getY()+evt.getY()>slider2.getY()+bttUpS2.getHeight()+2)
                && (bttSlider2.getY()+evt.getY()<slider2.getY()+slider2.getHeight()-bttUpS2.getHeight()-2-bttSlider2.getHeight())){

            bttSlider2.setLocation(bttSlider2.getX(), bttSlider2.getY()+evt.getY());
        }
        _scrollValue = evt.getY();
        
    }//GEN-LAST:event_bttSlider2MouseDragged

    private void bttSlider2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSlider2MouseReleased
        bttSlider2.setLocation(bttSlider2.getX(), 210);
        _onScroll = false;
        _scrollValue = 0;
    }//GEN-LAST:event_bttSlider2MouseReleased

    private void bttSlider1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSlider1MouseDragged
        if ((bttSlider1.getX()+evt.getX()>slider1.getX()+bttLeftS1.getWidth()+2)
                    && (bttSlider1.getX()+evt.getX()<slider1.getX()+slider1.getWidth()-bttLeftS1.getWidth()-2-bttSlider1.getWidth())){

            bttSlider1.setLocation(bttSlider1.getX()+evt.getX(), bttSlider1.getY());
        } 
        _scrollValue = evt.getX();
    }//GEN-LAST:event_bttSlider1MouseDragged

    private void bttSlider1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSlider1MouseReleased
        bttSlider1.setLocation(300, bttSlider1.getY());
        _onScroll = false;
        _scrollValue = 0;
    }//GEN-LAST:event_bttSlider1MouseReleased

    private void bttSlider2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSlider2MousePressed
        _onScroll = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                moveSliderAutomata(VERTICAL_SLIDER);
            }
        }).start();
    }//GEN-LAST:event_bttSlider2MousePressed

    private void bttSlider1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSlider1MousePressed
        _onScroll = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                moveSliderAutomata(HORIZONTAL_SLIDER);
            }
        }).start();
    }//GEN-LAST:event_bttSlider1MousePressed

    private void bttSaveExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSaveExitMouseClicked
        System.out.println(textExit.getText());
    }//GEN-LAST:event_bttSaveExitMouseClicked

    private void bttOpenEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttOpenEntryMouseClicked
        
        File file = Utils.loadFilePathDialog("Abrir entrada", Utils.getTxtFilter(), "entrada.txt", false, this);
        if (file != null){
            if (!file.exists()){
                JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo de entrada "+file.getName(), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Facade.getInstance().setEntryPath(file.getAbsolutePath());
                setEntryText(Facade.getInstance().loadEntry());
            }
        }
        
    }//GEN-LAST:event_bttOpenEntryMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        
    }//GEN-LAST:event_formKeyReleased

    private void bttSaveProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSaveProdMouseClicked
        
    }//GEN-LAST:event_bttSaveProdMouseClicked

    private void bttOpenAutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttOpenAutoMouseClicked
        loadAuto(this);
    }//GEN-LAST:event_bttOpenAutoMouseClicked

    private void bttCompileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttCompileMouseClicked
        boolean inDebug = debugMode.isSelected();
        compile(inDebug);
    }//GEN-LAST:event_bttCompileMouseClicked

    private void bttAutoViewerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttAutoViewerMouseClicked
        panAutomata.setVisible(false);
        lblViewerAuto.setVisible(true);
        hideAutoSliders();
        containerAutomata.remove(panAutomata);
        (new AutoViewer(this)).setVisible(true);
    }//GEN-LAST:event_bttAutoViewerMouseClicked
    
    protected void loadAuto(Component pParent){
        File file = Utils.loadFilePathDialog("Abrir automata", Utils.getTxtFilter(), "automata.txt", false, pParent);
        if (file != null){
            if (!file.exists()){
                JOptionPane.showMessageDialog(pParent, "No se ha encontrado el archivo de entrada "+file.getName(), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Facade.getInstance().setAutomataPath(file.getAbsolutePath());
                Facade.getInstance().loadAutomata();
            }
        }
    }
    
    protected void compile(boolean pDebugMode){
        Facade.getInstance().evaluateEntry(pDebugMode);
    }
    
    protected void showAutoAgain(){
        containerAutomata.add(panAutomata, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 290));
        lblViewerAuto.setVisible(false);
        panAutomata.setVisible(true);
        showAutoSliders();
    }
    
    public void setEntryText(String pEntry){
        textEntry.setText("");
        textEntry.insert(pEntry, 0);
    }
    
    
    
    private void moveSliderAutomata(int pDirection){
        while (_onScroll){
            if (pDirection == HORIZONTAL_SLIDER){
                moveHorizontalAutomata(_scrollValue*2);
            } else {
                moveVerticalAutomata(_scrollValue*2);
            }
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void moveHorizontalAutomata(int pValue){
        if (panAutomata.getX()-pValue <= 0){
            panAutomata.setSize(panAutomata.getWidth()+pValue, panAutomata.getHeight());
            panAutomata.setLocation(panAutomata.getX()-pValue, panAutomata.getY());
        } else {
            panAutomata.setSize(containerAutomata.getWidth(), panAutomata.getHeight());
            panAutomata.setLocation(0, panAutomata.getY());
        }
    }
    
    private void moveVerticalAutomata(int pValue){
        if (panAutomata.getY()-pValue <= 0){
            panAutomata.setSize(panAutomata.getWidth(), panAutomata.getHeight()+pValue);
            panAutomata.setLocation(panAutomata.getX(), panAutomata.getY()-pValue);
        } else {
            panAutomata.setSize(panAutomata.getWidth(), containerAutomata.getHeight());
            panAutomata.setLocation(panAutomata.getX(), 0);
        }
    }
    
    public GUIAutoHandler getAutoHandler(){
        return _guiAutoHandler;
    }

    protected JLayeredPane getAutoPane(){
        return panAutomata;
    }
    
//    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new StartWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bkg;
    private javax.swing.JLabel bttAutoViewer;
    private javax.swing.JLabel bttCleanAuto;
    private javax.swing.JLabel bttCompile;
    private javax.swing.JLabel bttDownS2;
    private javax.swing.JLabel bttEditAuto;
    private javax.swing.JLabel bttLeftS1;
    private javax.swing.JLabel bttOpenAuto;
    private javax.swing.JLabel bttOpenEntry;
    private javax.swing.JLabel bttRightS1;
    private javax.swing.JLabel bttSaveAuto;
    private javax.swing.JLabel bttSaveEntry;
    private javax.swing.JLabel bttSaveExit;
    private javax.swing.JLabel bttSaveProd;
    private javax.swing.JLabel bttSlider1;
    private javax.swing.JLabel bttSlider2;
    private javax.swing.JLabel bttUpS2;
    private javax.swing.JPanel containerAutomata;
    private javax.swing.JCheckBox debugMode;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lblAutomata;
    private javax.swing.JLabel lblEntry;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblViewerAuto;
    private javax.swing.JLayeredPane panAutomata;
    private javax.swing.JScrollPane scrollEntry;
    private javax.swing.JScrollPane scrollExit;
    private javax.swing.JScrollPane scrollProd;
    private javax.swing.JLabel slider1;
    private javax.swing.JLabel slider2;
    private javax.swing.JTextArea textEntry;
    private javax.swing.JTextArea textExit;
    private javax.swing.JTextArea textProd;
    // End of variables declaration//GEN-END:variables
}
