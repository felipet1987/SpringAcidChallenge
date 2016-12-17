package hello;


import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin
@RestController
public class HelloController {


	
	private UserRepository UserRepository;
	private UserService service;

	@Autowired
	HelloController(UserRepository repository) {
		this.UserRepository = repository;
		this.service = new UserService();
		
		
	}
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String index() {
//
//		return "index";
//
//	}
	
	
	
	

    @RequestMapping("/Hello")
    public String Hello(@RequestParam(value="name", defaultValue="World") String name) {
		return  "Hello " + name;
   	
    }

	@RequestMapping(value = "/users",method = RequestMethod.GET)
	Iterable<User> allUsers() {
		return this.UserRepository.findAll();
	}
    
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public void getImage(HttpServletResponse response) throws IOException {
		

		User user = UserRepository.findOne((long)1);
	    InputStream in = service.getImage(user);
	    
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(in, response.getOutputStream());
	}	

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Object> subirImagen(@RequestBody User user,UriComponentsBuilder ucBuilder) throws JsonProcessingException {
        System.out.println("leyendo User " + user.getName());
        
        
        Message res = new Message();
		if (service.isAuthorized(user)) {
            System.out.println("A User with name " + user.getName() + " is authorized");
            User updated = UserRepository.findOne((long)1);
            System.out.println("leyendo image " + user.getImage());
            updated.setImage(user.getImage());
            UserRepository.save(updated);
            
            res.setMessage("created");
            return new ResponseEntity<>(res,HttpStatus.CREATED);
        }
		res.setMessage("unauthorized");
        System.out.println("A User with name " + user.getName() + " is unauthorized");
        return new ResponseEntity<>(res,HttpStatus.UNAUTHORIZED);
		

 


    }
    
  
    
    
    
    
    
    
    
}
