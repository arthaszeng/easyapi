package com.arthaszeng.easyapi;

import com.avos.avoscloud.AVOSCloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        AVOSCloud.initialize("WWfarQ5WJWyBODyeS3YdWoce-gzGzoHsz","9jvhzsjWx2wJm5zizXdgV0Qq","l6AlnDCjtSe7fshCdX5Oo6gr");
        AVOSCloud.setDebugLogEnabled(true);
        SpringApplication.run(Application.class);
    }

}
