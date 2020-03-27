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
    public Vector<String> oldItemBuffer = new Vector<String>();
    public Vector<String> oldUserBuffer = new Vector<String>();
    public Vector<String> transactionsBuffer = new Vector<String>();
    public Vector<String> newUserBuffer = new Vector<String>();
    public Vector<String> newItemBuffer = new Vector<String>();
    public String transactionType;

    // OutputWriter class method to update users txt file, which will use newUserBuffer, transactionBuffer
    public void bufferNewUsers(Vector<String> newUserBuffer, String currentTransaction) {
        // TODO: Using the old user buffer, perform transactions from transactionBuffer marked for users, either create, delete or edit a user 

    }
    
    // OutputWriter class method to update items txt file, which will use newItemBuffer and manipulate it with transactionBuffer
    public void bufferNewItems(Vector<String> newItemBuffer, String currentTransaction) {
        currentTransaction = currentTransaction.replace("_", " ");
        // check if current transaction is advertise
        if (currentTransaction.substring(0,2).contains("03")) {
            String newEntry = currentTransaction.substring(3,currentTransaction.length());
            newItemBuffer.add(newEntry);
        // check if current transaction is bid
        } else if (currentTransaction.substring(0,2).contains("04")) {
            System.out.println("Adding new item...");
            for (int i = 0; i < newItemBuffer.size(); i++) {
                // replace any underscores with spaces
                // if the current index of newItemBuffer is 'END', remove it
                if (newItemBuffer.get(i).equals("END")) {
                    System.out.println("end of file");
                    System.out.println("Removing " + (newItemBuffer.size() - 1));
                    newItemBuffer.remove(newItemBuffer.size()-1);
                    break;
                }
                // check to see if current item in new item buffer is equal to the item in current transaction
                if (newItemBuffer.get(i).substring(0,25).contains(currentTransaction.substring(3,28))) {
                    // Creating new item here with applied transaction
                    String currentItem = newItemBuffer.get(i).substring(0,25);
                    String currentSeller = newItemBuffer.get(i).substring(25, 40);
                    String currentAuctionDays = newItemBuffer.get(i).substring(58, 61);
                    String newBidder = currentTransaction.substring(43,61);
                    String newBid = currentTransaction.substring(60, 67);
                    String newItem = currentItem + currentSeller + newBidder + currentAuctionDays + newBid;
                    newItem = newItem.replace("_", " ");
                    System.out.println("New item: " + newItem);

                    // replacing old item with new item
                    newItemBuffer.set(i, newItem);
                }
            }
        } else {
            System.out.println("ERROR: Invalid Transaction! Type: Transaction");
        }
    }
    
    // TODO: Writes the final changed user buffer to usersFileName path
    public void writeNewUsers(Vector<String> newUserBuffer, String usersFileName) {
        try {
            // creates a new file writer and writes to it
            FileWriter oFileWriter = new FileWriter(usersFileName);
            for (int i = 0; i < newUserBuffer.size(); i++) {
                oFileWriter.write(newUserBuffer.get(i) + "\n");
            }
            // add END to mark the end of the file, also check if the transaction file is not empty, otherwise two END will appear
            if (transactionsBuffer.size() != 0) {
                oFileWriter.write("END");
            }
            System.out.println("Writing new users...");
            oFileWriter.close();
        // exceptions for file not existing, or if there was a problem with writing the file
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
    }

    // Output function writeNewItems that will write the final changed item buffer to itemsFileName path
    public void writeNewItems(Vector<String> transactionsBuffer, Vector<String> newItemBuffer, Vector<String> newUserBuffer, String itemsFileName) {
        try {
            // decrement all auction days by 1 and handle any transactions
            for (int i = 0; i < newItemBuffer.size() - 1; i++) {
                int newAuctionDays = Integer.parseInt(newItemBuffer.get(i).substring(58,61)) - 1;
                String extraZero = "";
                
                // handle completion of auction here
                if (newAuctionDays <= 0) {
                    System.out.println("Removing item...");
                    int buyerIndex = -1; 
                    int sellerIndex = -1;
                    String seller = newItemBuffer.get(i).substring(26, 42);
                    String buyer = newItemBuffer.get(i).substring(42, 58);
                    String winningBid = newItemBuffer.get(i).substring(62, 68);

                    System.out.println("Winning bid: " + winningBid);
                   
                    String currentSeller = newItemBuffer.get(i).substring(26, 40);
                    String currentAuctionDays = newItemBuffer.get(i).substring(59, 61);

                    if (buyer.trim().equals("NULL") || buyer.trim().equals(null) || buyer.trim().equals("")|| 
                        seller.trim().equals("NULL") || buyer.trim().equals(null) || buyer.trim().equals("")) {
                        newItemBuffer.remove(i);
                        System.out.println("Stopping transaction, no bidder or seller for this item!");
                        break;
                    }  

                    // TODO: iterate through users vector to get that index
                    
                    System.out.println("Seller: " + seller);
                    System.out.println("Buyer: " + buyer);

                    for (int j = 0; j < newUserBuffer.size()-1; j++) {
                        if ((newUserBuffer.get(j).substring(0, 16)).equals(seller)) {
                            sellerIndex = j;
                            System.out.println("s index: " + j);
                            break;
                        }
                    }

                    for (int j = 0; j < newUserBuffer.size()-1; j++) {
                        if ((newUserBuffer.get(j).substring(0, 16)).equals(buyer)) {
                            buyerIndex = j;
                            System.out.println("b index: " + j);
                            break;
                        }
                    }
                    
                    if (sellerIndex >= 0 && buyerIndex >= 0) {
                        double sellerCredit = Double.parseDouble((newUserBuffer.get(sellerIndex)).substring(20, 26));
                        double buyerCredit = Double.parseDouble((newUserBuffer.get(buyerIndex)).substring(20, 26));
                        if (buyerCredit - Double.parseDouble(winningBid) < 0) {
                            System.out.println("ERROR: Buyer does not have enough credits. Transaction rejected and item deleted! Type: Transaction");
                        } else if (sellerCredit + Double.parseDouble(winningBid) > 999999) {
                            System.out.println("ERROR: Seller has exceed maximum credits! Transaction rejected and item deleted! Type: Transaction");
                        } else {
                            double newSellerCredit = sellerCredit + Double.parseDouble(winningBid);
                            double newBuyerCredit = buyerCredit - Double.parseDouble(winningBid);

                            //System.out.println("New Buyer Credit: " + newBuyerCredit);
                            //System.out.println("New Seller Credit: " + newSellerCredit);
    
                            char[] sellerZeroes = new char[7 - (Double.toString(newSellerCredit)).length()];
                            Arrays.fill(sellerZeroes, '0');
                            String extraSellerZero = new String(sellerZeroes); 
                            //System.out.println("Seller zeroes: " + extraSellerZero);
    
                            char[] buyerZeroes = new char[7 - (Double.toString(newBuyerCredit)).length()];
                            Arrays.fill(buyerZeroes, '0');
                            String extraBuyerZero = new String(buyerZeroes); 
                            //System.out.println("Buyer zeroes: " + extraBuyerZero);
    
                            String newSeller = newUserBuffer.get(sellerIndex).substring(0, 20) + extraSellerZero + newSellerCredit + newUserBuffer.get(i).substring(27, 38);
                            String newBuyer = newUserBuffer.get(buyerIndex).substring(0, 20) + extraBuyerZero + newBuyerCredit + newUserBuffer.get(i).substring(27, 38);
                            
                            System.out.println(newSeller);
                            System.out.println(newBuyer);

                            newUserBuffer.set(sellerIndex, newSeller);
                            newUserBuffer.set(buyerIndex, newBuyer);
                        }
                    }
                    //newItemBuffer.remove(i);
                } else {
                    if (newAuctionDays < 10) {
                        extraZero = "00";
                    } else if (newAuctionDays < 100) {
                        extraZero = "0";
                    }
                    String newItem = newItemBuffer.get(i).substring(0, 58) + extraZero + newAuctionDays + newItemBuffer.get(i).substring(61, 68);
                    newItemBuffer.set(i, newItem);
                }
                System.out.println(newItemBuffer.get(i));
            }

            // creates a new file writer and writes to it
            FileWriter oFileWriter = new FileWriter(itemsFileName);
            for (int i = 0; i < newItemBuffer.size(); i++) {
                oFileWriter.write(newItemBuffer.get(i) + "\n");
            }
            // add END to mark the end of the file, also check if the transaction file is not empty, otherwise two END will appear
            if (transactionsBuffer.size() != 0) {
                oFileWriter.write("END");
            }
            System.out.println("Writing new items...");
            oFileWriter.close();
        // exceptions for file not existing, or if there was a problem with writing the file
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
    }

    /* OutputWriter class method to determine which file to update. It will use currentTransaction, newUserBuffer, newItemBuffer, transactionsBuffer 
        and OutputFilepath to update the corresponding txt file */
    public void determineTransactionType(Vector<String> newUserBuffer, Vector<String> newItemBuffer, Vector<String> 
        transactionsBuffer) {
        for (int i = 0; i < transactionsBuffer.size(); i++) {
            System.out.println("Transaction: " + transactionsBuffer.get(i));
            // TODO: takes in the current transaction from the buffer, and determines if its user or items file affected
            if (transactionsBuffer.get(i).substring(0,2).contains("01") || transactionsBuffer.get(i).substring(0,2).contains("02")  || 
                transactionsBuffer.get(i).substring(0,2).contains("04") || transactionsBuffer.get(i).substring(0,2).contains("05") || transactionsBuffer.get(i).substring(0,2).contains("06")) {
                bufferNewUsers(newUserBuffer, transactionsBuffer.get(i));
            } if (transactionsBuffer.get(i).substring(0,2).contains("03") || transactionsBuffer.get(i).substring(0,2).contains("04")) {
                bufferNewItems(newItemBuffer, transactionsBuffer.get(i));
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
    
    // main function to execute the auction service backend, writeUsers works
    public static void main(String argv[]) throws IOException {
        try {
            // If user does not run program with 3 arguments
            if (argv.length != 3) {
                throw new Exception("ERROR: Not enough arguments. Usage: java OutputWriter <TransactionFile.txt> <UserFile.txt> <ItemFile.txt>"); 
            } else {
                
                // creating instances of classes here to be used for file reading and writing
                TransactionReader tReader = new TransactionReader();
                OldUserFileReader uReader = new OldUserFileReader();
                OldItemFileReader iReader = new OldItemFileReader();
                OutputWriter oWriter = new OutputWriter();

                // creating buffers here to be used by oWriter
                Vector<String> transactionsBuffer = tReader.readMergedTransaction(argv[0]);
                Vector<String> oldUserBuffer = uReader.readUserFile(argv[1]);
                Vector<String> oldItemBuffer = iReader.readItemFile(argv[2]);

                Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);
                Vector<String> newItemBuffer = new Vector<String>(oldItemBuffer);

                // Testing transaction that olny affect items here
                oWriter.determineTransactionType(newUserBuffer, newItemBuffer, transactionsBuffer);

                // writes the new items
                oWriter.writeNewItems(transactionsBuffer, newItemBuffer, newUserBuffer, argv[2]);
            }
            // exception with too few arguments
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}