package es.javiergarbedo.tutorialmasterdetail.person;

import java.util.ArrayList;
import java.util.List;

public class PersonContent {

    //Lista que almacenará las personas
    private static ArrayList<Person> personList = new ArrayList();

    public static ArrayList getPersonList() {
        return personList;
    }

    /**
     * Carga en la lista personList una serie de personas con datos ficticios
     */
    public static void loadPersonList() {
        Person person;

        person = new Person();
        person.setId(1);
        person.setName("CECILIO");
        person.setSurnames("SORIA YEPEZ");
        person.setAlias("CECILIO SORIA");
        person.setMobileNumber("676114831");
        person.setEmail("cecsor80@yahoo.com");
        person.setPhotoFileName("cecilio");
        personList.add(person);

        person = new Person();
        person.setId(2);
        person.setName("DAVID");
        person.setSurnames("SANCHEZ IGLESIAS");
        person.setAlias("DAVID SANCHEZ");
        person.setMobileNumber("670409567");
        person.setEmail("davsan66@gmail.com");
        person.setPhotoFileName("david");
        personList.add(person);

        person = new Person();
        person.setId(3);
        person.setName("ARANZAZU");
        person.setSurnames("PEREZ DIEZ");
        person.setAlias("ARANZAZU PEREZ");
        person.setMobileNumber("697258230");
        person.setEmail("araper79@hotmail.com");
        person.setPhotoFileName("aranzazu");
        personList.add(person);
    }

}
