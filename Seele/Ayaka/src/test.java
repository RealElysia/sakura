
import java.util.*;
public class test {
    public static void main(String[] args) {
        int[] num = {1,3,5,7,9,2,4,8,5};
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("1.两数之和\n"+"2.回文数\n"+"3.罗马文转数字\n");
            System.out.print("请选择测试的算法:");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    int sum = 6;
                    System.out.println(Arrays.toString(twoSum(num, sum)));
                    break;
                case 2:
                    int x = 12212;
                    System.out.println(isPalindrome(x));
                    break;
                case 3:
                    String s = "MCMXCIV";
                    System.out.println(romanInt(s));
                    break;
            }
        }
    }
    /**一个数组和一个整数，找出数组里和是整数的元素并返回下标
     * @param num, sum
     * @return num
     * @author Ayaka
     */
    public static int[] twoSum(int[] num, int sum){
        int size = num.length;
        for (int i=0; i<size; ++i){
            for (int j=i+1; j<size; ++j){
                if (sum == num[i] + num[j]){
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
    /**回文数，给定一个数从左到右和从右到左读完全相同
     * @param x
     * @author Ayaka
     */
    public static boolean isPalindrome(int x){
        // 特殊情况，负数、尾数0
        if (x<0 || (x%10==0 && x!=0)){
            return false;
        }
        // 反转一半的数字
        int re = 0;
        while (x>re){
            re = re*10 + x%10;
            x /= 10;
        }
        return x==re || x==re/10;
    }
    /**罗马数字转阿拉伯数字
     * @author Ayaka
     * */
    private static int value(char s){
        switch (s){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
    public static int romanInt(String roman){
        int sum = 0;
        int preNum = value(roman.charAt(0));
        for (int i=1; i<roman.length();i++){
            int num = value(roman.charAt(i));
            if (preNum < num){
                sum -= preNum;
            }else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }
}
