import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    public ArrayList<String> readData(String path){
        File file = new File(path);
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine(); //Skip header
            while(scan.hasNextLine()){
                String s = scan.nextLine();// "Twin Peaks; 1990-1991; Crime, Drama, Mystery; 8,8; 1-8, 2-22;"
                String[] values = s.split(";");
                String username = values[0];
                String password = values[1];
                User u = new User(username, password);
                data.add(s);
            }
        }catch(FileNotFoundException e){
            System.out.println("Der er sket en fejl i indlæsningen af dataen: Filen findes ikke");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Der er sket en fejl i indlæsningen af dataen: Index passer ikke");
        }
        return data;
    }

    public void writeData(String path) {
        File file = new File(path);
        try{
            FileWriter writer = new FileWriter(file);

        }catch (IOException e){

        }
    }

    /*public String[] readData(String path, int length) {


        // start metoden med at initialisere et String[] data med det antal pladser som er angivet i parameterlisten (length)
        String [] data = new String[length];
        File file = new File(path);
        try{
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header

            int counter =0;
            while(scan.hasNextLine()){
                String s = scan.nextLine();
                data[counter] = s;
                counter++;
            }
        }catch (FileNotFoundException e){

        }


        return data;
    }*/

    public void saveData(ArrayList<String> data, String path, String header){
        File file = new File(path);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(header+"\n");
            for(String s: data){
                writer.write(s+"\n");
            }
            writer.close();
        }catch (IOException e){

            System.out.println("filen blev ikke fundet");
        }
    }





    /*
    Skal bruges til indlæsning af serie data
    String[] data = line.split(";");

String name = data[0];
String year = data[1];
String[] genres = data[2].split(", ");
double rating = Double.parseDouble(data[3].replace(",", "."));
String[] seasons = data[4].split(", ");

for (String season : seasons) {
    String[] seasonInfo = season.split("-");

    int seasonNumber = Integer.parseInt(seasonInfo[0]);
    int numberOfEpisodes = Integer.parseInt(seasonInfo[1]);
}

Gør at selvom en serie har forskellige antal genre, så fungerer koden stadig
     */
}