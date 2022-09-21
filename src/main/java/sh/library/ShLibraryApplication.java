package sh.library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author huangqc
 * @date 2022/09/19
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"sh.library"})
@MapperScan(basePackages = {"sh.library.mapper"})
public class ShLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShLibraryApplication.class, args);
    }
}
