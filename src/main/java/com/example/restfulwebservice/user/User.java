package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


/*도메인클래스에서 노출하고 싶지 않은 엔티티를 송출하려며 어떻게 해야할까요?
* 데이터 제어를 위한 filtering
*
* @JsonIgnoreProperties(value = {"password"})
*
* @JsonFilter("UserInfo")
*
* */


@Data
@AllArgsConstructor
//@JsonFilter("UserInfo")
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2 , message = "Name은 2글자 이상 입력해 주세요.")
    private String name;
    @Past
    private Date joinDate;

    private String password;
    private String ssn;


    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(int id,String name,Date joinDate,String password,String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;

    }
}