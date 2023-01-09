import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Card extends JLabel implements MouseListener {
    private static ArrayList<JLabel> arrayOfCards;
    private String cardName;
    private ImageIcon imageIcon;
    private static int nrOfCards;
    private static ImageIcon backOfTheCard;
    private boolean found;
    private boolean faceUp;
    private static int cardsSeen = 0;

    public Card(String cardName){

        super(new ImageIcon(makeImg("card back red.png")));
        this.imageIcon = new ImageIcon(makeImg(cardName));
        this.faceUp = false;
        this.cardName = cardName;
        this.found = false;
        this.addMouseListener(this);
        /*BufferedImage image = ImageIO.read(new File("./images/"+cardName));
        Image dim = image.getScaledInstance(60, 100, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(dim);
        this.add(l);*/
    }

    private static Image makeImg(String cardName){
        try {
            return ImageIO.read(new File("./images/"+cardName)).getScaledInstance(100, 130, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.out.println("Cannot read card");
            return new Image() {
                @Override
                public int getWidth(ImageObserver observer) {
                    return 0;
                }

                @Override
                public int getHeight(ImageObserver observer) {
                    return 0;
                }

                @Override
                public ImageProducer getSource() {
                    return null;
                }

                @Override
                public Graphics getGraphics() {
                    return null;
                }

                @Override
                public Object getProperty(String name, ImageObserver observer) {
                    return null;
                }
            };
        }
    }
    public void mouseClicked(MouseEvent e) {
        repaintCard();
    }

    public static void initCards(int cards){
        arrayOfCards = new ArrayList<JLabel>(cards);
        backOfTheCard = new ImageIcon(makeImg("card back red.png"));
        nrOfCards = cards;
    }

    public static void addToDeck(Card card){
        arrayOfCards.add(card);
        backOfTheCard = new ImageIcon(makeImg("card back red.png"));
    }

    public void repaintCard(){
        //System.out.println(cardsSeen);
        if(this.faceUp) return;
        if(cardsSeen == 2) {
            cardsSeen = 0;
            for(int i = 0; i < nrOfCards - 1; i++){
                if (Board.getCard(i).faceUp && !Board.getCard(i).found) {
                    for( int j = i + 1; j < nrOfCards; j++){
                        if(Board.getCard(j).faceUp && !Board.getCard(j).found){
                            if(Board.getCard(i).getCardName().equals(Board.getCard(j).getCardName())){
                                Board.getCard(i).found = true;
                                Board.getCard(j).found = true;
                                break;
                            }
                            Board.getCard(i).changeFacing(false);
                            Board.getCard(i).setIcon(backOfTheCard);
                            Board.getCard(j).changeFacing(false);
                            Board.getCard(j).setIcon(backOfTheCard);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        if (this.faceUp == false) {
            this.setIcon(imageIcon);
            cardsSeen++;
            this.faceUp = !this.faceUp;
        }
        this.repaint();
    }

    public void changeFacing(boolean facing){
        this.faceUp = facing;
    }

    public String getCardName(){
        return cardName;
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseMoved   (MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}

}
