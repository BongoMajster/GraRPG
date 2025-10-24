import java.util.Scanner;
public class ProstaGraRPG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== KREATOR POSTACI RPG ===");
        System.out.print("Podaj imie postaci: ");
        String imie = scanner.nextLine();
        System.out.println("Wybierz rase:");
        System.out.println("1. Elf");
        System.out.println("2. Ork");
        System.out.println("3. Czlowiek");
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
        System.out.println("\n=== TWOJA POSTAC ===");
        System.out.println("Imie: " + imie);
        System.out.println("Rasa: " + rasa);
        System.out.println("Plec: " + plec);
        System.out.println("Wzrost: " + wzrost + " cm");
        System.out.println("Waga: " + waga + " kg");
        System.out.println("Kolor wlosow: " + kolorWlosow);
        System.out.println("Kolor oczu: " + kolorOczu);
        System.out.println("====================");
    }
}