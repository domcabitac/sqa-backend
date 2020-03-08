import java.io.*; 
import java.util.*; 

public class OutputWriter {
    //public TransactionReader tReader = new TransactionReader(); 
    //public OldItemFileReader uReader = new OldItemFileReader();  
    public Vector<String> oldItemBuffer = new Vector<String>();
    public Vector<String> oldUserBuffer = new Vector<String>();
    public Vector<String> transactionsBuffer = new Vector<String>();
    public Vector<String> newUserBuffer = new Vector<String>();
    public Vector<String> newItemBuffer = new Vector<String>();
    public Vector<String> errorMessages = new Vector<String>();
    public String transactionType;
    public String OutputFilepath;

    public void setMergedTransactions(Vector<String> transactionBuffer) {
        this.transactionBuffer = transactionBuffer;
    }
    
    public void setOldItems(Vector<String> oldItemBuffer) {
        this.oldItemBuffer = oldItemBuffer;
    } 
    
    public void setOldUser(Vector<String> oldUserBuffer) {
        this.oldUserBuffer = oldUserBuffer;
    } 
    public void writeNewUsers(Vector<String> oldUserBuffer, Vector<String> transactionsBuffer, String OutputFilepath) {
        // TODO: Using the old user buffer, perform transactions from transactionBuffer marked for users, either create, delete or edit a user 
    }
    
    public void writeNewItems(Vector<String> oldItemBuffer, Vector<String> transactionBuffer, String OutputFilepath) {
        // TODO: Using the old item buffer, perform transactions from transactionBuffer marked for items, either create or edit a bid. 
        // Also decrement all auction days in the file by 1
    }
    public String determineTransactionType(String currentTransaction) {
        // TODO: takes in the current transaction from the buffer, and determines if its user or items file affected
        return "Works";
    } 
    
    public String logInformation(String currentTransaction, String OutputFilepath, Vector<String> ErrorMessages) {
        // TODO: if an error has occurred, log the information to a file and print it out to a user
        return "Works";
    } 

    public static void main(String argsv[]) {
        System.out.println("Backend works!");
    }
}