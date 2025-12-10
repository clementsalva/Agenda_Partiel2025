package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class Termination {

    private final LocalDate start;
    private final ChronoUnit frequency;
    private final LocalDate terminationDate;
    private final long numberOfOccurrences;

    public LocalDate terminationDateInclusive() {
        return terminationDate;
    }

    public long numberOfOccurrences() {
        return numberOfOccurrences;
    }

    /**
     * Constructs a termination at a given date
     */
    public Termination(LocalDate start, ChronoUnit frequency, LocalDate terminationInclusive) {
        this.start = start;
        this.frequency = frequency;
        this.terminationDate = terminationInclusive;
        // La différence entre deux dates ne compte pas la borne supérieure, donc on
        // ajoute 1
        this.numberOfOccurrences = frequency.between(start, terminationInclusive) + 1;
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     */
    public Termination(LocalDate start, ChronoUnit frequency, long numberOfOccurrences) {
        this.start = start;
        this.frequency = frequency;
        this.numberOfOccurrences = numberOfOccurrences;
        this.terminationDate = start.plus(numberOfOccurrences - 1, frequency);
    }
}