import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        Set<String> gigablast = new HashSet<>();
        Set<String> elastic = new HashSet<>();
        Set<String> solr = new HashSet<>();
        try (Reader reader = Files.newBufferedReader(Paths.get("src/main/java/secondQuery.csv"));
             CSVReader csvReader = new CSVReader(reader)) {
            initSearch(csvReader, solr, elastic, gigablast);
        }
        StatsCounter statsCounter = new StatsCounter(new PrecisionCounter(), new RecallCounter(), new FmeasureCounter());
        ResultStats elasticStats = statsCounter.calculateStats("elasticsearch", elastic, solr, gigablast);
        ResultStats solrStats = statsCounter.calculateStats("solr", solr, elastic, gigablast);
        ResultStats gigablastStats = statsCounter.calculateStats("Gigablast", gigablast, elastic, solr);
        System.out.println(elasticStats);
        System.out.println(solrStats);
        System.out.println(gigablastStats);
    }

    private static void initSearch(CSVReader csvReader, Set<String> solr, Set<String> elastic, Set<String> gigablast) throws IOException {
        csvReader.readAll().forEach(x -> {
            if(!x[0].isEmpty()) solr.add(x[0]);
            if(!x[1].isEmpty()) elastic.add(x[1]);
            if(!x[2].isEmpty()) gigablast.add(x[2]);
        });
    }
}
