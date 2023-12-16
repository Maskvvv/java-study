package com.zhy.spring.spEL.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public List<Inventor> getInventors() {
        return inventors;
    }

    public void setInventors(List<Inventor> inventors) {
        this.inventors = inventors;
    }
}
