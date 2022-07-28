import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GamePanel gamePanel;
    int recordNote;

    public GameFrame() throws HeadlessException {
        setTitle("2048");
        setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
        setSize(680,730);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setResizable(false);
        menuBar();

        GamePanel panel = new GamePanel(this);
        gamePanel = panel;
        this.add(panel);
        this.setVisible(true);
    }

    public void menuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu game = new JMenu("Game");
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(e -> {
            int dialogButton = JOptionPane.showConfirmDialog(null, "Are you " +
                    "sure to Restart the game?", "WARNING" ,JOptionPane.YES_NO_OPTION);
            if(dialogButton == JOptionPane.YES_OPTION) {
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(false);
                GameFrame newGame = new GameFrame();
            }
        });
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            int dialogButton = JOptionPane.showConfirmDialog(null, "Are you " +
                    "sure to exit?", "WARNING" ,JOptionPane.YES_NO_OPTION);
            if(dialogButton == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        game.add(restart); game.addSeparator(); game.add(exit);
        bar.add(game);

        JMenu help = new JMenu("Help");
        JMenuItem rule = new JMenuItem("Rule");
        rule.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "The objective of the game is to slide numbered tiles" + "\n" +
                            " on a grid to combine them to create a tile with the" + "\n" +
                            " number 2048", "", JOptionPane.INFORMATION_MESSAGE);
            });

        JMenuItem record = new JMenuItem("Record");
        record.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, recordNote, "Best Note", JOptionPane.INFORMATION_MESSAGE);
                }
            );
        help.add(rule); help.addSeparator(); help.add(record);
        bar.add(help);

        setJMenuBar(bar);
    }
}
