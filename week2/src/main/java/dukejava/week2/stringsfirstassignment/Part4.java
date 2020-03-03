package dukejava.week2.stringsfirstassignment;

import edu.duke.URLResource;

public class Part4 {

    private final static String URL = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";

    public static void printUrls() {
        URLResource file = new  URLResource(URL);
        for (String item : file.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {
                int beg = item.lastIndexOf("\"",pos);
                int end = item.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1,end));
            }
        }

    }

    public static void main(String[] args) {
        printUrls();
    }

}
