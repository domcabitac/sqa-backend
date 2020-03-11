/*
    The purpose of this file is to read the old avaliable-items.txt file and place them
    into a buffer vector for overriding
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import java.io.*;

public class OldItemFileReader {
    public StringBuilder oldItemReader;
    public Vector<String> oldItemBuffer = new Vector<String>();
    public String itemsFileName;

    // Constructor for the class setItemBuffer
    public void setItemBuffer(Vector<String> oldItemBuffer) {
        this.oldItemBuffer = oldItemBuffer;
    }

    public Vector<String> getItemBuffer() {
        return oldItemBuffer;
    }

    // this class reads the content from the .txt file and places it into a buffer vector
    public Vector<String> readItemFile(String itemsFileName) {
        // oldItemReader defining new StringBuilder with the itemFileName
        oldItemReader = new StringBuilder(itemsFileName);
        
        // BufferedReader opens the file from the path given in itemsfilename
        try (BufferedReader br = Files.newBufferedReader(Paths.get(itemsFileName))) {
            String line;

            // br reading line by line in itemsfilename. While its not null, each
            // line is getting added to the oldItemBuffer Vector
            while ((line = br.readLine()) != null) {
                oldItemBuffer.add(line);
            }
            br.close();

        // catch if itemsFileName is not found
        } catch (IOException e) {
            System.out.println("File not found" + e);
        }
        // Test Code
        // System.out.println(oldItemBuffer);
        return oldItemBuffer; 
    }
    // Test Code to see if line is adding into olditembuffer
    // public static void main(String argsv[]) throws IOException {
    //     OldItemFileReader t = new OldItemFileReader();
    //     t.readItemFile("available-items.txt");

    // }
}
