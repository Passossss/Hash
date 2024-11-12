import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Hash Hash1 = new HashFirst(5000);
        Hash Hash2 = new HashSecond(5000);

        String filePath = "src/female_names.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Hash1.insert(line);
                Hash2.insert(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Hash1.printReport("Hash First");
        Hash2.printReport("Hash Second");
    }
}
    