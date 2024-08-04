package ru.gb.spring.my_timesheet.page;

import lombok.Data;

@Data
public class TimesheetPageDto {

    private String id;
    private String projectName;
    private String projectId;
    private String minutes;
    private String createdAt;

}
