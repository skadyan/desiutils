package desi.tools.utility.pdf;

import de.intarsys.pdf.content.CSOperation;
import de.intarsys.pdf.content.CSOperator;

public class CSUtil {

    public static int findOp(CSOperation[] ops, int offset, CSOperator opr) {
        int index = -1;
        for (int i = offset; i < ops.length; i++) {
            CSOperation op = ops[i];
            if (op.matchesOperator(opr)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String dumpPageContent(AbstractPDFDocumentSupport pdf, int pageNo) {
        PageWrapper pageWrapper = pdf.getPage(pageNo);
        CSPrettyPrinter printer = new CSPrettyPrinter();
        printer.setCreateComment(true);
        printer.setCreateIndent(true);
        printer.setCreateLineSeparator(true);

        printer.print(pageWrapper.getPageContent());

        return printer.getValue();
    }
}
