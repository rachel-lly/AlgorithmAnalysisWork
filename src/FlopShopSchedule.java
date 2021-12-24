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
            int key= Math.min(a[i], b[i]);//按Johnson法则分别取对应的b[i]或a[i]值作为关键字
            boolean job = a[i]<=b[i];//给符合条件a[i]<b[i]的放入到N1子集标记为true
            d[i] = new Element(key,i,job);
        }

        Arrays.sort(d);//对数组d按关键字升序进行排序
        int j=0,k=n-1;

        for(int i=0;i<n;i++){
            if(d[i].job){
                c[j++]=d[i].index;//将排过序的数组d，属于N1的从前面进插入
            }else{
                c[k--]=d[i].index;//属于N2的从后面进入
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
