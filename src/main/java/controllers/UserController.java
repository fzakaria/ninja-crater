package controllers;

import static ninja.Results.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.User;
import ninja.Context;
import ninja.Result;
import ninja.exceptions.BadRequestException;
import ninja.exceptions.InternalServerErrorException;
import ninja.exceptions.PostRequestViolationException;
import ninja.params.PathParam;
import ninja.validation.FieldViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import requests.UserRegisterRequest;
import services.UserService;

import java.util.List;


@Singleton
public class UserController extends BaseApiController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Result register(Context context, @JSR303Validation UserRegisterRequest registerRequest, Validation validation) {
        if (validation.hasBeanViolations()){
            throw  PostRequestViolationException.fromValidation(validation);
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
