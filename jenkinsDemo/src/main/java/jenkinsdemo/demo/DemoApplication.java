package jenkinsdemo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@SpringBootApplication
@RequestMapping("admin/")
public class DemoApplication {
    @RequestMapping("home")
    String home(HttpServletRequest request) {
        String userName = "Jef";
        int count = 100;
        return "欢迎您" + userName + "，这是您的第 " + count + " 次登录";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
