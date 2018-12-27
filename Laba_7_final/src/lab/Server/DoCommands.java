package lab.Server;

import sample.Main;

public class DoCommands {


   static String command;

     public static void DoCommand2() {
         System.out.println(command);}

         public static void DoCommand() {
         Collect collect = new Collect();

       String line = command;
        line = line.trim();
       if (line.equals("clear")){
           collect.getLiblaries().clear();
           System.out.println("Коллекция опусташена");
       }

       else if (line.startsWith("remove")){
           line = line.substring(line.indexOf("{"));
           try {


               collect.getLiblaries().remove(Collect.fromJsonStringToObject(line));
           }
           catch (Exception e) {
               System.out.println("Неверно введена книга");
           }
       }

       else if (line.equals("save"))
       {
           try { Collect.save();

           }
           catch (Exception e){}
           }
       else if (line.startsWith("add")){
           line = line.substring(line.indexOf("{"));
           try {
               collect.getLiblaries().put(collect.getLiblaries().size(),Collect.fromJsonStringToObject(line));
           }
           catch (Exception e) {
               System.out.println("Неверно введена книга");
           }
       }



        }}


