import java.util.Scanner;

/**
 * @Author: rachel-lly
 * @Date: 2021-12-23 16:17
 */
public class ChessCover {
    int size;//棋盘的行
    int[][] chess;//棋盘
    int number = 0;//L形编号

    //特殊点坐标 （specialRow，specialCol）
    int specialRow;
    int specialCol;

    public ChessCover(int specialRow, int specialCol, int size) {
        this.size = size;
        this.specialRow = specialRow;
        this.specialCol = specialCol;
        this.chess = new int[size][size];
    }

    // 特殊方格如果出现在左上角，则L型骨牌的方向朝向右下角；
    // 如果出现在右上角，则L型骨牌的方向朝向左下角；
    // 如果出现在左下角，则L型骨牌的方向朝向右上角；
    // 如果出现在右下角，则L型骨牌的方向朝向左上角。
    // 矩阵左起点 （leftRow，leftCol）
    public void setChess(int specialRow,int specialCol,int leftRow, int leftCol, int size) {

        if (1 == size) return;

        int subSize = size / 2;
        int n = ++number;

        //特殊点在左上角区域
        if (specialRow < (leftRow + subSize) && specialCol < (leftCol + subSize)) {
            setChess(specialRow,specialCol,leftRow, leftCol, subSize);
        }else {
            //左上角矩阵的右下角就是特殊点
            chess[leftRow + subSize - 1][leftCol + subSize - 1] = n;
            setChess(leftRow + subSize - 1, leftCol + subSize - 1, leftRow, leftCol, subSize);
        }

        //特殊点在右上方
        if (specialRow < leftRow + subSize && specialCol >= leftCol + subSize) {
            setChess(specialRow, specialCol, leftRow, leftCol + subSize, subSize);
        }else {
            //右上方矩阵的左下角就是特殊点
            chess[leftRow + subSize -1][leftCol + subSize] = n;
            setChess(leftRow + subSize -1, leftCol + subSize, leftRow, leftCol + subSize, subSize);
        }

        //特殊点在左下方
        if (specialRow >= leftRow + subSize && specialCol < leftCol + subSize) {
            setChess(specialRow, specialCol, leftRow + subSize, leftCol, subSize);
        }else {
            //左下方矩阵的右上角就是特殊点
            chess[leftRow + subSize][leftCol + subSize - 1] = n;
            setChess(leftRow + subSize, leftCol + subSize - 1, leftRow + subSize, leftCol, subSize);
        }

        //特殊点在右下角
        if (specialRow >= leftRow + subSize && specialCol >= leftCol + subSize) {
            setChess(specialRow, specialCol, leftRow + subSize, leftCol + subSize, subSize);
        }else {
            //右下角矩阵的左上就是特殊点
            chess[leftRow + subSize][leftCol + subSize] = n;
            setChess(leftRow + subSize, leftCol + subSize, leftRow + subSize, leftCol + subSize, subSize);
        }
    }


    public void printChess() {
        setChess(specialRow,specialCol,0, 0, size);
        System.out.println("\n"+ "chess:");
        for (int[] b : chess) {
            for (int x :b) {
                System.out.print(x+"\t");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int specialRow;
        int specialCol;
        int row;
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter special row&column and k(size of chess:2^k x 2^k{k>=0}) in turn: [eg: 0 1 2] ");
        while(true){
            specialRow = sc.nextInt();
            specialCol = sc.nextInt();

            row = (int)Math.pow(2,sc.nextInt());
            if(specialRow<row&&specialCol<row&&row>0) break;
            System.out.println("enter error!");
        }
        ChessCover chessCover = new ChessCover(specialRow , specialCol , row);
        chessCover.printChess();
    }

}
