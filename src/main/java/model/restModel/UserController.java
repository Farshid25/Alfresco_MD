package model.restModel;

import com.google.gson.Gson;
import services.Type_Read_Switch.TypeDetection;
import spark.Request;
import spark.Response;
import spark.ResponseTransformer;
import spark.Route;

import java.util.List;
import services.*;

import static model.jsonUtility.JsonUtil.json;
import static spark.Spark.*;

public class UserController {
    TypeDetection typeDetection = new TypeDetection();
    UserService service = new UserService();

    public UserController(final UserService userService ) {
        //get("/types",(req,res) -> userService.getAll(), (ResponseTransformer) json());

           get("types", new Route() {
               @Override
               public Object handle(Request request, Response response) {
                   //response.type("text/json");

                   userService.create("2", "reza");
                   return userService.getName("reza");
               }
           });
        get("user", new Route() {
                    @Override
                    public Object handle(Request request, Response response) {
                        return userService.getName("reza");
                    }
                });
          //  @Override
//            public Result handle(Request request, Response response) {
//                response.type("application/json");
//                String path = request.queryParams("path");
//                try {
//                    return typeDetection.mySwitch(path);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return new Result();
//            }
//        });
    }
}