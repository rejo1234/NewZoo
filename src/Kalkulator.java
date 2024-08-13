import java.util.Arrays;

public class Kalkulator {
    int a;
    String[] wynik;

    int index;


    public Kalkulator(int a) {
        this.a = a;
        this.wynik = new String[10];
        this.index = 0;
    }
    public void checkArrayAndIncreaseSizeIfNeeded(){
        if (index == 10){
            String[] bufforArray = new String[100];
            System.out.println("zwiekszam tablice");
            for (int i = 0; i < index; i++){
                bufforArray[i] = wynik[i];
            }
            wynik = bufforArray;
        }
    }

    public void printTotal() {

        for (int i = 0; i < index; i++) {
            System.out.println(wynik[i]);
        }
    }

    public void odejmowanie(short liczba2) {
        odejmowanie(a, liczba2);
    }

    public void odejmowanie(int liczba1) {
        odejmowanie(liczba1, a);
    }

    public void odejmowanie(int liczba1, int liczba2) {
        checkArrayAndIncreaseSizeIfNeeded();
        int result = liczba1 - liczba2;
        wynik[index++] = "odejmowanie " + liczba1 + " - " + liczba2 + " wynik to " + result;
        //System.out.println("odejmowanie " + liczba1 + " - " + liczba2 + " wynik to " + result);
        //System.out.println(Arrays.toString(Arrays.copyOfRange(wynik,0,index)));
    }

    public void dodawanie(int liczba1, int liczba2) {
        checkArrayAndIncreaseSizeIfNeeded();
        int result = liczba1 + liczba2;
        wynik[index++] = "dodawanie " + liczba1 + " + " + liczba2 + " wynik to " + result;
        //System.out.println("dodawanie " + liczba1 + " + " + liczba2 + " wynik to " + result);
        //  System.out.println(Arrays.toString(Arrays.copyOfRange(wynik,0,1)));
    }

    public void dodawanie(int liczba3) {
        dodawanie(liczba3, a);
    }

    public void dodawanie(short liczba1) {
        dodawanie(liczba1, a);
    }

    public void printResultOdejmowanie() {
        for (int i = 0; i < index; i++) {
            System.out.println(wynik[i]);
            if (i < index - 1) {
                System.out.print("");
            }
        }
    }

    public void printResultDodawania() {
        for (int i = 0; i < index - 1; i++) {
            System.out.println(wynik[i] + ",");
        }
        System.out.println(wynik[index - 1]);
    }
}
