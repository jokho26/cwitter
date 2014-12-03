package cwitter

class MessageDateComparator implements Comparator<Message> {

    public int compare(Message m1, Message m2) {
        if (m1.getDate().before(m2.getDate())) {
            return -1;
        }
        else if (m1.getDate().after(m2.getDate())) {
            return 1;
        } else {
            return 0;
        }
    }
}