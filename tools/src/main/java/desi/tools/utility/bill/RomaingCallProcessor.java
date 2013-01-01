package desi.tools.utility.bill;

import desi.tools.utility.pdf.TextBlockProvider;

public class RomaingCallProcessor extends CallProcessor {

    protected void setBoundByPatterns() {
        provider.setCurrentByPattern("Roaming Call Details");
        provider.setEndLimitByPattern("Total");
        provider.setEndLimitByPatternAfterEnd("Total");
    }

    @Override
    protected RomaingCallLog createCallIf(TextBlockProvider provider) {
        return RomaingCallLog.createIf(provider);
    }

}
