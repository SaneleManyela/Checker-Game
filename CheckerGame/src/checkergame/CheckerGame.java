/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkergame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author EL.2021.T2D0G7
 */
public class CheckerGame extends JFrame implements ActionListener {
    JPanel jpPanel = new JPanel();
    JLabel lblInstruction = new JLabel();
    
    JMenuItem mnuItemExit = new JMenuItem("Exit");
    
    JMenuItem mnuItemLevelOne = new JMenuItem("Level One");
    JMenuItem mnuItemLevelTwo = new JMenuItem("Level Two");
    
    JMenuItem mnuItemViewScore = new JMenuItem("View Score");
    JMenuItem mnuItemHideScore = new JMenuItem("Hide Score");
    
    JRadioButtonMenuItem rbMnuItemWindows = new JRadioButtonMenuItem("Windows");
    JRadioButtonMenuItem rbMnuItemMotif = new JRadioButtonMenuItem("Motif");
    JRadioButtonMenuItem rbMnuItemMetal = new JRadioButtonMenuItem("Metal");
        
    JMenuItem mnuItemAbout = new JMenuItem("About");
        
    GameInterface game;
    
    public CheckerGame() {
        super("Checkers Game");
        CheckerGame.this.setSize(500, 500);
        CheckerGame.this.setLocationRelativeTo(null);
        CheckerGame.this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        CheckerGame.this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(CheckerGame.this, "Would you like to exit the game app?",
                    "Exit?", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                    
                }
            }
        });
        
        JMenuBar mbMenuBar = new JMenuBar();
        CheckerGame.this.setJMenuBar(mbMenuBar);
        
        JMenu mnuFile = new JMenu("File");
        JMenu mnuNewGame = new JMenu("New Game");
        
        mnuItemLevelOne.addActionListener(CheckerGame.this);
        mnuItemLevelTwo.addActionListener(CheckerGame.this);
                
        mnuNewGame.add(mnuItemLevelOne);
        mnuNewGame.add(new JSeparator());
        mnuNewGame.add(mnuItemLevelTwo);
    
        mnuFile.add(mnuNewGame);
        mnuFile.add(new JSeparator());
        mnuItemExit.addActionListener(CheckerGame.this);
        mnuFile.add(mnuItemExit);
            
        JMenu mnuView = new JMenu("View");
        mnuItemViewScore.addActionListener(CheckerGame.this);
        mnuItemHideScore.addActionListener(CheckerGame.this);
        mnuView.add(mnuItemViewScore);
        mnuView.add(mnuItemHideScore);
        
        ButtonGroup bgMenuItems = new ButtonGroup();
        
        rbMnuItemWindows.addActionListener(CheckerGame.this);
        rbMnuItemMotif.addActionListener(CheckerGame.this);
        rbMnuItemMetal.addActionListener(CheckerGame.this);
        
        bgMenuItems.add(rbMnuItemWindows);
        bgMenuItems.add(rbMnuItemMotif);
        bgMenuItems.add(rbMnuItemMetal);
        
        JMenu mnuLookAndFeel = new JMenu("Look and Feel");
        mnuLookAndFeel.add(rbMnuItemWindows);
        mnuLookAndFeel.add(new JSeparator());
        mnuLookAndFeel.add(rbMnuItemMotif);
        mnuLookAndFeel.add(new JSeparator());
        mnuLookAndFeel.add(rbMnuItemMetal);
        mnuView.add(new JSeparator());
        mnuView.add(mnuLookAndFeel);
        
        JMenu mnuHelp = new JMenu("Help");
        mnuItemAbout.addActionListener(CheckerGame.this);
        mnuHelp.add(mnuItemAbout);
        
        mbMenuBar.add(mnuFile);
        mbMenuBar.add(mnuView);
        mbMenuBar.add(mnuHelp);
        
        lblInstruction.setText("Choose Level from file menu under new game.");
        lblInstruction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                lblInstruction.setForeground(new Color(0, 0, 255));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                lblInstruction.setForeground(new Color(0, 0, 0));
            }
        });
        jpPanel.setBackground(new Color(255, 255, 255));
        jpPanel.add(lblInstruction, BorderLayout.CENTER);
        CheckerGame.this.add(jpPanel);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new CheckerGame().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(mnuItemLevelOne)) {
            game = new GameInterface(this, mnuItemLevelOne.getText());
            game.setVisible(true);
            
        } else if(ae.getSource().equals(mnuItemLevelTwo)) {
            game = new GameInterface(this, mnuItemLevelTwo.getText());
            game.setVisible(true);
            
        } else if(ae.getSource().equals(mnuItemExit)) {
            if(JOptionPane.showConfirmDialog(CheckerGame.this, "Would you like to exit the game app?",
                    "Exit?", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
                    
            }
            
        } else if(ae.getSource().equals(mnuItemViewScore)) {
            if(game != null) {
                
                if(game.isShowing()) {
                    JOptionPane.showMessageDialog(CheckerGame.this, "Your current score is " +
                        game.getScore() + ", with " + game.getCheckingAttempts() +
                        " checking attempts", "SCORE", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    JOptionPane.showMessageDialog(CheckerGame.this, "Your last score is " +
                        game.getScore() + ", with " + game.getCheckingAttempts() +
                        " checking attempts", "SCORE", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(CheckerGame.this, "The is no game attempt currently going on!",
                        "WARNING", JOptionPane.WARNING_MESSAGE);
            }
            
        } else if(ae.getSource().equals(mnuItemHideScore)) {
            if(game != null) {
                game.hideScore();
                
            } else {
                JOptionPane.showMessageDialog(CheckerGame.this, "The is no game attempt currently going on!",
                        "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        
        } else if(ae.getSource().equals(rbMnuItemWindows)) {
            try {
                UIManager.setLookAndFeel(
                       ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));
                SwingUtilities.updateComponentTreeUI(CheckerGame.this);
                
            } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(ae.getSource().equals(rbMnuItemMotif)) {
            try {
                UIManager.setLookAndFeel(
                       ("com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
                SwingUtilities.updateComponentTreeUI(CheckerGame.this);
                
            } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(ae.getSource().equals(rbMnuItemMetal)) {
            try {
                UIManager.setLookAndFeel(
                       ("javax.swing.plaf.metal.MetalLookAndFeel"));
                SwingUtilities.updateComponentTreeUI(CheckerGame.this);
                
            } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(ae.getSource().equals(mnuItemAbout)) {
            JDialog dialogAbout = new JDialog(CheckerGame.this, "About Checker");
            JTextArea taAboutArea = new JTextArea();
            taAboutArea.setWrapStyleWord(true);
            taAboutArea.setEditable(false);
            String strAbout = "You play the checker game by choosing two buttons with the same color.\n"
                    + "For every two buttons with the same colour you earn a point.\n"
                    + "You can hide your score and only view it through the View menu.\n"
                    + "The game has two levels.\n"
                    + "\nDeveloped By: Sanele Manyela 03/09/2021";
            taAboutArea.setText(strAbout);
            dialogAbout.add(taAboutArea);
            dialogAbout.pack();
            dialogAbout.setLocationRelativeTo(null);
            dialogAbout.setVisible(true);
        }
    }
    
}
