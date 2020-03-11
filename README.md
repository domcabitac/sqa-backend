# SQA-Backend
Team Members:    
- Leon Chow  
- Dominic Cabitac

# Phase 4
The purpose of this program is to perform the backend functions for the online auction house experience. The intentions of this program are to take in 3 input files: MergedTransactions.txt, OldUsers.txt and OldItems.txt, and place them into 3 seperate buffer vectors for the OutputWriter to use. The OutputWriter will process the merged transactions one by one on the corresponding file that will be determined by a method. After running all transactions, the program will return the updated files associated with an error log for any transaction or file failures. The program is intended to be run by compiling this java file, OutputWriter.java, and running it with java. The program will later be integrated with the frontend to update the files concurrently. 

## How to compile and run the program
To compile and run the program, execute the following commands in the `sqa-backend directory`:
1. `javac OutputWriter.java`
2. `OutputWriter <transactionFile.txt> <usersAccountFile.txt> <availableItemsFile.txt>`

Example Input: `OutputWriter daily-transactions.txt current-user-accounts.txt available-items-test.txt`

The program will read in 3 files, `daily-transaction.txt`, `current-user-accounts.txt` and `available-items-test.txt` to produce the same user accounts and items file.

## What works and what does not work
The nature of this program is still in rapid development. As a result, not all functions will work and the program will only work on precise input. Each class has all the required variables and methods. All the readers are working, but the only writer functions that are working are `writeNewItems()`, `determineTransactionType()` and `bufferNewItems()`. As such, only the transactions that associate items will work right now, which are advertise and bid. If you wish to test the program, you can add a new transaction or item. 
Do note that the entry in the files have to be perfectly spaced out like so, or the file will not be read correctly. 

Examples of a transaction and item entry can be seen below:
<pre>
available-items-test.txt:

macbook pro 16 i7 silver  John            NULL            900 000.00  
Not a less paul guitaree  John            NULL            050 699.99  

daily-transactions.txt:
  
04 macbook pro 16 i7 silver  john            Bob             700.00  
03 UOIT Backpack             john            Bob             100 700.00  
</pre>
## Based off Front End
https://github.com/jon-perry/sqa-project

### Assumptions Made / Design Choices
- Passwords are a necessity
- Usernames are case insensitive
- Usernames will be trimmed on left/right side so no leading/trailing spaces are included
- Sellers cannot bid on their own items
- Available Items file must be 68 characters long
    - 25 for item name, 15 for seller's name, 15 for buyer's name, 3 for days left, 6 for price, and 4 for the necessary spaces in between
