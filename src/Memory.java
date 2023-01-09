import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Memory {
    public static void main(String[] args) throws IOException {
        int size;
        do {
            size = Integer.parseInt(JOptionPane.showInputDialog("Enter the size(max size: 18 pairs)"));
        } while (size > 36 || size <= 1);
        JFrame window = new JFrame();
        window.setTitle("Memory");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(1000, 700));
        window.setContentPane(new Board(2 * size));
        window.setVisible(true);
    }
}