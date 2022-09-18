package utils;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public Iterator<Object[]> addNewContact(){
        List<Object[]> list =new ArrayList<>();
        list.add(new Object[]{"Christo","Stoichkov","12345678978", "ghjk@kjh.com","Berlin","Forward"});
        list.add(new Object[]{"Christo","Stoichkov","12345898978", "ghjk@jh.com","Berlin","Forward"});
        list.add(new Object[]{"Christo","Stoichkov","12345458978", "g45k@jh.com","Berlin","Forward"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addNewContactFromCSV() throws IOException {
        List<Object[]> list =new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));

        String line = reader.readLine();

        while (line != null) {
            var split = line.split(",");
            list.add(new Object[]{new Contact().setName(split[0])
                    .setLastName(split[1])
                    .setPhone(split[2])
                    .setEmail(split[3])
                    .setAddress(split[4])
                    .setDescription(split[5])});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
