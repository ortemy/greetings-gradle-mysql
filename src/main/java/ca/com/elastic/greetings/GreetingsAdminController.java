package ca.com.elastic.greetings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class GreetingsAdminController {

    @Autowired
    GreetingsService greetingsService;

    @RequestMapping(path ="/admin/greetings/", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Greeting createGreeting(@RequestBody Greeting greeting) throws  Exception{
        return greetingsService.saveGreeting(greeting);
    }

    @RequestMapping(path="/admin/greetings/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Greeting updateGreeting(@RequestBody Greeting greeting, @PathVariable(name="id") Long greetingId){
        return greetingsService.updateGreeting(greeting,greetingId);
    }

    @RequestMapping(path="/admin/greetings/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Long deleteGreeting(@PathVariable(name="id") Long greetingId){
        return greetingsService.deleteGreeting(greetingId);
    }

    @RequestMapping(path="/admin/greetings/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Greeting> listGreetings(){
        return greetingsService.getAllGreetings();
    }
}
