import java.util.LinkedList;

/**
 * @Author: rachel-lly
 * @Date: 2021-12-23 17:09
 */
public class RoundRobinSchedule {

    private int num; // 人数
    private LinkedList<Integer> list = new LinkedList<Integer>();
    public RoundRobinSchedule(int n)  {
        this.num = n;
        init();
    }

    private void init()  {
        if (num % 2 == 0)  { //偶数个
            for (int i = 0; i < num; i++) {
                list.add(i + 1);
            }
        }
        else  //奇数个
        {
            for (int i = 0; i < num; i++)
            {
                list.add(i + 1);
            }
            list.add(0);
        }
    }
    public void print()  {
        for (int i = 0; i < list.size() - 1; i++)  {
            System.out.println("第" + (i + 1) + "天");
            for (int j = 0; j < list.size() / 2; j++)  {
                System.out.println(list.get(j) + "--"
                        + list.get(list.size() - 1 - j));
            }
            int temp = list.pollLast();  //获取最后一个元素
            //System.out.println(list.get(1));
            list.add(1, temp);//将最后一个元素放在List的第二个位置
        }
    }
    public static void main(String[] args) {
        //实例化对象
        RoundRobinSchedule r = new RoundRobinSchedule(16);
        r.print();
    }

}
