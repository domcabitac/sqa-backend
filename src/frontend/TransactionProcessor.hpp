#pragma once
#include "User.hpp"
#include "TransactionWriter.hpp"

using namespace std;
/*
 TransactionProcessor
 This class handles the different types of transaction processes.
 Can process advertises, bids, refunds and add credit
*/
class TransactionProcessor
{
public:
    TransactionWriter transactionWriter;
    // This is the default constructor.
    TransactionProcessor();
    
    TransactionProcessor(TransactionWriter transactionWriter);

    // function takes a user and stores it inside the program
    void processCreate(vector<User> &systemUsers);

    // function takes a user and deletes it from the program
    void processDelete(vector<User> &systemUsers, User *currentUser);

    // function takes in the itemName, minBid, numDays and sends it to the transactionWriter
    void processAdvertise(vector<User> &systemUsers, User *currentUser);

    // function takes in sellerName, itemName and bidAMount and sends it to the writeBid
    void processBid(vector<User> &systemUsers, User *currentUser);

    // function names in sellerName, buyerName and refundAmoutn and sends it to writeRefund
    void processRefund(vector<User> &systemUsers, User *currentUser);

    // function take in creditAmount and sends it to writeAddCredit
    void processAddCredit(vector<User> &systemUsers, User *currentUser);

    User *findUser(vector<User> &users, string username);

    bool usernameValid(vector<User> &systemUsers, string username);
    bool accountTypeValid(string accountType);
    bool initialCreditValid(string floatInput);
    
    bool itemNameValid(string itemName);
    bool minBidValid(string floatInput);
    bool numDaysValid(string intInput);

    bool bidAmountValid(string floatInput, Item *item);

    bool refundAmountValid(string floatInput);

    bool creditValid(string floatInput);


    ~TransactionProcessor();
};