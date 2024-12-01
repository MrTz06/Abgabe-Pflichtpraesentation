import java.util.List;

public class SelectionSort {

    //Methode zum Sortieren der Mitglieder nach Namen
    public void sortByName(List<Member> members) {
        int n = members.size();                                                                  // Größe der Liste

        for (int i = 0; i < n - 1; i++) {                                                      // Schleife für jedes Element (Schleife bestimmt Position in der Liste)
            int minimumIndex = i;                                                                  // Index des kleinsten Elements (Index steht für Position in der Liste)
            for (int j = i + 1; j < n; j++) {                                                  // Schleife für jedes kommende Element (Schleife vergleicht erneut Elemente)
                if (members.get(j).getName().compareTo(members.get(minimumIndex).getName()) < 0) { // Vergleich der Namen
                    minimumIndex = j;                                                               // aktualisiert Index des kleinsten Elements
                }
            }
            Member temporaer = members.get(minimumIndex);     // Tausch der Elemente in der Liste members (Erstellung eines temporaeren Speicher)
            members.set(minimumIndex, members.get(i));  // Tausch der Elemente (Element an position minimumIndex wird durch Element an Position i ersetzt)
            members.set(i, temporaer);                   // Tausch der Elemente (Element an Position i wird durch temporäres Memberobjekt ersetzt)

        }
    }


}
