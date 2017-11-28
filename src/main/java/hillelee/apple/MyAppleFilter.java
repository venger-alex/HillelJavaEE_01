package hillelee.apple;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class MyAppleFilter {
    public static List<Apple> filter(List<Apple> apples, MyApplePredicate predicate) {
        List<Apple> res = new ArrayList<>();

        for (Apple apple : apples) {
            if(predicate.test(apple)) {
                res.add(apple);
            }
        }

        return res;
    }
}

interface MyApplePredicate {
    Boolean test(Apple apple);
}

@AllArgsConstructor
class MyAppleFilterByWeight implements MyApplePredicate {
    Integer weight;

    @Override
    public Boolean test(Apple apple) {
        return apple.getWeight().equals(weight);
    }
}

@AllArgsConstructor
class MyAppleFilterByColor implements MyApplePredicate {
    String color;

    @Override
    public Boolean test(Apple apple) {
        return apple.getColor().equals(color);
    }
}