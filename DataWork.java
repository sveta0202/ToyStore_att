import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class DataWork {

    public ArrayList<Toy> readTxt() {
    ArrayList<Toy>storage = new ArrayList<>();
    String[] fields = new String[4];

        try {
            BufferedReader reader = new BufferedReader(new FileReader("ToyData.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (amountOfFields(line) != 4){
                    System.out.println("Неверное кол-во полей в файле ToyData.txt, возвращается пустой список");
                    reader.close();
                    return null;
                }
                fields = line.split(";");
                storage.add(new Toy(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]), Double.parseDouble(fields[3]) ));
                line = reader.readLine();
            }
            reader.close();
            if (storage.size() == 0) return null;
            else return storage;
        } catch (IOException e) {
            System.out.println("Некорректные данные в файле ToyData.txt");
            e.printStackTrace();
            return null;
        }
    }

    public int amountOfFields(String line){
        int counter = 0;
        int index = 0;
        index = line.indexOf(";", index);
        while (index != -1) {
            counter++;
            index = line.indexOf(";", index);
            if (index != -1) index++;
        }
        //System.out.println(counter);
        return counter;
    }

    public void saveTxt(String record) {
        try {
            FileWriter writer = new FileWriter("raffleLog.txt", true);
            
            writer.write(record + "\n");
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Возникла ошибка при записи.");
            }
        
    }

}