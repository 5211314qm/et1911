package com.etoak;

import com.etoak.service.HelloService;
import com.etoak.service.impl.HelloServiceImpl;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class CxfServer{

    public static void main(String[] args) {

        //创建JaxWSSerFactory
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();

        //设置wsdl地址
        factoryBean.setAddress("http://localhost:8000/hello");

        //设置发布的服务接口
        factoryBean.setServiceClass(HelloService.class);

        //设置发布的服务实现
        factoryBean.setServiceBean(new HelloServiceImpl());

        //设置服务，并调用start()启动
        Server server = factoryBean.create();

        server.start();
        System.out.println("Server start...");
    }
}
