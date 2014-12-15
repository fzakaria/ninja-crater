package controllers;

import static com.google.common.base.Preconditions.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.User;
import ninja.Context;
import ninja.Result;
import ninja.exceptions.PostRequestViolationException;
import ninja.params.PathParam;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import requests.UserRegisterRequest;
import services.UserService;

import static ninja.Results.notFound;
import static ninja.Results.ok;


@Singleton
public class UserController extends BaseApiController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Result register(Context context, @JSR303Validation UserRegisterRequest registerRequest, Validation validation) {
        checkNotNull(registerRequest);
        if (validation.hasBeanViolations()){
            throw  PostRequestViolationException.fromValidation(validation);
        }
        return ok().render(userService.create(registerRequest));
    }

    public Result index(@PathParam("username") String username) {
        checkNotNull(username);
        User foundUser = userService.findByUsername(username);
        if (foundUser == null) {
            return notFound();
        }
        return ok().render(foundUser);
    }




}
