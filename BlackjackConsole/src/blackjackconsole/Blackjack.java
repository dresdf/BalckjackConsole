package blackjackconsole;

import java.util.Scanner;

/**
 *
 * @author Dragos Secara
 */
public class Blackjack {

    private final int WIN_SCORE = 21;
    private Player player;
    private Player dealer;
    private Deck deck;

    /**
     * starting point for the game. deals 2 cards for each player and hides the dealer's first card.
     * Checks for initial blackjack
     */
    public void startGame() {
        deck.deal(dealer);
        dealer.getHand().get(0).setIsVisible(false);
        deck.deal(dealer);
        deck.deal(player);
        deck.deal(player);
        printTable(false);

        if ((dealer.getHandValue() == WIN_SCORE) && (player.getHandValue() == WIN_SCORE)) {
            endGame();
        } else if ((dealer.getHandValue() == WIN_SCORE) || (player.getHandValue() == WIN_SCORE)) {
            endGame();
        } else {
            playerTurn();
        }

    }

    /**
     * play logic for the player
     */
    private void playerTurn() {
        Scanner sc = new Scanner(System.in);
        int choice = 3;

        try {
            System.out.print("Input 1 for \"Hit\" or 2 for \"Hold\" : ");
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Must input a positive integer");
            playerTurn();
        }

        if (choice == 1) {
            deck.deal(player);
            if (player.getHandValue() == 21) {
                System.out.println("!!!!!BLACKJACK!!!!!");
                endGame();
            } else if (player.getHandValue() > WIN_SCORE) {
                endGame();
            } else {
                System.out.println(player.printHand() + "  -- Value: " + player.getHandValue());
                playerTurn();
            }
        } else if (choice == 2) {
            dealerTurn();
        } else {
            System.out.println("Unrecognized command.");
            playerTurn();
        }
    }

    /**
     * play logic for the dealer
     */
    private void dealerTurn() {
        while ((dealer.getHandValue() <= player.getHandValue())) {
            deck.deal(dealer);
        }
        if (dealer.getHandValue() > WIN_SCORE) {
            endGame();
        } else if (dealer.getHandValue() > player.getHandValue()) {
            endGame();
        }

    }

    /**
     * calculates the winner and ends the current hand
     */
    private void endGame() {
        dealer.getHand().get(0).setIsVisible(true);
        int dealerHandValue = dealer.getHandValue();
        int playerHandValue = player.getHandValue();
        String winner = "";

        if (dealerHandValue == WIN_SCORE && playerHandValue == WIN_SCORE) {
            winner = "It's a tie: Dealer: " + dealerHandValue + " Player: " + playerHandValue;
        } else if (dealerHandValue == playerHandValue) {
            winner = "It's a tie: Dealer: " + dealerHandValue + " Player: " + playerHandValue;
        } else if (dealerHandValue == WIN_SCORE || playerHandValue > WIN_SCORE || (dealerHandValue < WIN_SCORE && dealerHandValue > playerHandValue)) {
            if (dealer.getHandValue() == 21) {
                System.out.println("\n!!!!!BLACKJACK!!!!!\n");
            }
            winner = "DEALER WINS!!!";
            dealer.setScore(dealer.getScore() + 1);
        } else if (playerHandValue == WIN_SCORE || dealerHandValue > WIN_SCORE || playerHandValue > dealerHandValue) {
            if (player.getHandValue() == 21) {
                System.out.println("\n!!!!!BLACKJACK!!!!!\n");
            }
            winner = player.getName() + " WINS!!!";
            player.setScore(player.getScore() + 1);
        }

        printTable(true);
        System.out.println(winner + "\n");
        System.out.println("Score: Dealer: " + dealer.getScore() + " - Player: " + player.getScore());
        nextHand();
    }

    /**
     * deals another hand or exits the game, depending on the player's choice
     */
    public void nextHand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Deal a new hand?(Y/N)");
        String answer = sc.nextLine().toLowerCase().trim();
        switch (answer) {
            case "y":
                dealer.reset();
                player.reset();
                deck.shuffle();
                startGame();
                break;
            case "n":
                System.out.println("Game ended. Final score: Dealer: " + dealer.getScore() + " - Player: " + player.getScore());
                System.exit(0);
                break;
            default:
                System.out.println("Unrecognized input. Please input Y or N.");
                nextHand();
                break;
        }

    }

    /**
     * creates a layout for the game to be printed into the console
     */
    public void printTable(boolean isLastHand) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("*");
        }
        sb.append("\n");
        
        if (!isLastHand) {
            sb.append("Dealer: ").append(dealer.printHand()).append("\n\n");
        } else {
            sb.append("Dealer: ").append(dealer.printHand()).append("  -- Value: ").append(dealer.getHandValue()).append("\n\n");
        }
        sb.append("Player: ").append(player.printHand()).append("  -- Value: ").append(player.getHandValue()).append("\n");
        
        for (int i = 0; i < 10; i++) {
            sb.append("*");
        }
        sb.append("\n");

        System.out.println(sb.toString());
    }

    public int getWIN_SCORE() {
        return WIN_SCORE;
    }

    public Player getPlayer() {
        return player;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getDealer() {
        return dealer;
    }

    public Blackjack(Player player, int numberOfPacks) {

        this.dealer = new Player("Dealer");
        this.player = player;
        this.deck = new Deck(numberOfPacks);
        deck.shuffle();
    }
}
