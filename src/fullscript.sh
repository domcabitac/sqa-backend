# !/bin/bash

# Compile the program
# cd frontend
# make
# cd ..

available_items="./frontend/available-items.txt"
current_users="./frontend/current-user-accounts.txt"
daily_transactions="./frontend/daily-transactions.txt"
test_input="daily-input-1.txt"
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

# cd ./backend/
# make run

# cd -
available_items2="./backend/available-items.txt"
current_users2="./backend/current-user-accounts.txt"
test_input2="daily-input-2.txt"

rm $tempUser
touch $tempUser
for uFile in $current_users2* ; do
    echo $uFile
    cat $uFile >> $tempUser
done

rm $tempItem
touch $tempItem
for iFile in $available_items2* ; do
    echo $iFile
    cat $iFile >> $tempItem
done

./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input2

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
done

# cd ./backend/
# make run

# cd -
test_input3="daily-input-3.txt"

rm $tempUser
touch $tempUser
for uFile in $current_users2* ; do
    echo $uFile
    cat $uFile >> $tempUser
done

rm $tempItem
touch $tempItem
for iFile in $available_items2* ; do
    echo $iFile
    cat $iFile >> $tempItem
done

./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input3

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
done

# cd ./backend/
# make run

# cd -
test_input4="daily-input-4.txt"

rm $tempUser
touch $tempUser
for uFile in $current_users2* ; do
    echo $uFile
    cat $uFile >> $tempUser
done

rm $tempItem
touch $tempItem
for iFile in $available_items2* ; do
    echo $iFile
    cat $iFile >> $tempItem
done

./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input4

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
done

# cd ./backend/
# make run

# cd -
test_input5="daily-input-5.txt"

rm $tempUser
touch $tempUser
for uFile in $current_users2* ; do
    echo $uFile
    cat $uFile >> $tempUser
done

rm $tempItem
touch $tempItem
for iFile in $available_items2* ; do
    echo $iFile
    cat $iFile >> $tempItem
done

./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input5

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
done

cd ./backend/
make run