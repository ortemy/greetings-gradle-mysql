package ca.com.elastic.greetings;


import ca.com.elastic.greetings.exception.GreeetingExistsException;
import ca.com.elastic.greetings.exception.GreetingNotFoundException;
import ca.com.elastic.greetings.exception.InvalidTimeZoneException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.zone.ZoneRulesException;
import java.util.Optional;

@Service
@Transactional
public class GreetingsService {

    public static final String GREETING_DOES_NOT_EXIST="Greeting with this ID does not exist";
    public static final String GREETING_ALREADY_EXISTS="This greeting already exists. You can have onlly one greeting of the same type";
    public static final String NO_GREETING_FOR_TIME_PERIOD="No Greeting for this time period";
    public static final String INVALID_TIMEZONE="Time Zone is invalid";


    @Autowired
    GreetingRepository repository;

    public Greeting saveGreeting(Greeting greeting) throws  Exception {

        if (greetingTypeExists(greeting.getGreetingType())) {
            throw new GreeetingExistsException(GREETING_ALREADY_EXISTS);
        }

        return repository.save(greeting);
    }

    public Greeting updateGreeting(Greeting greeting, Long id) {
        Greeting old = Optional.of(repository.findById(id)).get().orElse(null);

        if (old == null) {
            throw new GreetingNotFoundException(GREETING_DOES_NOT_EXIST);

        }

        old.setMessage(greeting.getMessage());
        repository.save(old);

        return old;
    }

    public Long deleteGreeting(Long id) {
        Greeting old = Optional.of(repository.findById(id)).get().orElse(null);

        if (old == null) {
            throw new GreetingNotFoundException(GREETING_DOES_NOT_EXIST);
        }
        repository.delete(old);

        return id;
    }

    public Iterable<Greeting> getAllGreetings() {

        return repository.findAll();
    }

    private boolean greetingTypeExists(GreetingType type) {
        Greeting greeting = repository.findByGreetingType(type);

        return greeting != null;
    }

    public String getGreetingMessageById(Long id){
        Greeting greeting = Optional.of(repository.findById(id)).get().orElse(null);
        return greeting.getMessage();
    }

    public String  getGreetingForTimezone(String timeZone){
        if(StringUtils.isEmpty(timeZone)){
            return "Hello!";
        }
        ZoneId zone;
        try {
            zone = ZoneId.of(timeZone);
        }
        catch(ZoneRulesException e){
            throw new InvalidTimeZoneException(INVALID_TIMEZONE);
        }
        LocalTime time = LocalTime.now(zone);

        GreetingType searchCriteria;

        if(time.isAfter(LocalTime.of(4,1))&&time.isBefore(LocalTime.of(12,0))){
            searchCriteria=GreetingType.MORNING;
        }
        else if(time.isAfter(LocalTime.of(12,1))&&time.isBefore(LocalTime.of(18,0))){
            searchCriteria=GreetingType.DAY;
        }
        else if(time.isAfter(LocalTime.of(18,1))&&time.isBefore(LocalTime.of(23,0))){
            searchCriteria=GreetingType.EVENING;
        }
        else{
            searchCriteria=GreetingType.NIGHT;
        }

        Greeting timelyGreeting = repository.findByGreetingType(searchCriteria);

        if(timelyGreeting==null) {
            throw new GreetingNotFoundException(NO_GREETING_FOR_TIME_PERIOD);
        }

        return timelyGreeting.getMessage();
    }
}
