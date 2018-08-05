package com.example.demo.Resource;

import com.example.demo.Domain.User;
import com.example.demo.Domain.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserResource {
    @Autowired
    private UserValidator userValidator;

    @ResponseBody
    @RequestMapping(value="/users",method=RequestMethod.GET)
    public String User() {
        return "get a list of User.";
    }

    @ResponseBody
    @RequestMapping(value="/users/{id}",method=RequestMethod.GET)
    public String User(@PathVariable("id") String id) {
        return "get user by id: " + id;
    }

    @ResponseBody
    @RequestMapping(value="/users",method=RequestMethod.POST)
    public String User(User user, Errors errors) {
        if(userValidator.supports(User.class)) {
            userValidator.validate(user, errors);
        }
        if(errors.hasErrors()) {
            return errors.getAllErrors().toString();
        }
        return "create a user." + user;
    }
}
