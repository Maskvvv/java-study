package com.zhy.spring.spEL.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventor {
    private String name;
    private Date time;
    private String people;


    private List<Inventor> inventors;

    public Inventor(String name, Date time, String people) {
        this.name = name;
        this.time = time;
        this.people = people;
    }

    public Inventor(String name, String people) {
        this.name = name;
        this.people = people;
    }
}
