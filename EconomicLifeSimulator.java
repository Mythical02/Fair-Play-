import java.util.Scanner;
import java.util.Random;

public class EconomicLifeSimulator {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    
    private static int age = 18;
    private static String economicClass;
    private static double money;
    private static boolean isAlive = true;
    
    public static void main(String[] args) {
        System.out.println("=== Economic Inequality Life Simulator ===");
        System.out.println("This game demonstrates how economic class affects life experiences.");
        
        chooseEconomicClass();
        initializeMoney();
        
        while (isAlive) {
            displayStatus();
            promptToAge();
            
            if (!isAlive) break;
            
            triggerRandomEvent();
            checkDeath();
        }
        
        System.out.println("\n=== Game Over ===");
        System.out.println("You lived until age " + age);
        System.out.printf("Final net worth: $%,.2f%n", money);
        scanner.close();
    }
    
    private static void chooseEconomicClass() {
        System.out.println("\nChoose your starting economic class:");
        System.out.println("1. Upper Class");
        System.out.println("2. Lower Class");
        System.out.print("Enter your choice (1-2): ");
        
        int choice = scanner.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.print("Invalid choice. Please enter 1 or 2: ");
            choice = scanner.nextInt();
        }
        
        economicClass = (choice == 1) ? "Upper Class" : "Lower Class";
        System.out.println("\nYou've chosen to start as " + economicClass + ".");
    }
    
    private static void initializeMoney() {
        if (economicClass.equals("Upper Class")) {
            money = 50000 + random.nextInt(200000); // $50k-$250k
        } else {
            money = 500 + random.nextInt(9500); // $500-$10k
        }
        System.out.printf("Starting money: $%,.2f%n", money);
    }
    
    private static void displayStatus() {
        System.out.println("\n=== Current Status ===");
        System.out.println("Age: " + age);
        System.out.printf("Money: $%,.2f%n", money);
        System.out.println("Class: " + economicClass);
    }
    
    private static void promptToAge() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Age another year");
        System.out.println("2. Exit game");
        System.out.print("Enter your choice (1-2): ");
        
        int choice = scanner.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.print("Invalid choice. Please enter 1 or 2: ");
            choice = scanner.nextInt();
        }
        
        if (choice == 1) {
            age++;
        } else {
            isAlive = false;
        }
    }
    
    private static void triggerRandomEvent() {
        System.out.println("\n=== Age " + age + " Event ===");
        
        if (economicClass.equals("Upper Class")) {
            upperClassEvent();
        } else {
            lowerClassEvent();
        }
    }
    
    // Upper class events (20 total)
    private static void upperClassEvent() {
        int event = random.nextInt(20);
        
        switch (event) {
            case 0:
                System.out.println("You inherited $50,000 from a distant relative!");
                money += 50000;
                break;
            case 1:
                System.out.println("Your stock portfolio increased in value!");
                money += 10000 + random.nextInt(40000);
                break;
            case 2:
                System.out.println("You were invited to an exclusive gala. You network with powerful people.");
                // Networking might help future events
                break;
            case 3:
                System.out.println("Your accountant suggests some 'creative' tax strategies.");
                System.out.println("1. Follow the advice (save $15k, small risk)");
                System.out.println("2. Play it safe (pay full taxes)");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    if (random.nextDouble() < 0.8) {
                        System.out.println("You saved $15,000 in taxes!");
                        money += 15000;
                    } else {
                        System.out.println("You got caught! $30,000 fine.");
                        money -= 30000;
                    }
                } else {
                    System.out.println("You paid your taxes like a responsible citizen.");
                    money -= 10000;
                }
                break;
            case 4:
                System.out.println("You bought a vacation home in the Bahamas!");
                money -= 200000;
                break;
            case 5:
                System.out.println("Your private school connections got your child into an Ivy League university!");
                break;
            case 6:
                System.out.println("You invested in a startup that went public! Huge payout.");
                money += 50000 + random.nextInt(200000);
                break;
            case 7:
                System.out.println("You joined an exclusive country club.");
                money -= 10000;
                break;
            case 8:
                System.out.println("Your family's trust fund distributed its annual payout.");
                money += 25000;
                break;
            case 9:
                System.out.println("You donated to a political campaign. Potential future favors?");
                money -= 5000;
                break;
            case 10:
                System.out.println("Your lawyer got you out of a speeding ticket with connections.");
                break;
            case 11:
                System.out.println("You got a seat on the board of a major corporation.");
                money += 50000;
                break;
            case 12:
                System.out.println("Your art collection appreciated in value!");
                money += 30000 + random.nextInt(70000);
                break;
            case 13:
                System.out.println("You hosted a fundraiser for your favorite charity.");
                money -= 10000;
                break;
            case 14:
                System.out.println("Your hedge fund had an excellent year.");
                money += 100000 + random.nextInt(300000);
                break;
            case 15:
                System.out.println("You purchased a luxury yacht!");
                money -= 500000;
                break;
            case 16:
                System.out.println("Your family's foundation awarded you a grant for your 'philanthropic work'.");
                money += 40000;
                break;
            case 17:
                System.out.println("You used your connections to get a no-show consulting gig.");
                money += 60000;
                break;
            case 18:
                System.out.println("Your vintage wine collection sold at auction for a premium.");
                money += 75000;
                break;
            case 19:
                System.out.println("You attended Davos and made valuable international connections.");
                break;
        }
    }
    
    // Lower class events (20 total)
    private static void lowerClassEvent() {
        int event = random.nextInt(20);
        
        switch (event) {
            case 0:
                System.out.println("Your car broke down. Repair costs $800.");
                System.out.println("1. Pay for repairs (lose $800)");
                System.out.println("2. Try to fix it yourself (50% chance)");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    money -= 800;
                    System.out.println("Your car is fixed but money is tight.");
                } else {
                    if (random.nextBoolean()) {
                        System.out.println("You fixed it yourself! Saved $800.");
                    } else {
                        System.out.println("You made it worse! Now it costs $1200.");
                        money -= 1200;
                    }
                }
                break;
            case 1:
                System.out.println("Your rent increased by $200/month.");
                money -= 2400;
                break;
            case 2:
                System.out.println("You got sick but can't afford to miss work.");
                // Health consequences might appear later
                break;
            case 3:
                System.out.println("Your hours at work got cut.");
                money -= 300 * (1 + random.nextInt(10));
                break;
            case 4:
                System.out.println("You had to take a payday loan to cover bills.");
                System.out.println("The high interest will cost you $500 next year.");
                money -= 500;
                break;
            case 5:
                System.out.println("Your child needs braces. Insurance won't cover it.");
                money -= 3000;
                break;
            case 6:
                System.out.println("You got a second job to make ends meet.");
                money += 5000 + random.nextInt(5000);
                break;
            case 7:
                System.out.println("Your public transit pass increased in price.");
                money -= 200;
                break;
            case 8:
                System.out.println("Your apartment has mold but the landlord won't fix it.");
                // Potential health issue
                break;
            case 9:
                System.out.println("You had to choose between buying medicine or paying utilities.");
                money -= 150;
                break;
            case 10:
                System.out.println("Your old refrigerator died. You can't afford a new one.");
                money -= 300; // Used one
                break;
            case 11:
                System.out.println("You got a small raise at work!");
                money += 1000 + random.nextInt(2000);
                break;
            case 12:
                System.out.println("Your healthcare deductible reset. Medical costs increase.");
                money -= 1000 + random.nextInt(2000);
                break;
            case 13:
                System.out.println("You had to move to a cheaper neighborhood with higher crime.");
                money -= 1000; // Moving costs
                break;
            case 14:
                System.out.println("Your child's school is underfunded. You chip in for supplies.");
                money -= 200;
                break;
            case 15:
                System.out.println("Your job eliminated health benefits.");
                // Future medical costs will be higher
                break;
            case 16:
                System.out.println("You got jury duty and lost a week's wages.");
                money -= 800;
                break;
            case 17:
                System.out.println("Your food stamp benefits were reduced.");
                money -= 1200;
                break;
            case 18:
                System.out.println("Your old laptop died. Essential for job searching.");
                money -= 400; // Used replacement
                break;
            case 19:
                System.out.println("You had to take unpaid time off to care for a sick family member.");
                money -= 1500;
                break;
        }
    }
    
    private static void checkDeath() {
        // Death chance increases with age and is affected by economic class
        double deathChance = (age - 18) * 0.002; // Base chance
        if (economicClass.equals("Lower Class")) {
            deathChance *= 1.5; // Higher mortality for lower class
        } else {
            deathChance *= 0.7; // Lower mortality for upper class
        }
        
        // Extreme poverty or wealth effects
        if (economicClass.equals("Lower Class") && money < 0) {
            deathChance += 0.05; // Increased risk when in debt
        }
        
        if (random.nextDouble() < deathChance) {
            System.out.println("\nYou have passed away.");
            isAlive = false;
        }
    }
}