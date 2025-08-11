package com.zhy.web.serialization.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DateDto {

    private LocalDateTime localDateTime;

    private Date date;

}
