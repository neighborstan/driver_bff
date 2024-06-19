package tech.saas.adaptertms;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestUtils {
    /**
     * @param path path of the test file
     * @return json object as string
     * @throws IOException
     */
    public static String getJsonTestObjectAsString(String path) throws IOException {
        File file = new ClassPathResource(path).getFile();
        return Files.readString(file.toPath());
    }

    private TestUtils() {
    }
}

