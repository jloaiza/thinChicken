/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JComponent;

/**
 *
 * @author jmloaiza
 */
public class JArrow extends JComponent{
    private static final int ARR_SIZE = 6;
    
    private final int _x1;
    private final int _y1;
    private final int _x2;
    private final int _y2;
    private final Color _color;
    
    
    public JArrow(int pX1, int pY1, int pX2, int pY2){
        _x1 = pX1;
        _y1 = pY1;
        _x2 = pX2;
        _y2 = pY2;
        setVisible(true);
        setEnabled(true);
        setBounds(0, 0, (pX2>pX1?pX2:pX1), (pY2>pY1?pY2:pY1));
        _color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
    }
    
    public Color getColor(){
        return _color;
    }
    
    private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.setColor(_color);
        g.setStroke(new BasicStroke(2));
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

    @Override
    public void paintComponent(Graphics g) {
        drawArrow(g, _x1, _y1, _x2, _y2);
    }
}
