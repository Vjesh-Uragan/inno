import java.util.Scanner;

public class MyClass {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Как тебя зовут: ");
        String phrase1 = sc.nextLine();
        System.out.println("Привет" + " " + phrase1 + "!");
    }
}
