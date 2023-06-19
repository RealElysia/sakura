import java.util.Scanner;


public class question{
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = a/10%10;
            System.out.print(b);
        }
	}
}