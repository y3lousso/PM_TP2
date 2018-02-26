package org.spellingService;

import org.dictionaryService.IDictionaryService;

public interface ISpellingService {

    void setDictionaryService(IDictionaryService service);
    IDictionaryService getDictionaryService();

    void checkWord(String word);

    void addCallback(Callback callback);
    void removeCallback(Callback callback);
}
