package hillelee.apple;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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

        List<Apple> filtered = AppleSelector.filter(apples, apple -> apple.getWeight() > 120);

        Assert.assertThat(filtered, Matchers.hasSize(3));
    }

}