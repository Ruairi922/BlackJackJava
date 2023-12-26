import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class logic {
    private static final String[] SUITS = {"Spades", "Hearts", "Diamonds", "Clubs"};
    private static final String[] VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    List<String> deck;
    List<String> playerHand;
    List<String> dealerHand;

    private Scanner scanner;

    public logic() {
        deck = new ArrayList<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    private void initializeDeck() {
        for (String suit : SUITS) {
            for (String value : VALUES) {
                deck.add(value + " of " + suit);
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void dealInitialCards() {
        for (int i = 0; i < 2; i++) {
            playerHand.add(deck.remove(0));
            dealerHand.add(deck.remove(0));
        }
    }

    int calculateHandValue(List<String> hand) {
        int value = 0;
        int numAces = 0;

        for (String card : hand) {
            String cardValue = card.split(" ")[0];

            if ("JQK".contains(cardValue)) {
                value += 10;
            } else if ("A".equals(cardValue)) {
                numAces++;
                value += 11;
            } else {
                value += Integer.parseInt(cardValue);
            }
        }

        while (numAces > 0 && value > 21) {
            value -= 10;
            numAces--;
        }

        return value;
    }

    private void displayHands(boolean showDealerCard) {
        System.out.println("You have: " + playerHand + " (Your Total is: " + calculateHandValue(playerHand) + ")");
        System.out.println("Dealer has: " + dealerHand + " (Dealer Total is: " + calculateHandValue(dealerHand) + ")");
    }

    private void playerTurn() {
        while (true) {
            displayHands(true);
            System.out.print("Do you want to hit or stand? (h/s): ");
            String choice = scanner.next().toLowerCase();

            if ("h".equals(choice)) {
                playerHand.add(deck.remove(0));
                if (calculateHandValue(playerHand) > 21) {
                    displayHands(true);
                    System.out.println("Bust! You lose.");
                    return;
                }
            } else if ("s".equals(choice)) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'h' or 's'.");
            }
        }
    }

    void dealerTurn() {
        while (calculateHandValue(dealerHand) < 17) {
            dealerHand.add(deck.remove(0));
        }
    }

    private void determineWinner() {
        displayHands(true);

        int playerTotal = calculateHandValue(playerHand);
        int dealerTotal = calculateHandValue(dealerHand);

        if (playerTotal > 21 || (dealerTotal <= 21 && dealerTotal > playerTotal)) {
            System.out.println("Dealer wins!");
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("Congratulations! You win!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public void playGame() {
        playerHand.clear();
        dealerHand.clear();

        System.out.println("WELCOME TO BLACKJACK!!");

        initializeDeck();
        shuffleDeck();
        dealInitialCards();

        playerTurn();
        if (calculateHandValue(playerHand) <= 21) {
            dealerTurn();
            determineWinner();
        }

        // Do not close the scanner here
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        logic game = new logic();
        game.playGame();

        System.out.println("Do you want to play again?(y/n): ");
        String playAgain = input.nextLine();
        if(playAgain.equals("y")){
            game.playGame();
        } else if(playAgain.equals("n")){
            System.out.println("Thanks for playing!");
        }
    }
}