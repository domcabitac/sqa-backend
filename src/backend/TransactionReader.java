/*
    The purpose of this file is to read the old daily-transactions.txt file and place them
    into a buffer vector for overriding
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import java.io.*;

public class TransactionReader {
    public StringBuilder transactionReader;
    public Vector<String> transactionBuffer = new Vector<String>();
    public String transactionFileName;

    // Constructor for the class setTransactionBuffer
    public void setTransactionBuffer(Vector<String> transactionBuffer) {
        this.transactionBuffer = transactionBuffer;
    }

    public Vector<String> getTransactionBuffer() {
        return transactionBuffer;
    }

    // this class reads the content from the .txt file and places it into a buffer vector
    public Vector<String> readMergedTransaction(String transactionFileName) {
        // transactionReader defining new StringBuilder with the transactionFileName
        transactionReader = new StringBuilder(transactionFileName);
        
        // BufferedReader opens the file from the path given in transactionFileName
        try (BufferedReader br = Files.newBufferedReader(Paths.get(transactionFileName))) {
            String line;

            // br reading line by line in transactionFileName. While its not null, each
            // line is getting added to the transactionBuffer Vector
            while ((line = br.readLine()) != null) {
                transactionBuffer.add(line);
            }
            br.close();

        // catch if transactionFileName is not found
        } catch (IOException e) {
            System.out.println("File not found" + e);
        }
        // Test Code
        // System.out.println(transactionBuffer);
        return transactionBuffer; 
    }

    // Test code
    // public static void main(String argsv[]) throws IOException {
    //     TransactionReader t = new TransactionReader();
    //     t.readMergedTransaction("daily-transactions.txt");
    // }
}
