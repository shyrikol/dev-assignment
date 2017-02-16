package matskevich.testing.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class EventRequire {

    private String requestSubmissionTime;

    private String employeeId;

    private String startTime;

    private int duration;

    public String getRequestSubmissionTime() {
        return requestSubmissionTime;
    }

    public LocalDateTime getRequestSubmissionTimeDate() {
        DateTimeFormatter formatSeconds = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(requestSubmissionTime, formatSeconds);
    }

    public void setRequestSubmissionTime(String requestSubmissionTime) {
        this.requestSubmissionTime = requestSubmissionTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public LocalDateTime getStartTimeDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(startTime, format);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
