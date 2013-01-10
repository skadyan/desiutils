package d.t.u.b;

import java.io.IOException;
import java.io.PrintStream;


import d.t.u.x.Adnsdvnfdg;
import d.t.u.x.Dfdsfds;
import de.intarsys.pdf.content.common.CSPrettyPrinter;
import de.intarsys.pdf.parser.COSLoadException;

public class DocumentContentAnalyser extends Adnsdvnfdg {

    private String outfile;

    private int[] pageNos;

    public DocumentContentAnalyser(String file, String password) throws IOException, COSLoadException {
        open(file, password);
    }

    public static void main(String[] args) throws IOException, COSLoadException {
        DocumentContentAnalyser tool = new DocumentContentAnalyser(args[0], args[1]);

        tool.setOutfile(args[2]);

        tool.setPageNos(new int[] { 3 });
        tool.process();

    }

    private void setOutfile(String outfile) {
        this.outfile = outfile;
    }

    public void setPageNos(int[] pageNos) {
        this.pageNos = pageNos;
    }

    public int[] getPageNos() {
        return pageNos;
    }

    private void process() throws IOException {
        PrintStream out = new PrintStream(outfile);
        CSPrettyPrinter printer = new CSPrettyPrinter();
        printer.setCreateComment(true);
        printer.setCreateIndent(true);
        printer.setCreateLineSeparator(true);

        printPages(printer);
        String s = printer.getValue();

        out.println(s);
        out.close();

    }

    private void printPages(CSPrettyPrinter printer) {
        for (int pageNo : pageNos) {
            Dfdsfds wrapper = getPage(pageNo);
            if (wrapper != null) {
                printer.print(wrapper.getPageContent());
            }
        }
    }
}
