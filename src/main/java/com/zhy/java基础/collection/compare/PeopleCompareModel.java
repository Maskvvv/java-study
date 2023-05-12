package com.zhy.java基础.collection.compare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouhongyin
 * @since 2023/5/11 21:35
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeopleCompareModel implements Comparable<PeopleCompareModel> {

    private String id;

    private Integer age;

    @Override
    public int compareTo(PeopleCompareModel o) {
        return this.age - o.getAge();
    }
}
