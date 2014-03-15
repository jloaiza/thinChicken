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
public class JAutoArrow extends JArrow{
    private static final int ARR_SIZE = 6;

    
    
    public JAutoArrow(int pX, int pY){
        super(pX, pY-25, pX+25, pY);
        setVisible(true);
        setEnabled(true);
        setBounds(0, 0, (_x2>_x1?_x2:_x1), (_y2>_y1?_y2:_y1));
        _color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
    }
        
    private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        int dx = x2 - x1, dy = y2 - y1;
        //double angle = Math.atan2(dy, dx);
        //int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        //at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.setColor(_color);
        g.setStroke(new BasicStroke(2));
        g.drawLine(0, 0, 0, dy);
        g.drawLine(0, 0, dx, 0);
        g.drawLine(dx, 0, dx, dy);
        g.fillPolygon(new int[] {dx-ARR_SIZE/2, dx+ARR_SIZE/2, dx},
                      new int[] {dy-ARR_SIZE/2, dy-ARR_SIZE/2, dy}, 3);
    }

    @Override
    public void paintComponent(Graphics g) {
        drawArrow(g, _x1, _y1, _x2, _y2);
    }
}
