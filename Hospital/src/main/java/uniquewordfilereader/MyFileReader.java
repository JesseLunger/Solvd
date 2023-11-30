package uniquewordfilereader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;

public class MyFileReader {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private File file;

    public MyFileReader(String fileName) throws IOException {
        URL resource = MethodHandles.lookup().lookupClass().getResource("../" + fileName);
        try {
            file = new File(resource.toURI());
        } catch (URISyntaxException | NullPointerException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void writeUniqueWordsToFile() {
        if (file == null) {
            LOGGER.error("MyFileReader has no allocated file");
            return;
        }
        HashSet<String> hashSet = new HashSet<>();
        try {
            String fileContent = FileUtils.readFileToString(file, "UTF-8").toLowerCase();
            fileContent = fileContent.replaceAll("[^a-z\\s]", "");
            String[] words = StringUtils.split(fileContent);
            Collections.addAll(hashSet, words);
            FileUtils.write(file, "Unique Words: " + hashSet.size(), true); //writes to target file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
