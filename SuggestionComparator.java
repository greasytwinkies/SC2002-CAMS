import java.util.*;

public class SuggestionComparator implements Comparator<Suggestion> {
    public int compare(Suggestion s1, Suggestion s2) {
        return s1.getSuggestion().compareTo(s2.getSuggestion());
    }    
}
