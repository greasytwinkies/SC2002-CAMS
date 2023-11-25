import java.util.*;
/**
    Allows for comparison between two User's names.
*/
class NameComparator implements Comparator<User> {
        /**
        * Evaluates whether two user's names are the same.
        * @param u1 The first user (whose name is to be compared with the second user).
        * @param u2 The second user (whose name is to be compared with the first).
        */
        public int compare(User u1, User u2) {
            return u1.getName().compareTo(u2.getName());
        }
    }