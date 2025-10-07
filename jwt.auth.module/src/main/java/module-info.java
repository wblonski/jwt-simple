module jwt.auth.module {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.web;
    requires spring.webmvc;
    requires spring.security.core;
    requires spring.security.web;
    requires spring.security.config;
    requires spring.data.jpa;
    requires spring.tx;
    requires spring.data.commons;
    requires spring.aop;
    
    // Jakarta EE
    requires jakarta.persistence;
    
    // Tomcat
    requires org.apache.tomcat.embed.core;
    
    // Jackson
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;
    
    // JWT and 2FA
    requires jjwt.api;
    requires totp;
    
    // OpenAPI/Swagger
    requires io.swagger.v3.oas.annotations;
    
    // Java standard modules
    requires java.net.http;
    requires java.sql;
    requires jdk.jdi;
    
    // Lombok (static - compile time only)
    requires static lombok;
    
    // Custom modules
    requires jwt.persistence.module;
    requires logback.logger.module;
    requires spring.security.crypto;
    
    // Open packages for Spring's reflection-based operations (CGLIB proxies, dependency injection)
    opens pl.wblo.jwt to spring.core, spring.beans, spring.context, spring.aop;
    opens pl.wblo.jwt.controller to spring.core, spring.beans, spring.web, spring.aop;
    opens pl.wblo.jwt.service to spring.core, spring.beans, spring.aop;
    opens pl.wblo.jwt.security.config to spring.core, spring.beans, spring.aop;
    opens pl.wblo.jwt.restobjects to com.fasterxml.jackson.databind;
    
    // Export packages
    exports pl.wblo.jwt;
    exports pl.wblo.jwt.controller;
    exports pl.wblo.jwt.service;
    exports pl.wblo.jwt.restobjects;
    exports pl.wblo.jwt.security.config;
}
