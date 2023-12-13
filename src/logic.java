import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class logic {
    Scanner input = new Scanner(System.in);
    // create the deck
    String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    String[] value = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "K", "Q", "A"};
    String[] deck = new String[52];
    int index = 0;

    List<String> playerHand = new ArrayList<>();
    List<String> dealerHand = new ArrayList<>();

    public logic() {
        // Initialize the deck in the constructor
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                deck[index] = value[i] + " of " + suits[j];
                index++;
            }
        }
    }

    // create a shuffle deck method
    public void shuffleDeck() {
        Random random = new Random();

        for (int i = deck.length - 1; i > 0; i--) {
            int randIndex = random.nextInt(i + 1);

            // Swap deck[i] and deck[randIndex]
            String temp = deck[i];
            deck[i] = deck[randIndex];
            deck[randIndex] = temp;
        }
    }

    public List<String> dealInitialCards() {
        // Deal two cards to the player and two cards to the dealer
        Random random = new Random();
        // TODO: Implement logic for dealing initial cards
        // player and dealer need two cards each
        playerHand.add(deck[0]);
        playerHand.add(deck[1]);

        dealerHand.add(deck[random.nextInt(52)]);
        dealerHand.add(deck[random.nextInt(52)]);

        System.out.println("You have been dealt:");
        for(String elem:playerHand){
            System.out.println(elem);
        }
        System.out.println();
        return playerHand;
    }

    public int calculateHandValue() {
        // Implement logic to calculate the value of a hand
        // (sum up card values, consider Aces as 1 or 11)
        // Return the total hand value
        int valueOfHand = 0;
        int index = 0;
        String numOfCard = "";

        for(int i = 0; i < playerHand.size(); i++){
            numOfCard = playerHand.get(index).substring(0,2);
            if(numOfCard.equals("J ") || numOfCard.equals("K ") || numOfCard.equals("Q ")  || numOfCard.equals("10")){
                valueOfHand += 10;
            }else if(numOfCard.equals("A ")){
                if(valueOfHand <= 10){
                    valueOfHand += 11;
                }else{
                    valueOfHand += 1;
                }
            }else{
                valueOfHand += Integer.parseInt(numOfCard.substring(0,1));
            }
            index++;
        }
        return valueOfHand;
    }

    public void playerHit() {
        // Implement logic for the player to receive an additional card
        // Check for bust conditions
        Random random = new Random();
        System.out.println("Press 'h' to hit");
        String userHit = input.nextLine();
        String newCard = "";
        while(userHit.equals("h")){
            newCard = deck[random.nextInt(52)];
            playerHand.add(newCard);
            System.out.println("you have got a " + newCard);
            userHit = input.nextLine();
            if(userHit.equals("h") == false){
                break;
            }
        }

    }

    public void dealerTurn() {
        // Implement logic for the dealer's turn
        // Dealer hits until their hand value is at least 17
    }

    public void determineWinner() {
        // Implement logic to determine the winner based on hand values
    }

    public void resetGame() {
        // Reset the game state for a new round
    }

    public static void main(String[] args) {
        // TODO: Implement main method
        logic start = new logic();
        start.shuffleDeck();
        start.dealInitialCards();
        System.out.println("Your cureent total is: " + start.calculateHandValue());
        start.playerHit();
    }
}