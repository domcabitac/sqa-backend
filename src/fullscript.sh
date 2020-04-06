# !/bin/bash

# Compile the program
# cd frontend
# make
# cd ..

# script parameters
available_items="./frontend/available-items.txt"
current_users="./frontend/current-user-accounts.txt"
daily_transactions="./frontend/daily-transactions.txt"
test_input="daily-input-1.txt"
mergeTrans="./backend/mergeTrans.txt"
tempUser="./backend/tempUser.txt"
tempItem="./backend/tempItem.txt"
dailyTransRes="Daily_Transactions_Results.txt"
itemResult="Item_Results.txt"

cd frontend 
make
cd ../

# removes and makes a new tempUser file
rm $tempUser
touch $tempUser

truncate -s 0 $daily_transactions
truncate -s 0 $dailyTransRes

# appends uFile contents to tempUser
for uFile in $current_users* ; do
    echo $uFile
    cat $uFile >> $tempUser
done

# removes and makes a new tempItem file
rm $tempItem
touch $tempItem
# appends iFile contents to tempItem
for iFile in $available_items* ; do
    echo $iFile
    cat $iFile >> $tempItem
    echo "Day 1 Items" >> $itemResult
    cat $tempItem >> $itemResult
    echo "\n----------------------------------------------------------------------------------" >> $itemResult
done

# runs the frontend with the provided temp files, also copies the contents of test_input as daily transactions
./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input

# repeats these for both transactions and items, all the same for each day
rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
    echo "Day 1 Transactions" >> $dailyTransRes
    cat $daily_transactions >> $dailyTransRes
    echo "----------------------------------------------------------------------------------" >> $dailyTransRes
done

cd ./backend/
make run

cd -
available_items2="./backend/available-items-test.txt"
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
    echo "Day 2 Items" >> $itemResult
    cat $tempItem >> $itemResult
    echo "\n----------------------------------------------------------------------------------" >> $itemResult
done

./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input2

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
    echo "Day 2 Transactions" >> $dailyTransRes
    cat $daily_transactions >> $dailyTransRes
    echo "----------------------------------------------------------------------------------" >> $dailyTransRes
done

cd ./backend/
make run

cd -
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
    echo "Day 3 Items" >> $itemResult
    cat $tempItem >> $itemResult
    echo "\n----------------------------------------------------------------------------------" >> $itemResult
done

./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input3

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
    echo "Day 3 Transactions" >> $dailyTransRes
    cat $daily_transactions >> $dailyTransRes
    echo "----------------------------------------------------------------------------------" >> $dailyTransRes
done

cd ./backend/
make run

cd -
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
    echo "Day 4 Items" >> $itemResult
    cat $tempItem >> $itemResult
    echo "\n----------------------------------------------------------------------------------" >> $itemResult
done

./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input4

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
    echo "Day 4 Transactions" >> $dailyTransRes
    cat $daily_transactions >> $dailyTransRes
    echo "----------------------------------------------------------------------------------" >> $dailyTransRes
done

cd ./backend/
make run

cd -
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
    echo "Day 5 Items" >> $itemResult
    cat $tempItem >> $itemResult
    echo "\n----------------------------------------------------------------------------------" >> $itemResult
done

./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input5

rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
    echo "Day 5 Transactions" >> $dailyTransRes
    cat $daily_transactions >> $dailyTransRes
    echo "----------------------------------------------------------------------------------" >> $dailyTransRes
done

cd ./backend/
make run