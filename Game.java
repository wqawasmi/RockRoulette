public class Game {
    
    //Game vars
    public static int player1;
    public static int player2;
    public static boolean gameStart = false;
    
    /*
    private String paper = "PAPER";
    private String rock  = "ROCK";
    private String scissors = "SCISSORS";
    */
    
    private final static int rock = 1;
    private final static int paper = 2;
    private final static int scissors = 3;
    
    public Game() {
        //constructor shiz
    }
    
    
    public static boolean gameReady() {
        if(player1 != 0 && player2 != 0) 
            gameStart = true;
        else
            gameStart = false;
        return gameStart;
    }
    
    public static void resetGame() {
        player1 = 0;
        player2 = 0;
    }
    
    public static void processGestures() {
        System.out.println("Player one: " + player1 + "shite");
        System.out.println("Player two: " + player2);
        
        
        if(player1 == player2) {
            System.out.println("TIE!!!!");
            //System.out.print(player1);
        } 
        else if(player1 == rock && player2 == scissors){
            System.out.println("Rock CRUSHES Scissors!!!");
        }
        else if(player1 == rock && player2 == paper) {
            System.out.println("Paper COVERS Rock!!!");
        } 
        else if(player1 == paper && player2 == scissors) {
            System.out.println("Scissors CUTS Paper!!!");
        }
        else if(player1 == paper && player2 == rock) {
            System.out.println("Paper COVERS Rock!!!");
        }
        else if(player1 == scissors && player2 == rock) {
            System.out.println("Rock CRUSHES Scissors!!!");
        }
        else if(player1 == scissors && player2 == paper) {
            System.out.println("Scissors CUTS Paper!!!");
        }
        
        player1 = 0;
        player2 = 0;
    }
    
    
}
