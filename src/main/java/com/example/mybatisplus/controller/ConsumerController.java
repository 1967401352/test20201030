package com.example.mybatisplus.controller;

import com.example.mybatisplus.entity.Consumer;
import com.example.mybatisplus.validation.IcreateValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    @Autowired
    Consumer consumer;

    @PostMapping("test")
    public String test(@Validated({IcreateValidation.class}) @RequestBody Consumer consumer) {
        System.out.println("sssssssss"+consumer.getId()+consumer.getAge());
        return "cheng111gong";
    }
    @GetMapping("test2")
    public void test2() {
        consumer.setAge(11);
        System.out.println("sssssssss"+consumer.getId());

    }
}
@RestController
class  test{
    @Autowired
    Consumer consumer2;
    @GetMapping("test3")
    public void test3() {
        consumer2.setAge(11222);
        System.out.println("sssssssss"+consumer2.getId());

    }
}

@ControllerAdvice
class  exe {
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public String validationExceptionHandler(Exception e) {
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException)e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException)e).getBindingResult();
        }

        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "!";
        }
        return errorMesssage;
    }
}