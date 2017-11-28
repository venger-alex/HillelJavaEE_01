package hillelee.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.Method;


public class ProblemSolver {
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
}
