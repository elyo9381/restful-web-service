package com.example.restfulwebservice.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private static UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllusers(){
        return service.findAll();
    }

    // GET /users/1 or /users/10 -> String
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        //HETAOS >> 하나의 restful의 리소스에서 연관된 링크 정보를 함께 제공
        // 개발자 입장에선 업무량증가이지만 사용자는 편의성을 제공받을수있다.
        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllusers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if( user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user){
        User updateUser = service.update(id,user);

        if(user == null){
            throw  new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updateUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}