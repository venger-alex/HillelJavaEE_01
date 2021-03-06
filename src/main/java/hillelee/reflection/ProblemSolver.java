package hillelee.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ProblemSolver {
    @SneakyThrows
    public String solveOldSchool(Object problem) {
        Class<?> aClass = problem.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(CorrectAnswer.class)) {
                //try {
                    return (String) method.invoke(problem);
                //} catch (Exception e) {
                //    throw new RuntimeException(e);
                //}
            }
        }

        throw new RuntimeException("Annotation CorrectAnswer not found");
    }

    //@SneakyThrows
    public String solve(Object problem) {
        return Stream.of(problem)
                .map(Object::getClass)
                .flatMap(clazz -> Arrays.stream(clazz.getMethods()))
                .filter(method -> method.isAnnotationPresent(CorrectAnswer.class))
                //.map(method -> (String) method.invoke(problem))
                .map(method -> invokeMethod(problem, method))
                //.map(invokeOn(problem))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Annotation CorrectAnswer not found"));
    }

    @SneakyThrows
    private String invokeMethod(Object object, Method method) {
        return (String) method.invoke(object);
    }

    private Function<Method, String> invokeOn(Object obj) {
        return method -> {
            try {
                return (String) method.invoke(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
