import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children;
    int id;  // L'identifiant associé au terme composé

    public TrieNode() {
        this.children = new HashMap<>();
        this.id = -1;  // Initialise l'identifiant à -1 par défaut
    }
}