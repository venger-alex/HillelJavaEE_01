package hillelee.restaraunt;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class RestarauntTest {
    Restaraunt restaraunt = new Restaraunt(ImmutableList.of(
            new Dish("Пюре", 25, false, DishType.VEGETABLES),
            new Dish("Жареная картоха", 30, true, DishType.VEGETABLES),
            new Dish("Отбивная", 470, true, DishType.BEEF),
            new Dish("Пельмени", 570, false, DishType.BEEF),
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

    @Test
    public void printTop3HighCaloriesDishesOldSchool() {
        List<Dish> menu = new ArrayList<Dish>(restaraunt.getMenu());

        menu.sort(Comparator.comparing(Dish::getCalories).reversed());

        menu = new ArrayList<Dish>(menu.subList(0, 3));

        for (Dish dish : menu) {
            System.out.println(dish.getName());
        }
    }

    @Test
    public void printTop3HighCaloriesDishesStreamAPI() {
        restaraunt.getMenu().stream()
                .sorted(Comparator.comparing(Dish::getCalories).reversed())
                .limit(3)
                .map(Dish::getName)
                .forEach(System.out::println);
    }

    @Test
    public void printAllSortByBioThanByAlphabetOldSchool() {
        List<Dish> menu = new ArrayList<Dish>(restaraunt.getMenu());

        menu.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                int res = o1.getIsBio().compareTo(o2.getIsBio());
                return res == 0 ? o1.getName().compareTo(o2.getName()) : res;
            }
        });

        for (Dish dish : menu) {
            System.out.println(dish.getName());
        }
    }

    @Test
    public void printAllSortByBioThanByAlphabetStreamAPI() {
        restaraunt.getMenu().stream()
                .sorted(Comparator.comparing(Dish::getIsBio).thenComparing(Dish::getName))
                .map(Dish::getName)
                .forEach(System.out::println);
    }

    @Test
    public void averageCaloriesByGroupsOldSchool() {
        List<Dish> menu = new ArrayList<Dish>(restaraunt.getMenu());
        Map<DishType, Double> res = new HashMap<>();

        for (Dish dish : menu) {
            Double averageCalories = res.get(dish.getType());
            if(averageCalories != null) {
                res.put(dish.getType(), (averageCalories + dish.getCalories().doubleValue()) / 2);
            } else {
                res.put(dish.getType(), dish.getCalories().doubleValue());
            }
        }

        System.out.println(res);
    }

    @Test
    public void averageCaloriesByGroupsStreamAPI() {
        Map<DishType, Double> dishesGroupByTypes = restaraunt.getMenu().stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));

        dishesGroupByTypes.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    @Test
    public void groupBioDishesNamesByTypeOldSchool() {
        List<Dish> menu = new ArrayList<Dish>(restaraunt.getMenu());
        Map<DishType, List<String>> res = new HashMap<>();

        for (Dish dish : menu) {
            if(!dish.getIsBio()) continue;

            List<String> dishesNames = res.get(dish.getType());
            if(dishesNames == null) {
                dishesNames = new ArrayList<String>();
            }

            dishesNames.add(dish.getName());
            res.put(dish.getType(), dishesNames);
        }

        System.out.println(res);
    }

    @Test
    public void groupBioDishesNamesByTypeStreamAPI() {
        Map<DishType, List<String>> collect = restaraunt.getMenu().stream()
                .filter(Dish::getIsBio)
                .collect(Collectors.groupingBy(Dish::getType,
                                                Collectors.mapping(Dish::getName, Collectors.toList())));

        System.out.println(collect);
    }
}