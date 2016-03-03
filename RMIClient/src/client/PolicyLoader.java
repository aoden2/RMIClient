package client;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class PolicyLoader {

    public static final String POLICY_FILE_NAME = "/policy.policy";

    public static String getLocationOfPolicyFile() {
        try {
        	//create a temp file that have prefix rmi- and .policy extension
            File tempFile = File.createTempFile("rmi-", ".policy");
            // load source policy file
            InputStream is = PolicyLoader.class.getResourceAsStream(POLICY_FILE_NAME);
            // prepare outout stream to write to temp file
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            // copy cotent of source policy to temp file
            int read = 0;
            while((read = is.read()) != -1) {
                writer.write(read);
            }
            writer.close();
            // delete temfile when program exits
            tempFile.deleteOnExit();
            return tempFile.getAbsolutePath();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}