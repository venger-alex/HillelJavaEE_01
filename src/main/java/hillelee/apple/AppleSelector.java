package hillelee.apple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class AppleSelector {
    public static Optional<Apple> getHeaviest(List<Apple> apples) {
        Apple res = null;

        for(Apple apple : apples) {
            if(res == null || res.getWeight() < apple.getWeight()) {
                res = apple;
            }
        }

        return Optional.ofNullable(res);
    }

    public static List<Apple> filterByHeavy(List<Apple> apples, Integer weight) {
        List<Apple> res = new ArrayList<>();

        for (Apple apple : apples) {
            if(apple.getWeight() > weight) {
                res.add(apple);
            }
        }

        return res;
    }

    public static List<Apple> filterByColor(List<Apple> apples, String color) {
        List<Apple> res = new ArrayList<>();

        for (Apple apple : apples) {
            if(apple.getColor().equals(color)) {
                res.add(apple);
            }
        }

        return res;
    }

    public static <T> List<T> filter(List<T> items, Predicate<T> predicate) {
        List<T> res = new ArrayList<>();

        for (T item : items) {
            if(predicate.test(item)) {
                res.add(item);
            }
        }

        return res;
    }

}

class ColorPredicate implements Predicate<Apple> {
    @Override
    public boolean test(Apple apple) {
        if(apple.getColor().equals("GREEN")) {
            return true;
        }

        return false;
    }
}