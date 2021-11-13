/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkergame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author EL.2021.T2D0G7
 */
public class GameInterface extends JDialog {
    private JPanel jpMainPanel;
    private JPanel jpScorePanel = new JPanel();
    private final JLabel lblScore = new JLabel();
    private final Random rn = new Random();
    private Color[] arrColors;
    private JButton btnCompare;
    private final JButton btnControlColor = new JButton();
    private int intTrackChecks = 0;
    private int intScore = 0;
    private int intMatchingTries = 0;
    
    public GameInterface(CheckerGame frmParent, String strLevel) {
        super(frmParent, strLevel);
        jpScorePanel.add(lblScore);
        jpScorePanel.setBackground(new Color(255, 255, 255));
        
        if(strLevel.equals("Level One")) {
            arrColors = new Color[(4 * 4)];
            setColors();
            GameInterface.this.add(createInterface(new Dimension(400, 300), 4, 4));
            jpScorePanel.setPreferredSize(new Dimension(400, 40));
            GameInterface.this.add(jpScorePanel, BorderLayout.SOUTH);
                        
        } else if(strLevel.equals("Level Two")) {
            arrColors = new Color[(6 * 6)];
            setColors();
            GameInterface.this.add(createInterface(new Dimension(500, 400), 6, 6));
            jpScorePanel.setPreferredSize(new Dimension(500, 40));
            GameInterface.this.add(jpScorePanel, BorderLayout.SOUTH);
        }
        
        GameInterface.this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        GameInterface.this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(GameInterface.this, "Would you like to close the ongoing game attempt?",
                    "Exit?", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
                    GameInterface.this.dispose();
                    
                }
            }
        });
    }
    
    private void setColors() {
        int x = 0;
        
        for(int i = 0; i < (arrColors.length / 2); i++) {
            Color clColor = new Color(rn.nextInt(255)+1, rn.nextInt(255) + 1, rn.nextInt(255) + 1);
            arrColors[i] = clColor;
        }
        
        for(int i = arrColors.length / 2; i < arrColors.length; i++) {
            arrColors[i] = arrColors[x];
            x++;
            
        }
        
        for (int i = arrColors.length - 1; i > 0; i--) {
            int index = rn.nextInt(i + 1);
            // Simple swap
            Color a = arrColors[index];
            arrColors[index] = arrColors[i];
            arrColors[i] = a;
        }
    }
    
    private JPanel createInterface(Dimension d, int rows, int columns) {
        this.setSize(d);
        this.setLocationRelativeTo(null);
        
        jpMainPanel = new JPanel(new GridLayout(rows, columns, 10, 10));
        for(int i = 0; i < arrColors.length; i++) {
            jpMainPanel.add(button("ColorX"));
        }
        
        jpMainPanel.setBackground(new Color(255, 255, 255));
        return jpMainPanel;
    }
    
    private JButton button(String strText) {
        JButton btnButton = new JButton(strText);
        
        btnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                btnButton.setBackground(arrColors[jpMainPanel.getComponentZOrder(btnButton)]);
                
                intTrackChecks++;
                if(intTrackChecks == 1) {
                    btnCompare = btnButton;
                
                } else if(intTrackChecks == 2) {
                    
                    intTrackChecks = 0;
                    
                    if(btnButton != btnCompare) {
                        intMatchingTries++;
                    }
                    
                    if(btnButton.getBackground().equals(btnCompare.getBackground())
                            && btnButton != btnCompare) {
                        
                        intScore++;
                        lblScore.setText("Score: " + intScore);
                        btnButton.setEnabled(false);
                        btnCompare.setEnabled(false);
                        
                        if(intScore == (arrColors.length / 2)) {
                            JOptionPane.showMessageDialog(GameInterface.this, 
                                    "The game is over.\nAll colours have been "
                                            + "checked successfully.\nYour score "
                                            + "is " +intScore,
                                    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
                            GameInterface.this.dispose();
                        }
                        
                    } else {
                        btnButton.setBackground(btnControlColor.getBackground());
                        btnCompare.setBackground(btnControlColor.getBackground());
                    }
                }
            }
        });
        
        btnButton.setPreferredSize(new Dimension(120, 90));
        return btnButton;
    }
    
    public int getScore() {
        return intScore;
    }
    
    public int getCheckingAttempts() {
        return intMatchingTries;
    }
    
    public void hideScore() {
        GameInterface.this.remove(jpScorePanel);
        SwingUtilities.updateComponentTreeUI(GameInterface.this);
    }
}