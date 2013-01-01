package desi.tools.utility.bill;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class TestPattern {

    @Test
    public void run() {
        run1();
        run2();
        run3();
        run4();
        run5();
        run6();
        run7();
    }

    private void run7() {
        Pattern pattern = Pattern.compile("[0-9]{1,3}.[0-9]{2}");
        result(pattern, "0.00", true);
        result(pattern, "0.10", true);
        result(pattern, "1.10", true);
        result(pattern, "11.10", true);
        result(pattern, "111.10", true);

    }

    private void run6() {
        Pattern pattern = Pattern.compile("[0-9]{1,3}");
        result(pattern, "0", true);
        result(pattern, "1", true);
        result(pattern, "10", true);
        result(pattern, "100", true);
        result(pattern, "10000", false);
    }

    private void run5() {
        Pattern pattern = Pattern.compile("[APO]");
        result(pattern, "A", true);
        result(pattern, "P", true);
        result(pattern, "O", true);
        result(pattern, "OO", false);
    }

    private void run4() {
        Pattern pattern = Pattern.compile("[0-9]{10,12}");
        result(pattern, "7654328765", true);
        result(pattern, "917654328765", true);
        result(pattern, "9176543287651", false);
        result(pattern, "765432876", false);
    }

    private void run3() {
        Pattern pattern = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}");
        result(pattern, "00:00:00", true);
        result(pattern, "0:0:00", false);
    }

    private void run2() {
        Pattern pattern = Pattern.compile("[0-9]{2}-[a-zA-Z]{3}");
        result(pattern, "02-Aug", true);
        result(pattern, "35-Aug", true);
        result(pattern, "05-Jul", true);

    }

    private void run1() {
        Pattern pattern = Pattern.compile("[0-9]{1,3}");
        result(pattern, "1", true);
        result(pattern, "12", true);
        result(pattern, "103", true);
    }

    public void result(Pattern p, String input, boolean expected) {
        boolean matches = p.matcher(input).matches();
        Assert.assertEquals(input + " follows pattern " + p + "----> " + matches, expected, matches);
    }
}
