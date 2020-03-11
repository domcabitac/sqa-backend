import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class TransactionReader {
    public FileInputStream transactionReader;
    public Vector<String> transactionBuffer = new Vector<String>();
    public String transactionFileName;

    public void setTransactionBuffer(Vector<String> transactionBuffer) {
        this.transactionBuffer = transactionBuffer;
    }

    public Vector<String> getTransactionBuffer() {
        return transactionBuffer;
    }

    public Vector<String> readMergedTransaction(String transactionFileName) {
        FileInputStream fin = null;
        try { // create FileInputStream object
            transactionReader = new FileInputStream(transactionFileName);
            Scanner input = new Scanner(transactionReader);
            // Reads up to certain bytes of transactionBuffer from this input stream into an
            // array of bytes.
            transactionReader.read();
            while (input.hasNextLine()) {
                transactionBuffer.add(input.nextLine());
            }
            System.out.println(transactionBuffer);
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
        return transactionBuffer;
    }

    public static void main(String argsv[]) throws IOException {
        TransactionReader t = new TransactionReader();
        t.readMergedTransaction("daily-transactions.txt");
    }
}
