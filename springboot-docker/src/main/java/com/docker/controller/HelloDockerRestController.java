package com.docker.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(value = "Spring-REST Management System", description = "Showing welcome message to the users")
public class HelloDockerRestController {
	
    @ApiOperation(value = "Prints welcome message for the user.", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved welcome message"),
        @ApiResponse(code = 401, message = "You are not authorized to view the message"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")

    })
    @GetMapping("/hello/{name}")
    public String helloDocker(@PathVariable(value = "name") String name) {
        String response = "Hello " + name + " Response received on : " + new Date();
        System.out.println(response);
        return response;
 
    }
}
