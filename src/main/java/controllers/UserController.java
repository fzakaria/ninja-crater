package controllers;

import static ninja.Results.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.User;
import ninja.Result;
import ninja.exceptions.BadRequestException;
import ninja.exceptions.InternalServerErrorException;
import ninja.params.PathParam;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import requests.UserRegisterRequest;
import services.UserService;


@Singleton
public class UserController extends BaseApiController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Inject
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public Result register(@JSR303Validation UserRegisterRequest registerRequest, Validation validation) {
        if (validation.hasBeanViolations()){
            try {
                String json = objectMapper.writeValueAsString(validation.getBeanViolations());
                throw new BadRequestException(json);
            }catch(JsonProcessingException e) {
                throw new InternalServerErrorException("Exception occured while processing bean violations.");
            }
        }
        return ok().render(userService.create(registerRequest));
    }

    public Result index(@PathParam("username") String username) {
        User foundUser = userService.findByUsername(username);
        if (foundUser == null) {
            return notFound();
        }
        return ok().render(foundUser);
    }
    


}
