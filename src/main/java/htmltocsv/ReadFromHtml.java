package htmltocsv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFromHtml {

    public String readHtml(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException ioException) {
           throw new IllegalStateException("Cannot read file:" +path,ioException);
        }
    }
}
