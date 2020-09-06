package lt.uvytautas.solver.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderServiceTest {
    private final FileReaderService fileReaderService = new FileReaderService();

    @Test
    void parseFileIntoLinesTest() throws IOException, URISyntaxException {
        String fileName = "poker-test.txt";

        List<String> lines = fileReaderService.parseFileIntoLines(fileName);

        assertEquals(4, lines.size());
        assertEquals("7C 5H KC QH JD AS KH 4C AD 4S", lines.get(0));
        assertEquals("5H KS 9C 7D 9H 8D 3S 5D 5C AH", lines.get(1));
        assertEquals("6H 4H 5C 3H 2H 3S QH 5S 6S AS", lines.get(2));
        assertEquals("TD 8C 4H 7C TC KC 4C 3H 7S KS", lines.get(3));
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> fileReaderService.parseFileIntoLines("randomfilename"));
    }
}