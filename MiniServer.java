//Threaded server class
//Access game logic via Game class

import java.io.*;
import java.net.*;

public class MiniServer extends Thread{

    private Socket socket = null;
    public String result = "";
    private int playerNum;
    public DataOutputStream outToClient;
    
    public MiniServer(Socket socket, int pNum) {

        super("MiniServer");
        this.socket = socket;
        playerNum = pNum;

    }

    public void run(){
            //Read input and process here
            
            while(true) {
                try {
                     BufferedReader inFromClient =
                       new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     outToClient = new DataOutputStream(socket.getOutputStream());
                      result = inFromClient.readLine();
                     //System.out.println("Received: " + result);
                    
                    if(result != null) {
                         if(playerNum == 1 && result != null) {
                             Game.player1 = Integer.parseInt(result);
                         } else {
                             Game.player2 = Integer.parseInt(result);
                         }  
                    }
             
                    if(Game.winner != "") {
                        outToClient.writeBytes(Game.winner);
                        System.out.println("SENT TO CLIENT: " + Game.winner);
                    }
                      
                    if(Game.gameReady()) {
                        Game.processGestures();
                    }
                     
                } catch(IOException e) {
                        System.out.println("Error: " + e);
                }
            }
    }
    
}
