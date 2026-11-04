import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
/*
    public String[] readData(String path, int length) {


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
    }

    public ArrayList<String> readData(String path){
        File file = new File(path);
        ArrayList<String> data = new ArrayList<>(); //"Tess, 0", "Lina, 0", "Signe, 0"
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header
            while(scan.hasNextLine()){

                String s = scan.nextLine();// "Egon , 20000"
                data.add(s);

            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("der findes kun 2 værdier pr. række");
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
*/
}