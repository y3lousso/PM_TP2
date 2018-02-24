package dictionaryService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryService implements IDictionaryService {

    private List<String> words;

    public DictionaryService(String path) throws IOException, URISyntaxException {
        words = loadFromFile(path);
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


    private List<String> loadFromFile(String path) throws IOException, URISyntaxException {
        URI uri = this.getClass().getResource(path).toURI();
        return Files.readAllLines(Paths.get(uri));
    }
}