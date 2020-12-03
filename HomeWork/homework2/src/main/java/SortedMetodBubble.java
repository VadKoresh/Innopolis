public class SortedMetodBubble implements Sorted {
    @Override
    public void sortedPerson(Person[] people) {
        // должен ли являться замер времени выполнения частью сортировки?
        long start = System.currentTimeMillis();

        Person temp;
        for (int j = people.length - 1; j >= 0; j--) {
            for (int i = 0; i < j; i++) {
                // очень много повторяющегося кода перестановки двух переменных
                if (!people[i].getSex().equals(people[i + 1].getSex())) {
                    if (people[i].getSex().equals(Sex.WOMEN)) {
                        temp = people[i];
                        people[i] = people[i + 1];
                        people[i + 1] = temp;
                    }
                } else if (people[i].getAge() < people[i + 1].getAge()) {
                    temp = people[i];
                    people[i] = people[i + 1];
                    people[i + 1] = temp;
                } else if (people[i].getAge() == people[i + 1].getAge()) {
                    int cash = people[i].getName().compareTo(people[i + 1].getName());
                    if (cash > 0) {
                        temp = people[i];
                        people[i] = people[i + 1];
                        people[i + 1] = temp;
                    } else if (cash == 0) {
                        // бессмысленная конструкция. С тем же успехом вы могли просто написать System.err.println("Имя и возраст совпадают!");
                        // кроме того задайтесь вопросом, должна ли эта проверка быть частью алгоритма сортировки
                        try {
                            throw new MyException();
                        } catch (MyException e) {
                            System.err.println("Имя и возраст совпадают!");
                        }
                    }
                }


            }
        }
        long finish = System.currentTimeMillis();

        int number = 0;
        for (Person person : people) {
            number += 1;
            System.out.println(number + " - " + person.getSex() + " - " + person.getAge() + " - " + person.getName());
        }
        long elapsed = finish - start;

        System.out.println("Прошло времени, мс: " + elapsed);
    }
}
