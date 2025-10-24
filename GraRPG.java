import java.util.Scanner;


interface Attackable {
    void attack(Attackable target);
    String getName();
    int getHealth();
    void takeDamage(int damage);
}


class Character implements Attackable {
    protected String name;
    protected int level;
    protected int health;

    public Character(String name, int level, int health) {
        this.name = name;
        this.level = level;
        this.health = health;
    }

    @Override
    public void attack(Attackable target) {
        int damage = this.level * 2;
        System.out.println(this.name + " atakuje " + target.getName() + " i zadaje " + damage + " obrażeń.");
        target.takeDamage(damage);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
        System.out.println(this.name + " ma teraz " + this.health + " punktów życia.");
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}

public class GraRPG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== KREATOR POSTACI RPG ===");
        System.out.print("Podaj imie postaci: ");
        String imie = scanner.nextLine();
        System.out.println("Wybierz rase:");
        System.out.println("1. Elf");
        System.out.println("2. Ork");
        System.out.println("3. Człowiek");
        System.out.println("4. Wampir");
        System.out.println("5. Ghoul");
        System.out.print("Twój wybór: ");
        int rasaWybor = scanner.nextInt();
        scanner.nextLine();
        String rasa = switch (rasaWybor) {
            case 1 -> "Elf";
            case 2 -> "Ork";
            case 3 -> "Człowiek";
            case 4 -> "Wampir";
            case 5 -> "Ghoul";
            default -> "Nieznana rasa";
        };
        System.out.print("Podaj plec: ");
        String plec = scanner.nextLine();
        System.out.print("Podaj wzrost (cm): ");
        int wzrost = scanner.nextInt();
        System.out.print("Podaj wage (kg): ");
        int waga = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Podaj kolor wlosow: ");
        String kolorWlosow = scanner.nextLine();
        System.out.print("Podaj kolor oczu: ");
        String kolorOczu = scanner.nextLine();


        Character postac1 = new Character(imie, 5, 100);
        Character postac2 = new Character("Przeciwnik", 3, 80);

        System.out.println("\n=== TWOJA POSTAC ===");
        System.out.println("Imie: " + imie);
        System.out.println("Rasa: " + rasa);
        System.out.println("Plec: " + plec);
        System.out.println("Wzrost: " + wzrost + " cm");
        System.out.println("Waga: " + waga + " kg");
        System.out.println("Kolor wlosow: " + kolorWlosow);
        System.out.println("Kolor oczu: " + kolorOczu);
        System.out.println("====================");


        System.out.println("\nRozpoczynamy walkę!");
        while (postac1.isAlive() && postac2.isAlive()) {
            postac1.attack(postac2);
            if (!postac2.isAlive()) {
                System.out.println(postac2.getName() + " został pokonany!");
                break;
            }
            postac2.attack(postac1);
            if (!postac1.isAlive()) {
                System.out.println(postac1.name + " został pokonany!");
                break;
            }
        }

        System.out.println("Koniec walki!");
    }
}