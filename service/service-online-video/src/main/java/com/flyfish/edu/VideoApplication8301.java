package com.flyfish.edu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @autohr flyfish
 * @date: 2023/5/11 15:45
 * @description:
 */
@SpringBootApplication
@Slf4j
public class VideoApplication8301 {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication8301.class,args);
        log.info("8031服务已启动");
    }
}