package ru.gb.spring.my_timesheet.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Описание структуры json-ответа на REST-запросы.
 * Т.е. запросы, ответ на которые - JSON.
 */
// эта аннотация ломбок позволяет не прописывать геттеры и сеттеры, они идут вместе с ней
@Data
public class Timesheet {
    private Long id;
    private Long projectId;
    private int minutes;
    private LocalDateTime createdAt;
}
