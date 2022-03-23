package com.zhy.java基础.继承;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  
 * @author zhouhongyin
 * @since 2021/8/25 11:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NcdHealthControllerBaseParam {

    protected String doLx;

    protected String eventId;

    protected String idCard;

    protected String orgCode;

    protected String mainIndex;

    protected String eventType;

    protected String dataSource;

}
