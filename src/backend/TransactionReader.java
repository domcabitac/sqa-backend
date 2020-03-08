import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TransactionReader {
    public static void main(String argsv[]) throws IOException {
        File file = new File("available-items.txt");
        FileInputStream fin = null;
        try { // create FileInputStream object
            fin = new FileInputStream(file);
            byte transactionBuffer[] = new byte[(int)file.length()];
                    
            // Reads up to certain bytes of data from this input stream into an array of bytes.
            fin.read(transactionBuffer);
            //create string from byte array
            String s = new String(transactionBuffer);
            List<String> data = new Vector<String>();
            data.add(s);
            System.out.println(data);
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        }
        finally { // close the streams using close method
            try {
                if (fin != null) {
                    fin.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }
    }
}
