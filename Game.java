public class Game {
    
    //Game vars
    public static String player1;
    public static String player2;
    public static boolean gameStart = false;
    
    public Game() {
        //constructor shiz
    }
    
    
    public static boolean gameReady() {
        if(player1 != null && player2 != null) 
            gameStart = true;
        else
            gameStart = false;
        return gameStart;
    }
    
    public static void resetGame() {
        player1 = null;
        player2 = null;
    }
    
    public static void processGestures() {
        System.out.println("Player one: " + player1);
        System.out.println("Player two: " + player2);
        
        if(player1.equals(player2)) {
            System.out.println("TIE!!!!");
        } else if(player1.equals("ROCK") && player2.equals("SCISSORS")){
            System.out.println("Rock CRUSHES Scissors!!!");
        } else if(player1.equals("ROCK") && player2.equals("PAPER")) {
            System.out.println("Paper COVERS Rock!!!");
        } else if(player1.equals("PAPER") && player2.equals("SCISSORS")) {
            System.out.println("Scissors CUTS Paper!!!");
        }
        
        player1 = null;
        player2 = null;
    }
    
    
}
