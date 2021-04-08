package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {

    @Autowired
    private MessageSource messageSource;
    // GET
    // /hello-world (endpoint)
    // @RequestMapping(method=RequestMethod.GET , path = "/hello-world") <<4.0이전 방식

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }


    //자바빈 형태로 반환
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    // 가변데이터 사용 Path Variable사용
    @GetMapping(path = "/hello-world-bean/Path-variable/{name}")
    public HelloWorldBean helloWorldBean2(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s" , name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(
            @RequestHeader(name="Accept-Language",required = false) Locale locale){
        return messageSource.getMessage("greeting.message",null, locale);
    }


}