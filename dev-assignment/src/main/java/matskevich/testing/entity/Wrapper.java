package matskevich.testing.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Wrapper {

    private WorkTime access;

    private List<EventRequire> events;

    public WorkTime getAccess() {
        return access;
    }

    public void setAccess(WorkTime access) {
        this.access = access;
    }

    public List<EventRequire> getEvents() {
        return events;
    }

    public void setEvents(List<EventRequire> events) {
        this.events = events;
    }
}
