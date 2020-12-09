import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CleanUp {

    // очень хотелось бы примеров, на которых можно протестировать работу
    void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput){

        Class<?> clazz = object.getClass();

        // если класс будет реализовывать ин терфейс не Map, а например NavigableMap, то мы это не увидим, нужно
        // также просмотреть и что наследуют интерфейсы
        Class[] interfaces = clazz.getInterfaces();
        for (Class interClazz: interfaces){
            // лучше используйте полное имя класса
            if (interClazz.getName().equals("Map")){
                mapOutput(object, fieldsToOutput);
                mapClean(object, fieldsToCleanup);
            }
        }
        // если вы поняли, что это мапа, то вам не нужно применять у ней подход для обычных объектов
        fieldCleanUp(object, fieldsToCleanup, clazz);
        fieldOutput(object, fieldsToOutput, clazz);
    }

    public void fieldCleanUp(Object object, Set<String> fieldsToCleanup, Class<?> clazz){
        Iterator<String> iterator = fieldsToCleanup.iterator();

        while (iterator.hasNext()){
            try {
                Field field = clazz.getDeclaredField(iterator.next());
                field.setAccessible(true);
                if (field.getType().isPrimitive()){
                    if (field.getType().getName().equals("boolean")){
                        field.setBoolean(object,false);
                    }else if (field.getType().getName().equals("char")){
                        field.setChar(object, '\u0000');
                    }else {
                        field.set(object, 0);
                    }
                }else {
                    field.set(object, null);
                }
                // если вы упадёте, то те поля, которые вы уже успели очистить такими и останутся, а по зпдпче объект
                // должен в случае ошибки остаться неизменным
                // и лучше прокиньте, нес стоит замалчтивать ошибку. То же про вывод полей
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void fieldOutput(Object object, Set<String> fieldsToOutput, Class<?> clazz){
        Iterator<String> iterator = fieldsToOutput.iterator();

        while (iterator.hasNext()){
            try {
                Field field = clazz.getDeclaredField(iterator.next());
                field.setAccessible(true);
                if (field.getType().isPrimitive()){
                    System.out.println(String.valueOf(field.get(object)));
                }else {
                    System.out.println(field.get(object).toString());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void mapClean(Object object, Set<String> fieldsToCleanup) {
        Map<?, ?> map = (Map<?, ?>) object;

        Iterator<String> iteratorClean = fieldsToCleanup.iterator();


        while (iteratorClean.hasNext()) {
            String key = iteratorClean.next();
            if (map.containsKey(key)) {
                map.remove(key);
            } else {
                // опять же если вы упадёте, то те поля, которые вы уже успели очистить такими и останутся, а по зпдпче
                // объект
                // должен в случае ошибки остаться неизменным. То же про вывод полей
                throw new IllegalArgumentException("Поле отсутствует");
            }
        }
    }

        public void mapOutput(Object object, Set<String> fieldsToOutput){
            Map<?, ?> map = (Map<?, ?>) object;

            Iterator<String> iteratorOut = fieldsToOutput.iterator();

            while (iteratorOut.hasNext()) {
                String key = iteratorOut.next();
                if (map.containsKey(key)) {
                    System.out.println(map.get(key));
                } else {
                    throw new IllegalArgumentException("Поле отсутствует");
                }
            }
        }
    }

