package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {



    @RequestMapping("/Hello")
    public String Hello(@RequestParam(value="name", defaultValue="World") String name) {
		return  "Hello " + name;
   	
    }
}
