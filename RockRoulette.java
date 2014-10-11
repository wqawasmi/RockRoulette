import com.leapmotion.leap.*;
import java.io.*;
import java.net.*;


class SampleListener extends Listener {

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
    }

    public void onFrame(Controller controller) {
    	  Frame frame = controller.frame();

    	    for (int index = 0; index < frame.gestures().count(); index++) {
    	       // System.out.println(frame.gestures().get(index));
    	        if(frame.gestures().get(index).type() == Gesture.Type.TYPE_CIRCLE){
    	        	System.out.println("OMG A CIRCLE");
    	        }
    	        if(frame.gestures().get(index).type() == Gesture.Type.TYPE_SWIPE){
    	        	System.out.println("OMG A SWIPE");
    	        }
    	    }
    }
}

public class RockRoulette {
    public static void main(String[] args) throws Exception{
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
        Socket clientSocket = new Socket("54.86.250.239", 8999);
           
        //Create server output/input streams
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           
        //Take input and write to server via DataOutputStream
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
           
        //Print out data received from server
        System.out.println("FROM SERVER: " + modifiedSentence);
        
        
        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Close Server Socket
        clientSocket.close();
        //Remove listener
        controller.removeListener(listener);
    }
}

