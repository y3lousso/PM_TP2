package dictionaryService;

import java.util.List;

public interface IDictionaryService {

    boolean check(String word);
    List<String> GetWordsBeginWith(String word);
}
