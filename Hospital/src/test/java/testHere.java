import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


public class testHere {

    @Test
    public void testWorking(){
        assertTrue(true);
    }

    @Test
    public void testWorking2(){
        assertTrue(true);
    }

//    @Test
//    public void testMethodLimitCheck() throws Exception {
//        // Create a Checker instance
//        Checker checker = new Checker();
//        assertTrue(true);  // Placeholder assertion, replace with your actual test logic
//
//
//        try {
//            // Set the class loader
//            checker.setModuleClassLoader(getClass().getClassLoader());
//
//            // Load the Checkstyle configuration
//            Configuration configuration = new DefaultConfiguration("checkstyle.xml");
//            checker.configure(configuration);
//            List<File> files = new ArrayList<>();
//            files.add(new File("src/main/java/Main.java"));
//            // Process the file you want to check
//            checker.process(files);
//
//
//
//        } catch (CheckstyleException e) {
//            // Handle CheckstyleException
//            e.printStackTrace();
//        } finally {
//            // Destroy the Checker to release resources
//            checker.destroy();
//        }
//    }
}
