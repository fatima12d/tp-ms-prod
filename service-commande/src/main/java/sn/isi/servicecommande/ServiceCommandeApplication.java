package sn.isi.servicecommande;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceCommandeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCommandeApplication.class, args);
    }

}
