package DZ_10;

import java.util.Scanner;

public class simvol {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите последовательность символов ('>', '<', '-'): ");

        String sequence = scanner.nextLine();

        int arrowCount = 0;

        // Ищем стрелки >>--> и <--<<
        for (int i = 0; i <= sequence.length() - 5; i++) {
            String substring = sequence.substring(i, i + 5);
            if (substring.equals(">>-->") || substring.equals("<--<<")) {
                arrowCount++;
            }
        }

        System.out.println(arrowCount);
        scanner.close();
    }
}
