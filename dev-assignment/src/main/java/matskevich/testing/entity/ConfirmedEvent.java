package matskevich.testing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "app_event")
@JsonIgnoreProperties({"id"})
public class ConfirmedEvent {

    @Id
    @GeneratedValue
    private long id;

    private String beginning;

    private String ending;

    private String employeeId;

    public ConfirmedEvent() {
    }

    public ConfirmedEvent(String employeeId, String beginning, String ending) {
        this.employeeId = employeeId;
        this.beginning = beginning;
        this.ending = ending;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
