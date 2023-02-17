package com.zhy.other.arthas;

import com.alibaba.fastjson.JSON;
import com.zhy.other.arthas.model.ArthasBody;
import com.zhy.other.arthas.model.ArthasParam;
import com.zhy.other.arthas.model.AtrhasResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhongyin
 * @since 2023/2/17 11:59
 */
@Slf4j
@RestController
@RequestMapping("arthas_watch")
public class ArthasWatchController {

    @PostMapping("test")
    public AtrhasResponse test(ArthasParam param, @RequestBody ArthasBody body) {

        AtrhasResponse atrhasResponse = new AtrhasResponse();
        atrhasResponse.setCode(200);
        atrhasResponse.setMessage("success");

        log.info("param:{}", JSON.toJSONString(param));
        log.info("body:{}", JSON.toJSONString(body));

        return atrhasResponse;


    }

}
