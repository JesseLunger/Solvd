package reflections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class ClassInfoReflection {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    Class<?> targetClass;
    Object classInstance;

    public ClassInfoReflection(Object targetClass) {
        this.targetClass = targetClass.getClass();
    }

    public void getClassName() {
        LOGGER.info("ClassName: " + targetClass.getSimpleName());
    }

    public void getSuperClass() {
        LOGGER.info("Superclass: " + targetClass.getSuperclass() != null ? targetClass.getSuperclass() : "None");
    }

    public void logFields() {
        LOGGER.info("Fields: ");
        Class<?> tmpClass = targetClass;
        ArrayList<Field> fields = new ArrayList<>();
        while (tmpClass != null) {
            Arrays.stream(tmpClass.getDeclaredFields()).forEach(field -> fields.add(field));
            tmpClass = tmpClass.getSuperclass();
        }
        fields.stream().forEach(field -> LOGGER.info("-" + field));
    }

    public void logConstructors() {
        LOGGER.info("Constructors: ");
        Class<?> tmpClass = targetClass;
        ArrayList<Constructor<?>> constructors = new ArrayList<>();
        while (tmpClass != null) {
            Arrays.stream(tmpClass.getDeclaredConstructors()).forEach(constructor -> constructors.add(constructor));
            tmpClass = tmpClass.getSuperclass();
        }
        constructors.stream().forEach(constructor -> LOGGER.info("-" + constructor));
    }

    public void logMethods() {
        LOGGER.info("Methods:");
        Class<?> tmpClass = targetClass;
        ArrayList<Method> methods = new ArrayList<>();
        while (tmpClass != null) {
            Arrays.stream(targetClass.getDeclaredMethods()).forEach(method -> methods.add(method));
            tmpClass = tmpClass.getSuperclass();
        }
        methods.stream().forEach(method -> LOGGER.info("-" + method));
    }

    private Class<?>[] getParameterTypes(Object[] constructorArgs) {
        return Arrays.stream(constructorArgs)
                .map(Object::getClass)
                .toArray(Class<?>[]::new);
    }

    public <T> T createInstance(Class<T> targetClass, Object[] constructorArgs) {
        try {
            Constructor<T> constructor = targetClass.getDeclaredConstructor(getParameterTypes(constructorArgs));
            return constructor.newInstance(constructorArgs);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}