
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите число персонажей которые хотите создать:");
        Scanner scanner = new Scanner(System.in);
        int coutPerson = scanner.nextInt();

        Registr registr = new Registr(coutPerson);
        registr.add();

        System.out.println("Выберите позицию(число) метода сортировки:\n1 - Пузырек\n2 - Вставка");
        int numberMethod = scanner.nextInt();
        switch (numberMethod) {
            case 1:
                registr.sortedBubble();
                break;
            case 2:
                registr.sortPast();
                break;
            default:
                System.out.println("Неверная команда!");
        }
    }

}
