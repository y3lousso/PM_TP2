package org.spellingService;

import org.dictionaryService.IDictionaryService;

import java.util.ArrayList;
import java.util.List;

public class SpellingService implements ISpellingService {

    private IDictionaryService service;
    private List<Callback> callbacks = new ArrayList<>();

    public SpellingService(IDictionaryService service) {
        this.service = service;
    }

    @Override
    public void setDictionaryService(IDictionaryService service) {
        this.service = service;
    }

    @Override
    public IDictionaryService getDictionaryService() {
        return service;
    }

    @Override
    public void checkWord(String word) {
        if (service == null) {
            for (Callback callback : callbacks) {
                callback.callServiceUnavailable();
            }
        }
        else if (service.check(word)) {
            for (Callback callback : callbacks) {
                callback.callGoodSpelling();
            }
        }
        else {
            List<String> suggestions = service.GetWordsBeginWith(word);
            for (Callback callback : callbacks) {
                callback.callBadSpelling(suggestions);
            }
        }
    }

    @Override
    public void addCallback(Callback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeCallback(Callback callback) {
        callbacks.add(callback);
    }
}
