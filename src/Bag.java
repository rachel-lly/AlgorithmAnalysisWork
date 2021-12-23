/**
 * @Author: rachel-lly
 * @Date: 2021-12-23 17:14
 */
public class Bag {

    int[][] maxValue;//当前背包最重重量（dp解决）
    int bagWeight;//背包能承受最大重量

    int n;//物品个数
    int[] weight;//物品重量
    int[] value;//物品价值

    public Bag(int[] weight, int[] value,int bagWeight) {
        this.bagWeight = bagWeight;
        this.weight = weight;
        this.value = value;

        this.n = weight.length;

        this.maxValue = new int[n+1][bagWeight + 1];
    }

    public void bag(int[] weight, int[] value, int bagWeight){


        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= bagWeight; j++) {

                maxValue[i][j] = maxValue[i-1][j];

                if (j - weight[i-1] >= 0) {
                    maxValue[i][j] = Math.max(maxValue[i][j],(maxValue[i-1][j - weight[i-1]] + value[i-1]));
                }
            }
        }

        System.out.println();
        for(int[] m: maxValue){
            for(int x: m){
                System.out.print(x+"\t");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("maxValue: " +maxValue[n][bagWeight]);

    }

    //回推求最优解
    public void traceback() {

        System.out.println("\ngoods of bag: ");

        int nowWeight = bagWeight;

        for(int i=n;i>=1;i--){
            if(maxValue[i][nowWeight]>maxValue[i-1][nowWeight]){
                System.out.printf(i+"\t");
                nowWeight-=weight[i-1];

            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int weight[] = {2,1,3,2};
        int value[] = {12,10,20,15};
        int bagWeight = 5;
        Bag bag = new Bag(weight,value,bagWeight);
        bag.bag(weight, value, bagWeight);
        bag.traceback();
    }

}
