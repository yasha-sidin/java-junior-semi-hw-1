package seminar1.task2.market;

import lombok.Getter;
import seminar1.task2.market.food.*;
import seminar1.task2.market.thing.Notebook;
import seminar1.task2.market.thing.Pen;
import seminar1.task2.market.thing.Thing;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class UMarket {

    private final Collection<Thing> things;

    public UMarket() {
        things = new ArrayList<>();
        initializeThings();
    }

    private void initializeThings() {
        things.add(new Pen());
        things.add(new Notebook());
        things.add(new Chicken());
        things.add(new Fruit());
        things.add(new OliveOil());
        things.add(new BalykCheese());
        things.add(new Chips());
        things.add(new ChocolateBar());
        things.add(new DumplingsBerries());
        things.add(new DumplingsMeat());
        things.add(new Chebureck());
    }

    public <T extends Thing> List<String> getThingStrings(Class<T> clazz) {
        AtomicInteger index = new AtomicInteger(0);
        return things.stream().filter(clazz::isInstance).map((x) -> {
            if (clazz.isInstance(x)) {
                if (Food.class.isAssignableFrom(x.getClass())) {
                    return "[" + index.addAndGet(1) + "] - " + ((Food) x).getNameWithPFC();
                } else {
                    return "[" + index.addAndGet(1) + "] - " + x.getName();
                }
            }
            return null;
        }).toList();
    }

    public <T extends Thing> List<T> getThingsByClass(Class<T> clazz) {
        return things.stream().filter(clazz::isInstance).filter(clazz::isInstance).map(clazz::cast).toList();
    }

    public <T extends Thing> Optional<T> getThingByIndex(Class<T> clazz, int index) {
        AtomicInteger counter = new AtomicInteger(0);
        return things.stream()
                .filter(x -> clazz.isAssignableFrom(x.getClass()))
                .filter(x -> counter.getAndAdd(1) == index)
                .map(clazz::cast)
                .findFirst();
    }
}
