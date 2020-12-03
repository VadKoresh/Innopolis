// очень важная мелочь - не допускайте орфографических ошибок в названиях
public class Registr {

    private Person[] people;

    public Registr(int countPers) {
        people = new Person[countPers];
    }

    // интерфейс класса не интуитивен, на консультации разберём почему
    public void add() {
        PersonCreates personCreates = new PersonCreates();
        for (int i = 0; i < people.length; i++) {
            people[i] = personCreates.creat();
        }
    }

    public Person[] getPeople() {
        return people;
    }

    public void sortedBubble() {
        new SortedMetodBubble().sortedPerson(people);
    }

    public void sortPast() {
        new SotredInsertMetod().sortedPerson(people);
    }

    //======================================================================================================
    private class PersonCreates {

        protected Person creat() {
            Sex sex;
            int randomSex = (int) (Math.random() * 2);
            if (randomSex == 0) {
                sex = Sex.MEN;
            } else {
                sex = Sex.WOMEN;
            }

            String[] manName = {"Вадим", "Антон", "Сергей", "Константин", "Артем", "Максим", "Алексей"
                    , "Петр", "Денис", "Стас"};
            String[] womanName = {"Лена", "Анна", "Настя", "Юля", "Маргарита", "Ксения", "Светлана", "Алена"
                    , "Наташа", "Тамара"};
            int randomName = (int) (Math.random() * 10);

            int randomAge = (int) (Math.random() * 101);

            return new Person(sex, sex == Sex.MEN ? manName[randomName] : womanName[randomName], randomAge);
        }
    }

}
