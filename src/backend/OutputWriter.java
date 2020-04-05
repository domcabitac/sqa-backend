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
        currentTransaction = currentTransaction.replace("_", " ");
        String currentUser = currentTransaction.substring(3, 19);

        // check if current transaction is CREATE
        if (currentTransaction.substring(0,2).contains("01")) {
            int index = -1;
            
            // loop to assign the index of the matching user
            for (int j = 0; j < newUserBuffer.size(); j++) {
                if ((newUserBuffer.get(j).substring(0, 16)).equals(currentUser)) {
                    index = j;
                    break;
                }
            }

            // if user already exists
            if (index >= 0) {
                System.out.println("ERROR: User already exists! Transaction rejected! Type: Create");
            // otherwise, add user to buffer
            } else {
<<<<<<< HEAD
                currentUser = currentTransaction.substring(3, 23) + "0" + currentTransaction.substring(23,28) + ".00 password";
                newUserBuffer.add(currentUser);
=======
                currentUser = currentTransaction.substring(3, 23) + "0" + currentTransaction.substring(23,28) + ".00 password";                newUserBuffer.add(currentUser);
>>>>>>> f8ad1d5d9a5eb093c9fc408a884d98eaa0b61818
            } 
        // check if current transaction is DELETE
        } else if (currentTransaction.substring(0,2).contains("02")) {
            int index = -1;
            
            // loop to assign the index of the matching user
            for (int j = 0; j < newUserBuffer.size(); j++) {
                if ((newUserBuffer.get(j).substring(0, 16)).equals(currentUser)) {
                    index = j;
                    break;
                }
            }

            // remove user from file if found
            if (index >= 0) {
                System.out.println("Removing user from file...");
                newUserBuffer.remove(index);
            } else {
                System.out.println("ERROR: User does not exist! Transaction rejected! Type: Delete");
            }  
        // check if current transaction is REFUND
        } else if (currentTransaction.substring(0,2).contains("05")) {
            int buyerIndex = -1; 
            int sellerIndex = -1;

            String buyer = currentTransaction.substring(3, 18);
            String seller = currentTransaction.substring(19, 34);
            String amount = currentTransaction.substring(35, 43);

            // loop to assign the sellerindex of the matching user
            for (int j = 0; j < newUserBuffer.size(); j++) {
                if ((newUserBuffer.get(j).substring(0, 15)).equals(seller)) {
                    sellerIndex = j;
                    break;
                }
            }

            // loop to assign the buyerindex of the matching user
            for (int j = 0; j < newUserBuffer.size(); j++) {
                if ((newUserBuffer.get(j).substring(0, 15)).equals(buyer)) {
                    buyerIndex = j;
                    break;
                }
            }
            
            // check if both seller and buyer exists in user list
            if (sellerIndex >= 0 && buyerIndex >= 0) {
                double sellerCredit = Double.parseDouble((newUserBuffer.get(sellerIndex)).substring(20, 26));
                double buyerCredit = Double.parseDouble((newUserBuffer.get(buyerIndex)).substring(20, 26));
                // check if seller can afford to fund
                if (sellerCredit - Double.parseDouble(amount) < 0) {
                    System.out.println("ERROR: Seller does not have enough credits! Transaction rejected and item deleted! Type: Refund");
                // check if buyer is not at max credits
                } else if (buyerCredit + Double.parseDouble(amount) > 999999) {
                    System.out.println("ERROR: Buyer has exceeded maximum credits! Transaction rejected and item deleted! Type: Refund");
                // otherwise, run the transaction and process it 
                } else {
                    double newSellerCredit = sellerCredit - Double.parseDouble(amount);
                    double newBuyerCredit = buyerCredit + Double.parseDouble(amount);

                    // Zero formating for both seller and buyer
                    char[] sellerZeroes = new char[7 - (Double.toString(newSellerCredit)).length()];
                    Arrays.fill(sellerZeroes, '0');
                    String extraSellerZero = new String(sellerZeroes); 

                    char[] buyerZeroes = new char[7 - (Double.toString(newBuyerCredit)).length()];
                    Arrays.fill(buyerZeroes, '0');
                    String extraBuyerZero = new String(buyerZeroes); 

                    // create new entries for the buffer
                    String newSeller = newUserBuffer.get(sellerIndex).substring(0, 20) + extraSellerZero + newSellerCredit + newUserBuffer.get(sellerIndex).substring(27, 38);
                    String newBuyer = newUserBuffer.get(buyerIndex).substring(0, 20) + extraBuyerZero + newBuyerCredit + newUserBuffer.get(buyerIndex).substring(27, 38);

                    // overwrite indices with new seller and buyer 
                    newUserBuffer.set(sellerIndex, newSeller);
                    newUserBuffer.set(buyerIndex, newBuyer);
                }
            } else {
                System.out.println("ERROR: Buyer or Seller does not exist! Transaction rejected and item deleted! Type: Transaction");
            }
        // check if transaction is ADD CREDIT
        } else if (currentTransaction.substring(0,2).contains("06")) {
            String user = currentTransaction.substring(3, 18);
            String credits = currentTransaction.substring(23, 31);

            int index = -1;
            
            // loop to assign the index of the matching user
            for (int j = 0; j < newUserBuffer.size(); j++) {
                if ((newUserBuffer.get(j).substring(0, 15)).equals(user)) {
                    index = j;
                    break;
                }
            }
            // check if user exists
            if (index >= 0) {
                // check that the added credits doesn't exceed over 999999
                if (Double.parseDouble(newUserBuffer.get(index).substring(20, 30)) + Double.parseDouble(credits) < 999999) {
                    Double newCredits = (Double.parseDouble(newUserBuffer.get(index).substring(20, 30)) + Double.parseDouble(credits));
                    
                    // new credit zero formatting
                    char[] zeroes = new char[8 - (Double.toString(newCredits)).length()];
                    Arrays.fill(zeroes, '0');
                    String extraZeroes = new String(zeroes); 

                    String newUser = newUserBuffer.get(index).substring(0, 20) + extraZeroes + newCredits + "0" + newUserBuffer.get(index).substring(29, 38);
                    newUserBuffer.set(index, newUser);
                } else {
                    // max credit error
                    System.out.println("ERROR: Exceeding max credits! Type: Add Credit");
                }
            } else {
                // user does not exist error 
                System.out.println("ERROR: User not found! Type: Add Credit");
            }
        } 
    }
    
    // OutputWriter class method to update items txt file, which will use newItemBuffer and manipulate it with transactionBuffer
    public void bufferNewItems(Vector<String> newItemBuffer, String currentTransaction) {
        
        // replace any underscores with spaces
        currentTransaction = currentTransaction.replace("_", " ");
        // check if current transaction is advertise
        if (currentTransaction.substring(0,2).contains("03")) {
            String newEntry = currentTransaction.substring(3, 45) + "NULL            " + currentTransaction.substring(45, currentTransaction.length());
            newItemBuffer.add(newEntry);
        // check if current transaction is BID
        } else if (currentTransaction.substring(0,2).contains("04")) {
            for (int i = 0; i < newItemBuffer.size(); i++) {
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

                    // replacing old item with new item
                    newItemBuffer.set(i, newItem);
                }
            }
        } 
    }
    
    // OutputWriter class function that will write the changed user buffer to the file
    public Vector<String> writeNewUsers(Vector<String> transactionsBuffer, Vector<String> newUserBuffer, String usersFileName) {
        try {
            // creates a new file writer and writes to it
            FileWriter oFileWriter = new FileWriter(usersFileName);
            for (int i = 0; i < newUserBuffer.size(); i++) {
                oFileWriter.write(newUserBuffer.get(i) + "\n");
                if (i == newUserBuffer.size()-1) {
                    // add END to mark the end of the file, 
                    oFileWriter.write("END");
                }
            }
            
            System.out.println("Writing new users...");
            oFileWriter.close();
        // exceptions for file not existing, or if there was a problem with writing the file
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
        // test return
        return newUserBuffer;
    }

    // Output function writeNewItems that will write the final changed item buffer to itemsFileName path
    public Vector<String> writeNewItems(Vector<String> transactionsBuffer, Vector<String> newItemBuffer, Vector<String> newUserBuffer, String itemsFileName) {
        try {
            // decrement all auction days by 1 and handle any transactions
            for (int i = 0; i < newItemBuffer.size() - 1; i++) {
                int newAuctionDays = Integer.parseInt(newItemBuffer.get(i).substring(58,61)) - 1;
                String extraZero = "";
                
                // handle completion of auction here
                if (newAuctionDays <= 0) {
                    int buyerIndex = -1; 
                    int sellerIndex = -1;
                    String seller = newItemBuffer.get(i).substring(26, 42);
                    String buyer = newItemBuffer.get(i).substring(42, 58);
                    String winningBid = newItemBuffer.get(i).substring(62, 68);
                   
                    String currentSeller = newItemBuffer.get(i).substring(26, 40);
                    String currentAuctionDays = newItemBuffer.get(i).substring(59, 61);

                    if (buyer.trim().equals("NULL") || buyer.trim().equals(null) || buyer.trim().equals("")|| 
                        seller.trim().equals("NULL") || buyer.trim().equals(null) || buyer.trim().equals("")) {
                        newItemBuffer.remove(i);
                        System.out.println("ERROR: Stopping transaction, no bidder or seller for this item! Type: Transaction");
                        break;
                    }  

                    // loop to assign the index of the matching user
                    for (int j = 0; j < newUserBuffer.size(); j++) {
                        if ((newUserBuffer.get(j).substring(0, 16)).equals(seller)) {
                            sellerIndex = j;
                            break;
                        }
                    }

                    // loop to assign the index of the matching user
                    for (int j = 0; j < newUserBuffer.size(); j++) {
                        if ((newUserBuffer.get(j).substring(0, 16)).equals(buyer)) {
                            buyerIndex = j;
                            break;
                        }
                    }
                    
                    // check if both seller and user exists, will proceed to process transaction
                    if (sellerIndex >= 0 && buyerIndex >= 0) {
                        // assign both credit values to buyer and seller credit
                        double sellerCredit = Double.parseDouble((newUserBuffer.get(sellerIndex)).substring(20, 26));
                        double buyerCredit = Double.parseDouble((newUserBuffer.get(buyerIndex)).substring(20, 26));
                        // check if seller does not exceed max credits
                        if (sellerCredit + Double.parseDouble(winningBid) > 999999) {
                            System.out.println("ERROR: Seller has exceed maximum credits! Transaction rejected and item deleted! Type: Transaction");
                        } else {
                            double newSellerCredit = sellerCredit + Double.parseDouble(winningBid);
                            double newBuyerCredit = buyerCredit - Double.parseDouble(winningBid);
    
                            // adjust add extra zeroes for seller
                            char[] sellerZeroes = new char[7 - (Double.toString(newSellerCredit)).length()];
                            Arrays.fill(sellerZeroes, '0');
                            String extraSellerZero = new String(sellerZeroes); 
    
                            char[] buyerZeroes = new char[7 - (Double.toString(newBuyerCredit)).length()];
                            Arrays.fill(buyerZeroes, '0');
                            String extraBuyerZero = new String(buyerZeroes); 
    
                            String newSeller = newUserBuffer.get(sellerIndex).substring(0, 20) + extraSellerZero + newSellerCredit + newUserBuffer.get(sellerIndex).substring(27, 38);
                            String newBuyer = newUserBuffer.get(buyerIndex).substring(0, 20) + extraBuyerZero + newBuyerCredit + newUserBuffer.get(buyerIndex).substring(27, 38);

                            // overwrite the buffer with the new values
                            newUserBuffer.set(sellerIndex, newSeller);
                            newUserBuffer.set(buyerIndex, newBuyer);
                        }
                    } else {
                        System.out.println("ERROR: Buyer or Seller does not exist! Transaction rejected and item deleted! Type: Transaction");
                    }
                    newItemBuffer.remove(i);
                } else {
                    // check if auction days less than 10, add another zero
                    if (newAuctionDays < 10) {
                        extraZero = "00";
                    // check if auction day less than 100, add one zero
                    } else if (newAuctionDays < 100) {
                        extraZero = "0";
                    }
                    // create the new item and overwrite the buffer with it
                    String newItem = newItemBuffer.get(i).substring(0, 58) + extraZero + newAuctionDays + newItemBuffer.get(i).substring(61, 68);
                    newItemBuffer.set(i, newItem);
                }
                System.out.println(newItemBuffer.get(i));
            }

            // creates a new file writer and writes to it
            FileWriter oFileWriter = new FileWriter(itemsFileName);
            for (int j = 0; j < newItemBuffer.size(); j++) {
                oFileWriter.write(newItemBuffer.get(j) + "\n");
                if (j == newItemBuffer.size()-1) {
                    // add END to the end of the file
                    oFileWriter.write("END");
                }
            }

            System.out.println("Writing new items...");
            oFileWriter.close();
        // exceptions for file not existing, or if there was a problem with writing the file
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
        // test return
        return newItemBuffer;
    }

    /* OutputWriter class method to determine which file to update. It will use currentTransaction, newUserBuffer, newItemBuffer, transactionsBuffer 
        and OutputFilepath to update the corresponding txt file */
    public Vector<String> determineTransactionType(Vector<String> newUserBuffer, Vector<String> newItemBuffer, Vector<String> transactionsBuffer) {
        for (int i = 0; i < transactionsBuffer.size()-1; i++) {
            // System.out.println("Transaction: " + transactionsBuffer.get(i));
            // takes in the current transaction from the transaction buffer, and determines if its user or items file affected
            // transactions that affect users are Create, Delete, Refund, Bid and Add Credit
            if (transactionsBuffer.get(i).substring(0,2).contains("01") || transactionsBuffer.get(i).substring(0,2).contains("02") || 
                transactionsBuffer.get(i).substring(0,2).contains("05") || transactionsBuffer.get(i).substring(0,2).contains("06")) {
                // System.out.println("Writing to users...");
                bufferNewUsers(newUserBuffer, transactionsBuffer.get(i));
            // transactions that affect items are bid and advertise
            } else if (transactionsBuffer.get(i).substring(0,2).contains("03") || transactionsBuffer.get(i).substring(0,2).contains("04")) {
                // System.out.println("Writing to items...");
                bufferNewItems(newItemBuffer, transactionsBuffer.get(i));
            } else {
                System.out.println("ERROR: Invalid transaction! Type: Transaction");
            }
        }
        return transactionsBuffer;
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

                // creating the buffers to overwrite with
                Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);
                Vector<String> newItemBuffer = new Vector<String>(oldItemBuffer);

                newUserBuffer.remove(newUserBuffer.indexOf("END"));
                newItemBuffer.remove(newItemBuffer.indexOf("END"));

                // Testing transaction that olny affect items here
                oWriter.determineTransactionType(newUserBuffer, newItemBuffer, transactionsBuffer);

                // writes the new items
                System.out.println("\n" + oWriter.writeNewItems(transactionsBuffer, newItemBuffer, newUserBuffer, argv[2]));
                System.out.println("\n" + oWriter.writeNewUsers(transactionsBuffer, newUserBuffer, argv[1]));
            }
            // exception with too few arguments
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}