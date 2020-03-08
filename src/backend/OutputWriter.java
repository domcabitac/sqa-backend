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

    }
    
    public void setOldItems(Vector<String> oldItemBuffer) {

    } 
    
    public void setOldUser(Vector<String> oldUserBuffer) {

    } 
    public void writeNewUsers(Vector<String> oldUserBuffer, Vector<String> transactionsBuffer, String OutputFilepath) {

    }
    
    public void writeNewItems(Vector<String> oldItemBuffer, Vector<String> transactionBuffer, String OutputFilepath) {

    }
    public String determineTransactionType(String currentTransaction) {
        return "Works";
    } 
    
    public String logInformation(String currentTransaction, String OutputFilepath, Vector<String> ErrorMessages) {
        return "Works";
    } 

    public static void main(String argsv[]) {
        System.out.println("Backend works!");
    }
}