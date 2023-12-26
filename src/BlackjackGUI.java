import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Card {
    private final String suit;
    private final String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        if (rank.equals("Ace")) {
            return 11;
        } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
            return 10;
        } else {
            return Integer.parseInt(rank);
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }

        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
}

class BlackjackGame {
    private final Deck deck;
    private final List<Card> playerHand;
    private final List<Card> dealerHand;

    public BlackjackGame() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        dealInitialCards();
    }

    private void dealInitialCards() {
        playerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());
        dealerHand.add(deck.drawCard());
    }

    public void playerHit() {
        playerHand.add(deck.drawCard());
    }

    public void dealerHit() {
        dealerHand.add(deck.drawCard());
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public int getPlayerScore() {
        return calculateScore(playerHand);
    }

    public int getDealerScore() {
        return calculateScore(dealerHand);
    }

    private int calculateScore(List<Card> hand) {
        int score = 0;
        int numAces = 0;

        for (Card card : hand) {
            score += card.getValue();
            if (card.getValue() == 11) {
                numAces++;
            }
        }

        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }
}

public class BlackjackGUI {
    private final BlackjackGame game;
    private final JFrame frame;
    private final JTextArea outputArea;

    public BlackjackGUI() {
        game = new BlackjackGame();
        frame = new JFrame("Blackjack");
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton hitButton = new JButton("Hit");
        JButton standButton = new JButton("Stand");

        hitButton.addActionListener(e -> handleHit());
        standButton.addActionListener(e -> handleStand());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);

        frame.setLayout(new BorderLayout());
        frame.add(outputArea, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        updateDisplay();

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void updateDisplay() {
        outputArea.setText("Player Hand: " + game.getPlayerHand() + " (Score: " + game.getPlayerScore() + ")\n");
        outputArea.append("Dealer Hand: " + game.getDealerHand().get(0) + " and [Hidden Card]\n");
    }

    private void handleHit() {
        game.playerHit();
        updateDisplay();
        if (game.getPlayerScore() > 21) {
            outputArea.append("Bust! You lose.\n");
            disableButtons();
        }
    }

    private void handleStand() {
        while (game.getDealerScore() < 17) {
            game.dealerHit();
        }

        updateDisplay();

        if (game.getDealerScore() > 21 || game.getPlayerScore() > game.getDealerScore()) {
            outputArea.append("You win!\n");
        } else if (game.getPlayerScore() == game.getDealerScore()) {
            outputArea.append("It's a tie!\n");
        } else {
            outputArea.append("You lose.\n");
        }

        disableButtons();
    }

    private void disableButtons() {
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                ((JButton) component).setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BlackjackGUI::new);
    }
}