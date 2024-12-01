import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Initialisierung von Binärbaum und Sortierer
        BST baum = new BST();

        // Beispielmitglieder hinzufügen (von ChatGPT generiert)
        baum.insert(new Member("Anna Müller", "01-01-2022", Member.MembershipType.STUDENT));
        baum.insert(new Member("Max Mustermann", "15-03-2021", Member.MembershipType.ERWACHSEN));
        baum.insert(new Member("Lisa Schmidt", "20-07-2023", Member.MembershipType.KIND));
        baum.insert(new Member("Familie Becker", "05-12-2009", Member.MembershipType.FAMILIE));
        baum.insert(new Member("Jonas Meyer", "10-11-2011", Member.MembershipType.FAMILIENANGEHOERIG));
        baum.insert(new Member("Julia Schuster", "30-04-2023", Member.MembershipType.STUDENT));
        baum.insert(new Member("Familie Wagner", "25-09-2021", Member.MembershipType.FAMILIE));
        baum.insert(new Member("Sophie Richter", "12-06-2022", Member.MembershipType.KIND));
        baum.insert(new Member("Familie Schmitt", "08-08-2019", Member.MembershipType.FAMILIE));
        baum.insert(new Member("Lukas Keller", "03-02-2023", Member.MembershipType.ERWACHSEN));
        baum.insert(new Member("Familie Huber", "18-10-2018", Member.MembershipType.FAMILIE));
        baum.insert(new Member("Nina Fischer", "22-05-2012", Member.MembershipType.STUDENT));
        baum.insert(new Member("Familie Lange", "14-11-2010", Member.MembershipType.FAMILIE));
        baum.insert(new Member("Tom Scholz", "07-09-2023", Member.MembershipType.KIND));
        baum.insert(new Member("Familie Wolf", "29-01-2000", Member.MembershipType.FAMILIE));
        baum.insert(new Member("Laura Berger", "02-07-1999", Member.MembershipType.STUDENT));
        baum.insert(new Member("Familie Graf", "11-04-2020", Member.MembershipType.FAMILIE));



        SelectionSort sortierer = new SelectionSort();

        // Scanner zur Benutzereingabe
        Scanner scanner = new Scanner(System.in);
        List<Member> members = new ArrayList<>();

        // Endlosschleife für das Menü
        while (true) {
            displayMenu(); // Menü anzeigen
            int option = getUserInput(scanner, "Geben Sie eine Zahl ein: ");

            switch (option) {
                case 1 -> addMember(baum, scanner);
                case 2 -> deleteMember(baum, scanner);
                case 3 -> editMember(baum, scanner);
                case 4 -> displayMembers(baum);
                case 5 -> sortMembers(baum, sortierer, members);
                case 6 -> searchByID(baum, scanner);
                case 7 -> searchByName(baum, scanner);
                case 8 -> {
                    System.out.println("Programm beendet.");
                    return; // Programm beenden
                }
                default -> System.out.println("Ungültige Eingabe. Bitte versuchen Sie es erneut.");
            }
        }
    }

    // Methode zum Anzeigen des Menüs in der Konsole
    private static void displayMenu() {
        System.out.println("\n-------- Willkommen in der Fechtclub Mitgliederverwaltung! --------");
        System.out.println("1. Mitglied hinzufügen");
        System.out.println("2. Mitglied löschen");
        System.out.println("3. Mitglied bearbeiten");
        System.out.println("4. Mitglieder anzeigen");
        System.out.println("5. Mitgliederdaten nach Namen sortieren");
        System.out.println("6. Mitglied suchen (ID)");
        System.out.println("7. Mitglied suchen (Name)");
        System.out.println("8. Programm beenden");
    }

    // Methode zum Abrufen von Benutzereingaben
    private static int getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Ungültige Eingabe. Bitte geben Sie eine Zahl ein: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Eingabepuffer leeren
        return input;
    }

    // Methode zum Hinzufügen eines Mitglieds
    private static void addMember(BST baum, Scanner scanner) {
        System.out.println("\n--- Neues Mitglied hinzufügen ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Beitrittsdatum (dd-MM-yyyy): ");

        String joinDate = scanner.nextLine();

        System.out.println("Mitgliedschaftstyp:");
        System.out.println("1. Student");
        System.out.println("2. Erwachsener");
        System.out.println("3. Kind");
        System.out.println("4. Familie");
        System.out.println("5. Familienangehörig");
        int typeOption = getUserInput(scanner, "Bitte wählen Sie einen Typ (1-5): ");

        try {
            Member.MembershipType type = switch (typeOption) {
                case 1 -> Member.MembershipType.STUDENT;
                case 2 -> Member.MembershipType.ERWACHSEN;
                case 3 -> Member.MembershipType.KIND;
                case 4 -> Member.MembershipType.FAMILIE;
                case 5 -> Member.MembershipType.FAMILIENANGEHOERIG;
                default -> throw new IllegalArgumentException("Ungültige Auswahl.");
            };

            Member newMember = new Member(name, joinDate, type);
            baum.insert(newMember);
            System.out.println("Mitglied erfolgreich hinzugefügt: " + newMember);
        } catch (IllegalArgumentException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    // Methode zum Löschen eines Mitglieds
    private static void deleteMember(BST baum, Scanner scanner) {

        System.out.println("\n--- Mitglied löschen ---");
        int memberID = getUserInput(scanner, "Mitglieds-ID eingeben: ");

        Member member = baum.search(memberID);
        if (member != null) {
            baum.delete(memberID);

            System.out.println("Mitglied erfolgreich gelöscht: " + member.getName());
        } else {
            System.out.println("Kein Mitglied mit dieser ID gefunden.");
        }
    }

    // Methode zum Bearbeiten eines Mitglieds
    private static void editMember(BST baum, Scanner scanner) {
        System.out.println("\n--- Mitglied bearbeiten ---");
        int memberID = getUserInput(scanner, "Mitglieds-ID eingeben: ");

        Member member = baum.search(memberID);
        if (member == null) {
            System.out.println("Kein Mitglied mit dieser ID gefunden.");
            return;
        }

        System.out.println("Gefundenes Mitglied: " + member);

        System.out.println("1. Name ändern");

        System.out.println("2. Mitgliedschaftstyp ändern");

        int option = getUserInput(scanner, "Wählen Sie eine Option: ");

        switch (option) {
            case 1 -> {
                System.out.print("Neuer Name: ");
                String newName = scanner.nextLine();
                member.setName(newName);
                System.out.println("Name erfolgreich geändert.");
            }
            case 2 -> {
                System.out.println("Neuer Mitgliedschaftstyp:");
                System.out.println("1. Student");
                System.out.println("2. Erwachsener");
                System.out.println("3. Kind");
                System.out.println("4. Familie");
                System.out.println("5. Familienangehörig");
                int typeOption = getUserInput(scanner, "Bitte wählen Sie einen Typ (1-5): ");

                Member.MembershipType newType = switch (typeOption) {
                    case 1 -> Member.MembershipType.STUDENT;
                    case 2 -> Member.MembershipType.ERWACHSEN;
                    case 3 -> Member.MembershipType.KIND;
                    case 4 -> Member.MembershipType.FAMILIE;
                    case 5 -> Member.MembershipType.FAMILIENANGEHOERIG;
                    default -> throw new IllegalArgumentException("Ungültige Auswahl.");
                };
                member.setMembershipType(newType);
                System.out.println("Mitgliedschaftstyp erfolgreich geändert.");
            }
            default -> System.out.println("Ungültige Auswahl.");
        }
    }

    // Methode zum Anzeigen der Mitglieder
    private static void displayMembers(BST baum) {
        System.out.println("\n--- Alle Mitglieder ---");
        baum.inorder();
    }

    // Methode zum Sortieren der Mitglieder
    private static void sortMembers(BST baum, SelectionSort sortierer, List<Member> members) {
        System.out.println("\n--- Mitgliederdaten nach Namen sortiert ---");
        members.clear();
        members.addAll(baum.toList());
        sortierer.sortByName(members);

        System.out.println("Sortierte Mitgliederliste:");
        for (Member member : members) {
            System.out.println(member + "\n" );
        }
    }

    // Methode zur Suche nach ID
    private static void searchByID(BST baum, Scanner scanner) {
        System.out.println("\n--- Mitglied suchen (ID) ---");
        int memberID = getUserInput(scanner, "Mitglieds-ID eingeben: ");

        Member member = baum.search(memberID);
        if (member != null) {
            System.out.println("Mitglied gefunden: " + member);
        } else {
            System.out.println("Kein Mitglied mit dieser ID gefunden.");
        }
    }

    // Methode zur Suche nach Name
    private static void searchByName(BST baum, Scanner scanner) {
        System.out.println("\n--- Mitglied suchen (Name) ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        List<Member> members = baum.toList();
        boolean found = false;
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                System.out.println("Mitglied gefunden: " + member + "\n");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Kein Mitglied mit diesem Namen gefunden.");
        }
    }
}