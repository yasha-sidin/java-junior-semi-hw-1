package seminar1.task2.market.food;

public class Chebureck implements SemiFinishedFood {
    @Override
    public boolean getProteins() {
        return false;
    }

    @Override
    public boolean getFats() {
        return true;
    }

    @Override
    public boolean getCarbohydrates() {
        return false;
    }

    @Override
    public String getName() {
        return "Chebureck";
    }
}
