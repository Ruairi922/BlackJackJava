import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        // Create and set up the JFrame
        JFrame frame = new JFrame("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // Set the size of the frame

        // Create a JButton
        JButton dealButton = new JButton("Play Now!!");
        JButton hitButton = new JButton("Hit");
        JButton standButton = new JButton("Stand");
        JLabel resultLabel = new JLabel("Welcome to Blackjack!");



        // Create a JLabel to display messages
        JLabel label = new JLabel("Welcome to BlackJack!!");

        // Add an ActionListener to the button
        dealButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change the text when the button is clicked
                label.setText("Let us Begin!");
            }
        });

        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change the text when the button is clicked
                label.setText("Let us Begin!");
            }
        });

        standButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change the text when the button is clicked
                label.setText("Let us Begin!");
            }
        });

        // Create a JPanel to hold the components
        JPanel panel = new JPanel();
        panel.add(dealButton);
        panel.add(hitButton);
        panel.add(standButton);
        panel.add(resultLabel);
        frame.add(panel);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}