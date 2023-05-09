import ru.tinkoff.edu.LinkParser;

import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.Parser;

import static org.junit.jupiter.api.Assertions.*;


public class LPTest {

    /*@Test
    void performValidTests() {
        testInvalidLinks();
        testValidLinks();
    }
    */

    @Test
    void testValidLinks() {
        String[] links = new String[] {"https://github.com/sharpestghost/TWP/pull/3",
                "https://github.com/williamfiset/Algorithms",
                "https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value"};
        for (String link: links) {
            assertTrue(Parser.isValid(link));
        }
    }

    @Test
    void testInvalidLinks() {
        String[] links = new String[] {"https://github.com/firstInvalidLink",
                "https://github.com/secondInvalidLink",
                "https://stackoverflow.com/question/40480/is-java-pass-by-reference-or-pass-by-value"};
        for (String link: links) {
            assertFalse(Parser.isValid(link));
        }
    }
}
