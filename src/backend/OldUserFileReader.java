/*
    The purpose of this file is to read the old current-user-accounts.txt file and place them
    into a buffer vector for overriding
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import java.io.*;

public class OldUserFileReader {
    public StringBuilder oldUserReader;
    public Vector<String> oldUserBuffer = new Vector<String>();
    public String usersFileName;

    // Constructor for the class setUserBuffer
    public void setUserBuffer(Vector<String> oldUserBuffer) {
        this.oldUserBuffer = oldUserBuffer;
    }

    public Vector<String> getUserBuffer() {
        return oldUserBuffer;
    }

    // this class reads the content from the .txt file and places it into a buffer vector
    public Vector<String> readUserFile(String usersFileName) {
        // oldUserReader defining new StringBuilder with the userFileName
        oldUserReader = new StringBuilder(usersFileName);
        
        // BufferedReader opens the file from the path given in usersFileName
        try (BufferedReader br = Files.newBufferedReader(Paths.get(usersFileName))) {
            String line;

            // br reading line by line in usersFileName. While its not null, each
            // line is getting added to the oldUserBuffer Vector
            while ((line = br.readLine()) != null) {
                oldUserBuffer.add(line);
            }
            br.close();

        // catch if usersFileName is not found
        } catch (IOException e) {
            System.out.println("File not found" + e);
        }
        // Test Code
        // System.out.println(oldUserBuffer);
        return oldUserBuffer; 
    }

    // Test Code
    // public static void main(String argsv[]) throws IOException {
    //     OldUserFileReader t = new OldUserFileReader();
    //     t.readUserFile("current-user-accounts.txt");
    // }
}
