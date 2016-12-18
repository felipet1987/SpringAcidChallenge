package hello;


import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;


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
	


    @RequestMapping("/Hello")
    public ResponseEntity<Object> Hello(@RequestParam(value="name", defaultValue="World") String name) {
		return new ResponseEntity<>("Hello " + name,HttpStatus.OK);
    }

	@RequestMapping(value = "/users",method = RequestMethod.GET)
	Iterable<User> allUsers() {
		return this.UserRepository.findAll();
	}
    
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public void getImage(HttpServletResponse response) throws IOException {
		

		User user = UserRepository.findOne((long)1);
	    InputStream in = service.getImage(user);
	    
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(in, response.getOutputStream());
	}	

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Object> subirImagen(@RequestBody User user,UriComponentsBuilder ucBuilder) throws JsonProcessingException {
        System.out.println("leyendo User " + user.getUserName());
        
        
        Message res = new Message();
        
        
		if(user.getImage() == null ||user.getImage() ==  "" || user.getUserName() == null || user.getUserName() == "")
		{
			res.setMessage("Bad Request");
	        return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
			
		}        

		if (service.isAuthorized(user)) {
			
			

			
            System.out.println("A User with name " + user.getUserName() + " is authorized");
            User updated = UserRepository.findOne((long)1);
            System.out.println("leyendo image " + user.getImage());
            updated.setImage(user.getImage());
            
            Long id = updated.getId();
			UriComponents uriComponents = ucBuilder.path("/users/{id}").buildAndExpand(id);
            
            UserRepository.save(updated);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=UTF-8"); 
            headers.setLocation(uriComponents.toUri());
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }
		
		
		
		
		
		res.setMessage("unauthorized");
        System.out.println("A User with name " + user.getUserName() + " is unauthorized");
        return new ResponseEntity<>(res,HttpStatus.UNAUTHORIZED);
		

 


    }
    
  
    
    
    
    
    
    
    
}
