//Threaded server class
//Access game logic via Game class

import java.io.*;
import java.net.*;

public class MiniServer extends Thread{

    private Socket socket = null;
    public String result = "";
    private int playerNum;
    
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
                     DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                      result = inFromClient.readLine();
                     //System.out.println("Received: " + result);
             
                     if(playerNum == 1) {
                         Game.player1 = result;
                      } else {
                       Game.player2 = result;
                     }
             
                     if(Game.gameReady()) {
                        System.out.println("Game Started");
                        Game.processGestures();
                        Game.gameStart = false;
                     }
                      
                } catch(IOException e) {
                        System.out.println("Error: " + e);
                }
            }
    }
    
}
