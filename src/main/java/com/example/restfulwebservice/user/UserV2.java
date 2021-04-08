package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;


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
@JsonFilter("UserInfoV2")
@NoArgsConstructor
public class UserV2 extends User{
    private String grade;
}