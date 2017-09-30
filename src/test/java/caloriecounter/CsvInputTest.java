package caloriecounter;

import caloriecounter.input.CsvInput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CsvInputTest {
    private static final int DEFAULT_TIMEOUT = 2000;

    @Test(timeout = DEFAULT_TIMEOUT )
    public void testGetContents(){
        String mockLine = "300,200,100";
        InputStream in = System.in;
        try {
            System.setIn(new ByteArrayInputStream((mockLine+"\r\n").getBytes()));
            Scanner mockScanner = new Scanner(System.in);
            CsvInput input = new CsvInput(mockScanner);
            assertEquals("returns file contents",
                    mockLine,input.getContents().iterator().next());
        } finally {
            System.setIn(in);
        }
    }
}
