package org.dictionaryService.able;

import java.util.List;

/** An interface for a service of Dictionary
 */
public interface IDictionaryService {

    /** Check a word in the dictionary
     *
     * @param word the word to check
     * @return if the word is correct, according to the dictionary
     */
    boolean check(String word);

    /** Get all the words starting with the given word
     *
     * @param word the prefix word to check
     * @return all the words in the dictionary starting with the given word
     */
    List<String> GetWordsBeginWith(String word);

    /** Get the language of the dictionary
     *
     * @return the language
     */
    String getLanguage();
}
