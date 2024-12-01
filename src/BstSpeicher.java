/* import java.io.*;
import java.util.List;


    public class BstSpeicher {

        // Speichert Mitglieder in einer Datei
        public static void saveMembers(BST baum, String membersSpeicher) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(membersSpeicher))) {
                List<Member> members = baum.toList(); // Mitglieder aus dem Baum in Liste umwandeln
                for (Member member : members) {
                    writer.write(formatMember(member)); // Mitglied in eine Zeile umwandeln
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Fehler beim Speichern der Mitglieder: " + e.getMessage());
            }
        }

        // Lädt Mitglieder aus einer Datei
        public static void loadMembers(BST baum, String membersSpeicher) {
            try (BufferedReader reader = new BufferedReader(new FileReader(membersSpeicher))) {
                String Linie;
                while ((Linie = reader.readLine()) != null) {
                    Member member = parseMember(Linie); // Zeile in Member umwandeln
                    baum.insert(member); // Mitglied in den Baum einfügen
                }
            } catch (IOException e) {
                System.out.println("Fehler beim Laden der Mitglieder: " + e.getMessage());
            }
        }

        // Konvertiert ein Mitglied in eine CSV-Zeile
        private static String formatMember(Member member) {
            return member.getMemberID() + ";" +
                    member.getName() + ";" +
                    member.getJoinDate() + ";" +
                    member.getMembershipType().name();
        }

        // Konvertiert eine CSV-Zeile in ein Member-Objekt
        private static Member parseMember(String line) {
            String[] parts = line.split(";");
            if (parts.length != 4) {
                throw new IllegalArgumentException("Ungültige Daten in der Datei.");
            }
            String name = parts[1];
            String joinDate = parts[2];
            Member.MembershipType type = Member.MembershipType.valueOf(parts[3]);
            return new Member(name, joinDate, type);
        }
    }
*/