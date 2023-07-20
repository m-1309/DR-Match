package TextAnalysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter;

public class JsRun {

    public static void main(String[] args) throws IOException {
        String url = "https://mtsamples.com/site/pages/browse.asp?type=6-Cardiovascular%20/%20Pulmonary";
        Document doc = Jsoup.connect(url).get();
        Elements tbody = doc.select("table tbody"); // Select the tbody element
        Elements rows = tbody.select("tr"); // Select all rows in the tbody
        File file = new File("AB2.csv"); // Create a new file to store the data
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);

        for (Element row : rows) {
            Elements cells = row.select("td"); // Select all cells in the row
            String[] rowData = new String[cells.size()]; // Create an array to store data for each row
            int i = 0;
            for (Element cell : cells) {
                rowData[i++] = cell.text(); // Extract the data from each cell
            }
            writer.writeNext(rowData); // Write the row data to the CSV file
        }

        writer.close();
        System.out.println("Dataset created at: " + file.getAbsolutePath()); // Display the path to the file
    }
}