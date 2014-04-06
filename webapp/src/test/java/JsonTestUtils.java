import db.util.ClasspathUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by vitaly on 4/6/14.
 */
public final class JsonTestUtils {
    public static String readExpectedJsonAnswer(String fileName) throws IOException {
        return FileUtils.readFileToString(new File(ClasspathUtil.getFilePath("apiAnswers/" + fileName)));
    }
}
