package d.t.u.b;

public class Range<T extends Comparable<T>> {
    private T lower;
    private T upper;

    public Range(T lower, T upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public boolean in(T target) {
        boolean result = false;
        int cmp = lower.compareTo(target);
        if (cmp <= 0) {
            cmp = upper.compareTo(target);
            if (cmp >= 0)
                result = true;
        }

        return result;
    }
}
