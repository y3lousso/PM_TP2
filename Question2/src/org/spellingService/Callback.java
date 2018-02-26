package org.spellingService;

import java.util.List;

public interface Callback {

    void callGoodSpelling();
    void callBadSpelling(List<String> suggestions);
    void callServiceUnavailable();

}
