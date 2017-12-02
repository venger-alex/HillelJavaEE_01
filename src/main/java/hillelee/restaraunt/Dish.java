package hillelee.restaraunt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
    String name;
    Integer calories;
    Boolean isBio;
    DishType type;

}
