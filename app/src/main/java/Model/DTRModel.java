package Model;

public class DTRModel {
    String datedtr, timein, timeout;

    public DTRModel(String date, String timeIN, String timeOUT) {
        this.datedtr = date;
        this.timein = timeIN;
        this.timeout = timeOUT;
    }

    public String getDate() {
        return datedtr;
    }

    public void setDate(String date) {
        this.datedtr = date;
    }

    public String getTimein() {
        return timein;
    }

    public void setTimein(String timein) {
        this.timein = timein;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
}
