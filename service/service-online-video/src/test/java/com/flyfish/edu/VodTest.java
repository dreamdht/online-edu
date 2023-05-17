package com.flyfish.edu;

import com.flyfish.edu.service.VodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @autohr flyfish
 * @date: 2023/5/17 19:11
 * @description:
 */
@SpringBootTest
public class VodTest {
    @Autowired
    private VodService vodService;

    @Test
    public void demo01(){
        vodService.upload();
    }
}
