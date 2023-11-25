

import java.util.*;
/**
    Allows for comparison between two Enquiry objects.
*/
class EnquiryComparator implements Comparator<Enquiry> {
        /**
        * Evaluates whether two given enquiries are the same.
        * @param e1 The first enquiry (to be compared with the second enquiry).
        * @param e2 The second enquiry (to be compared with the first).
        */
        public int compare(Enquiry e1, Enquiry e2) {
            return e1.getEnquiry().compareTo(e2.getEnquiry());
        }
    }