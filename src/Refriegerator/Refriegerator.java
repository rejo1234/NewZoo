package Refriegerator;

public class Refriegerator {
        ExpiryDate myExpiryDates;
        Vegetables[] myVegetables;
        Fruits[] myFruits;
        private static final int ExpiryDate = 4;
        public Refriegerator(){
            myVegetables = new Vegetables[5];
            myFruits = new Fruits[5];
            for (int i = 0; i < myFruits.length; i++){
                myFruits[i] = new Fruits();
                myVegetables[i] = new Vegetables();
            }
        }
        public void info(){
        String[] fruits = myFruits[0].getFruits();
        for (int i = 0; i < fruits.length; i++){
            System.out.println("zjedz " + fruits[i] + " do " + (ExpiryDate + i) + " dni");
        }
        String[] vegetables = myVegetables[0].getVegetables();
        for (int i = 0; i < vegetables.length; i++){
            System.out.println("zjedz " + vegetables[i] + " do " + (ExpiryDate + i) + " dni");
        }
        }
    }
