package org.dictionaryService.able;

import java.util.List;

public interface IDictionaryService {

    boolean check(String word);
    List<String> GetWordsBeginWith(String word);
    String getLanguage();
}
