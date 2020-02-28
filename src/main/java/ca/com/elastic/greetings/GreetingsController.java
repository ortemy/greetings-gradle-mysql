package ca.com.elastic.greetings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    @Autowired
    GreetingsService greetingsService;

    @RequestMapping(method = GET)
    public String getGreetings(@RequestParam(required = false, name="timeZone") String zone){
        return  greetingsService.getGreetingForTimezone(zone);
    }

    @GetMapping("/{id}")
    public String getMessage(@PathVariable Long id){
        return  greetingsService.getGreetingMessageById(id);
    }
}
