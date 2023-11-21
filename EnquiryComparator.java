import java.util.*;

class EnquiryComparator implements Comparator<Enquiry> {
        public int compare(Enquiry e1, Enquiry e2) {
            return e1.getEnquiry().compareTo(e2.getEnquiry());
        }
    }