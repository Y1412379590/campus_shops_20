package cn.edu.mju;

import cn.edu.mju.web.filter.CodingFilter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CampusShopsStarter {


    public static void main(String[] args) {

        new SpringApplicationBuilder(CampusShopsStarter.class)
                .web(WebApplicationType.SERVLET)
                .build()
                .run(args);
    }






}
