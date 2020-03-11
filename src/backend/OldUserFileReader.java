import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class OldUserFileReader {
    public FileInputStream oldUserReader;
    public Vector<String> oldUserBuffer = new Vector<String>();
    public String usersFileName;

    public Vector<String> readUserFile(String usersFileName) {
        FileInputStream fin = null;
        try { // create FileInputStream object
            oldUserReader = new FileInputStream(usersFileName);
            Scanner input = new Scanner(oldUserReader);
            // Reads up to certain bytes of oldUserBuffer from this input stream into an
            // array of bytes.
            oldUserReader.read();
            while (input.hasNextLine()) {
                oldUserBuffer.add(input.nextLine());
            }
            System.out.println(oldUserBuffer);
            input.close();
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
        return oldUserBuffer;
    }

    /*public static void main(String argsv[]) throws IOException {
        OldUserFileReader t = new OldUserFileReader();
        t.readUserFile("current-user-accounts.txt");
    }*/
}
