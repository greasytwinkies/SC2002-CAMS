    import java.util.*;

    class CampNameComparator implements Comparator<Camp> {
        public int compare(Camp c1, Camp c2) {
            return c1.getCampInfo().getCampName().compareTo(c2.getCampInfo().getCampName());
        }
    }