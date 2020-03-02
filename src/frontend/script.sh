# !/bin/bash

# TODO add "exit" to the end of every console-input.txt file for every test case
# TODO any time we ask for input from a user, do cout << "ENTER WHATEVER INFORMATION: " << endl;
# TODO remove "_" from any daily transaction files that have them and replace with actual spaces
# TODO need to >> the difference for each test that fails into the folder it's contained in into a diff.txt file

available_items="available-items.txt"
current_users="current-user-accounts.txt"
# comment/uncomment below line to exclude/include all tests
tests_location="../../tests/frontend/transaction-codes/*"
# comment/uncomment below line to exclude/include tests in testfolder when developing script
# tests_location="../../tests/frontend/transaction-codes/testfolder"
# # loop over folders in transactions codes
rm "testing.csv"
touch "testing.csv"

for d in $tests_location ; do
    # loop over each case in current folder
    for case in $d/* ; do
        rm daily-transactions.txt
        touch daily-transactions.txt

        # echo $case
        console_input="$case/console-input.txt"
        expected_console_output="$case/expected-console-output.txt"
        expected_transaction_output="$case/expected-transaction-output.txt"
    
        console_output=$case/console-output.txt
        rm "$console_output"
        touch "$console_output"

        # this needs to be fixed. I currently have to hard code cmnd line args in main.cpp because it doesn't work on WSL
        ./RUN_FRONTEND $available_items $current_users "daily-transactions.txt" < "$console_input" >> "$case/console-output.txt"
        
        # diff not working totally on WSL - can tell the difference but doesn't actually output it into the file
        # look at difference in console output
        rm "$case/diff_console.txt"
        touch "$case/diff_console.txt"
        if diff "$expected_console_output" "$console_output" ;
        then
            echo "$case: CONSOLE, PASSED" >> testing.csv
        else
            echo "$case: CONSOLE, FAILED" >> testing.csv
            # diff not working on WSL
            diff "$expected_console_output" "$console_output" >> "$case/diff_console.txt"
        fi

        # look at difference in transaction files
        rm "$case/diff_transaction.txt"
        touch "$case/diff_transaction.txt"
        echo $expected_transaction_output
        if diff "daily-transactions.txt" "$expected_transaction_output" ;
        then
            echo "$case: TRANSACTION, PASSED" >> testing.csv
        else
            echo "$case: TRANSACTION, FAILED" >> testing.csv
            # diff not working on WSL
            diff "$expected_console_output" "$console_output" >> "$case/diff_transaction.txt"
        fi
    done
done