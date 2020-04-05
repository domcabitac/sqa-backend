# !/bin/bash

# Compile the program
# cd frontend
# make
# cd ..

available_items="./frontend/available-items.txt"
current_users="./frontend/current-user-accounts.txt"
daily_transactions="./frontend/daily-transactions.txt"
test_input="daily-input0.txt"
mergeTrans="./backend/mergeTrans.txt"
tempUser="./backend/tempUser.txt"
tempItem="./backend/tempItem.txt"

rm $tempUser
touch $tempUser
for uFile in $current_users* ; do
    echo $uFile
    cat $uFile >> $tempUser
done

rm $tempItem
touch $tempItem
for iFile in $available_items* ; do
    echo $iFile
    cat $iFile >> $tempItem
done

./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
done

cd ./backend/
make run

