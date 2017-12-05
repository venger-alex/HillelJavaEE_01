package hillelee.restaraunt;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Restaraunt {
    private List<Dish> menu;

}
