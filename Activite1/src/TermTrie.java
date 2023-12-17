import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TermTrie {
    private TrieNode root;

    public TermTrie() {
        this.root = new TrieNode();
    }

    // Insère un terme composé dans le trie
    public void insert(String term, int id) {
        TrieNode node = root;

        for (char c : term.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.id = id;
    }

    // Vérifie si un terme est un mot composé et retourne son identifiant
    public int isCompoundWord(String term) {
        TrieNode node = root;

        for (char c : term.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return -1;  // Le terme n'est pas dans le trie
            }
            node = node.children.get(c);
        }

        return node.id;
    }

    // Recherche des termes composés ayant le préfixe donné
    public List<Pair<String, Integer>> searchByPrefix(String prefix) {
        List<Pair<String, Integer>> result = new ArrayList<>();
        TrieNode node = root;

        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return result;  // Aucun terme avec ce préfixe
            }
            node = node.children.get(c);
        }

        findAllWordsWithPrefix(node, prefix, result);
        return result;
    }

    // Fonction récursive pour trouver tous les termes avec un préfixe donné
    private void findAllWordsWithPrefix(TrieNode node, String currentPrefix, List<Pair<String, Integer>> result) {
        if (node.id != -1) {
            result.add(new Pair<>(currentPrefix, node.id));
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            char c = entry.getKey();
            TrieNode childNode = entry.getValue();

            findAllWordsWithPrefix(childNode, currentPrefix + c, result);
        }
    }

    // Méthode toString récursive pour afficher l'arbre trie
    private String toStringRecursive(TrieNode node, String currentPrefix) {
        StringBuilder result = new StringBuilder();

        if (node.id != -1) {
            result.append("(").append(currentPrefix).append(", ").append(node.id).append(")\n");
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            char c = entry.getKey();
            TrieNode childNode = entry.getValue();

            result.append(toStringRecursive(childNode, currentPrefix + c));
        }

        return result.toString();
    }

    // Méthode toString principale pour afficher l'ensemble de l'arbre trie
    @Override
    public String toString() {
        return toStringRecursive(root, "");
    }

    public void clear() {
        this.root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void setRoot(TrieNode root) {
        this.root = root;
    }
    
}
