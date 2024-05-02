package desafioprojetodio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <h1>DESAFIO DIO.</h1>
 * Projeto Spring Boot gerado via Spring Initializr,
 *
 * Os Seguintes Módulos Foram Selecionados:
 *
 * -Spring Data JPA
 * -Spring Web
 * -H2 Database
 * -OpenFeign
 *
 * @author Wesley Silvestre Corrêa.
 */

@EnableFeignClients
@SpringBootApplication
public class DesafioProjetoDioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioProjetoDioApplication.class, args);
	}

}
