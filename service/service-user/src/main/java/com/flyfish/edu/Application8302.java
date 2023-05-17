package com.flyfish.edu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @autohr flyfish
 * @date: 2023/5/11 17:58
 * @description:
 */
@SpringBootApplication
@Slf4j
public class Application8302 {
    public static void main(String[] args) {
        SpringApplication.run(Application8302.class,args);
        log.info("8302端口服务已启动");
    }
}
