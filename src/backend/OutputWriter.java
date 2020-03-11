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
//import reader.TransactionReader;
//import reader.OldItemFileReader;
// import OldUserFileReader.java;

/*  The intention of this class is to take in all of the stored buffers (transaction, old item and old users) and outputs 
        them into their respective output file, */
public class OutputWriter {
    // public fields
    // public TransactionReader tReader = new TransactionReader();
    // public OldItemFileReader iReader = new OldItemFileReader(); 
    // public OldUserFileReader uReader = new OldUserFileReader();
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
    public void bufferNewUsers(Vector<String> oldUserBuffer, String currentTransaction) {
        // TODO: Using the old user buffer, perform transactions from transactionBuffer marked for users, either create, delete or edit a user 

    }
    
    // OutputWriter class method to update items txt file, which will use oldItemBuffer, transactionBuffer and OutputFilepath
    public void bufferNewItems(Vector<String> oldItemBuffer, String currentTransaction) {
        System.out.println("buffering item...\n");
        if (currentTransaction.substring(0,2) == "03") {
            String newEntry = currentTransaction.substring(3,currentTransaction.length());
            oldItemBuffer.add(newEntry);
        } else if (currentTransaction.substring(0,2) == "04") {
            for (int i = 0; i < oldItemBuffer.size(); i++) {
                System.out.println("buffer1: " + oldItemBuffer.get(i).substring(0,25));
                System.out.println("buffer2: " + currentTransaction.substring(3,28));
                if (oldItemBuffer.get(i).substring(0,24) == currentTransaction.substring(3,28)) {
                    // Creating new item here with applied transaction
                    String currentItem = oldItemBuffer.get(i).substring(0,46);
                    String newBidder = currentTransaction.substring(46,61);
                    String newBid = currentTransaction.substring(61,67);
                    String newItem = currentItem + newBidder + newBid;

                    System.out.println("New item: " + newItem);

                    // replacing old item with new item
                    oldItemBuffer.set(i, newItem);
                    break;
                }
            }
        } else {
            System.out.println("Invalid transaction");
        }
        // TODO: Using the old item buffer, perform transactions from transactionBuffer marked for items, either create or edit a bid. 
        // Also decrement all auction days in the file by 1
    }
    
    // TODO: Writes the final changed user buffer to usersFileName path
    public void writeNewUsers(Vector<String> oldUserBuffer, String usersFileName) {
        System.out.println("Writing new users...");
    }

    // TODO: Writes the final changed item buffer to itemsFileName path
    public void writeNewItems(Vector<String> oldItemsBuffer, String itemsFileName) {
        try {
            FileWriter oFileWriter = new FileWriter(itemsFileName);
            for (int i = 0; i < oldItemsBuffer.size(); i++) {
                oFileWriter.write(oldItemsBuffer.get(i) + "\n");
            }
            System.out.println("Writing new items...");
            oFileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        }
    }

    /* OutputWriter class method to determine which file to update. It will use currentTransaction, oldUserBuffer, oldItemBuffer, transactionsBuffer 
        and OutputFilepath to update the corresponding txt file */
    public void determineTransactionType(Vector<String> oldUserBuffer, Vector<String> oldItemBuffer, Vector<String> 
        transactionsBuffer) {
        for (int i = 0; i < transactionsBuffer.size(); i++) {
            System.out.println("Current code: " + transactionsBuffer.get(i).substring(0,5));
            // TODO: takes in the current transaction from the buffer, and determines if its user or items file affected
            if ((transactionsBuffer.get(i).substring(0,2) == "01" || transactionsBuffer.get(i).substring(0,2) == "02"  || 
                transactionsBuffer.get(i).substring(0,2) == "04" || transactionsBuffer.get(i).substring(0,2) == "05" || ttransactionsBuffer.get(i).substring(0,2) == "06") {
                bufferNewUsers(oldUserBuffer, transactionsBuffer.get(i));
            } if (transactionsBuffer.get(i).substring(0,2) == "03" || transactionsBuffer.get(i).substring(0,2) == "04") {
                bufferNewItems(oldItemBuffer, transactionsBuffer.get(i));
            }/* else {
                if (currentTransaction.charAt(0) == 0 && currentTransaction.charAt(1) == 1) {
                    String newName = currentTransaction.substring(3, 18);
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
    } 
    
    // OutputWriter class method to log errors to a file, which will use currentTransaction, OutputFilepath and the Error class
    public void logInformation(String OutputFilepath, Error error) {
        System.out.println("Logging error...");
        // TODO: if an error has occurred, log the information to a file and print it out to a user
        /*File oFile = new File("log.txt");
        oFile.createNewFile(); // if file already exists will do nothing 
        FileOutputStream oFile = new FileOutputStream(oFile, false);
        oFile.write(error.errorMsg + "\n");
        System.out.println(error.errorMsg);*/
    } 

    // main function to execute the auction service backend, writeUsers works
    public static void main(String argv[]) throws IOException {
        // creating instances of classes here to be used for file reading and writing
        TransactionReader tReader = new TransactionReader();
        OldUserFileReader uReader = new OldUserFileReader();
        OldItemFileReader iReader = new OldItemFileReader();
        OutputWriter oWriter = new OutputWriter();
        
        Vector<String> transactionsBuffer = tReader.readMergedTransaction(argv[0]);
        Vector<String> oldUserBuffer = uReader.readUserFile(argv[1]);
        Vector<String> oldItemBuffer = iReader.readItemFile(argv[2]);

        // Testing transactions that only affect users here
        //Vector<String> newUserbuffer = bufferNewUsers(oldUserBuffer, transactionsBuffer); 
        //writeNewUsers(newUserBuffer, argv[1]);
        
        // Testing transaction that olny affect items here
        oWriter.determineTransactionType(oldUserBuffer, oldItemBuffer, transactionsBuffer);
        
        System.out.println("Changed buffers: \n\n\n" + oldUserBuffer);
        System.out.println(oldItemBuffer);

        oWriter.writeNewItems(oldItemBuffer, argv[2]);
    }
}