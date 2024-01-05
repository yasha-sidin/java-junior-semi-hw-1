package seminar1.task2.market.food;

import seminar1.task2.market.UMarket;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Cart<T extends Food> {
    private final ArrayList<T> foodStuffs;
    private final UMarket market;
    private final Class<T> clazz;

    public Cart(Class<T> clazz, UMarket market) {
        this.clazz = clazz;
        this.market = market;
        foodStuffs = new ArrayList<>();
    }

    public void add(T product) {
        foodStuffs.add(product);
    }

    public ArrayList<T> getFoodstuffs() {
        return foodStuffs;
    }

    public boolean isBalancing() {
        return isBalancing(Food::getCarbohydrates, Food::getProteins, Food::getFats);
    }

    public boolean isBalancing(Predicate<Food> p1, Predicate<Food> p2, Predicate<Food> p3) {
        return Stream.of(foodStuffs.stream().anyMatch(p1),
                foodStuffs.stream().anyMatch(p2),
                foodStuffs.stream().anyMatch(p3)).allMatch(x -> x);
    }

    public boolean needForBalance(Food food) {
        return foodStuffs.stream()
                .filter(x -> x.getProteins() && food.getProteins() ||
                x.getFats() && food.getFats() ||
                x.getCarbohydrates() && food.getCarbohydrates())
                .toList()
                .isEmpty();
    }
}
