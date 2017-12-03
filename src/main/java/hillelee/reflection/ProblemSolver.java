package hillelee.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;


public class ProblemSolver {
/*
    @SneakyThrows
    public String solve(Object problem) {
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
*/

    public String solve(Object problem) {
        Class<?> aClass = problem.getClass();
        Method[] methods = aClass.getMethods();

        String s = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(CorrectAnswer.class))
                //.map(method -> (String) method.invoke(problem))
                /**
                 * Вынес invoke в отдельный приватный метод, а над ним уже
                 * поставил SneakyThrows и уже его использую в лямбде,
                 * но не уверен, что нет получше вариантов или есть?))
                 */
                .map(method -> invokeMethod(problem, method))
                .collect(Collectors.toList()).get(0);

        return s;
    }

    @SneakyThrows
    private String invokeMethod(Object object, Method method) {
        return (String) method.invoke(object);
    }
}
