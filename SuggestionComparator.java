import java.util.*;

/**
 * Utility class to compare 2 suggestion objects
 */
public class SuggestionComparator implements Comparator<Suggestion> {

    /**
     * Method to compare to suggestion objects
     * @param s1 Suggestion 1
     * @param s2 Suggestion 2
     * @return 0 if their the same
     */
    public int compare(Suggestion s1, Suggestion s2) {
        return s1.getSuggestion().compareTo(s2.getSuggestion());
    }    
}
