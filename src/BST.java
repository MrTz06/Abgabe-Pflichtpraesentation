import java.util.ArrayList;
import java.util.List;

public class BST {

    // Wurzel des Baumes (erstes Element)
    private Node root;

    // Innere Klasse für die Knoten des Baumes
    private static class Node {  // Klassenname muss mit Großbuchstaben beginnen!
        Member member;       // Mitglied-Objekt
        Node left, right;  // Verweis auf die Kinder des Knotens

        // Konstruktor für den Knoten
        Node(Member member) {
            this.member = member;    // Zuweisung des Mitglieds
        }
    }

    // Methode zum Einfügen eines neuen Mitglieds
    public void insert(Member member) {
        root = insertRekursiv(root, member);   // Aufruf der rekursiven (selbstaufrufende) Methode
    }

    // Rekursive (selbstaufrufende) Methode zum Einfügen eines neuen Mitglieds
    private Node insertRekursiv(Node root, Member member) {
        if (root == null) {  // Wenn der Baum leer ist
            root = new Node(member);  // Wurzel wird erstellt
            return root; 
        }



        // Vergleicht die Mitglieds-ID, um die Position zu bestimmen
        if (member.getMemberID() < root.member.getMemberID()) {  // Links einfügen, wenn kleiner
            root.left = insertRekursiv(root.left, member);
        } else if (member.getMemberID() > root.member.getMemberID()) {  // Rechts einfügen, wenn größer
            root.right = insertRekursiv(root.right, member);
        }

        return root; 
    }


    // Methode zum Löschen eines Mitglieds
    public void delete(int memberID) {
        root = deleteRekursiv(root, memberID);
    }

    // Rekursive (selbstaufrufende) Methode zum Löschen eines Mitglieds
    private Node deleteRekursiv(Node root, int memberID) {
        if (root == null) {
            return root;
        }

        if (memberID < root.member.getMemberID()) {             // Wenn die Mitglieds-ID kleiner ist, gehe nach links
            root.left = deleteRekursiv(root.left, memberID);         // Lösche das linke Kind
        } else if (memberID > root.member.getMemberID()) {      // Wenn die Mitglieds-ID größer ist, gehe nach rechts
            root.right = deleteRekursiv(root.right, memberID);       // Lösche das rechte Kind
        } else {

            if (root.left == null) {                            // Wenn der Knoten nur ein rechtes Kind hat
                return root.right;                              // Gebe das rechte Kind zurück
            } else if (root.right == null) {                    // Wenn der Knoten nur ein linkes Kind hat
                return root.left;                               //Gib das linke Kind zurück
            }

            // Knoten mit zwei Kindern: Finde das kleinste Element im rechten Teilbaum
            root.member = findMin(root.right).member;          // Überschreibe den Knoten mit dem kleinsten Element
            root.right = deleteRekursiv(root.right, root.member.getMemberID()); // Lösche das kleinste Element
        }
        return root;
    }

    //Methode zum Finden des kleinsten Elements für das Löschen
    private Node findMin(Node root) {
        while (root.left != null) {                         // Solange Links ein Element vorhanden ist
            root = root.left;                               // Gehe nach links
        }
        return root;
    }

    //Methode zum Suchen eines Mitglieds
    public Member search(int memberID) {
        return searchRek(root, memberID);  // Aufruf der rekursiven Methode
    }

    //Rekursive Methode zum Suchen eines Mitglieds
    private Member searchRek(Node root, int memberID) {
        if (root == null || root.member.getMemberID() == memberID) {  // Wenn der Knoten leer ist oder die Mitglieds-ID gefunden wurde
            if (root == null) {
                return null; // Knoten ist nicht gefunden.
            }
            return root.member;  // Gibt das Mitglied zurück
        }

        if (memberID < root.member.getMemberID()) {  // Wenn die Mitglieds-ID kleiner ist
            return searchRek(root.left, memberID);   // Suche im linken Teilbaum
        }

        return searchRek(root.right, memberID);  // Suche im rechten Teilbaum
    }

    //Methode zum Ausgeben des Baumes in Inorder-Reihenfolge
    public void inorder() {
        inorderRek(root);  // Aufruf der rekursiven Methode
    }

    //Rekursive Methode zum Ausgeben des Baumes in Inorder-Reihenfolge
    private void inorderRek(Node root) {
        if (root != null) {  // Wenn der Knoten nicht leer ist
            inorderRek(root.left);  // Gehe nach links

            System.out.println(root.member.getName());  // Gebe den Namen des Mitglieds aus

            inorderRek(root.right);  // Gehe nach rechts
        }
    }
    //Methode zum Erstellen einer Liste aus dem Baum
    private void toList(Node root, List<Member> list) {
        if (root != null) {
            toList(root.left, list);

            list.add(root.member);

            toList(root.right, list);
        }
    }

    //Methode zum aufrufen der toList Methode
    public List<Member> toList() {
        List<Member> list = new ArrayList<>();
        toList(root, list);
        return list;
    }
}