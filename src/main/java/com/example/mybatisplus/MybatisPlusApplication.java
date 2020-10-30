package com.example.mybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class MybatisPlusApplication  {


    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MybatisPlusApplication.class, args);
    }
    @ControllerAdvice
    class Exec{
        @ExceptionHandler(Exception.class)
        public  void test02(Exception e){
            e.printStackTrace();
            System.out.println("异常信息");
        }
    }


}
