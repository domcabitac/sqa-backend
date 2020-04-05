# !/bin/bash

# Compile the program
# cd frontend
# make
# cd ..

available_items="./frontend/available-items.txt"
current_users="./frontend/current-user-accounts.txt"
daily_transactions="./frontend/daily-transactions.txt"
test_input="daily-input0.txt"

./frontend/./RUN_FRONTEND $available_items $current_users $daily_transactions < $test_input

# rm $daily_transactions
# touch $daily_transactions
# for transFile in $1* ; do
#     echo $transFile
#     cat $transFile >> $daily_transactions
# done

cd ./backend/
make run