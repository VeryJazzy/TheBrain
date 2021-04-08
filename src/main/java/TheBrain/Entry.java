package TheBrain;

public class Entry {

    public static class Builder {

        private String id;
        private String date;
        private String text;

        public Builder(String id) {
            this.id = id;
        }

        public Builder withDate(String date){
            this.date= date;
            return this;
        }

        public Builder withMessage(String text){
            this.text = text;
            return this;
        }

        public Entry build() {
            Entry entry = new Entry();
            entry.id = this.id;
            entry.date =this.date;
            entry.text = this.text;
            return entry;
        }
    }

    private String id;
    private String date;
    private String text;

    private Entry() { }

    public String getId() {
        return id;
    }

    public String getDate() { return date; }

    public String getText() { return text; }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry c = (Entry) obj;
        return this.id.equals(((Entry) obj).id);
    }

}
