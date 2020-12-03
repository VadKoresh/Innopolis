public class SotredInsertMetod implements Sorted {
    @Override
    public void sortedPerson(Person[] people) {
        // должен ли являться замер времени выполнения частью сортировки?

        long start = System.currentTimeMillis();

        for (int i = 1; i < people.length; i++) {
            Person current = people[i];
            int j = i - 1;

            while (j >= 0 && current.getSex().equals(Sex.MEN) && !current.getSex().equals(people[j].getSex())) {
                people[j + 1] = people[j];
                j--;
            }
            if (j >= 0 && current.getSex().equals(people[j].getSex())) {
                while (j >= 0 && current.getSex().equals(people[j].getSex()) && current.getAge() > people[j].getAge()) {
                    people[j + 1] = people[j];
                    j--;
                }
                if (j >= 0 && current.getAge() == people[j].getAge()) {
                    while (j >= 0 && current.getAge() == people[j].getAge()
                            && current.getName().compareTo(people[j].getName()) < 0) {
                        people[j + 1] = people[j];
                        j--;
                    }
                    if (j >= 0 && current.getName().compareTo(people[j].getName()) == 0) {
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
            people[j + 1] = current;
        }
        long finish = System.currentTimeMillis();

        int i = 0;
        for (Person person : people) {
            i += 1;
            System.out.println(i + " - " + person.getSex() + " - " + person.getAge() + " - " + person.getName());
        }

        long elapsed = finish - start;
        System.out.println("Прошло времени, мс: " + elapsed);
    }

}
