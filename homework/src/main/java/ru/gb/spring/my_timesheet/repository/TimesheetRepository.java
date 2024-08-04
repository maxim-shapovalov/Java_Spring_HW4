package ru.gb.spring.my_timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.spring.my_timesheet.model.Timesheet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository // @Component для классов, работающих с данными, есть некоторые отличия от Component
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    public Optional<Timesheet> getById(Long id) {
        // select * from timesheets where id = $id
        return timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    public List<Timesheet> getAll() {
        return List.copyOf(timesheets); // использовать вместо List.of(), чтобы создавалась копия и не было прямого доступа к исходным данным
    }

    public List<Timesheet> getCreatedAfter(LocalDate date){
        return timesheets.stream().filter(elem -> (elem.getCreatedAt().compareTo(date.atStartOfDay()) > 0 )).toList();
    }

    public List<Timesheet> getCreatedBefore(LocalDate date){
        return timesheets.stream().filter(elem -> (elem.getCreatedAt().compareTo(date.atStartOfDay()) < 0 )).toList();
    }

    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
//        timesheet.setCreatedAt(LocalDateTime.now().withNano(0));
        timesheets.add(timesheet);
        return timesheet;
    }

    public void delete(Long id) {
        timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(timesheets::remove); // если нет - иногда посылают 404 Not Found
    }
}
