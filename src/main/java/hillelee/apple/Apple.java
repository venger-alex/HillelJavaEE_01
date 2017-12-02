package hillelee.apple;

import com.google.common.collect.ImmutableList;
import hillelee.defaultMethods.Fruit;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Apple implements Fruit {
    private String color;
    private Integer weight;
    private List<String> worms = ImmutableList.of("Worm1", "Worm2", "Worm3");

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }
}
