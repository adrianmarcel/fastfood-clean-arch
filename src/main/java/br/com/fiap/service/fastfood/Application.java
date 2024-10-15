package br.com.fiap.service.fastfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.fiap")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
