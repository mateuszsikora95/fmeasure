public class EngineStats {

    private int tn = 0;
    private int tp = 0;
    private int fp = 0;
    private int fn = 0;

    public EngineStats(int tn, int tp, int fp, int fn) {
        this.tn = tn;
        this.tp = tp;
        this.fp = fp;
        this.fn = fn;
    }

    public int getTn() {
        return tn;
    }

    public void setTn(int tn) {
        this.tn = tn;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public int getFp() {
        return fp;
    }

    public void setFp(int fp) {
        this.fp = fp;
    }

    public int getFn() {
        return fn;
    }

    public void setFn(int fn) {
        this.fn = fn;
    }
}
