public class ResultStats {

    private final String name;
    private final double precision;
    private final double recall;
    private final double fmeasure;

    public ResultStats(String name, double precision, double recall, double fmeasure) {
        this.name = name;
        this.precision = precision;
        this.recall = recall;
        this.fmeasure = fmeasure;
    }

    @Override
    public String toString() {
        return "ResultStats{" +
                "name='" + name + '\'' +
                ", precision=" + precision +
                ", recall=" + recall +
                ", fmeasure=" + fmeasure +
                '}';
    }
}
