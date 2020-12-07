import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CleanUp {

    void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput){

        Class<?> clazz = object.getClass();

        Class[] interfaces = clazz.getInterfaces();
        for (Class interClazz: interfaces){
            if (interClazz.getName().equals("Map")){
                mapCleanOutput(object, fieldsToCleanup, fieldsToOutput);
            }
        }
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

    public void mapCleanOutput(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput){
        Map<?, ?> map = (Map<?, ?>) object;

        Iterator<String> iteratorClean = fieldsToCleanup.iterator();
        Iterator<String> iteratorOut = fieldsToOutput.iterator();

        while (iteratorClean.hasNext()) {
            String key = iteratorClean.next();
            if(map.containsKey(key)) {
                map.remove(key);
            } else {
                throw new IllegalArgumentException("Поле отсутствует");
            }
        }

        while (iteratorOut.hasNext()) {
            String key = iteratorOut.next();
            if(map.containsKey(key)) {
                System.out.println(map.get(key));
            } else {
                throw new IllegalArgumentException("Поле отсутствует");
            }
        }
    }


}
