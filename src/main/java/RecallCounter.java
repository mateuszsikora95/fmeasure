public class RecallCounter {
    public double countRecall(EngineStats engineStats) {
        return (engineStats.getTp() + engineStats.getTn()) /
                (engineStats.getTp() * 1.0 + engineStats.getTn() + engineStats.getFp() + engineStats.getFn());
    }
}
