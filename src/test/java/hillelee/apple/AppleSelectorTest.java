package hillelee.apple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

public class AppleSelectorTest {
    List<Apple> apples = ImmutableList.of(
            new Apple("RED", 150),
            new Apple("GREEN", 110),
            new Apple("RED", 120),
            new Apple("GREEN", 140),
            new Apple("GREEN", 130)
    );

    @Test
    public void getHeaviest() throws Exception {
        Optional<Apple> heaviestOptional = AppleSelector.getHeaviest(apples);

        if(heaviestOptional.isPresent()) {
            Apple heaviest = heaviestOptional.get();

            Assert.assertThat(heaviest.getWeight(), Matchers.is(150));
        } else {
            fail();
        }
    }

    @Test
    public void getHeaviestFromEmptyList() throws Exception {
        Optional<Apple> heaviestOptional = AppleSelector.getHeaviest(ImmutableList.of());

        if(heaviestOptional.isPresent()) {
            fail();
        }
    }

    @Test
    public void sort() throws Exception {
        List<Apple> applesMutable = new ArrayList<>(apples);

        applesMutable.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        System.out.println(applesMutable);

    }

    @Test
    public void mapDefault() throws Exception {
        Map<String, Integer> nameToSalary = ImmutableMap.of("Ivan", 200);

        Integer salary = nameToSalary.getOrDefault("Alex", 0);

        System.out.println("salary=" + salary);
    }

    @Test
    public void filterByPredicate() throws Exception {
        List<Apple> filtered = AppleSelector.filter(apples, new ColorPredicate());

        Assert.assertThat(filtered, Matchers.hasSize(3));

    }

    @Test
    public void filterByAnonymousPredicate() throws Exception {

        //List<Apple> filtered = AppleSelector.filter(apples, apple -> apple.getWeight() > 120);

        Predicate<Apple> heavierThan120 = apple -> apple.getWeight() > 120;
        Predicate<Apple> isRed = apple -> apple.getColor().equals("RED");
        Predicate<Apple> heavyAndRed = isRed.and(heavierThan120);
        //heavyAndRed = ((Predicate<Apple>) apple -> apple.getWeight() > 120).and(apple -> apple.getColor().equals("RED"));

        List<Apple> filtered = apples.stream()
                    .filter(heavyAndRed)
                    .collect(Collectors.toList());

        Assert.assertThat(filtered, Matchers.hasSize(1));
    }

    @Test
    public void mapToColor() throws Exception {
        List<String> colors = apples.stream()
                .map(Apple::getColor)
                .collect(Collectors.toList());

        Assert.assertThat(colors, Matchers.hasSize(5));
        Assert.assertThat(colors.get(0), Matchers.is("RED"));
    }

    @Test
    public void printApples() throws Exception {
        apples.stream()
                .forEach(System.out::println);
    }

    @Test
    public void findExactSame() throws Exception {
        ColorAdjuster colorAdjuster = new ColorAdjuster();

        apples.stream()
                .map(Apple::getColor)
                .map(colorAdjuster::adjust)
                .forEach(System.out::println);


    }

    @Test
    public void executionFlow() throws Exception {
        apples.stream()
                .filter(apple -> apple.getWeight() > 59)
                .map(Apple::getColor)
                .peek(System.out::println)
                .limit(3)
                .collect(Collectors.toList());
    }

    @Test
    public void findFirst() throws Exception {
        apples.stream()
                .filter(apple -> apple.getWeight() > 59)
                .findFirst()
                .map(Apple::getColor)
                .ifPresent(System.out::println);
    }

    @Test
    public void intStream() throws Exception {
        IntSummaryStatistics intSummaryStatistics = apples.stream()
                .map(apple -> apple.getWeight())
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        System.out.println(intSummaryStatistics);

    }

    @Test
    public void collectorsToMap() throws Exception {
        Map<Integer, Apple> weightToApple = apples.stream()
                                            .collect(Collectors.toMap(Apple::getWeight, Function.identity()));

        Assert.assertThat(weightToApple.get(120), Matchers.is(apples.get(2)));
    }

    @Test
    public void groupingBy() throws Exception {
        Map<String, List<Apple>> collect = apples.stream()
                .collect(Collectors.groupingBy(Apple::getColor, Collectors.toList()));

        System.out.println(collect.get("RED"));

    }

    @Test
    public void getAllWorms() throws Exception {
        apples.stream()
                .flatMap(apple -> apple.getWorms().stream())
//              .map(Apple::getWorms)
//              .flatMap(List::stream)
                .distinct()
                .forEach(System.out::println);
    }
}

