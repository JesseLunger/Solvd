package uniquewordfilereader;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class FileReader {

    private File file;

    public FileReader(String filePath) throws IOException{
        file = new File(filePath);

    }

    public void writeUniqueWordsToFile(){
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(null);
        try {
            String fileContent = FileUtils.readFileToString(file, "UTF-8").toLowerCase();
            fileContent = fileContent.replaceAll("[^a-z\\s]", "");
            String[] words = StringUtils.split(fileContent);
            for (String word: words){
                hashSet.add(word.equals("") ?  null : word); //more efficient than checking contains() and then add()
            }
            FileUtils.write(file, "Unique Words: " + Integer.toString(hashSet.size() - 1), true); // -1 for null
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
