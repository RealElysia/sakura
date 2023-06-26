import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class demo {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("输入你的选择:");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    flower();
                    break;
                case 2:
                    mount();
                    break;
                case 3:
                    guess();
                    break;
                case 4:
                   ArrayList<Integer> skip = skipSeven();
                   System.out.println(skip);
            }
        }
    }
    // 水仙花数
    public static void flower(){
        int c = 0;
        for (int i = 0; i<999;i++){
            int g = i % 10;
            int s = i / 10 % 10;
            int b = i/100;
            if (g*g*g + s*s*s + b*b*b == i){
                System.out.println(i);
                c++;
            }
        }
        System.out.printf("一共%d个", c);
    }
    // 纸折叠珠穆朗玛峰
    public static void mount(){
        int count = 0;
        double paper = 0.1;
        int high = 8844430;
        while (paper <= high){
            paper *= 2;
            count++;
        }
        System.out.format("需要折叠%d次", count);
    }
    // 猜数字
    public static void guess(){
        Random rd = new Random();
        int pc = rd.nextInt(101);
        int count = 0;
        while (true){
            try (Scanner sc = new Scanner(System.in)) {
                System.out.print("请输入一个数:");
                int player = sc.nextInt();
                if (player<pc){
                    System.out.println("小了");
                }
                else if (player>pc) {
                    System.out.println("大了");
                }
                else {
                    System.out.println("答对了");
                    break;
                }
            }
            count++;
        }
        System.out.format("你猜了%d次", count);
    }

    // 逢七过
    public static ArrayList<Integer> skipSeven(){
        int num = 100;
        ArrayList<Integer> skip = new ArrayList<>();
        for (int i=1; i<=num; i++){
            if (i%10==7 | i%7==0 | i/10==7)
            {
                skip.add(i);
            }
        }
        return skip;
    }
}
