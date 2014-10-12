public class Game {
    
    //Game vars
    public static String player1;
    public static String player2;
    public static boolean gameStart = false;
    
    private String paper = "PAPER";
    private String rock  = "ROCK";
    private String scissors = "SCISSORS";
    
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
        } 
        else if(player1.equals(rock) && player2.equals(scissors)){
            System.out.println("Rock CRUSHES Scissors!!!");
        }
        else if(player1.equals(rock) && player2.equals(paper)) {
            System.out.println("Paper COVERS Rock!!!");
        } 
        else if(player1.equals(paper) && player2.equals(scissors)) {
            System.out.println("Scissors CUTS Paper!!!");
        }
        else if(player1.equals(paper) && player2.equals(rock)) {
            System.out.println("Paper COVERS Rock!!!");
        }
        else if(player1.equals(scissors) && player2.equals(rock)) {
            System.out.println("Rock CRUSHES Scissors!!!");
        }
        else if(player1.equals(scissors) && player2.equals(paper)) {
            System.out.println("Scissors CUTS Paper!!!");
        }
        
        player1 = null;
        player2 = null;
    }
    
    
}
