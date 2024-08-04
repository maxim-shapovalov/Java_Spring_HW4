package ru.gb.spring.my_timesheet.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.my_timesheet.model.Timesheet;
import ru.gb.spring.my_timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

//    @GetMapping
//    public Timesheet get(){
//        Timesheet timesheet = new Timesheet();
//        timesheet.setId(1L);
//        timesheet.setProject("Spring");
//        timesheet.setMinutes(200);
//        timesheet.setCreatedAt(LocalDate.now());
//
//        return timesheet;
//    }

//    @GetMapping
//    public List<Timesheet> get(){
//        Timesheet timesheet1 = new Timesheet();
//        timesheet1.setId(1L);
//        timesheet1.setProject("Spring");
//        timesheet1.setMinutes(200);
//        timesheet1.setCreatedAt(LocalDate.now());
//
//        Timesheet timesheet2 = new Timesheet();
//        timesheet2.setId(2L);
//        timesheet2.setProject("Spring2");
//        timesheet2.setMinutes(500);
//        timesheet2.setCreatedAt(LocalDate.now());
//
//        return List.of(timesheet1, timesheet2);
//    }

    // GET - получить - не содержит тела
    // POST - create
    // PUT - изменение
    // PATCH - изменение
    // DELETE - удаление

    // @GetMapping("/timesheets") // получить всё из указанного ресурса
    // @GetMapping("/timesheets/{id}") // получить конкретную запись по идентификатору
    // @DeleteMapping("/timesheets/{id}") // удалить конкретную запись по идентификатору
    // @PutMapping("/timesheets/{id}") // обновить конкретную запись по идентификатору

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    // /timesheets/{id}
    @GetMapping("/{id}") // получить все
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> ts = service.getById(id);
        if(ts.isPresent()){
//      return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping() // получить все
    public ResponseEntity<List<Timesheet>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/after")
    public ResponseEntity<List<Timesheet>> getCreatedAfter(@RequestParam LocalDate createdAtAfter){
        return ResponseEntity.status(HttpStatus.OK).body(service.getCreatedAfter(createdAtAfter));
    }

    @GetMapping("/before")
    public ResponseEntity<List<Timesheet>> getCreatedBefore(@RequestParam LocalDate createdAtBefore){
        return ResponseEntity.status(HttpStatus.OK).body(service.getCreatedBefore(createdAtBefore));
    }

    @PostMapping // создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        if(service.getProjectById(timesheet.getProjectId()).isPresent()){
            timesheet = service.create(timesheet);
            // 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(timesheet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}
