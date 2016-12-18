package hello;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			
			UserService service = new UserService();
		    String currentDir = System.getProperty("user.dir");
		    log.info("Current dir using System:" +currentDir);
		    File imagen = new File( currentDir  + "/imagen.jpg");
		    
		    if(imagen.exists()){
		    	log.info(imagen.getPath());
		    } 
			
			String imagenString = service.convertImage(imagen);
		    
			// save a couple of users
		    
			repository.save(new User("usuario1", imagenString));
			repository.save(new User("Chloe", "imagen"));
			repository.save(new User("Kim", "imagen"));
			repository.save(new User("David", "imagen"));
			repository.save(new User("Michelle", "imagen"));

			// fetch all users
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (User user : repository.findAll()) {
				log.info(user.getUserName());
			}
			log.info("");




		};
	}

}
