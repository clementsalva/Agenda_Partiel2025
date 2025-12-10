package agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {

    private final List<Event> events = new ArrayList<>();

    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day to test
     * @return a list of events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e.isInDay(day)) {
                result.add(e);
            }
        }
        return result;
    }

    public List<Event> findByTitle(String title) {
        List<Event> foundEvents = new ArrayList<>();
        for (Event e : events) {
            if (e.getTitle().equals(title)) {
                foundEvents.add(e);
            }
        }
        return foundEvents;
    }

    public boolean isFreeFor(Event e) {
        LocalDateTime startE = e.getStart();
        LocalDateTime endE = startE.plus(e.getDuration());

        for (Event existing : events) {
            LocalDateTime startExisting = existing.getStart();
            LocalDateTime endExisting = startExisting.plus(existing.getDuration());

            if (startE.isBefore(endExisting) && startExisting.isBefore(endE)) {
                return false; // Il y a un conflit
            }
        }
        return true;
    }
}