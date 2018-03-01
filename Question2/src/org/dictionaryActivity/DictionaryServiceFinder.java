package org.dictionaryActivity;

import org.dictionaryService.able.IDictionaryService;

/** An interface to find all the DictionaryService installed and started
 */
public interface DictionaryServiceFinder {

    IDictionaryService[] findAllDictionaryServices();
}
