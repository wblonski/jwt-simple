package pl.wblo.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"pl.wblo.jwt", "pl.wblo.repository"})
@EnableJpaRepositories(basePackages = "pl.wblo.repository")
@EntityScan(basePackages = "pl.wblo.repository.entity")
public class JwtAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtAuthApplication.class, args);
    }
}
