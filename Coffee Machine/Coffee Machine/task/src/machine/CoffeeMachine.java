package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static boolean isWorking = true;
    public static void main(String[] args) {
        CoffeeMaker machine = new CoffeeMaker();
        Scanner scanner = new Scanner(System.in);
        while (isWorking) {
            String input = scanner.next();
            machine.processInput(input);
        }
    }
}

class CoffeeMaker {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private CurrentState state;
    int currentFill = 0;

    public CoffeeMaker() {
        state = CurrentState.MAIN_MENU;
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
        toMainMenu();
    }

    public static void main(String[] args) {

    }

    private void mainMenu(String action) {

        switch (action) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                state = CurrentState.COFFEE_MENU;
                break;
            case "fill":
                state = CurrentState.FILL;
                checkFillingProgress();
                break;
            case "take":
                take();
                break;
            case "remaining":
                remaining();
                break;
            case "exit":
                CoffeeMachine.isWorking = false;
                return;
            default:
                System.out.println("Try to input a correct action next time :/");
                break;
        }
    }

    public void processInput(String input) {

        switch (state) {
            case MAIN_MENU:
                mainMenu(input);
                break;
            case COFFEE_MENU:
                buy(input);
                break;
            case FILL:
                fill(input);
                break;
        }
    }

    private void toMainMenu() {
        state = CurrentState.MAIN_MENU;
        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
    }

    private void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void remaining() {
        System.out.println("The coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                beans + " of coffee beans\n" +
                cups + " of disposable cups\n" +
                money + " of money");
    }

    private void checkFillingProgress() {
        switch (currentFill) {
            case 0:
                System.out.println("Write how many ml of water do you want to add:");
                break;
            case 1:
                System.out.println("Write how many ml of milk do you want to add: ");
                break;
            case 2:
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case 3:
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;

        }
    }
    private void fill (String input) {
        switch (currentFill) {
            case 0:
                water = water + Integer.parseInt(input);
                currentFill++;
                checkFillingProgress();
                break;
            case 1:
                milk = milk + Integer.parseInt(input);
                currentFill++;
                checkFillingProgress();
                break;
            case 2:
                beans = beans + Integer.parseInt(input);
                currentFill++;
                checkFillingProgress();
                break;
            case 3:
                cups = cups + Integer.parseInt(input);
                currentFill = 0;
                state = CurrentState.MAIN_MENU;
                toMainMenu();
                break;
        }
    }

    private void buy(String choice) {
        switch (choice) {
            case "1":
                if (isEnough(250, 0, 16)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= 250;
                    beans -= 16;
                    cups--;
                    money += 4;
                }
                break;
            case "2":
                if (isEnough(350, 75, 20)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    cups--;
                    money += 7;
                }
                break;
            case "3":
                if (isEnough(200, 100, 12)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    cups--;
                    money += 6;
                }
                break;
            case "back":
                toMainMenu();
                return;
            default:
                System.out.println("Try to input a correct number next time :/");
                break;
        }
        toMainMenu();
    }

    private boolean isEnough(int water, int milk, int beans) {
        if (this.water < water) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (this.milk < milk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (this.beans < beans) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        if (this.cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        return true;
    }
}
enum CurrentState {
    MAIN_MENU,
    COFFEE_MENU,
    FILL;
}
