package hillelee.restaraunt;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RestarauntTest {
    Restaraunt restaraunt = new Restaraunt(ImmutableList.of(
            new Dish("Пюре", 25, true, DishType.VEGETABLES),
            new Dish("Отбивная", 470, true, DishType.BEEF),
            new Dish("Пельмени", 570, true, DishType.BEEF),
            new Dish("Салат", 35, true, DishType.VEGETABLES),
            new Dish("Окорочек", 370, true, DishType.CHICKEN)));

    @Test
    public void printLowCaloriesDishesNamesStreamAPI() {
        restaraunt.getMenu().stream()
                .filter(dish -> dish.getCalories() < 100)
                .map(dish -> dish.getName())
                .forEach(System.out::println);
    }

    @Test
    public void printLowCaloriesDishesNamesOldSchool() {
        List<Dish> menu = restaraunt.getMenu();

        for (Dish dish : menu) {
            if(dish.getCalories() < 100) {
                System.out.println(dish.getName());
            }
        }

    }
}