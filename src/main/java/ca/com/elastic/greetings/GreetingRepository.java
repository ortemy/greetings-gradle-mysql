package ca.com.elastic.greetings;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface GreetingRepository extends CrudRepository<Greeting, Long> {

    Greeting findByGreetingType(@Param("greetingType") GreetingType greetingType);
}
