package com.xuyl.example.controller;

import com.xuyl.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/7/13.
 */
@Path("/api")
public class Hello {

    private static final Logger logger = LoggerFactory.getLogger(Hello.class);

    @GET
    @Produces("application/json")
    @Path("hello/{id}")
    public Map get(@PathParam("id")String id){
        logger.info("hello",id);
        String result = "hello:"+id;
        UserService userService = new UserService();
        Map map = userService.getList(id);
        return map;
    }




}
