import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/*
 * To use in RockRoulette.java:
 * 
 * Before main() add "public static RockWindow rw;"
 * 
 * Before new SampleListener() in main() add "rw = new RockWindow(); rw.showRadioButtonDemo();"
 * 
 * Before switch(RockRoulette.handGesture) in onFrame() add "RockRoulette.rw.setGesture(RockRoulette.handGesture);"
 */

public class RockWindow extends JFrame {
	
	   private JFrame mainFrame;
	   private JLabel headerLabel;
	   private JLabel statusLabel;
	   private JPanel controlPanel;
	   
	   private JRadioButton radNothing, radRock, radPaper, radScissor;
	   private ButtonGroup group;

	   public RockWindow(){
		   SwingUtilities.invokeLater(new Runnable() {
			      @Override
			      public void run() {
			        prepareGUI();
			      }
			    });
	   }
	   
	   public void setGesture(RockRoulette.HandGesture gesture)
	   {
		   switch (gesture)
		   {
		   case ROCK:
			   group.setSelected(radRock.getModel(), true);
			   break;
		   case PAPER:
			   group.setSelected(radPaper.getModel(), true);
			   break;
		   case SCISSOR:
			   group.setSelected(radScissor.getModel(), true);
			   break;
		   default:
			   group.setSelected(radNothing.getModel(), true);
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

		      statusLabel.setSize(350,100);

		      controlPanel = new JPanel();
		      controlPanel.setLayout(new FlowLayout());

		      mainFrame.add(headerLabel);
		      mainFrame.add(controlPanel);
		      mainFrame.add(statusLabel);
		      mainFrame.setVisible(true);  
		   }

		   public void showRadioButtonDemo(){

			  radNothing = new JRadioButton("Nothing", true);
		      radRock = new JRadioButton("Rock");
		      radPaper = new JRadioButton("Paper");
		      radScissor = new JRadioButton("Scissors");

		      //Group the radio buttons.
		      group = new ButtonGroup();
		      group.add(radNothing);
		      group.add(radRock);
		      group.add(radPaper);
		      group.add(radScissor);

		      controlPanel.add(radNothing);
		      controlPanel.add(radRock);
		      controlPanel.add(radPaper);
		      controlPanel.add(radScissor);   

		      mainFrame.setVisible(true);  
		   }
}
