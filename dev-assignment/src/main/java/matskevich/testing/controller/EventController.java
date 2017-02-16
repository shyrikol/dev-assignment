package matskevich.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import matskevich.testing.entity.*;
import matskevich.testing.comparator.*;
import matskevich.testing.repository.ConfirmedEventRepository;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@RestController()
public class EventController {

    private ConfirmedEventRepository eventRepository;

    private Map<String, List<ConfirmedEvent>> calendar = new TreeMap<>();

    private List<EventRequire> toAdd = new ArrayList<>();

    @Autowired
    public EventController(ConfirmedEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, List<ConfirmedEvent>> findAllMeetings() {
        return calendar;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addNewWebEvent(@RequestBody Wrapper wrapper) {
        try {
            Comparator<EventRequire> comp = new RequestComparator<EventRequire>();
            wrapper.getEvents().sort(comp);  // sorting all events by request submission time
            List<EventRequire> result = dateFiltering(wrapper.getEvents(), wrapper.getAccess().getOpeningDate(),
                    wrapper.getAccess().getClosingDate());  //deleting all unsuitable events
            outputGenerating(calendar, result);  // group chronologically by day
            for (Map.Entry<String, List<ConfirmedEvent>> entry : calendar.entrySet()) {
                eventRepository.save(entry.getValue());
            }
        }   catch (NullPointerException e) {
            System.out.println("no data");
            e.printStackTrace();
        }   catch (DateTimeParseException e) {
            System.out.println("wrong date format");
            e.printStackTrace();
        }   catch (RuntimeException e){
            System.out.println("some troubles");
            e.printStackTrace();
        }
    }

    private List<EventRequire> dateFiltering(List<EventRequire> r, LocalTime startTime, LocalTime stopTime){
        for (EventRequire o : r){
            boolean adding = true;
            if (outOfBorders(o.getDuration(), o.getStartTimeDate(), startTime, stopTime)){  // not in office time
                continue;
            }
            for (EventRequire overlap : toAdd){
                if (isOverlap(o.getDuration(), overlap.getDuration(),  // time is engaged
                        o.getStartTimeDate(), overlap.getStartTimeDate())){
                    adding = false;
                    break;
                }
            }
            if (adding){
                toAdd.add(o);
            }
        }
        return toAdd;
    }

    private boolean outOfBorders (Integer dur, LocalDateTime toCheck, LocalTime start, LocalTime end){
        return start.compareTo(LocalTime.from(toCheck)) == 1
                || end.compareTo(LocalTime.from(toCheck.plusHours(dur))) == -1;
    }

    private boolean isOverlap (Integer dur1, Integer dur2, LocalDateTime t1, LocalDateTime t2){
        return t1.isAfter(t2) && t1.isBefore(t2.plusHours(dur2))
                || t1.plusHours(dur1).isAfter(t2) && t1.plusHours(dur1).isBefore(t2.plusHours(dur2))
                || t1.isEqual(t2) || (t1.plusHours(dur1).isEqual(t2.plusHours(dur2)));
    }

    private void outputGenerating (Map <String, List<ConfirmedEvent>> calendar, List<EventRequire> r){
        calendar.clear();
        Comparator<ConfirmedEvent> comp = new ConfirmedComparator<ConfirmedEvent>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (EventRequire objToAdd : r){
            ConfirmedEvent eventToAdd = new ConfirmedEvent(objToAdd.getEmployeeId(),
                    objToAdd.getStartTimeDate().format(formatter),
                    objToAdd.getStartTimeDate().plusHours(objToAdd.getDuration()).format(formatter));
            if (calendar.containsKey(objToAdd.getStartTimeDate().format(dateFormatter))){  // adding to existing date
                calendar.get(objToAdd.getStartTimeDate().format(dateFormatter)).add(eventToAdd);
                calendar.get(objToAdd.getStartTimeDate().format(dateFormatter)).sort(comp);
            } else {  // create new record
                List<ConfirmedEvent> listToAdd = new ArrayList<>();
                listToAdd.add(eventToAdd);
                calendar.put(objToAdd.getStartTimeDate().format(dateFormatter), listToAdd);
            }
        }
    }
}
