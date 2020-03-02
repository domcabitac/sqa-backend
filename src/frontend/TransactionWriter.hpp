#pragma once
#include "User.hpp"
#include <fstream>

using namespace std;
/*
TransactionWriter
This class handles the different types of transaction writing.
Can write advertises, bids, refunds and add credit
*/
class TransactionWriter
{
public:
    // This is the default constructor.
    string outputFileName;
    TransactionWriter();
    TransactionWriter(string outputFileName);

    void writeLogout(User *user);

    // function writes to the output file once the create transaction has been processed
    void writeCreate(User user);

    // function writes to the output file once the delete transaction has been processed
    void writeDelete(User *user);

    // function takes in itemName, midBid and numDays and outputs "Advertise Transaction Written." Will write to an output file in future
    void writeAdvertise(string itemName, string username, float minBid, int numDays);

    // function takes in sellerName, itemName and bidAmount and outputs "Bid Transaction Written." Will write to an output file in future
    void writeBid(string sellerName, string buyerName, string itemName, float bidAmount);

    // function takes in sellerName, buyerName and refundAmount and outputs "Refund Transaction Written." Will write to an output file in future
    void writeRefund(string sellerName, string buyerName, float refundAmount);

    // function takes in creditAmount and outputs "Add Credit Transaction Written." Will write to an output file in future
    void writeAddCredit(User *user);

    void writeBasicTransaction(User user, string transactionCode);

    ~TransactionWriter();
private:
    string getFormattedUsername(string username);

    string getFormattedCredit(float credit);
    
    string getFormattedBid(float bid);

    string getFormattedItemName(string name);

};
