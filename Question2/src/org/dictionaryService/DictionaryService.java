package org.dictionaryService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryService implements IDictionaryService {

    private List<String> words;
    private String language;

    public DictionaryService(String path, String language) throws IOException {
        this.words = loadFromFile(path);
        this.language = language;
    }

    @Override
    public boolean check(String word) {
        return words != null && words.contains(word);
    }

    @Override
    public List<String> GetWordsBeginWith(String word) {
        if (words == null) {
            return new ArrayList<>();
        }
        else {
            return words.stream().filter(w -> w.startsWith(word)).collect(Collectors.toList());
        }
    }

    @Override
    public String getLanguage() {
        return language;
    }


    private List<String> loadFromFile(String path) throws IOException {
        List<String> words =  new ArrayList<>();

        // Load dictionary as InputStream :
        InputStream is = this.getClass().getResourceAsStream(path);
        File file = new File(path);

        // Create virtual file :
        Files.copy(
                is,
                file.toPath(),
                StandardCopyOption.REPLACE_EXISTING);

        // Read all the words :
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
            br.close();
        }
        return words;
    }
}