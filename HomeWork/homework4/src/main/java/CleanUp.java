import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class CleanUp {

    void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {

        Class<?> clazz = object.getClass();

        Class[] interfaces = clazz.getInterfaces();
        for (Class interClazz : interfaces) {
            if (interClazz.getName().equals("Map")) {
                mapOutput(object, fieldsToOutput);
                mapCleanup(object,fieldsToCleanup);
            }
        }
        fieldCleanUp(object, fieldsToCleanup, clazz);
        fieldOutput(object, fieldsToOutput, clazz);
    }

    public void fieldCleanUp(Object object, Set<String> fieldsToCleanup, Class<?> clazz) {
        fieldsToCleanup.forEach((el -> {
            try {
                Field field = clazz.getDeclaredField(el);
                field.setAccessible(true);
                if (field.getType().isPrimitive()) {
                    if (field.getType().getName().equals("boolean")) {
                        field.setBoolean(object, false);
                    } else if (field.getType().getName().equals("char")) {
                        field.setChar(object, '\u0000');
                    } else {
                        field.set(object, 0);
                    }
                } else {
                    field.set(object, null);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        ));

    }

    public void fieldOutput(Object object, Set<String> fieldsToOutput, Class<?> clazz) {
        fieldsToOutput.forEach((el -> {
            try {
                Field field = clazz.getDeclaredField(el);
                field.setAccessible(true);
                if (field.getType().isPrimitive()) {
                    System.out.println(String.valueOf(field.get(object)));
                } else {
                    System.out.println(field.get(object).toString());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        ));
    }

    public void mapOutput(Object object, Set<String> fieldsToOutput) {
        Map<?, ?> map = (Map<?, ?>) object;

        fieldsToOutput.forEach((el -> {
            if (map.containsKey(el)) {
                System.out.println(map.get(el));
            } else {
                throw new IllegalArgumentException("Поле отсутствует");
            }
        }));
    }

        public void mapCleanup(Object object, Set<String> fieldsToCleanup){
            Map<?, ?> map = (Map<?, ?>) object;

            fieldsToCleanup.forEach((el -> {
                if (map.containsKey(el)) {
                    map.remove(el);
                } else {
                    throw new IllegalArgumentException("Поле отсутствует");
                }
            }
            ));
        }



}
