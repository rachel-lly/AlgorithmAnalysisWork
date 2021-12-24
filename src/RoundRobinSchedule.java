import java.util.Scanner;

/**
 * @Author: rachel-lly
 * @Date: 2021-12-23 17:09
 */
public class RoundRobinSchedule {

    public static int[][] table(int n){

        int[][] a = new int[n][n];
        //参赛人员
        for(int i = 0; i<n;i++)
            a[0][i] = i+1;

        //采用分治算法，构造整个赛程表
        for(int r = 1;r<n;r<<=1){
            for(int i =0;i<n;i += 2*r){
                copy(a,r,r+i, i,r);
                copy(a,r,i, r+i,r);
            }
        }
        return a;
    }

    private static void copy(int[][] a, int tox, int toy, int fromy, int r){
        for(int i =0;i<r;i++){
            System.arraycopy(a[i], fromy, a[tox + i], toy, r);
        }

    }
    public static void main(String[] args) {
        System.out.println("please enter k:[eg:4](people = 2^k)");
        Scanner scan =new Scanner(System.in);
        int num;
        while(true){
            int enter = scan.nextInt();
            if(enter>0){
                num=(int) Math.pow(enter,2);
                break;
            }
            System.out.println("enter error!");
        }

        System.out.println("people's number: "+num+"\n");

        System.out.print("day:");
        for(int i=1;i<num;i++){
            System.out.print(i + "\t");
        }
        System.out.println();
        int[][] a = table(num);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i][0]+"|\t");
            for(int j = 1;j<a[0].length;j++){
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }


}
