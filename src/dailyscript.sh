available_items="./frontend/available-items.txt"
current_users="./frontend/current-user-accounts.txt"
daily_transactions="./frontend/daily-transactions.txt"
test_input="daily-input-1.txt"
test_input2="daily-input-2.txt"
test_input3="daily-input-3.txt"
test_input4="daily-input-4.txt"
test_input5="daily-input-5.txt"
mergeTrans="./backend/mergeTrans.txt"
tempUser="./backend/tempUser.txt"
tempItem="./backend/tempItem.txt"

# remove and create a new temporary user file
rm $tempUser
touch $tempUser
# loop through each user file in current users directory and copies it to tempUser file
for uFile in $current_users* ; do
    echo $uFile
    cat $uFile >> $tempUser
done

#remove and create a new temporary item file
rm $tempItem
touch $tempItem
for iFile in $available_items* ; do
    echo $iFile
    cat $iFile >> $tempItem
done

## To test each daily input individually, uncomment a line
## Day 1
./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input

## Day 2
# ./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input2

## Day 3
# ./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input3

## Day 4
# ./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input4

## Day 5
# ./frontend/./RUN_FRONTEND $tempItem $tempUser $daily_transactions < $test_input5

# remove and make a new merged transaction file, then iterates through each transaction file in the daily transaction
rm $mergeTrans
touch $mergeTrans
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
done

cat $mergeTrans

# change to backend directory and runs the merged transactions
cd ./backend/
make run