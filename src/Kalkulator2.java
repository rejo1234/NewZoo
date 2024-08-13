import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Kalkulator2 {
    int a;
    ArrayList<String> wynik;

    int index;
    private File file;

    public Kalkulator2(int a, String nazwaPliku) {
        this.a = a;
        this.index = 0;
        this.wynik = new ArrayList<String>();
        this.file = new File(nazwaPliku);
        this.scanFileToList();
    }

    public void scanFileToList(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                wynik.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(wynik);
    }

    public void writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < wynik.size(); i++) {
                bufferedWriter.write(wynik.get(i));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("zapisano wyniki do pliku " + file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printWynik() {
        System.out.println(wynik);
    }

    public void printTotal() {

        for (int i = 0; i < index; i++) {
            System.out.println(wynik.get(i));
        }
    }

    public void odejmowanie(short liczba2) {
        int result = a - liczba2;
        wynik.add("odejmowanie " + a + " - " + liczba2 + " wynik to " + result);
        // System.out.println(a + " - " + liczba2 + " = " + result);
    }


    public void odejmowanie(int liczba1) {
        int result = a - liczba1;
        wynik.add("odejmowanie " + a + " - " + liczba1 + " wynik to " + result);
        // System.out.println(a + " - " + liczba1 + " = " + result);
    }

    public void odejmowanie(int liczba1, int liczba2) {
        int result = liczba1 - liczba2;
        wynik.add("odejmowanie " + liczba1 + " - " + liczba2 + " wynik to " + result);
        //System.out.println("odejmowanie " + liczba1 + " - " + liczba2 + " wynik to " + result);
        //System.out.println(Arrays.toString(Arrays.copyOfRange(wynik,0,index)));
    }

    public void dodawanie(int liczba1, int liczba2) {
        int result = liczba1 + liczba2;
        wynik.add("dodawanie " + liczba1 + " + " + liczba2 + " wynik to " + result);
        //System.out.println("dodawanie " + liczba1 + " + " + liczba2 + " wynik to " + result);
        //  System.out.println(Arrays.toString(Arrays.copyOfRange(wynik,0,1)));
    }

    public void dodawanie(int liczba3) {
        int result = a + liczba3;
        wynik.add("dodawanie " + a + " + " + liczba3 + " wynik to " + result);
        // System.out.println(liczba3 + " + " + a + " = " + result);
    }

//    public void dodawanie(short liczba1) {
//        int result = a + liczba1;
//        wynik.add("dodawanie " + liczba1 + " + " + a + " wynik to " + result);
//        String wyniki = wynik.get(index++);
//    }

    public void printResultOdejmowanie2() {
        List<String> filtredList = wynik.stream()
                .filter(element -> element.charAt(0) == 'o')
                .collect(Collectors.toList());
        System.out.println(filtredList);
    }

    public void printResultOdejmowanie() {
        for (String operation : wynik) {
            char firstChar = operation.charAt(0);
            if (firstChar == 'o') {
                System.out.println(operation);
            }
        }
    }

    public void printResultDodawania() {
        for (String operation : wynik) {
            char firstChar = operation.charAt(0);
            if (firstChar == 'd') {
                System.out.println(operation);
            }
        }
    }
}
