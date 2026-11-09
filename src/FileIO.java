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
                data.add(s);
            }
        }catch(FileNotFoundException e){
            System.out.println("Der er sket en fejl i indlæsningen af dataen: Filen findes ikke");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Der er sket en fejl i indlæsningen af dataen: Index passer ikke");
        }
        return data;
    }

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
}