package ro.mycode.repository;

import org.junit.jupiter.api.Test;
import ro.mycode.model.Medic;
import ro.mycode.model.Persoana;

import static org.junit.jupiter.api.Assertions.*;

class MedicRepositoryTest {


    @Test

    void test1(){
        Medic medic =new Medic(11,"alex","ciorba","alex@yahoo.com",20);


        MedicRepository medicRepository=new MedicRepository();

        medicRepository.addMedic(medic);

        medicRepository.deleteMedic(35);

        medicRepository.update(medic);
        System.out.println(medicRepository.allMedics());

    }

}