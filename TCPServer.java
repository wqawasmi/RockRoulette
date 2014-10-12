import java.io.*;
import java.net.*;

class TCPServer
{
   public static void main(String argv[]) throws Exception
      {
         String clientSentence;
         String capitalizedSentence;
         ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(argv[0]));
         //Socket connectionSocket = welcomeSocket.accept();
         
         System.out.println("Created server on port " + argv[0]);
         
         int playerNum = 1;
         
         while(true){
            Socket clientSocket = welcomeSocket.accept();
            MiniServer mini = new MiniServer(clientSocket, playerNum);
            System.out.println("Connection Made With Player " + playerNum);
            playerNum++;
            
            //Reset player count once two clients have connected
            if(playerNum > 2) {
                playerNum = 1;
               // Game.resetGame();
            }
                
            mini.start();
        }
        
         
      }
}
