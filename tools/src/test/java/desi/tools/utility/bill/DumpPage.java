package desi.tools.utility.bill;

import java.io.PrintStream;


import de.intarsys.pdf.content.common.CSPrettyPrinter;
import desi.tools.utility.pdf.AbstractPDFDocumentSupport;
import desi.tools.utility.pdf.PageWrapper;

public class DumpPage extends AbstractPDFDocumentSupport {

    private PrintStream out;

    public DumpPage() {
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    public static void main(String[] args) {
        try {
            DumpPage doc = new DumpPage();
            doc.open(args[0], args[1]);

            doc.setOut(new PrintStream(args[2]));
            doc.doDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDemo() throws Exception {
        out.println("Processing: " + document.getName());
        PageWrapper wrapper = getPage(3);
        CSPrettyPrinter printer = new CSPrettyPrinter();
        printer.setCreateComment(true);
        printer.setCreateIndent(true);
        printer.setCreateLineSeparator(true);

        printer.print(wrapper.getPageContent());
        String value = printer.getValue();

        out.println(value);

    }
}
