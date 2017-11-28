package hillelee.apple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static List<Apple> filter(List<Apple> apples, ApplePredicate predicate) {
        List<Apple> res = new ArrayList<>();

        for (Apple apple : apples) {
            if(predicate.test(apple)) {
                res.add(apple);
            }
        }

        return res;
    }

}

interface ApplePredicate {
    Boolean test(Apple apple);
}

class ColorPredicate implements ApplePredicate {
    @Override
    public Boolean test(Apple apple) {
        if(apple.getColor().equals("GREEN")) {
            return true;
        }

        return false;
    }
}