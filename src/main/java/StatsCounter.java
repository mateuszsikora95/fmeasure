import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StatsCounter {

    private PrecisionCounter precisionCounter;
    private RecallCounter recallCounter;
    private FmeasureCounter fmeasureCounter;
    private final int RECORD_COUNT = 57650;


    public StatsCounter(PrecisionCounter precisionCounter, RecallCounter recallCounter, FmeasureCounter fmeasureCounter) {
        this.precisionCounter = precisionCounter;
        this.recallCounter = recallCounter;
        this.fmeasureCounter = fmeasureCounter;
    }

    public ResultStats calculateStats(String name, Set<String> first, Set<String> second, Set<String> third) {

        Set<String> existInMoreThanTwoSearch = existInMoreThanTwoSearch(first, second, third);
        int tn = countTrueNegative(first, second, third);
        EngineStats engineStats = new EngineStats(tn, countTruePositiveForEngine(first, existInMoreThanTwoSearch),
                countFalsePositive(first, second, third), countFalseNegative(first, second, third));
        double precision = precisionCounter.countPrecision(engineStats);
        double recall = recallCounter.countRecall(engineStats);
        double fmeasure = fmeasureCounter.countFmeasure(precision, recall);
        return new ResultStats(name, precision, recall, fmeasure);
    }

    private int countFalseNegative(Set<String> first, Set<String> second, Set<String> third) {
        Set<String> intersect = intersect(second, third);
        return (int) intersect.stream().filter(x -> !first.contains(x)).count();
    }

    private int countFalsePositive(Set<String> first, Set<String> second, Set<String> third) {
        return (int) first.stream().filter(x -> !second.contains(x) && !third.contains(x)).count();
    }

    private int countTruePositiveForEngine(Set<String> elastic, Set<String> existInMoreThanTwoSearch) {
        return (int) elastic.stream().filter(existInMoreThanTwoSearch::contains).count();
    }

    private Set<String> existInMoreThanTwoSearch(Set<String> first, Set<String> second, Set<String> third) {
        Set<String> firstInter = intersect(first, second);
        Set<String> secondInter = intersect(third, second);
        Set<String> thirdInter = intersect(first, third);
        firstInter.addAll(secondInter);
        firstInter.addAll(thirdInter);
        return firstInter;
    }

    private Set<String> intersect(Set<String> firstSet, Set<String> secondSet) {
        return firstSet.stream().filter(secondSet::contains).collect(Collectors.toSet());
    }

    private int countTrueNegative(Set<String> first, Set<String> second, Set<String> third) {
        Set<String> allMatches = new HashSet<>();
        allMatches.addAll(first);
        allMatches.addAll(second);
        allMatches.addAll(third);
        return RECORD_COUNT - allMatches.size();
    }
}
