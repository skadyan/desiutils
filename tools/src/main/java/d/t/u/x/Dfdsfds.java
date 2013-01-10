/**
 *
 */
package d.t.u.x;

import de.intarsys.pdf.content.CSContent;
import de.intarsys.pdf.content.CSOperation;
import de.intarsys.pdf.pd.PDPage;

/**
 * @author kadyans
 * 
 */
public class Dfdsfds {

    private PDPage page;

    private CSContent pageContent;

    private CSOperation[] operations;

    private Dfdsfds(PDPage page) {
        this.page = page;
        this.pageContent = page.getContentStream();
        this.operations = pageContent.getOperations();

    }

    public static Dfdsfds wrap(PDPage page) {
        Dfdsfds wrapper = new Dfdsfds(page);

        return wrapper;
    }

    public CSOperation[] getOperations() {
        return operations;
    }

    public CSContent getPageContent() {
        return pageContent;
    }

    public PDPage getPage() {
        return page;
    }

    public void flushChanges() {
        CSContent newPageContent = CSContent.createNew();

        for (int i = 0; i < operations.length; i++) {
            CSOperation op = operations[i];
            if (op != null)
                newPageContent.addOperation(op);
        }
        page.setContentStream(newPageContent);
    }
}
