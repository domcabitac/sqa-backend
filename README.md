# SQA-Backend
Team Members:    
- Leon Chow  
- Dominic Cabitac

The purpose of this program is to perform the backend functions for the online auction house experience. The intentions of this program are to take in 3 input files: MergedTransactions.txt, OldUsers.txt and OldItems.txt, and place them into 3 seperate buffer vectors for the OutputWriter to use. The OutputWriter will process the merged transactions one by one on the corresponding file that will be determined by a method. After running all transactions, the program will return the updated files associated with an error log for any transaction or file failures. The program is intended to be run by compiling this java file, OutputWriter.java, and running it with java. The program will later be integrated with the frontend to update the files concurrently. 

# Phase 6
We have successfully wrote two scripts to merge both frontend and backend. The `dailyscript.sh` file will merge frontend and backend with a list of daily inputs, also merging them to a merged transaction file and run this merged file with the items and users file.
The `fullscript.sh` file will do the same as `dailyscript.sh`, except that it will run it 5 times to simulate 5 days of operation. The 
`Daily_Transactions_Results.txt` file will be populated with all the daily transactions and the `Item_Results.txt` file will be populated with the items after 5 days of operation.  
                  
**NOTE: Upon pulling this repo, both files will be empty. You will need to run both daily script or weekly script to populate this file.**

To run the daily script, execute the following commands in the `sqa-backend/src directory`
1. `sh dailyscript.sh`

To run the weekly script, execute the following commands in the `sqa-backend/src directory`
1. `sh fullscript.sh`

## Bug Report 
There is an issue with Bidding right now, you need to enter the exact amount of spaces when trying to make a bid in the frontend. The backend will correctly process a bid though, if it happens.

# Phase 5
To compile and run the program, execute the following commands in the `sqa-backend directory`:
1. `make run`

To run testing code, execute the following command in the `sqa-backend/src/backend directory`:
1. `make test`
2. `java TestRunner`

We have decided to use JUnit version 4.13 to build and run our tests. Upon running tests, you will note that the output looks similar to that of running the actual code. running the test will also modify the transaction files supplied as well. 

At this point in time, every function in this version will work. the program will take in a merged transaction file, apply the transactions to it, and then overwrite the item and user files. The next step in the phase is to correctly integrate the frontend and the backend to have a fully operational program. For examples of the item and transaction files, please refer below to that section in phase 4.

# Phase 4

## How to compile and run the program
To compile and run the program, execute the following commands in the `sqa-backend directory`:
1. `javac OutputWriter.java`
2. `OutputWriter <transactionFile.txt> <usersAccountFile.txt> <availableItemsFile.txt>`

Example Input: `OutputWriter daily-transactions.txt current-user-accounts.txt available-items-test.txt`

The program will read in 3 files, `daily-transaction.txt`, `current-user-accounts.txt` and `available-items-test.txt` to produce the same user accounts and items file.

## What works and what does not work
The nature of this program is still in rapid development. As a result, not all functions will work and the program will only work on precise input. Each class has all the required variables and methods. All the readers are working, but the only writer functions that are working are `writeNewItems()`, `determineTransactionType()` and `bufferNewItems()`. As such, only the transactions that associate items will work right now, which are advertise and bid. If you wish to test the program, you can add a new transaction or item. 
Do note that the entry in the files have to be perfectly spaced out like so, or the file will not be read correctly. 

Examples of a transaction and item entry can be seen below. See `available-items-test.txt` and `daily-transactions.txt` for more examples on spacing.
<pre>
available-items-test.txt:

macbook pro 16 i7 silver  John            Bob             900 700.00
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
