package lab.Server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.beans.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static lab.Server.ServerLogic.toDB;

public class Collect {

    static Map<Integer, Book> Liblaries = new ConcurrentHashMap<>();

    public Map<Integer, Book> getLiblaries() {
        return Liblaries;
    }

    public void setLiblaries(Map<Integer, Book> liblaries) {
        Liblaries = liblaries;
    }

    public void Add() {

            Book b1 = new Book();
            b1.setAuthtor("Tolstoy");
            b1.setBookname("VoinaIMir");
            b1.setYear(1867);
            b1.setPages(1244);
            b1.setDate(LocalDateTime.now());
            Book b2 = new Book();
            b2.setAuthtor("Оруэлл");
            b2.setBookname("1984");
            b2.setYear(1944);
            b2.setPages(356);
        b2.setDate(LocalDateTime.now());

        Book b3 = new Book();
            b3.setPages(15);
            b3.setYear(1974);
            b3.setBookname("Stihi");
            b3.setAuthtor("Agniya Barto");
             b3.setDate(LocalDateTime.now());

            Liblaries.put(1, b1);
            Liblaries.put(2, b2);
            Liblaries.put(3, b3);

        }

        public static void save() {
            try {
                XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("src/Kuuk.xml")));
                x.setPersistenceDelegate(LocalDateTime.class,
                        new PersistenceDelegate() {
                            @Override
                            protected Expression instantiate(Object localDate, Encoder encdr) {
                                return new Expression(localDate,
                                        LocalDateTime.class,
                                        "parse",
                                        new Object[]{localDate.toString()});
                            }
                        });
                x.writeObject(Liblaries);
                x.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        public static void deserializeFromXML () throws IOException {
            FileInputStream fis = new FileInputStream("src/Kuuk.xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            ConcurrentHashMap<Integer, Book> decodedBooks = (ConcurrentHashMap) decoder.readObject();
            Liblaries.putAll(decodedBooks);
            decoder.close();
            fis.close();
            for (Integer key : decodedBooks.keySet())
                System.out.println(decodedBooks.get(key));

        }
        public static void Import(String format_input) throws IOException {
            Map<Integer, Book> ImportLiblaries = new HashMap<>();
            FileInputStream fis = new FileInputStream(format_input);
            XMLDecoder decoder = new XMLDecoder(fis);
            ImportLiblaries = (HashMap) decoder.readObject();

            decoder.close();
            fis.close();
            Liblaries.putAll(ImportLiblaries);
        }
        public static void JsonToObject(String format_value, Integer format_key){
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = format_value;
            try {
                Book Book_buffer = mapper.readValue(jsonInString, Book.class);
                Liblaries.put(format_key, Book_buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        public static String ObjectToJson(Integer id) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(Liblaries.get(id));
            return json;
        }
    public static Book fromJsonStringToObject(String jsonString)
    {
        String todb = jsonString.concat("1");
        toDB(todb);
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Book.class);
    }


    public static String fromObjectToJsonString(Book book)
    {
        Gson gson = new Gson();
        StringWriter writer = new StringWriter();
        gson.toJson(book, writer);

        return writer.toString();
    }
}

