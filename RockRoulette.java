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
    	  try {
    	  	for(int index = 0; index < frame.hands().count(); index++) {
    	  		Hand hand = frame.hands().get(index);
    	  		
    	  		//Count extended fingers by looping through and checking for isExtended()
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
    	  	
    	  } catch(NullPointerException e) {
    		  System.out.println("No hand detected yet.");
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
    				RockRoulette.rw.setGesture(RockRoulette.handGesture);
    				switch(RockRoulette.handGesture) {
    				case ROCK:
    					System.out.println("ROCK");
    					RockRoulette.outToServer.writeBytes("1\n");
    					break;
    				case PAPER:
    					System.out.println("PAPER");
    					RockRoulette.outToServer.writeBytes("2\n");
    					break;
    				case SCISSOR:
    					System.out.println("SCISSOR");
    					RockRoulette.outToServer.writeBytes("3\n");
    					break;
    				default:
    					System.out.println("FAILED");
    					RockRoulette.outToServer.writeBytes("0\n");
    				}
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				System.out.println("No Hand Detected");
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
	public static RockWindow rw;
	
    public static void main(String[] args) throws Exception{
    	//Server properties
    	String server = "54.172.108.156";
    	int port = 9016;
    	
    	//Create GUI 
    	rw = new RockWindow(); 
    	rw.showRadioButtonDemo();
    	
    	//Add controller
    	SampleListener listener = new SampleListener();
    	Controller controller = new Controller();
    	
    	//Add listener to controller
    	controller.addListener(listener);
        
    	//TCP Client Configuration
    	//Currently sends a sentence to the server
        String winner;
           
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
        winner = inFromServer.readLine();
           
        //Print out data received from server
        System.out.println("FROM SERVER: " + winner + " WINS!");
        

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

