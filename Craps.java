import java.util.*;
public class Craps {
	int balance, d1, d2, sum;
	Random rand = new Random();
	
	public Craps(int balance) {
		this.balance = balance;
	}	
	
	public int roll() {
		d1 = rand.nextInt(6) + 1;
		d2 = rand.nextInt(6) + 1;
		return sum = d1 + d2;
	}
	
	public boolean winPass(int val) {
		if (val == 7 || val == 11) {
			return true;
		}
		return false;
	}
	
	public boolean losePass(int val) {
		if (val == 2 || val == 3 || val == 12) {
			return true;
		}
		return false;
	}
	
	public void lose(int bet) {
		this.balance -= bet;
	}
	
	public void win(int bet) {
		this.balance += bet;
	}
	// program does not catch/handle errors
	public static void main(String[] args) { // Craps game with ability to bet on or against pass line, example payout of 1 to 1
		Scanner input = new Scanner(System.in);
		int balance, bet, result, point;
		System.out.println("Please enter your deposit");  // Integers only
		balance = input.nextInt();
		Craps craps = new Craps(balance);
		while (craps.balance > 0) {
			System.out.println("Please enter your bet amount");
			System.out.println("Current balance: " + craps.balance);
			System.out.println("Enter 0 to exit");
			bet = input.nextInt();
			if (bet > 0) {
				if (craps.balance >= bet) { // Bet cannot be greater than balance
					result = craps.roll();
					System.out.println("Roll is " + result);
					if (craps.winPass(result)) { // Roll is 7 or 11 which is a win
						System.out.println("Winner!");
						craps.win(bet);
						System.out.println("Balance is now " + craps.balance);
					}
					else if (craps.losePass(result)) { // Roll is a 2, 3, or 12 which is a loss
						System.out.println("You Lose :( ");
						craps.lose(bet);
						System.out.println("Balance is now " + craps.balance);
					}
					else {
						System.out.println("No Natural or Craps roll, rolling again"); // Roll until roll is repeated or a 7 
						point = result;
						result = craps.roll();
						while (result != point || result != 7) { 
							System.out.println("Roll is " + result);
							if (result == point) { // Repeat of original roll is a win
								System.out.println("Winner!");
								craps.win(bet);
								System.out.println("Balance is now " + craps.balance);
								break;
							}
							else if (result == 7) { // Roll of a 7 is a loss
								System.out.println("You Lose :( ");
								craps.lose(bet);
								System.out.println("Balance is now " + craps.balance);
								break;
							}
							else {
								result = craps.roll();
							}
						}
					}
				}
				else { // Bet is too high
					System.out.println("Insufficient funds");
				}
			}
			else if (bet == 0){ // Exit program 
				System.out.println("Your take is " + craps.balance + ", Goodbye!");
				System.exit(0);
			}
			else { 
				System.out.println("Invalid input");
			}
		}
	}
}
