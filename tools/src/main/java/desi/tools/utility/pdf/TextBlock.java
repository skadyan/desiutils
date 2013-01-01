package desi.tools.utility.pdf;

import de.intarsys.pdf.content.CSOperation;
import de.intarsys.pdf.content.CSOperators;
import de.intarsys.pdf.cos.COSObject;
import de.intarsys.pdf.cos.COSString;

public class TextBlock {

    private CSOperation[] ops;
    private int startOffset;
    private int endOffset;
    private int operationCount;

    public TextBlock(CSOperation[] ops, int st, int end) {
        this.ops = ops;
        this.startOffset = st;
        this.endOffset = end;
        this.operationCount = end - st + 1;
    }

    public int getEndOffset() {
        return endOffset;
    }

    public int getOperationCount() {
        return operationCount;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public String getText() {
        for (int i = endOffset; i > 0; i--) {
            CSOperation op = ops[i];
            if (op.matchesOperator(CSOperators.CSO_Tj)) {
                COSObject operand = op.getOperand(0);

                return operand.stringValue();
            }
        }
        return null;
    }

    public void setText(String newText) {
        String oldtext = getText();
        if (!oldtext.equals(newText)) {
            for (int i = endOffset; i > startOffset; i--) {
                CSOperation op = ops[i];
                if (op.matchesOperator(CSOperators.CSO_Tj)) {
                    // COSString operand = (COSString) op.getOperand(0);
                    op.setOperand(0, COSString.create(newText));
                    if (oldtext.length() != newText.length()) {

                        // need to update the position
                        // D.trace(" Changed " + oldtext + " --> " + newText);
                    }

                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return getText();
    }

    public static TextBlock createIf(CSOperation[] ops, int offset) {
        TextBlock block = null;
        int st = -1;
        int end = -1;

        st = CSUtil.findOp(ops, offset, CSOperators.CSO_BT);
        if (st > 0)
            end = CSUtil.findOp(ops, st, CSOperators.CSO_ET);

        if (end > 0)
            block = new TextBlock(ops, st, end);

        return block;
    }

    public void copyFrom(TextBlock src) {
        String text = src.getText();

        setText(text);
        // how to update the position

    }

    public void delete() {
        for (int i = startOffset; i <= endOffset; i++) {
            ops[i] = null;
        }
    }
}
