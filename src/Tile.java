import java.awt.*;


public class Tile {
    static int MARGIN = 10;
    static int WEIGHT = 150;
    static int HEIGHT = 150;
    static int GAP = 10;
    int value;
    int X; //坐标X轴
    int Y; //坐标Y轴
    int M; //画图用的坐标横轴
    int N; //画图用的坐标竖轴
    boolean merged = false;
    Color color = null;
    public Tile( int X, int Y, int value) {
        this.value = value;
        this.X = X;
        this.Y = Y;
        positionCalculate();
        setColor();
    }
    private void positionCalculate() {
        M = MARGIN + X * WEIGHT + (X + 1) * GAP;
        N = MARGIN + Y * HEIGHT + (Y + 1) * GAP;
    }
    public int getWEIGHT() {return WEIGHT;}
    public int getHEIGHT() {return HEIGHT;}

    public void resetValue(int value) {
        this.value = value;
        this.setColor();
    }

    public boolean moveTo(Tile TargetT) { //这个值变成0；merge的那个tile的值 X 2
        if (this.X == TargetT.X && this.Y == TargetT.Y) {return false;} //并未移动
        int V = TargetT.value;
        int thisValue = value;
        this.resetValue(0);
        if (V != 0) {
            TargetT.resetValue(V * 2);
            TargetT.merged = true;
        } else {
            TargetT.resetValue(thisValue);
        }
        return true; //移动了
    }
    private void setColor() {
        switch (value) {
            case 0:
                color = Color.BLACK;
                break;
            case 2:
                color = Color.blue;
                break;
            case 4:
                color = Color.cyan;
                break;
            case 8:
                color = Color.gray;
                break;
            case 16:
                color = Color.green;
                break;
            case 32:
                color = Color.darkGray;
                break;
            case 64:
                color = Color.PINK;
                break;
            case 128:
                color = Color.red;
                break;
            case 256:
                color = Color.YELLOW;
                break;
            case 512:
                color = Color.ORANGE;
                break;
            case 1024:
                color = Color.white;
                break;
        }
    }

    public static void main(String[] args) {
        Tile tile1 = new Tile(0, 0, 4);
        Tile tile2 = new Tile(0, 1, 4);
        tile1.moveTo(tile2);
        System.out.print(tile1.color);
        System.out.print(tile2.color);
    }
}
