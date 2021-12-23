/**
 * @Author: rachel-lly
 * @Date: 2021-12-23 17:19
 */
public class FlopShopSchedule {
    private static class Element implements Comparable<Object>{
        int key;
        int index;
        boolean job;
        public Element(int kk,int ii,boolean jj){
            key = kk;
            index = ii;
            job = jj;
        }
        public int compareTo(Object x) {
            int xkey =((Element)x).key;
            if(key<xkey) return -1;
            if(key==xkey) return 0;
            return 1;
        }
    }
    public static int flowShop(int[] a,int[] b,int[] c){
        int n = a.length;
        Element[] d = new Element[n];
        for(int i=0;i<n;i++){
            int key=a[i]>b[i]? b[i]:a[i];//按Johnson法则分别取对应的b[i]或a[i]值作为关键字
            boolean job = a[i]<=b[i];//给符合条件a[i]<b[i]的放入到N1子集标记为true
            d[i] = new Element(key,i,job);
        }
        java.util.Arrays.sort(d);//对数组d按关键字升序进行排序
        int j=0,k=n-1;
        for(int i=0;i<n;i++){
            if(d[i].job)
                c[j++]=d[i].index;//将排过序的数组d，属于N1的从前面进插入
            else
                c[k--]=d[i].index;//属于N2的从后面进入
        }
        j=a[c[0]];
        k=j+b[c[0]];
        for(int i=1;i<n;i++){
            j+=a[c[i]];
            k=j<k?k+b[c[i]]:j+b[c[i]];//计算最优加工时间
        }
        System.out.println("作业的最优加工顺序为(编号从0开始)：");
        for(int i=0;i<c.length;i++){
            System.out.print(c[i]+"  ");
        }
        System.out.println();
        return k;
    }
    public static void main(String[] args) {
        int[] a = new int[6];
        int[] b = new int[6];
        int[] c = new int[6];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 10 + 1);
            b[i] = (int) (Math.random() * 10 + 1);
            System.out.println("第" + i + "个作业在M1,M2上的加工时间为：" + a[i] + " " + b[i]);
        }
        int k = flowShop(a, b, c);
        System.out.println("完成作业的最短时间为:" + k);
    }
}
