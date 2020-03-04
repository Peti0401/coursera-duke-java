package dukejava.week2.stringsthirdassignment;

public class Test {

    public static void main(String[] args) {
        String dna = "CCC";
        int countcg = 0;
        int start = 0;
        while (true) {
            int pos = dna.indexOf("C", start);
            if (pos == -1) {
                break;
            }
            countcg += 1;
            start = pos + 1;
        }
        System.out.println("countcg = " + countcg);
    }

}
