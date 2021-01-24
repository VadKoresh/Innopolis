
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите число персонажей которые хотите создать:");
        Scanner scanner = new Scanner(System.in);
        int countPerson = scanner.nextInt();

        Register register = new Register(countPerson);

        System.out.println("Выберите позицию(число) метода сортировки:\n1 - Пузырек\n2 - Вставка");
        int numberMethod = scanner.nextInt();
        switch (numberMethod) {
            case 1:
                long startBubble = System.currentTimeMillis();
                register.sortedBubble();
                long finishBubble = System.currentTimeMillis();
                long elapsedBubble = finishBubble - startBubble;
                System.out.println("Прошло времени, мс: " + elapsedBubble);
                break;
            case 2:
                long startPast = System.currentTimeMillis();
                register.sortPast();
                long finishPast = System.currentTimeMillis();
                long elapsedPast = finishPast - startPast;
                System.out.println("Прошло времени, мс: " + elapsedPast);
                break;
            default:
                System.out.println("Неверная команда!");
        }

        int number = 0;
        for (Person person : register.getPeople()) {
            number += 1;
            System.out.println(number + " - " + person.getSex() + " - " + person.getAge() + " - " + person.getName());
        }
    }

}
