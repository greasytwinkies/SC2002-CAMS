import java.util.*;
/**
Allows for comparison between two Camp's names.
*/
class CampNameComparator implements Comparator<Camp> {
    /**
    * Evaluates whether two camps's names are the same.
    * @param c1 The first camp (whose name is to be compared with the second camp).
    * @param c2 The second camp (whose name is to be compared with the first).
    * @return 0 if the two names are identical.
    */
    public int compare(Camp c1, Camp c2) {
        return c1.getCampInfo().getCampName().compareTo(c2.getCampInfo().getCampName());
    }
}