package hillelee.apple;

import hillelee.defaultMethods.Fruit;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Apple implements Fruit {
    private String color;
    private Integer weight;
}
