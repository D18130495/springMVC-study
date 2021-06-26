# Spring
Official website: https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#spring-core
# Project structure and configuration
1. Create maven
2. Delete src folder
3. Maven dependencies and another module
4. web.xml
5. springmvc-servlet.xml(annotation)
6. Controller
7. Forward and redirect
8. URL parameter(Same like mybatis @param(" "))

### 3. Maven dependencies
``` xml
        <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.9.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
    </dependencies>
```

### web.xml
``` xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
             version="4.0">
    
        <servlet>
            <servlet-name>springmvc</servlet-name>
            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    
            <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:springmvc-servlet.xml</param-value>
            </init-param>
    
            <load-on-startup>1</load-on-startup>
        </servlet>
    
        <servlet-mapping>
            <servlet-name>springmvc</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>
    </web-app>
```

### 5. springmvc-servlet.xml(annotation)
``` xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xmlns:mvc="http://www.springframework.org/schema/mvc"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
            https://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            https://www.springframework.org/schema/context/spring-context.xsd
            http://www.springframework.org/schema/mvc
            https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    
        <context:component-scan base-package="com.shun.controller"/>
        <mvc:default-servlet-handler/>
        <mvc:annotation-driven/>
    
        <!--视图解析器-->
        <bean id="InternalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="prefix" value="/WEB-INF/jsp/"/>
            <property name="suffix" value=".jsp"/>
        </bean>
    </beans>
```

### 6. Controller
``` java
    package com.shun.controller;
    
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.RequestMapping;
    
    @Controller
    public class HelloController {
    
        @RequestMapping("/h1")
        public String hello(Model model) {
            model.addAttribute("msg", "HelloSpringMVC");
            return "Hello";
        }
    }
```

### 7. Forward and redirect
``` java
    @RequestMapping("/t1")
    public String test(Model model) {

        model.addAttribute("msg", "hello");

        return "test";
    }
```

``` java
    @RequestMapping("/t1")
    public String test(Model model) {

        model.addAttribute("msg", "hello");

        return "redirect:index.jsp";
    }
```

### 8. URL parameter(Same like mybatis @param(" "))
``` java
    @RequestMapping("/t1")
    public String test(@RequestParam("username") String name, Model model) {
    
        model.addAttribute("msg", name);
    
        return "test";
    }
```

### Character encoding
``` xml
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

# Json

### Maven dependency
``` xml
    <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.10.0</version>
        </dependency>
    </dependencies>
```

### web.xml
``` xml
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```

### controller
``` java
    @RequestMapping("/j1")
    @ResponseBody
    public String json1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User("潜江一号", 23,"男");

        return mapper.writeValueAsString(user);
    }
    
        @RequestMapping("/j2")
    @ResponseBody
    public String json2() throws JsonProcessingException {
        List<User> list = new ArrayList<User>();

        User user1 = new User("潜江一号", 23,"男");
        User user2 = new User("潜江二号", 23,"男");
        User user3 = new User("潜江三号", 23,"男");
        User user4 = new User("潜江四号", 23,"男");

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        return JsonUtils.getJson(list);
    }

    @RequestMapping("/j3")
    @ResponseBody
    public String json3() throws JsonProcessingException {
        Date date = new Date();

        return JsonUtils.getJson(date, "yyyy-MM-dd HH:mm:ss");
    }
```

### Json util
``` java
    public class JsonUtils {
        public static String getJson(Object object) {
            return getJson(object, "yyyy-MM-dd HH:mm:ss");
        }
    
        public static String getJson(Object object, String format){
            ObjectMapper mapper = new ObjectMapper();
    
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            mapper.setDateFormat(sdf);
    
            try {
                return mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return  null;
        }
    }

```