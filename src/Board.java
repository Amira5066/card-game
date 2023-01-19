import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel {
    private static final String[] cardName = {"clubs", "spades", "diamonds", "hearts"};
    private static ArrayList<Card> deck;

    public Board(int size){
        this.setSize(new Dimension(1000, 700));
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(220, 94, 150));
        Card.initCards(size);
        deck = new ArrayList<Card>(size);

        int cardsPlaced = 0;
        ArrayList<String> cardsOnBoard = new ArrayList<String>(size);
        Random rand = new Random();
        int cardNr = rand.nextInt(8) + 2;
        int cardType = rand.nextInt(4);
        String nameOfTheCard = cardNr + "_of_" + cardName[cardType] + ".png";

        while(cardsPlaced != size) {
            while(cardsOnBoard.contains(nameOfTheCard)){
                cardNr = rand.nextInt(8) + 2;
                cardType = rand.nextInt(4);
                nameOfTheCard = cardNr + "_of_" + cardName[cardType] + ".png";
            }
            cardsOnBoard.add(nameOfTheCard);
            cardsOnBoard.add(nameOfTheCard);
            cardsPlaced+=2;
        }

        int auxSize = size;
        for(int i =0; i < auxSize; i++){
            /*this.add(card);
            deck.add(card);*/
            int randomPlace = rand.nextInt(size);
            Card card = new Card(cardsOnBoard.get(randomPlace));
            this.add(card);
            deck.add(card);
            cardsOnBoard.remove(randomPlace);
            size--;
        }
    }

    public static Card getCard(int i){
        return deck.get(i);
    }
}
