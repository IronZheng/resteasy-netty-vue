package com.xuyl.example.controller;

import com.xuyl.example.main.NettyContainer;
import org.jboss.resteasy.spi.Registry;



/**
 * Created by dell on 2017/7/13.
 */
public class HelloServer{
    public static void main(String[] args) throws Exception {
        Registry registry = NettyContainer.start().getRegistry();
        registry.addPerRequestResource(Hello.class);
    }
}
