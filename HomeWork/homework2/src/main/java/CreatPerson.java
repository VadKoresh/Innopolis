public class CreatPerson {

  public static Person creatPerson(){
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
