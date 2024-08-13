import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileTutorial {
    public void starter() throws IOException {
        File file = new File("C:\\Users\\ana\\IdeaProjects\\NewZoo\\src\\mojPlik.txt");


        BufferedReader reader
                = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null)

            System.out.println(line);
    }

    public void writeToFile() throws IOException {
        File file = new File("C:\\Users\\ana\\IdeaProjects\\NewZoo\\src\\mojPlik2.txt");


        if (!file.exists()) {
            System.out.println("We had to make a new file.");
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, true);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("\n123"  );
        bufferedWriter.write("\n123" );
        bufferedWriter.close();

        System.out.println("Done");
    }
}
