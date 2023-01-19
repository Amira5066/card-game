import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Memory {
    private static int pairs;
    private static JFrame window;

    public static int getPairs() {
        return pairs;
    }

    public static void endGame() {
        try{
            JOptionPane.showMessageDialog(window, "You won! ^-^", "All the pairs were found!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ImageIO.read(new File("./images/Smiley.svg.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            System.exit(0);
        }
        catch (Exception e){
            System.out.println("Cannot read image");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        do {
            pairs = Integer.parseInt(JOptionPane.showInputDialog("Enter the size(max size: 30 pairs)"));
        } while (pairs > 30 || pairs <= 1);
        window = new JFrame();
        window.setTitle("Memory");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(1000, 700));
        window.setContentPane(new Board(2 * pairs));
        window.setVisible(true);
    }
}