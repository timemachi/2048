import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel {
    private GameFrame Game;
    private Graphics g;
    private Tile[][] tiles = new Tile[4][4];
    public GamePanel(GameFrame Game) {
        init();
        setPreferredSize(new Dimension(680, 730));
        setLayout(null);
        setOpaque(false);
        this.Game = Game;
        this.g = Game.getGraphics();
        GameKeyListener key = new GameKeyListener();
        Game.addKeyListener(key);

    }
    private class GameKeyListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
            char order = e.getKeyChar();
            order = Character.toLowerCase(order);
            boolean moved = false;
            switch (order) {
                case 'w':
                    for (int i = 0; i < 4; i++) {
                        for (int j = 1; j < 4; j++) {
                            if (tiles[i][j].value != 0) {
                                if (upMove(tiles[i][j])) {moved = true;}
                                upMove(tiles[i][j]);
                            }
                        }
                    }
                    break;
                case 's':
                    for (int i = 0; i < 4; i++) {
                        for (int j = 2; j >= 0; j--) {
                            if (tiles[i][j].value != 0) {
                                if (downMove(tiles[i][j])) {moved = true;}
                                downMove(tiles[i][j]);
                            }
                        }
                    }
                    break;

                case 'a':
                    for (int i = 0; i < 4; i++) {
                        for (int j = 1; j < 4; j++) {
                            if (tiles[j][i].value != 0) {
                                if(leftMove(tiles[j][i])) {moved = true;}
                                leftMove(tiles[j][i]);
                            }
                        }
                    }
                    break;

                case 'd':
                    for (int i = 2; i >= 0; i--) {
                        for (int j = 0; j < 4; j++) {
                            if (tiles[i][j].value != 0) {
                                if(rightMove(tiles[i][j])) {moved = true;}
                                rightMove(tiles[i][j]);
                            }
                        }
                    }
                    break;
            }
            if (moved){
                addRandomTile();
                repaint();
                if (checkGameOver()) {
                    JOptionPane.showMessageDialog(null, "You lost!", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }
    private void addRandomTile() {
        Random random = new Random();
        int X = random.nextInt(4);
        int Y = random.nextInt(4);
        while (tiles[X][Y].value != 0) {
            X = random.nextInt(4);
            Y = random.nextInt(4);
        }
        tiles[X][Y].resetValue(2);

    }
    private boolean upMove(Tile T) { //向上之后，每个tile应该如何判断往哪移动
        boolean anyTile = false;
        boolean moved = false;
        for (int k = 1; k < T.Y + 1; k++) {
            if (tiles[T.X][T.Y - k].value == T.value ) {
                if (T.value == 1024) {
                    JOptionPane.showMessageDialog(null, "You win!", "", JOptionPane.INFORMATION_MESSAGE);
                }
                T.moveTo(tiles[T.X][T.Y - k]);
                anyTile = true;
                moved = true;
                break;
            }
            if (tiles[T.X][T.Y - k].value != T.value && tiles[T.X][T.Y - k].value !=0) {
                if (T.Y - k + 1 != T.Y) {
                    T.moveTo(tiles[T.X][T.Y - k + 1]);
                    anyTile = true;
                    moved = true;
                    break;
                }
                anyTile = true;
                break;
            }
        }
        if (!anyTile) {
            T.moveTo(tiles[T.X][0]);
            moved = true;
        }
        return moved;
    }

    private boolean leftMove(Tile T) {
        boolean anyTile = false;
        boolean moved = false;
        for (int k = 1; k < T.X + 1; k++) {
            if (tiles[T.X - k][T.Y].value == T.value ) {
                if (T.value == 1024) {
                    JOptionPane.showMessageDialog(null, "You win!", "", JOptionPane.INFORMATION_MESSAGE);
                }
                T.moveTo(tiles[T.X - k][T.Y]);
                anyTile = true;
                moved = true;
                break;
            }
            if (tiles[T.X - k][T.Y].value != T.value && tiles[T.X - k][T.Y].value !=0) {
                if (T.X - k + 1 != T.X) {
                    T.moveTo(tiles[T.X - k + 1][T.Y]);
                    anyTile = true;
                    moved = true;
                    break;
                }
                anyTile = true;
                break;
            }
        }
        if (!anyTile) {
            T.moveTo(tiles[0][T.Y]);
            moved = true;
        }
        return moved;
    }


    private boolean downMove(Tile T) {
        boolean anyTile = false;
        boolean moved = false;
        for (int k = 1; k < 4 - T.Y; k++) {
            if (tiles[T.X][T.Y + k].value == T.value ) {
                if (T.value == 1024) {
                    JOptionPane.showMessageDialog(null, "You win!", "", JOptionPane.INFORMATION_MESSAGE);
                }
                T.moveTo(tiles[T.X][T.Y + k]);
                anyTile = true;
                moved = true;
                break;
            }
            if (tiles[T.X][T.Y + k].value != T.value && tiles[T.X][T.Y + k].value !=0) {
                if (T.Y + k - 1 != T.Y) {
                    T.moveTo(tiles[T.X][T.Y + k - 1]);
                    anyTile = true;
                    moved = true;
                    break;
                }
                anyTile = true;
                break;
            }
        }
        if (!anyTile) {
            T.moveTo(tiles[T.X][3]);
            moved = true;
        }
        return moved;
    }
    private boolean rightMove(Tile T) {
        boolean anyTile = false;
        boolean moved = false;
        for (int k = 1; k < 4 - T.X; k++) {
            if (tiles[T.X + k][T.Y].value == T.value ) {
                if (T.value == 1024) {
                    JOptionPane.showMessageDialog(null, "You win!", "", JOptionPane.INFORMATION_MESSAGE);
                }
                T.moveTo(tiles[T.X + k][T.Y]);
                anyTile = true;
                moved = true;
                break;
            }
            if (tiles[T.X + k][T.Y].value != T.value && tiles[T.X + k][T.Y].value !=0) {
                if (T.X + k - 1 != T.X) {
                    T.moveTo(tiles[T.X + k - 1][T.Y]);
                    anyTile = true;
                    moved = true;
                    break;
                }
                anyTile = true;
                break;
            }
        }
        if (!anyTile) {
            T.moveTo(tiles[3][T.Y]);
            moved = true;
        }
        return moved;
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.g = g;
        refreshTiles();
    }
    private boolean writeValue(Tile T) {
        int value = T.value;
        String V = String.valueOf(value);
        if (value == 0) {return false;}
        Font valueFont = new Font("Time new roman", Font.BOLD, 50);
        g.setFont(valueFont);
        g.setColor(Color.BLACK);

        //把字写在中间
        FontMetrics metrics = g.getFontMetrics(valueFont);
        int X = T.M + (T.getWEIGHT() - metrics.stringWidth(V)) / 2;
        int Y = T.N + ((T.getHEIGHT() - metrics.getHeight()) / 2) + metrics.getAscent();

        g.drawString(V, X, Y);
        return true;
    }
    public void drawTile(Tile T) {
        g.setColor(T.color);
        g.fillRoundRect(T.M, T.N, T.getWEIGHT(), T.getHEIGHT(), 4, 4);
        writeValue(T);
    }
    private void refreshTiles() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getSize().width, getSize().height);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile T = tiles[i][j];
                T.merged = false;
                drawTile(T);
            }
        }
    }
    public void init() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile T = new Tile(i, j, 0);
                tiles[T.X][T.Y] = T;
            }
        }
        Random random = new Random();
        int X = random.nextInt(4);
        int Y = random.nextInt(4);
        int X1 = random.nextInt(4);
        int Y1 = random.nextInt(4);
        //确保两个坐标不一样
        while (X == X1 && Y == Y1) {
            X1 = random.nextInt(4);
            Y1 = random.nextInt(4);
        }
        tiles[X][Y].resetValue(2);
        tiles[X1][Y1].resetValue(2);
    }

    private boolean checkGameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile T = tiles[i][j];
                if (T.value == 0) {return false;}
                if (helperCheckGameOver(T)) {return false;}
            }
        }
        return true;
    }
    private boolean helperCheckGameOver(Tile T) {
        int X = T.X;
        int Y = T.Y;
        int V = T.value;
        if (Y + 1 < 4) {
            if (tiles[X][Y + 1].value == V) {return true;}
        }
        if (Y - 1 >= 0) {
            if (tiles[X][Y - 1].value == V) {return true;}
        }
        if (X + 1 < 4) {
            if (tiles[X + 1][Y].value == V) {return true;}
        }
        if (X - 1 >= 0) {
            if (tiles[X - 1][Y].value == V) {return true;}
        }
        return false;
    }
}
