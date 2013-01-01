package desi.tools.utility.pdf;

import de.intarsys.pdf.content.CSContent;
import de.intarsys.pdf.content.CSOperation;
import de.intarsys.pdf.content.CSOperators;

/**
 * A simple pretty printer for content streams.
 * 
 */
public class CSPrettyPrinter {
    private static final int COL_WIDTH = 50;

    /**
     * The string buffer containing the current serialized rendering program
     */
    private StringBuilder sb;

    private StringBuilder lb;

    private boolean createComment = false;

    private boolean createLineSeparator = true;

    private boolean createIndent = true;

    /**
     * The line separator to use
     */
    private String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$

    /**
     * The current indent. The pretty printer indents the program for each save/restore graphic
     * state operation pair.
     */
    private int indent = 0;

    public CSPrettyPrinter() {
        super();
    }

    public String getValue() {
        return sb.toString();
    }

    public void print(CSContent content) {
        sb = new StringBuilder();
        lb = new StringBuilder();
        int len = content.size();
        for (int i = 0; i < len; i++) {
            CSOperation operation = content.getOperation(i);
            printOperation(i, operation);
        }
    }

    protected void printOperation(int index, CSOperation operation) {
        lb.setLength(0);

        if (operation.matchesOperator(CSOperators.CSO_Q)) {
            indent--;
        }
        if (isCreateIndent()) {
            for (int i = 0; i < indent; i++) {
                lb.append("   "); //$NON-NLS-1$
            }
        }
        String opString = operation.toString();
        lb.append(opString);
        if (isCreateComment()) {
            for (int i = lb.length(); i < COL_WIDTH; i++) {
                lb.append(" "); //$NON-NLS-1$
            }
            lb.append("% "); //$NON-NLS-1$
            lb.append(index).append(' ');
            String opDescription = CSOperators.getDescription(operation.getOperator());
            lb.append(opDescription);
        }
        if (isCreateLineSeparator() || isCreateComment() || isCreateIndent()) {
            lb.append(lineSeparator);
        } else {
            lb.append(" "); //$NON-NLS-1$
        }
        if (operation.matchesOperator(CSOperators.CSO_q)) {
            indent++;
        }
        sb.append(lb);
    }

    public boolean isCreateComment() {
        return createComment;
    }

    public void setCreateComment(boolean createComment) {
        this.createComment = createComment;
    }

    public boolean isCreateLineSeparator() {
        return createLineSeparator;
    }

    public void setCreateLineSeparator(boolean separateLines) {
        this.createLineSeparator = separateLines;
    }

    public boolean isCreateIndent() {
        return createIndent;
    }

    public void setCreateIndent(boolean createIndent) {
        this.createIndent = createIndent;
    }
}
