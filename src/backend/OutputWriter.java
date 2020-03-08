/* The purpose of this program is to perform the backend functions for the online auction house experience. The intentions of this program are to take in 
* 3 input files: MergedTransactions, OldUsers.txt and OldItems.txt and process the merged transactions one by one on the corresponding file that will be 
* determined by a method. After running all transactions, the program will return the updated files associated with an error log for any transaction or 
* file failures. The program is intended to be run by compiling this java file, OutputWriter.java, and running it with java. 
* The program will later be integrated with the frontend to update the files concurrently. 
*/
import java.io.*; 
import java.util.*; 
import java.io.File;  
import java.io.IOException;  

/*  The intention of this class is to take in all of the stored buffers (transaction, old item and old users) and outputs 
        them into their respective output file, */
public class OutputWriter {
    // public fields
    // public TransactionReader tReader = new TransactionReader(); 
    // public OldItemFileReader uReader = new OldItemFileReader();
    // public Error error = new Error();  
    public Vector<String> oldItemBuffer = new Vector<String>();
    public Vector<String> oldUserBuffer = new Vector<String>();
    public Vector<String> transactionsBuffer = new Vector<String>();
    public Vector<String> newUserBuffer = new Vector<String>();
    public Vector<String> newItemBuffer = new Vector<String>();
    public String transactionType;
    public String OutputFilepath;

    // OutputWriter class method to set transactionsBuffer, which will be used to update item and user files
    public void setMergedTransactions(Vector<String> transactionsBuffer) {
        this.transactionsBuffer = transactionsBuffer;
    }
    
    // OutputWriter class method to set oldItemBuffer, which will be used as a base to write new item entries
    public void setOldItems(Vector<String> oldItemBuffer) {
        this.oldItemBuffer = oldItemBuffer;
    } 
    
    // OutputWriter class method to set oldUserBuffer, which will be used as a base to write new user entries
    public void setOldUser(Vector<String> oldUserBuffer) {
        this.oldUserBuffer = oldUserBuffer;
    } 

    // OutputWriter class method to update users txt file, which will use oldUserBuffer, transactionBuffer and OutputFilepath
    public void writeNewUsers(Vector<String> oldUserBuffer, Vector<String> transactionsBuffer, String OutputFilepath) {
        // TODO: Using the old user buffer, perform transactions from transactionBuffer marked for users, either create, delete or edit a user 
    }
    
    // OutputWriter class method to update items txt file, which will use oldItemBuffer, transactionBuffer and OutputFilepath
    public void writeNewItems(Vector<String> oldItemBuffer, Vector<String> transactionsBuffer, String OutputFilepath) {
        // TODO: Using the old item buffer, perform transactions from transactionBuffer marked for items, either create or edit a bid. 
        // Also decrement all auction days in the file by 1
    }

    /* OutputWriter class method to determine which file to update. It will use currentTransaction, oldUserBuffer, oldItemBuffer, transactionsBuffer 
        and OutputFilepath to update the corresponding txt file */
    public void determineTransactionType(String currentTransaction, Vector<String> oldUserBuffer, Vector<String> oldItemBuffer, Vector<String> 
        transactionsBuffer, String OutputFilepath, Error error) {
        // TODO: takes in the current transaction from the buffer, and determines if its user or items file affected
        /*if ((currentTransaction.charAt(1) > 3 && currentTransaction.charAt(1) < 7 || currentTransaction.charAt(1) > 0 && currentTransaction.charAt(1) < 3) 
            && currentTransaction.charAt(0) == 0) {
            writeNewUsers(oldUserBuffer, transactionsBuffer, OutputFilepath);
        } if ((currentTransaction.charAt(1) > 2 && currentTransaction.charAt(1) < 5) 
        && currentTransaction.charAt(0) == 0) {
            writeNewItems(oldBuffer);
        } else {
            if (currentTransaction.charAt(0) == 0 && currentTransaction.charAt(1) == 1) {
                String newName = currentTransaction.charAt(2) + currentTransaction.substring(3, 18);
                Iterator itr = oldUserBuffer.iterator(); 

                // checking the next element availabilty 
                while (itr.hasNext()) {
                    if (itr.substring(3, 18) == newName) {
                        error.setErrorType("constraint");
                        error.setTransaction(currentTransaction.substring(0,1));
                        error.setErrorReason("user already existing");
                        error.setErrorMsg("ERROR: " + error.errorType + " type error," + error.transaction + " transaction has failed due to " + error.errorReason);
                        logInformation(OutputFilepath, error);
                        break;
                    } 
                    error.setErrorType("general");
                    error.setTransaction(currentTransaction.substring(0, 1));
                    error.setErrorReason("general error in transaction");
                    error.setErrorMsg("ERROR: " + error.errorType + " type error," + error.transaction + " transaction has failed due to " + error.errorReason);
                    logInformation(OutputFilepath, error);
                }
            } else if (currentTransaction.charAt(0) == 0 && currentTransaction.charAt(1) == 3 && (currentTransaction.substring(45, 47)) > 0) {
                error.setErrorType("constraint");
                error.setTransaction(itr.substring(0,1));
                error.setErrorReason("Auction days should never be negative");
                error.setErrorMsg("ERROR: " + error.errorType + " type error," + error.transaction + " transaction has failed due to " + error.errorReason);
                logInformation(OutputFilepath, error);      
            }
        }*/
    } 
    
    // OutputWriter class method to log errors to a file, which will use currentTransaction, OutputFilepath and the Error class
    public void logInformation(String OutputFilepath, Error error) {
        // TODO: if an error has occurred, log the information to a file and print it out to a user
        /*File oFile = new File("log.txt");
        oFile.createNewFile(); // if file already exists will do nothing 
        FileOutputStream oFile = new FileOutputStream(oFile, false);
        oFile.write(error.errorMsg + "\n");
        System.out.println(error.errorMsg);*/
    } 

    // main function to execute the auction service backend
    public static void main(String argsv[]) {
        System.out.println("Backend works!");
    }
}