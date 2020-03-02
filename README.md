# sqa-backend
Team Members:    
- Leon Chow  
- Dominic Cabitac

# sqa-project

Team Members:  
- Jon Perry  
- Martin Reventar  
- Dominic Cabitac


## Phase 1

### Test Plan
The test folder structure is strucutured primarily based upon transaction code types. Each transaction code folder has case folders which contain console-input.txt, expected-console-output.txt, and expected-transaction-output.txt. Our test plan is to write a script or scripts that will run each test case folder through the system. The console-input.txt file in each folder would be used to automatically enter text into the console for each case. The output received from the console will be recorded and measured against the expected-console-output.txt to ensure the result that happens matches what is expected to happen. A login script to the appropiate account type would be run before most tests to ensure proper credentials are met for tests. The test results would be saved in a folder which name would be the current time the test run started. The results would mirror tests folder structure but would only include a console-output-result.txt and/or transaction-output-result.txt which would specify what differed (or nothing differed) between the expected results and the actual output produced.

Invalid cases have been created for the daily transaction file, current users file, and available items file. These will be used against a format checker once implemented to ensure it catches these ill-formated transactions. There is also one large test file that includes test cases for each transaction code which should create a properly formatted daily-transaction file to be compared against the expected-transaction-output.txt file.

Something that will be required is that the state of the system is set up and torn down properly for tests. This means that the proper account types for different tests will need to be set up in the test system properly to ensure that the tests can be run properly. An example of this can be found in tests/frontend/output-files/daily-transaction/available-items.txt and tests/frontend/output-files/daily-transaction/current-users.txt files. More of these files will be made as the tests are actually being written.

### Requirement Problems
- Password is not a requiremnt
- What happens to a deleted user's bids is not specified
- What happens to a deleted user's auction items for sale is not specified
- Better order would be to ask for the username and then the item for sale from that user (rather than item -> user)?
- For standard accounts, couldn't users just give themselves unlimited credits? (based on add credits description)
- Does the transaction file show the added credit or the users total credit after adding the additional credit?
- There are inconsistent user lengths in the 4th example for "Daily Transaction File"
- Available items file must be longer than 62 character (see below for design choice made)

### Assumptions Made / Design Choices
- Passwords are a necessity
- Usernames are case insensitive
- Usernames will be trimmed on left/right side so no leading/trailing spaces are included
- Sellers cannot bid on their own items
- Available Items file must be 68 characters long
    - 25 for item name, 15 for seller's name, 15 for buyer's name, 3 for days left, 6 for price, and 4 for the necessary spaces in between
