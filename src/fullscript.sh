# !/bin/bash

# Compile the program
# cd frontend
# make
# cd ..

available_items="./frontend/available-items.txt"
current_users="./frontend/current-user-accounts.txt"
daily_transactions="./backend/daily-transactions.txt"
test_input="daily-input0.txt"
mergeTrans="./backend/mergeTrans.txt"

./frontend/./RUN_FRONTEND $available_items $current_users $daily_transactions < $test_input

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
done

cd ./backend/
make run