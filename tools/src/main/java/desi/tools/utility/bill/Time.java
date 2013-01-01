package desi.tools.utility.bill;

public class Time implements Comparable<Time> {
    private int HH;
    private int MM;
    private String raw;

    public Time(String raw) {
        this.raw = raw;
        String[] parts = raw.split(":");
        HH = parseUnit(parts[0]);
        MM = parseUnit(parts[1]);
    }

    private int parseUnit(String in) {
        int unit = 0;

        if (in.charAt(0) == '0')
            unit = Integer.parseInt(in.substring(1));
        else
            unit = Integer.parseInt(in);

        return unit;
    }

    @Override
    public int compareTo(Time o) {
        int r = HH - o.HH;

        return r == 0 ? MM - o.MM : r;
    }

    @Override
    public String toString() {
        return raw;
    }

    public static Time parse(String raw) {
        return new Time(raw.trim());
    }
}
