package com.example.swagger.controller;

import com.example.swagger.dto.UserReq;
import com.example.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags ={"API 정보를 제공하는 Controller"})        // title명을 말해주는 어노테이션  @Api
@RestController
@RequestMapping("/api")
public class ApiController {

    //http://localhost:8080/swagger-ui/  <- 이 url로 가면 swagger ui로 간다.
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //이런식으로 넣어줌으로서 변수에 @ApiParam들어가서 코드 가독성을 떨어트리는 걸 방지한다.
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x 값", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "y" , value = "y 값", required = true, paramType = "query", dataType = "int")
    })
    @GetMapping("/plus/{x}")
    public int plus(
                    //@ApiParam(value ="x값")     // param에 description을 달아준다.
                    @PathVariable int x,
                    //@ApiParam(value ="y값")     // 이러한 변수가 많이 붙는건 불편한 형태 -> 해결책 => 메소드 자체에 지정한다.
                    @RequestParam int y){
        return x + y;
    }

    // Object로 받는 경우.
    @ApiResponse(code = 502, message ="사용자의 나이가 10살 이하일때")    // 에러에 대해서 설명을 붙여줄 수 있는 ApiResponse 어노테이션
    @ApiOperation(value ="사용자의 이름과 나이를 echo 해주는 메서드")      // 메서드에대해서 설명을 붙여주는 어노테이션  @ApiOperation
    @GetMapping("/user")
    public UserRes userGet(UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }

    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }
}
