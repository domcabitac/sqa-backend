import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class OldItemFileReader {
    public FileInputStream oldItemReader;
    public Vector<String> oldItemBuffer = new Vector<String>();
    public String itemsFileName;

    public Vector<String> readItemFile(String itemsFileName) {
        try { // create FileInputStream object
            oldItemReader = new FileInputStream(itemsFileName);
            Scanner input = new Scanner(oldItemReader);
            // Reads up to certain bytes of oldItemBuffer from this input stream into an
            // array of bytes.
            oldItemReader.read();
            while (input.hasNextLine()) {
                oldItemBuffer.add(input.nextLine());
            }
            System.out.println(oldItemBuffer);
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        }
        finally { // close the streams using close method
            try {
                if (oldItemReader != null) {
                    oldItemReader.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }
        return oldItemBuffer;
    }

    /*public static void main(String argsv[]) throws IOException {
        OldItemFileReader t = new OldItemFileReader();
        t.readItemFile("available-items.txt");
        
    }*/
}
