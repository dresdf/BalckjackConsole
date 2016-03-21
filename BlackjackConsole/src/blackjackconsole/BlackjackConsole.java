package blackjackconsole;

import java.util.Scanner;

/**
 *
 * @author Dragos Secara
 */
public class BlackjackConsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numberOfPacks = 1;
        boolean isNumberOfPacksValid = false;
        Blackjack game;

        System.out.println("Wellcome to the Blackjack table.");
        System.out.print("Please enter your name: ");
        String playerName = sc.nextLine();
        System.out.print("Enter the number of card packs you wish to use for the game(1 - 7): ");

        while (!isNumberOfPacksValid) {
            try {
                numberOfPacks = Integer.parseInt(sc.nextLine());
                if ((numberOfPacks > 0) && (numberOfPacks < 8)) {
                    isNumberOfPacksValid = true;
                } else {
                    System.out.print("The number of packs in the deck must be at least 1 and at most 7.\nTry again: ");
                }
            } catch (Exception e) {
                System.out.println("Please enter a positive integer");
            }
        }

        if (playerName.trim().equals("")) {
            System.out.println("Name not recognized or absent. Default name will be used.");
            game = new Blackjack(new Player("Human Player"), numberOfPacks);
        } else {
            game = new Blackjack(new Player(playerName), numberOfPacks);
        }

        game.startGame();

    }

}
