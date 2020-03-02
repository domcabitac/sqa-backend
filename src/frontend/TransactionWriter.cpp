#include "User.hpp"
#include "TransactionWriter.hpp"
#include <sstream>
#include <iostream>
#include <iomanip>

using namespace std;

TransactionWriter::TransactionWriter() {}
TransactionWriter::TransactionWriter(string outputFileName)
{
    this->outputFileName = outputFileName;
}

void TransactionWriter::writeLogout(User *user)
{
    writeBasicTransaction(*user, "00");
}

void TransactionWriter::writeCreate(User user)
{
    writeBasicTransaction(user, "01");
}

void TransactionWriter::writeDelete(User *user)
{
    writeBasicTransaction(*user, "02");
}

void TransactionWriter::writeAdvertise(string itemName, string username, float minBid, int numDays)
{
    ofstream out;
    out.open(this->outputFileName, ios_base::app);
    out << "03 ";
    out << getFormattedItemName(itemName) << " ";
    out << getFormattedUsername(username) << " ";

    ostringstream daysSstream;
    daysSstream << setw(3);
    daysSstream << right;
    daysSstream << setfill('0');
    daysSstream << numDays;
    out << daysSstream.str() << " ";
    out << getFormattedBid(minBid) << endl;
    out.close();
}

void TransactionWriter::writeBid(string sellerName, string buyerName, string itemName, float bidAmount)
{
    ofstream out;
    out.open(this->outputFileName, ios_base::app);
    out << "04 ";
    out << getFormattedItemName(itemName) << " ";
    out << getFormattedUsername(sellerName) << " ";
    out << getFormattedUsername(buyerName) << " ";
    out << getFormattedBid(bidAmount) << endl;
}

void TransactionWriter::writeRefund(string sellerName, string buyerName, float refundAmount)
{
    ofstream out;
    out.open(this->outputFileName, ios_base::app);
    out << "05 ";
    out << getFormattedUsername(sellerName) << " ";
    out << getFormattedUsername(buyerName) << " ";
    out << getFormattedCredit(refundAmount) << endl;
    out.close();
}

void TransactionWriter::writeAddCredit(User *user)
{
    writeBasicTransaction(*user, "06");
}

void TransactionWriter::writeBasicTransaction(User user, string transactionCode)
{
    ofstream out;
    out.open(this->outputFileName, ios_base::app);
    if (transactionCode == "00")
    {
        out << "00" << endl;
        return;
    }

    out << transactionCode << " ";
    out << getFormattedUsername(user.username) << " ";
    out << user.accountType << " ";
    out << getFormattedCredit(user.credit) << endl;
    out.close();
}

string TransactionWriter::getFormattedUsername(string username)
{
    ostringstream usernameSstream;
    usernameSstream << setfill(' ');
    usernameSstream << setw(15);
    usernameSstream << left;
    usernameSstream << username;

    return usernameSstream.str();
}

string TransactionWriter::getFormattedCredit(float credit)
{
    ostringstream creditSstream;
    creditSstream << fixed;
    creditSstream << setprecision(2);
    creditSstream << setfill('0');
    creditSstream << setw(9);
    creditSstream << credit;
    return creditSstream.str();
}

string TransactionWriter::getFormattedBid(float bid)
{
    ostringstream bidSstream;
    bidSstream << fixed;
    bidSstream << setprecision(2);
    bidSstream << setfill('0');
    bidSstream << setw(6);
    bidSstream << bid;
    return bidSstream.str();
}

string TransactionWriter::getFormattedItemName(string name)
{
    ostringstream itemSstream;
    itemSstream << setfill(' ');
    itemSstream << setw(25);
    itemSstream << left;
    itemSstream << name;

    return itemSstream.str();
}

TransactionWriter::~TransactionWriter() {}
