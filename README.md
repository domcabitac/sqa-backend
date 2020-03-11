# sqa-backend
Team Members:    
- Leon Chow  
- Dominic Cabitac

## Phase 4
This program are to take in 3 input files: MergedTransactions, OldUsers.txt and OldItems.txt and process the merged transactions one by one on the corresponding file that will be determined by a method. After running all transactions, the program will return the updated files associated with an error log for any transaction or file failures. The program is intended to be run by compiling this java file, OutputWriter.java, and running it with java. The program will later be integrated with the frontend to update the files concurrently. 

## What works and what does not work

## Based off Front End
https://github.com/jon-perry/sqa-project

### Assumptions Made / Design Choices
- Passwords are a necessity
- Usernames are case insensitive
- Usernames will be trimmed on left/right side so no leading/trailing spaces are included
- Sellers cannot bid on their own items
- Available Items file must be 68 characters long
    - 25 for item name, 15 for seller's name, 15 for buyer's name, 3 for days left, 6 for price, and 4 for the necessary spaces in between
