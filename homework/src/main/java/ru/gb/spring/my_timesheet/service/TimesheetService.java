package ru.gb.spring.my_timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.spring.my_timesheet.model.Project;
import ru.gb.spring.my_timesheet.model.Timesheet;
import ru.gb.spring.my_timesheet.repository.ProjectRepository;
import ru.gb.spring.my_timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service // то же самое, что и Component, это больше метка для нас
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
    }

    public Optional<Timesheet> getById(Long id) {
        return timesheetRepository.getById(id);
    }

    public List<Timesheet> getAll() {
        return timesheetRepository.getAll();
    }

    public List<Timesheet> getCreatedAfter(LocalDate date){
        return timesheetRepository.getCreatedAfter(date);
    }

    public List<Timesheet> getCreatedBefore(LocalDate date){
        return timesheetRepository.getCreatedBefore(date);
    }

    public Timesheet create(Timesheet timesheet) {
        if (Objects.isNull(timesheet.getProjectId())) {
            throw new IllegalArgumentException("projectId must not be null");
        }

        if (projectRepository.getById(timesheet.getProjectId()).isEmpty()) {
            throw new NoSuchElementException("Project with id " + timesheet.getProjectId() + " does not exists");
        }
        timesheet.setCreatedAt(LocalDateTime.now().withNano(0));
        return timesheetRepository.create(timesheet);
    }

    public void delete(Long id) {
        timesheetRepository.delete(id);
    }

    public Optional<Project> getProjectById(Long id){
        return projectRepository.getById(id);
    }

}
