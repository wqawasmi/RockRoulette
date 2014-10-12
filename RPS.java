public class RPS {

	public static void main(String[] args) {
	
		String rock, paper, scissors;
		
		String user, comp, yes, no;
		int r, p, s; //user inputs
		int rmem, pmem, smem; //computer "memory" of user inputs
		r = 0;
		p = 0;
		s = 0;
		rmem = 0;
		pmem = 0;
		smem = 0;
		
		boolean play_again;
		play_again = true;
		
		System.out.println("Begin the game!");
		
		System.out.println("What's your move???");
		
		System.out.print("Rock, paper, scissors...");
		System.out.println("Shoot!");
		
		while ( play_again == true ) {
		
			user = IO.readString();
			
				if ( user != rock || user != paper || user != scissors ) {
				
					System.out.println("Enter a valid input!");
					break;
				}
				
			if ( user == rock ){
				r++;
			}else if ( user == paper ) {
				p++;
			}else{ 
				s++;
			}
			
			if ( r > p && r > s ){
				pmem++;
				smem++;
			}else if( p > r && p > s ){
				rmem++;
				smem++;
			}else{
				rmem++;
				pmem++;
			}
			
				if ( r > p && r > s && smem > pmem ) {
					comp = paper;		}
				else if ( r > p && r > s && pmem > smem ) {
					comp = scissors;	}
				else if ( p > r && p > s && rmem > pmem ) {
					comp = paper;   	}
				else if ( p > r && p > s && pmem > rmem ) {
					comp = rock;		}
				else if ( s > p && s > r && rmem > pmem ) {
					comp = paper;		}
				else if ( s > p && s > r && pmem > rmem ) {
					comp = rock;		}
				else if ( r == p && smem > pmem ) {
					comp = paper;		}
				else if ( r == p && pmem > smem ) {
					comp = scissors;	}
				else if ( r == s && smem > pmem ) {
					comp = paper;   	}
				else if ( r == s && pmem > smem ) {
					comp = scissors;		}
				else if ( p == s && rmem > smem ) {
					comp = scissors;	}
				else {
					comp = rock;   	}
				
				String play;
				
				play = IO.readString();
				
				if ( play != yes || play != no ) {
					System.out.println("Please enter a valid input.");
					break;	}
				
			if ( "comp" == "user" ) {
				System.out.println("Tie! Try again.");
				play_again = true;
				user = IO.readString();
				}
			
			else if ( comp == rock && user == scissors ) {
				System.out.println("You lose!! Sorry. Play again? (yes/no)");
				if ( play == yes ) {
					play_again = true;
					user = IO.readString();
				}
				else {
					play_again = false;
					System.out.println("Thanks for playing.");
					break;
				}
			}
			
			else if ( comp == rock && user == paper ) {
				System.out.println("You win! Play again? (yes/no)");
				if ( play == yes ) {
					play_again = true;
					user = IO.readString();
				}

				else {
					play_again = false;
					System.out.println("Thanks for playing.");
					break;
				}
			}
			else if ( comp == paper && user == scissors ) {
				System.out.println("You win! Play again? (yes/no)");
				if ( play == yes ) {
					play_again = true;
					user = IO.readString();
				}
				else {
					play_again = false;
					System.out.println("Thanks for playing.");
					break;
				}
			}
			else if ( comp == paper && user == rock ) {
				System.out.println("You lose!! Sorry. Play again? (yes/no)");
				if ( play == yes ) {
					play_again = true;
					user = IO.readString();
				}
				else {
					play_again = false;
					System.out.println("Thanks for playing.");
					break;
				}
			}
			else if ( comp == scissors && user == paper ) {
				System.out.println("You lose!! Sorry. Play again? (yes/no)");
				if ( play == yes ) { 
					play_again = true;
					user = IO.readString();
				}
				else {
					play_again = false;
					System.out.println("Thanks for playing.");
					break;
				}
			}
			else if ( comp == scissors && user == rock ) {
				System.out.println("You win! Sorry. Play again? (yes/no)");
				if ( play == yes ) {
					play_again = true;
					user = IO.readString();
				}
				else {
					play_again = false;
					System.out.println("Thanks for playing.");
					break;
				}
			}
			
			}
	}
}
