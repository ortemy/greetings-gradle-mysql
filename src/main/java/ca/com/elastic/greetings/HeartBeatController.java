package ca.com.elastic.greetings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {

    @GetMapping("/heartbeat")
    public String ping() {
        return "Alive";
    }

}
