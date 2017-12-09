package model.restModel;

import com.google.gson.Gson;
import services.Type_Read_Switch.TypeDetection;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;
import services.*;

import static spark.Spark.*;

public class UserController {
    TypeDetection typeDetection = new TypeDetection();

    public UserController( ) {
        post("/types", new Route() {




            @Override
            public Result handle(Request request, Response response) {
                response.type("application/json");
                String path = request.queryParams("path");
                try {
                    return typeDetection.mySwitch(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new Result();
            }
        });
    }
}