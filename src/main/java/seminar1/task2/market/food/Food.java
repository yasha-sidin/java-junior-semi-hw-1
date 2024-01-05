package seminar1.task2.market.food;

import seminar1.task2.market.thing.Thing;

public interface Food extends Thing {
    boolean getProteins();

    boolean getFats();

    boolean getCarbohydrates();

    interface Snack extends Food {
    }

    default String getNameWithPFC() {
        return String.format("%s (Proteins: %s, Fats: %s, Carbohydrates: %s)", getName(),
                getProteins() ? "Yes" : "No", getFats() ? "Yes" : "No", getCarbohydrates() ? "Yes" : "No");
    }
}
