package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class Repetition {

    private final ChronoUnit myFrequency;
    private final Set<LocalDate> exceptions;
    private Termination termination;

    public Repetition(ChronoUnit myFrequency) {
        this.myFrequency = myFrequency;
        this.exceptions = new HashSet<>();
    }

    public ChronoUnit getFrequency() {
        return myFrequency;
    }

    public void addException(LocalDate date) {
        this.exceptions.add(date);
    }

    public void setTermination(Termination termination) {
        this.termination = termination;
    }

    public Termination getTermination() {
        return termination;
    }

    /**
     * Vérifie uniquement si la date est marquée comme exception.
     */
    public boolean isException(LocalDate date) {
        return exceptions.contains(date);
    }

    /**
     * Vérifie si une date d'occurrence spécifique est valide
     * (n'est pas une exception et respecte la terminaison).
     * Cette méthode sert à valider le DÉBUT d'une occurrence.
     */
    public boolean isValid(LocalDate date) {
        if (isException(date)) {
            return false;
        }
        if (termination != null) {
            if (date.isAfter(termination.terminationDateInclusive())) {
                return false;
            }
        }
        return true;
    }
}