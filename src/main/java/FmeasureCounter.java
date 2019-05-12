public class FmeasureCounter {
    public double countFmeasure(double precision, double recall) {
        return 2 * ((precision * recall) / (precision + recall));
    }
}
