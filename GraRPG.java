import java.util.*;

public class graRPG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Podaj imie swojej postaci: ");
        String imie = scanner.nextLine();

        System.out.print("Podaj plec (Mezczyzna / Kobieta): ");
        String plec = scanner.nextLine();

        System.out.println("Wybierz rase:");
        System.out.println("1. Czlowiek");
        System.out.println("2. Elf");
        System.out.println("3. Krasnolud");
        System.out.println("4. Ork");
        System.out.print("Wpisz numer rasy: ");
        int wyborRasy = scanner.nextInt();
        scanner.nextLine();

        String rasa = switch (wyborRasy) {
            case 1 -> "Czlowiek";
            case 2 -> "Elf";
            case 3 -> "Krasnolud";
            case 4 -> "Ork";
            default -> "";
        };

        System.out.println("Wybierz klase postaci:");
        System.out.println("1. Wojownik");
        System.out.println("2. Mag");
        System.out.println("3. Lowca");
        System.out.println("4. Zlodziej");
        System.out.println("5. Paladyn");
        System.out.print("Wpisz numer klasy: ");
        int wyborKlasy = scanner.nextInt();
        scanner.nextLine();

        String klasa = switch (wyborKlasy) {
            case 1 -> "Wojownik";
            case 2 -> "Mag";
            case 3 -> "Lowca";
            case 4 -> "Zlodziej";
            case 5 -> "Paladyn";
            default -> "";
        };

        //statystyki gracza
        int atak = 0;
        int obrona = 0;
        int charyzma = 0;
        int punkty = 10;

        System.out.println("\nMasz 10 punktow do rozdania pomiedzy: Atak, Obrona, Charyzma");

        while (punkty > 0) {
            System.out.println("\nPozostalo punktow: " + punkty);
            System.out.print("Ile chcesz dodac do Ataku: ");
            int dodajAtak = scanner.nextInt();
            if (dodajAtak > punkty) dodajAtak = punkty;
            atak += dodajAtak;
            punkty -= dodajAtak;
            if (punkty <= 0) break;

            System.out.println("Pozostalo punktow: " + punkty);
            System.out.print("Ile chcesz dodac do Obrony: ");
            int dodajObrona = scanner.nextInt();
            if (dodajObrona > punkty) dodajObrona = punkty;
            obrona += dodajObrona;
            punkty -= dodajObrona;
            if (punkty <= 0) break;

            System.out.println("Pozostalo punktow: " + punkty);
            System.out.print("Ile chcesz dodac do Charyzmy: ");
            int dodajCharyzma = scanner.nextInt();
            if (dodajCharyzma > punkty) dodajCharyzma = punkty;
            charyzma += dodajCharyzma;
            punkty -= dodajCharyzma;
        }

        int zycieGracza = 30 + obrona * 2;

        //bonusy klasowe
        switch (klasa) {
            case "Wojownik" -> {
                atak += 2;
                zycieGracza += 10;
            }
            case "Paladyn" -> {
                charyzma += 2;
                zycieGracza += 5;
            }
            case "Mag" -> {
                charyzma += 4;
                zycieGracza -= 3;
            }
            case "Lowca" -> {
                atak += 3;
            }
            case "Zlodziej" -> {
                charyzma += 1;
                atak += 2;
            }
        }

        System.out.println("\n<=-=> TWOJA POSTAC <=-=>");
        System.out.println("Imie: " + imie);
        System.out.println("Plec: " + plec);
        System.out.println("Rasa: " + rasa);
        System.out.println("Klasa: " + klasa);
        System.out.println("Atak: " + atak);
        System.out.println("Obrona: " + obrona);
        System.out.println("Charyzma: " + charyzma);
        System.out.println("Zycie: " + zycieGracza);

        //potwory
        String[] nazwyPotworow = {"Zebrak spod biedry", "Kradzieje bizuteri Louvre", "Gadajacy Ben", "George Druid", "Pani od anglika", "Stefan Hawking"};
        int[] zyciePotwora = {30, 36, 24, 40, 28, 22};
        int[] atakPotwora = {5, 7, 4, 8, 6, 3};
        int[] charyzmaPotwora = {2, 7, 6, 5, -1, 12};
        String[][] typyPotworow = {
                {"waleczny", "chaotyczny"}, {"szybki", "chaotyczny"}, {"chaotyczny", "stwor"}, {"technologiczny", "szybki"}, {"stwor", "waleczny"}, {"stwor", "technologiczny"}
        };
        String[] nagroda = {"Hotdog z Zabki", "Zloty Lancuch", "Monsterek", "Arbuz", "Dostep do Librusa", "Wozek Inwalidzki"};

        int pokonane = 0;
        List<String> ekwipunek = new ArrayList<>();

        boolean kontynuuj = true;

        while (kontynuuj && zycieGracza > 0) {
            int index = random.nextInt(nazwyPotworow.length);
            String potwor = nazwyPotworow[index];
            int hpPotwor = zyciePotwora[index];
            int atkPotwor = atakPotwora[index];
            int chaPotwor = charyzmaPotwora[index];
            String[] typ = typyPotworow[index];
            String item = nagroda[index];

            //bonusy dla klasy
            int bonusAtak = atak;
            int bonusCharyzma = charyzma;
            for (String t : typ) {
                if (klasa.equals("Wojownik") && t.equals("waleczny")) bonusAtak += 2;
                if (klasa.equals("Mag") && t.equals("technologiczny")) bonusCharyzma += 2;
                if (klasa.equals("Zlodziej") && t.equals("szybki")) bonusCharyzma += 2;
                if (klasa.equals("Lowca") && t.equals("stwor")) bonusAtak += 2;
                if (klasa.equals("Paladyn") && t.equals("chaotyczny")) bonusAtak += 2;
            }

            boolean walkaTrwa = true;
            boolean itemDostepny = false;

            System.out.println("\n<=-=> WALKA <=-=>");
            System.out.print("Pojawia sie potwor: " + potwor + " [");
            System.out.println(String.join(", ", typ) + "]");
            System.out.println("HP: " + hpPotwor + ", Atak: " + atkPotwor + ", Charyzma: " + chaPotwor);

            while (walkaTrwa && zycieGracza > 0) {
                System.out.println("\nWybierz akcje:");
                System.out.println("1. Atak");
                System.out.println("2. Act (sprobuj pogadac)");
                System.out.println("3. Ucieczka");

                if (!ekwipunek.isEmpty()) {
                    int idx = 4;
                    for (String eq : ekwipunek) {
                        System.out.println(idx + ". Uzyj " + eq);
                        idx++;
                    }
                }

                System.out.print("TwÃ³j wybor: ");
                int akcja = scanner.nextInt();

                if (akcja == 1) {
                    int obrazenia = bonusAtak + random.nextInt(4);
                    hpPotwor -= obrazenia;
                    System.out.println("Zadales " + obrazenia + " obrazen!");
                    if (hpPotwor <= 0) {
                        System.out.println("Pokonales potwora " + potwor + "!");
                        pokonane++;
                        itemDostepny = true;
                        walkaTrwa = false;
                        continue;
                    }

                } else if (akcja == 2) {
                    int szansa = bonusCharyzma + random.nextInt(10);
                    if (szansa > chaPotwor / 2) {
                        System.out.println("Twoja charyzma dziala! Potwor odpuszcza walke!");
                        pokonane++;
                        if (random.nextInt(100) < 50) {
                            System.out.println("Z wdziecznosci potwor daje ci " + item + "!");
                            ekwipunek.add(item);
                        }
                        walkaTrwa = false;
                        continue;
                    } else {
                        System.out.println("Potwor ci nie slucha i atakuje!");
                    }

                } else if (akcja == 3) {
                    if (random.nextInt(100) < 50) {
                        System.out.println("Udalo ci sie uciec!");
                        walkaTrwa = false;
                        continue;
                    } else {
                        System.out.println("Nie udalo sie uciec!");
                    }

                } else if (akcja >= 4 && akcja < 4 + ekwipunek.size()) {
                    String uzyty = ekwipunek.get(akcja - 4);
                    switch (uzyty) {
                        case "Hotdog z Zabki" -> zycieGracza += 20;
                        case "Monsterek" -> zycieGracza += 10;
                        case "Arbuz" -> zycieGracza += 15;
                        case "Zloty Lancuch" -> bonusCharyzma += 3;
                        case "Dostep do Librusa" -> bonusCharyzma += 5;
                        case "Wozek Inwalidzki" -> zycieGracza += 25;
                    }
                    System.out.println("Uzyles " + uzyty + "! Twoje HP: " + zycieGracza + ", Charyzma: " + bonusCharyzma);
                    ekwipunek.remove(uzyty);

                } else {
                    System.out.println("Nieznana akcja!");
                }

                //tura potwora
                int obrazeniaPotwora = atkPotwor - (obrona / 2);
                if (obrazeniaPotwora < 0) obrazeniaPotwora = 0;
                zycieGracza -= obrazeniaPotwora;
                System.out.println("Potwor atakuje i zadaje " + obrazeniaPotwora + " obrazen!");
                System.out.println("Twoje HP: " + zycieGracza);
                System.out.println("HP potwora: " + hpPotwor);

                if (zycieGracza <= 0) {
                    System.out.println("Zostales pokonany...");
                    walkaTrwa = false;
                }
            }

            if (itemDostepny && !ekwipunek.contains(item)) {
                System.out.println("Zdobywasz: " + item);
                ekwipunek.add(item);
            }

            if (zycieGracza <= 0) break;

            System.out.print("\nChcesz kontynuowac do nastepnej walki? (tak/nie): ");
            String decyzja = scanner.next();
            if (!decyzja.equalsIgnoreCase("tak")) kontynuuj = false;
        }

        System.out.println("\n<===> KONIEC GRY <===>");
        System.out.println("Pokonales " + pokonane + " potworow!");
        if (!ekwipunek.isEmpty()) System.out.println("Masz w ekwipunku: " + String.join(", ", ekwipunek));
        System.out.println("Stworzone przez Joachima & Wiktora");
        scanner.close();
    }
}