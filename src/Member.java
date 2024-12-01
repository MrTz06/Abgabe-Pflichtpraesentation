import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Member {
    private static int IDcounter = 1;       // Zähler für die Mitglieds-ID
    private final int MemberID;             // ID des Mitglieds
    private String name;                    // Name des Mitglieds
    private final LocalDate joinDate;       // Eintrittsdatum des Mitglieds
    private MembershipType membershipType;  // Mitgliedschaftstyp

    // Enum (Aufzaehlung) der Mitgliedschaftstypen
    public enum MembershipType {
        STUDENT(10), ERWACHSEN(37), KIND(22),
        FAMILIE(84), FAMILIENANGEHOERIG(0);         // Mitgliedschaftstypen mit zugehörigen Beiträgen


        private final int fee;                              // Beitrag für den Mitgliedschaftstyp


        MembershipType(int fee) {                           // Konstruktor für den Enum
            this.fee = fee;                                 // Zuweisung des Beitrags
        }

        // Getter Methode für den Mitgliederbeitrag
        public int getFee() {
            return fee;
        }
    }

    // Konstruktor für die Member Klasse
    public Member(String name, String joinDate, MembershipType membershipType) {

        if (name == null || name.trim().isEmpty()) {                               // Validierung, ob Name leer ist
            throw new IllegalArgumentException("Bitte gib einen Namen ein.");
        }

        this.MemberID = IDcounter++;                                                // Zuweisung der Mitglieds-ID
        this.name = name;

        try {
            DateTimeFormatter DatumsFormatierer = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // erlaubtes Datumsformat
            this.joinDate = LocalDate.parse(joinDate, DatumsFormatierer);                    // joinDate zu LocalDate parsen

        } catch (DateTimeParseException unerlaubtesDatum) {                                    // Fehlerbehandlung für ungültiges Datum
            throw new IllegalArgumentException("Ungültiges Datum. Bitte im Format dd-MM-yyyy (Tag-Monat-Jahr) eingeben.");
        }

        this.membershipType = membershipType; // Zuweisung des Mitgliedschaftstyps
    }

    // Getter und Setter Methoden
    public int getMemberID() {
        return MemberID;
    }

    public String getName() { // Getter Methode für den Namen
        return name;
    }

    public String getJoinDate() { // Getter Methode für das Eintrittsdatum als String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return joinDate.format(formatter);
    }

    public void setName(String name) { // Setter Methode für den Namen
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer sein");
        }
        this.name = name;
    }

    public MembershipType getMembershipType() { // Getter Methode für den Mitgliedschaftstyp
        return membershipType;
    }

    public int getMembershipFee() { // Getter Methode für den Mitgliedsbeitrag
        return membershipType.getFee();
    }

    public void setMembershipType(MembershipType membershipType) { // Setter Methode für den Mitgliedschaftstyp
        if (membershipType == null) {
            throw new IllegalArgumentException("Mitgliedschaftstyp darf nicht null sein");
        }
        this.membershipType = membershipType;
    }

    @Override
    public String toString() {
        return "Mitglieds-ID: " + MemberID +
                "\nName: " + name +
                "\nBeitrittsdatum: " + getJoinDate() +
                "\nMitgliedschaftstyp: " + membershipType +
                "\nMitgliedsbeitrag: " + getMembershipFee();
    }
}