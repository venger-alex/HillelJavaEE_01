package hillelee.apple;

import com.google.common.collect.ImmutableList;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MyAppleFilterTest {
    List<Apple> apples = ImmutableList.of(
            new Apple("RED", 150),
            new Apple("GREEN", 110),
            new Apple("RED", 120),
            new Apple("GREEN", 140),
            new Apple("GREEN", 120)
    );

    @Test
    public void filterByWeight() throws Exception {
        List<Apple> filtered = MyAppleFilter.filter(apples, new MyAppleFilterByWeight(120));

        System.out.println(filtered);

        Assert.assertThat(filtered, Matchers.hasSize(2));

    }

    @Test
    public void filterByColor() throws Exception {
        List<Apple> filtered = MyAppleFilter.filter(apples, new MyAppleFilterByColor("GREEN"));

        System.out.println(filtered);

        Assert.assertThat(filtered, Matchers.hasSize(3));

    }

}