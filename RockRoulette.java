import com.leapmotion.leap.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;

class SampleListener extends Listener {
	
	public static long tStart = System.currentTimeMillis() - 1000;

    public void onConnect(Controller controller) {
        System.out.println("Connected to LeapMotion");
        //controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        //controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
    }

    public void onFrame(Controller controller) {
    	  Frame frame = controller.frame();
    	  	
    	  	//Loop through hands and detect open palm for "Paper" action
    	  	for(int index = 0; index < frame.hands().count(); index++) {
    	  		Hand hand = frame.hands().get(index);
    	  		
    	  		int extendedFingers = 0;
    	  		for (Finger finger : hand.fingers())
    	  		{
    	  		    if(finger.isExtended()) extendedFingers++;
    	  		}
    	  		
    	  		if(extendedFingers == 2) 
    	  			RockRoulette.handGesture = RockRoulette.HandGesture.SCISSOR;
    	  		else if(hand.grabStrength() == 0)
    	  			RockRoulette.handGesture = RockRoulette.HandGesture.PAPER;
    	  		else if(hand.grabStrength() == 1)
    	  			RockRoulette.handGesture = RockRoulette.HandGesture.ROCK;
    	  		else
    	  			RockRoulette.handGesture = RockRoulette.HandGesture.FAILED;
    	  	}
    	  
    	  	//Loop through gestures
    	   /* for (int index = 0; index < frame.gestures().count(); index++) {
    	       // System.out.println(frame.gestures().get(index));
    	        if(frame.gestures().get(index).type() == Gesture.Type.TYPE_CIRCLE){
    	        	System.out.println("OMG A CIRCLE");
    	        }
    	        if(frame.gestures().get(index).type() == Gesture.Type.TYPE_SWIPE){
    	        	System.out.println("OMG A SWIPE");
    	        }
    	    }*/
    	    

    		//Send a message to server every 1 sec
    		if(System.currentTimeMillis() - tStart > 1000 && RockRoulette.connected) {
    			
    			try {
    				//System.out.println( tStart);
    				switch(RockRoulette.handGesture) {
    				case PAPER:
    					System.out.println("PAPER");
    					RockRoulette.outToServer.writeBytes("PAPER");
    					break;
    				case ROCK:
    					System.out.println("ROCK");
    					RockRoulette.outToServer.writeBytes("ROCK");
    					break;
    				case SCISSOR:
    					System.out.println("SCISSOR");
    					RockRoulette.outToServer.writeBytes("SCISSOR");
    					break;
    				default:
    					System.out.println("FAILED");
    					RockRoulette.outToServer.writeBytes("NOTHING");
    				}
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    			//Reset tStart after certain time passed
    			tStart = System.currentTimeMillis();
    		}
    	}
    
}

public class RockRoulette implements KeyListener {
	
	public static DataOutputStream outToServer;
	
	public static boolean close = false;
	
	public static enum HandGesture{
	    ROCK, PAPER, SCISSOR, FAILED
	}
	
	public static HandGesture handGesture;
	public static boolean connected = false;

    public static void main(String[] args) throws Exception{
    	//Server properties
    	String server = "54.172.108.156";
    	int port = 9010;
    	
    	//Add controller
    	SampleListener listener = new SampleListener();
    	Controller controller = new Controller();
    	
    	//Add listener to controller
    	controller.addListener(listener);
        
    	//TCP Client Configuration
    	//Currently sends a sentence to the server
        String sentence;
        String modifiedSentence;
           
        //Create socket and bufferedReader for user input
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        Socket clientSocket = new Socket(server, port);
        connected = true;
        System.out.println("Connected to Server");
        
        //Create server output/input streams
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           
        //Take input and write to server via DataOutputStream
        //sentence = inFromUser.readLine();
        //outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
           
        //Print out data received from server
        System.out.println("FROM SERVER: " + modifiedSentence);
        

        if(close == true) {
             //Close Server Socket
             clientSocket.close();
             
             //Remove listener
             controller.removeListener(listener);
             return;
        }
        
       
        
    }
    
    //Detect esc key pressed
    @Override
    public void keyPressed(KeyEvent e) {
    
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("hiii");
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	close = true;
        	System.out.println("esc pressed");
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

