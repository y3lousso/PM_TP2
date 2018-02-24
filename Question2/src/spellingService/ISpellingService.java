package spellingService;

import dictionaryService.IDictionaryService;

public interface ISpellingService {

    void setDictionaryService(IDictionaryService service);

    void checkWord(String word);

    void addCallback(Callback callback);
    void removeCallback(Callback callback);
}
