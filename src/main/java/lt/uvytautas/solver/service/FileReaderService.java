package lt.uvytautas.solver.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderService {
    public List<String> parseFileIntoLines(String filePath) throws URISyntaxException, IOException {
        URL res = getClass().getClassLoader().getResource(filePath);
        if (res == null) {
            throw new IllegalArgumentException("File " + filePath + " not found");
        }
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        return Files.readAllLines(Paths.get(absolutePath));
    }
}
