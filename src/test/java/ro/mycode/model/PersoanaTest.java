package ro.mycode.model;

import org.junit.jupiter.api.Test;
import ro.mycode.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

class PersoanaTest {
    @Test

    void test1(){

        Persoana persoana=new Persoana(1,"alex","ciorba","alex@yahoo.com",20);
 

        PersonRepository personRepository= new PersonRepository();

        personRepository.addPersoana(persoana);

        personRepository.deletePersoana(35);
        Persoana persoana1=new Persoana(27,"marinel1","popescu1","marinel.popescu@yahoo.com",35);
       // personRepository.addPersoana(persoana1);

        personRepository.update(persoana1);
        System.out.println(personRepository.allPersons());


    }

}