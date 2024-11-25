package mk.ukim.finki.wpaud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class VebProgramiranjeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VebProgramiranjeApplication.class, args);
    }

}
