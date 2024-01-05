package seminar1.task2.market;

import seminar1.task2.market.food.*;
import seminar1.task2.market.thing.Thing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Program {
    private final static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        UMarket market = new UMarket();
        System.out.println("\tWelcome to the UMarket!");

        while (true) {
            System.out.println("===========================================================");
            System.out.println("0 - exit");
            System.out.println("1 - all products");
            System.out.println("2 - form snack cart");
            System.out.println("3 - form healthy food cart");
            System.out.println("4 - form semi-finished food cart");
            System.out.println("5 - form all food cart");
            System.out.print("Choose menu point: ");

            try {
                int command = Integer.parseInt(bf.readLine());
                switch (command) {
                    case 0 -> {
                        try {
                            bf.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Exit. Goodbye!");
                        return;
                    }
                    case 1 -> {
                        System.out.println("List of products: ");
                        market.getThingStrings(Thing.class).forEach(System.out::println);
                    }
                    case 2 -> createCart(Snack.class, market);
                    case 3 -> createCart(HealthyFood.class, market);
                    case 4 -> createCart(SemiFinishedFood.class, market);
                    case 5 -> createCart(Food.class, market);
                    default -> System.out.println("Wrong command. Try again.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + ".\nWrong command. Try again.");
            }
        }

    }

    public static <T extends Food> void createCart(Class<T> clazz, UMarket market) {
        Cart<T> cart = new Cart<>(clazz, market);
        while (true) {
            makingInfoForCart(clazz, market).forEach(System.out::println);
            try {
                System.out.print("Please input product which you want to add: ");
                int command = Integer.parseInt(bf.readLine());
                switch (command) {
                    case 0 -> {
                        cartBalancing(cart, clazz, market);
                        System.out.println("End balancing and forming cart. Your cart: ");
                        cart.getFoodstuffs().forEach(x -> System.out.println(x.getNameWithPFC()));
                        return;
                    }
                    default -> {
                        var thing = market.getThingByIndex(clazz, command - 1);
                        if (thing.isPresent() && command <= market.getThingStrings(clazz).size()) {
                            cart.add(thing.get());
                            System.out.println("Product '" + thing.get().getNameWithPFC() + "' was added.");
                            System.out.println("Your cart: ");
                            cart.getFoodstuffs().forEach(x -> System.out.println(x.getNameWithPFC()));
                        } else {
                            System.out.println("There is not any product with this index in list. Please try again.");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.");
            }
        }}

    private static <T extends Food> void cartBalancing(Cart<T> cart, Class<T> clazz, UMarket market) {
        if(cart.isBalancing()) {
            System.out.println("Cart has been already balanced");
        } else {
            System.out.println("Cart is not balanced");
            market.getThingsByClass(clazz).forEach(x -> {
                if (!cart.isBalancing() && cart.needForBalance(x)) {
                    cart.add(x);
                    System.out.println("Product: " + x.getNameWithPFC() + " was added to cart for balancing");
                }
            });
            if (cart.isBalancing()) {
                System.out.println("Cart has been balanced automatically");
            } else {
                System.out.println("Cart can't be balanced");
            }
        }
    }

    public static <T extends Food> List<String> makingInfoForCart(Class<T> clazz, UMarket market) {
        List<String> list = new LinkedList<>();
        list.add("List of available " + clazz.getSimpleName() + ": ");
        list.add("[0] Stop forming cart and balancing");
        list.addAll(market.getThingStrings(clazz));
        return list;
    }
}
