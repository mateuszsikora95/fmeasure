public class PrecisionCounter {
    public double countPrecision(EngineStats engineStats) {
        return engineStats.getTn() / (engineStats.getTn() * 1.0 + engineStats.getFp());
    }
}
