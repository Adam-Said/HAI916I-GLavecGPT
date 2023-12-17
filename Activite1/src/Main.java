import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int CHUNK_SIZE = 1000; // Taille de chaque partie à traiter

    public static void main(String[] args) {
        // Exemple d'utilisation et tests
        TermTrie termTrie = new TermTrie();

        long startTime = System.currentTimeMillis(); // Mesurer le temps de début

        // Charger les termes composés depuis le fichier F
        try {
            File file = new File("data" + File.separator + "20231207-LEXICALNET-JEUXDEMOTS-ENTRIES-MWE.txt");

            if (!file.exists() || file.length() == 0) {
                System.out.println("File not found or empty");
                return;
            }

            System.out.println("Absolute Path: " + file.getAbsolutePath());

            try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
                processFile(reader, termTrie);
            }

            // System.out.println(termTrie.toString());
            System.out.println("Fin parsing");

        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // Mesurer le temps de fin
        long elapsedTime = endTime - startTime;
        System.out.println("Temps total du parsing : " + TimeUnit.MILLISECONDS.toSeconds(elapsedTime) + " secondes");

        System.out.println("Fin parsing.\nDébut de la recherche\n");

        // Test 1 : Vérifier si un terme est un mot composé et obtenir son identifiant
        long searchStartTime = System.currentTimeMillis(); // Mesurer le temps de début de la recherche
        int id1 = termTrie.isCompoundWord("marabout d'Afrique");
        System.out.println("ID 1 : " + id1); // Afficher l'identifiant du terme composé
        long searchEndTime = System.currentTimeMillis(); // Mesurer le temps de fin de la recherche
        long searchElapsedTime = searchEndTime - searchStartTime;
        System.out.println("Temps de recherche pour 'marabout d'Afrique' : "
                + TimeUnit.MILLISECONDS.toMillis(searchElapsedTime) + " millisecondes");

        // Test 2 : Rechercher des termes composés avec un préfixe donné
        long prefixSearchStartTime = System.currentTimeMillis(); // Mesurer le temps de début de la recherche par
                                                                 // préfixe
        List<Pair<String, Integer>> results = termTrie.searchByPrefix("transfusion");
        long prefixSearchEndTime = System.currentTimeMillis(); // Mesurer le temps de fin de la recherche par préfixe
        long prefixSearchElapsedTime = prefixSearchEndTime - prefixSearchStartTime;
        System.out.println("Temps de recherche par préfixe pour 'transfusion' : "
                + TimeUnit.MILLISECONDS.toMillis(prefixSearchElapsedTime) + " millisecondes");
        System.out.println("Nombre de préfixes pour 'transfusion' : " + results.size());
        System.out.println("Résultats : " + results); // Afficher la liste des termes composés avec le préfixe "sir"

        // Afficher l'utilisation de la mémoire
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        System.out.println("Mémoire totale : " + convertBytesToMB(totalMemory) + " Mb");
        System.out.println("Mémoire libre : " + convertBytesToMB(freeMemory) + " Mb");
        System.out.println("Mémoire utilisée : " + convertBytesToMB(usedMemory) + " Mb");
    }

    private static void processFile(BufferedReader reader, TermTrie termTrie) throws IOException {
        String line;
        int lineCount = 0;

        while ((line = reader.readLine()) != null) {
            processLine(line, termTrie);
            lineCount++;

            if (lineCount % CHUNK_SIZE == 0) {

                float state = (float) ((double) lineCount / 3275167) * 100;
                System.out.println("Processed: " + state + "%");
            }
        }
        // System.out.println(termTrie.toString());
        // System.out.println(termTrie.getRoot().getChildren().size());
    }

    private static void processLine(String line, TermTrie termTrie) {
        // Ignorer les lignes vides
        if (line.trim().isEmpty()) {
            return;
        }

        // Ignorer les lignes commençant par "//"
        if (line.startsWith("//")) {
            return;
        }

        // Supposer que chaque ligne contient un terme composé et un identifiant séparés
        // par des points-virgules
        String[] parts = line.split(";", 3);
        if (parts.length == 3) {
            // Supprimer les guillemets autour du terme
            String term = parts[1].replaceAll("\"", "");
            int id = Integer.parseInt(parts[0]);
            termTrie.insert(term, id);
        }
    }

    private static double convertBytesToMB(long bytes) {
        return bytes / (1024.0 * 1024);
    }
}