package matskevich.testing.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class WorkTime {

    private String opening;

    private String closing;

    private DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");

    public String getOpening() {
        return opening;
    }

    public LocalTime getOpeningDate() {
        return LocalTime.parse(opening, format);
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getClosing() {
        return closing;
    }

    public LocalTime getClosingDate() {
        return LocalTime.parse(closing, format);
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }
}

