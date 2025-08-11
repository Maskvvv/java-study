package com.zhy.web.serialization;

import com.zhy.web.serialization.dto.DateDto;
import com.zhy.web.serialization.dto.EnumsDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * spring boot 序列化
 */
@RestController
@RequestMapping("/serialization")
public class DateController {

    @PostMapping("/dateSeri")
    public DateDto index(@RequestBody DateDto dateDto) {
        return dateDto;
    }

    @PostMapping("/dateFormat")
    public DateDto format() {
        DateDto dateDto = new DateDto();
        dateDto.setDate(new Date());
        dateDto.setLocalDateTime(LocalDateTime.now());
        return dateDto;
    }

    @PostMapping("/enums")
    public EnumsDto enums(@RequestBody EnumsDto enumsDto) {
        return enumsDto;
    }


}
