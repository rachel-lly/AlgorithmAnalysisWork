import java.util.Arrays;

/**
 * @Author: rachel-lly
 * @Date: 2021-12-23 17:19
 */
public class FlopShopSchedule {

    private static class Element implements Comparable<Object>{
        int key;
        int index;
        boolean job;

        public Element(int key,int index,boolean job){
            this.key = key;
            this.index = index;
            this.job = job;
        }

        public int compareTo(Object o) {
            int k1 =((Element)o).key;
            return Integer.compare(key, k1);
        }
    }

    public static int flowShop(int[] a,int[] b,int[] c){
        int n = a.length;
        Element[] d = new Element[n];

        for(int i=0;i<n;i++){
            int key= Math.min(a[i], b[i]);
            boolean job = a[i]<=b[i];
            d[i] = new Element(key,i,job);
        }

        Arrays.sort(d);
        int j=0,k=n-1;

        for(int i=0;i<n;i++){
            if(d[i].job){
                c[j++]=d[i].index;
            }else{
                c[k--]=d[i].index;
            }

        }

        j = a[c[0]];
        k = j + b[c[0]];

        for(int i=1;i<n;i++){
            j += a[c[i]];
            k = Math.max(j,k) + b[c[i]];//计算最优加工时间
        }

        System.out.print("\nbest order: ");
        for (int value : c) {
            System.out.print(value + "  ");
        }
        System.out.println();

        return k;
    }

    public static void main(String[] args) {
        int[] a = new int[6];
        int[] b = new int[6];
        int[] c = new int[6];
        System.out.println("no\tM1\tM2");
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 10 + 1);
            b[i] = (int) (Math.random() * 10 + 1);
            System.out.println(i + "\t" + a[i] + "\t" + b[i]);
        }

        int k = flowShop(a, b, c);
        System.out.println("finish work time is: " + k);
    }

}
