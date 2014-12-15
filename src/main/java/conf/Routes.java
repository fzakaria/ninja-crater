package conf;

import controllers.UserController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {
        
        ///////////////////////////////////////////////////////////////////////
        // User Routes
        ///////////////////////////////////////////////////////////////////////
        router.POST().route("/users/register").with(UserController.class, "register");
        router.GET().route("/user/{username}").with(UserController.class, "index");
    }

}
