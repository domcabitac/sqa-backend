# script parameters
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

# removes and makes a new tempUser file
rm $tempUser
touch $tempUser
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

# removes and creates a new mergeTrans file for merged transactions
rm $mergeTrans
touch $mergeTrans
# appends transFile contents to mergeTrans file
for transFile in $daily_transactions* ; do
    echo $transFile
    cat $transFile >> $mergeTrans
done


cat $mergeTrans

# runs mergeTrans on the backend
cd ./backend/
make run