package ca.com.elastic.greetings;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
@Entity
public class Greeting {

     @Id
     @GeneratedValue(strategy= GenerationType.AUTO)
     private Long id;
     private String message;
     private GreetingType greetingType;

}
