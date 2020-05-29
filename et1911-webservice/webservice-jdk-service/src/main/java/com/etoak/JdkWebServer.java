package com.etoak;

import com.etoak.service.impl.HelloServiceImpl;

import javax.xml.ws.Endpoint;

public class JdkWebServer {
    public static void main(String[] args) {

        //两个参数
        //参数一：发布的地址（wsdl地址）
        //參數二：要暴露的服務
        Endpoint.publish("http://localhost:8080/hello",new HelloServiceImpl());
        System.out.println("Server start");
    }
}
