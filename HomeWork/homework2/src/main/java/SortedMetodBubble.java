public class SortedMetodBubble implements Sorted {
    @Override
    public void sortedPerson(Person[] people) {
        long start = System.currentTimeMillis();

        Person temp;
        for (int j = people.length - 1; j >= 0; j--) {
            for (int i = 0; i < j; i++) {
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
