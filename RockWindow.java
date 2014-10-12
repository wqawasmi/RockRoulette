

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RockWindow extends JFrame {
	
	   private JFrame mainFrame;
	   private JLabel headerLabel;
	   private JLabel statusLabel;
	   private JPanel controlPanel, controlPanel2;
	   
	   private int p1wins = 0, p2wins = 0, ties = 0;
	   
	   private JRadioButton radNothing, radRock, radPaper, radScissor;
	   private ButtonGroup p1choice;
	   
	   private JRadioButton winNeither, winP1, winP2;
	   private ButtonGroup winrar;

	   public RockWindow(){
		   SwingUtilities.invokeLater(new Runnable() {
			      @Override
			      public void run() {
			        prepareGUI();
			      }
			    });
	   }
	   
	   public void setStatus(String what)
	   {
		   statusLabel.setText(what);
	   }
	   
	   private enum Winner { Player1, Player2, Tie };  
	   public void declareWinner(Winner w)
	   {
		   switch (w)
		   {
		   case Player1:
			   winrar.setSelected(winP1.getModel(), true);
			   p1wins++;
			   setStatus("Latest win #" + p1wins + " from Player1!");
			   break;
		   case Player2:
			   winrar.setSelected(winP2.getModel(), true);
			   p2wins++;
			   setStatus("Latest win #" + p2wins + " from Player2!");
			   break;
		   case Tie:
			   winrar.setSelected(winNeither.getModel(), true);
			   ties++;
			   setStatus("Latest tie #" + ties);
			   break;
		   default:
			   setStatus("I can't understand who won!");
		     break;
		   }
	   }
	   
	   public void setGesture(RockRoulette.HandGesture gesture)
	   {
		   switch (gesture)
		   {
		   case ROCK:
			   p1choice.setSelected(radRock.getModel(), true);
			   break;
		   case PAPER:
			   p1choice.setSelected(radPaper.getModel(), true);
			   break;
		   case SCISSOR:
			   p1choice.setSelected(radScissor.getModel(), true);
			   break;
		   default:
			   p1choice.setSelected(radNothing.getModel(), true);
			   break;
		   }
	   }
	   
	   private void prepareGUI(){
		      mainFrame = new JFrame("Rock Roulette");
		      mainFrame.setSize(400,400);
		      mainFrame.setLayout(new GridLayout(3, 1));
		      mainFrame.addWindowListener(new WindowAdapter() {
		         public void windowClosing(WindowEvent windowEvent){
		            System.exit(0);
		         }        
		      });    
		      headerLabel = new JLabel("", JLabel.CENTER);        
		      statusLabel = new JLabel("",JLabel.CENTER);    

		      statusLabel.setSize(300,100);
		      statusLabel.setText("No wins to speak of, yet...");

		      controlPanel = new JPanel();
		      controlPanel.setLayout(new FlowLayout());
		      
		      controlPanel2 = new JPanel();
		      controlPanel2.setLayout(new FlowLayout());

		      mainFrame.add(headerLabel);
		      mainFrame.add(controlPanel);
		      mainFrame.add(controlPanel2);
		      mainFrame.add(statusLabel);
		      mainFrame.setVisible(true);  
		   }

		   public void showRadioButtonDemo(){

			  
			  radNothing = new JRadioButton("Neither", true);
			  radRock = new JRadioButton("Rock");
		      radPaper = new JRadioButton("Paper");
		      radScissor = new JRadioButton("Scissors");
		      
		      winNeither = new JRadioButton("Tie", true);
		      winP1 = new JRadioButton("Player 1 Wins!");
		      winP2 = new JRadioButton("Player 2 Wins!");

		      //Group the first radio buttons.
		      p1choice = new ButtonGroup();
		      p1choice.add(radNothing);
		      p1choice.add(radRock);
		      p1choice.add(radPaper);
		      p1choice.add(radScissor);
		      
		      //Group the second group.
		      winrar = new ButtonGroup();
		      winrar.add(winNeither/*, BorderLayout.CENTER*/);
		      winrar.add(winP1/*, BorderLayout.LINE_START*/);
		      winrar.add(winP2/*, BorderLayout.LINE_END*/);

		      controlPanel.add(radNothing);
		      controlPanel.add(radRock);
		      controlPanel.add(radPaper);
		      controlPanel.add(radScissor);
		      
		      controlPanel2.add(winNeither);
		      controlPanel2.add(winP1);
		      controlPanel2.add(winP2);

		      mainFrame.setVisible(true);  
		   }
}
