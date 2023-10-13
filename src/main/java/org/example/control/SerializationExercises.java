package org.example.control;
import com.google.gson.Gson;

import java.io.*;

import org.example.model.Movie;
import org.example.model.Session;
import org.example.model.Theater;

public class SerializationExercises {
    /*
        Should define the class for the concepts Movie, Theater and Session.
        A session is a play of movie in a theater.
        Create 2 instances of each class and relate among them.
        Serialize to Json all objects and save then in different files.
     */

    public static class Exercise1 {
        public static void main(String[] args) {
            Movie movie1 =  new Movie("Titanic");
            Movie movie2 =  new Movie("Frozen");

            Theater theater1 = new Theater("Yelmo Cines Las Arenas");
            Theater theater2 = new Theater("Cinesa El Muelle");

            Session session1 = new Session(movie1, theater1);
            Session session2 = new Session(movie2, theater2);

            Gson gson = new Gson();
            String movie1Json = gson.toJson(movie1);
            String movie2Json = gson.toJson(movie2);
            String theater1Json = gson.toJson(theater1);
            String theater2Json = gson.toJson(theater2);
            String session1Json = gson.toJson(session1);
            String session2Json = gson.toJson(session2);
            //System.out.println(movie2Json);
            //File fileMovie1 = new File("./hola");
            //fileMovie1.mkdirs();

            String[] filename = {"movie1Json.txt", "movie2Json.txt", "theater1Json.txt", "theater2Json.txt", "session1Json.txt", "session2Json.txt"};

            String[] textToAdd = {movie1Json, movie2Json, theater1Json, theater2Json, session1Json, session2Json};

            for (int i = 0; i < filename.length; i++) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename[i]))) {
                    bw.append(textToAdd[i]);
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
        Deserialize the objects created in exercise 1.
        Now serialize them using ObjectOutputStream
    */



    public static class Exercise2 {

        public static void main(String[] args) {
            // Create a Gson instance
            Gson gson = new Gson();

            Movie movie1 = null;
            Movie movie2 = null;
            Theater theater1 = null;
            Theater theater2 = null;
            Session session1 = null;
            Session session2 = null;
            // Declare BufferedReader outside the try-catch block
            String[] filename = {"movie1Json.txt", "movie2Json.txt", "theater1Json.txt", "theater2Json.txt", "session1Json.txt", "session2Json.txt"};
            BufferedReader br = null;
            for (int i = 0; i < filename.length; i++){
            try {
                br = new BufferedReader(new FileReader("/Users/alejandroalemanaleman/IdeaProjects/Act3octDACD/" + filename[i]));

                // Read and process the file content here
                String line;
                line = br.readLine();
                if (i==0){
                    movie1 = gson.fromJson(line, Movie.class);
                } else if(i==1){
                    movie2 = gson.fromJson(line, Movie.class);
                } else if(i==2){
                    theater1 = gson.fromJson(line, Theater.class);
                } else if (i == 3) {
                    theater2 = gson.fromJson(line, Theater.class);
                }else if (i == 4) {
                    session1 = gson.fromJson(line, Session.class);
                }else if (i == 5) {
                    session2 = gson.fromJson(line, Session.class);
                }
                System.out.println(line);

            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO error: " + e.getMessage());
            } finally {
                // Close the BufferedReader in the finally block to ensure it's always closed
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            }

            System.out.println(movie1.getName());
            System.out.println(movie2.getName());
            System.out.println(theater1.getName());
            System.out.println(theater2.getName());
            System.out.println(session1.getMovie().getName());
            System.out.println(session2.getTheater().getName());




            //Apartado 2.2

            String[] filename2 = {"movie1OBJ.txt", "movie2OBJ.txt", "theater1OBJ.txt", "theater2OBJ.txt", "session1OBJ.txt", "session2OBJ.txt"};
            Object[] objects = {movie1, movie2, theater1, theater2, session1, session2};

            for (int i = 0; i < filename2.length; i++){

            try {

                // Create a FileOutputStream to write to a file
                FileOutputStream fileOutputStream = new FileOutputStream(filename2[i]);

                // Create an ObjectOutputStream to serialize the object
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                // Write the Movie object to the file


                objectOutputStream.writeObject(objects[i]);

                // Flush and close the ObjectOutputStream
                objectOutputStream.flush();
                objectOutputStream.close();

                // Close the FileOutputStream
                fileOutputStream.close();

                System.out.println("Movie object has been serialized and saved to" + filename2[i]);

            } catch (IOException e) {
                e.printStackTrace();
                }
            }
        }
    }

    /*Deserialize the objects from the binary files created in exercise 2.*/
    public static class Exercise3 {

        public static void main(String[] args) {

            Movie movie1 = new Movie("Titanic");
            Theater theater1 = new Theater("Yelmo Cines Las Arenas");
            Session session1 = new Session(movie1, theater1);

            Movie movie2 = new Movie("Frozen2");
            Theater theater2 = new Theater("Yelmo Cines Los Alisios");
            Session session2 = new Session(movie2, theater2);

            String[] filename2 = {"movie1OBJ.txt", "movie2OBJ.txt", "theater1OBJ.txt", "theater2OBJ.txt", "session1OBJ.txt", "session2OBJ.txt"};

            Movie deserializedMovie1 = null;
            Movie deserializedMovie2 = null;
            Theater deserializedTheater1 = null;
            Theater deserializedTheater2 = null;
            Session deserializedSession1= null;
            Session deserializedSession2 = null;
            Object[] objects = {deserializedMovie1, deserializedMovie2, deserializedTheater1, deserializedTheater2, deserializedSession1, deserializedSession2};



            for (int i = 0; i < filename2.length; i++){

            try {
                // Create an ObjectInputStream to read from the binary file
                FileInputStream fileInputStream = new FileInputStream(filename2[i]);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                // Deserialize the Movie object

                if (i == 0 || i == 1) {
                    objects[i] = (Movie) objectInputStream.readObject();
                } else if (i == 2 || i == 3) {
                    objects[i] = (Theater) objectInputStream.readObject();}
                else {
                    objects[i] = (Session) objectInputStream.readObject();
                }



                // Close the ObjectInputStream
                objectInputStream.close();

                // Now you can work with the deserialized Movie object
                System.out.println("Deserialized object" + (i + 1) +  ": " + objects[i]);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                }
            }










        }
    }
}