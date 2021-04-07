package com.example.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
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


}