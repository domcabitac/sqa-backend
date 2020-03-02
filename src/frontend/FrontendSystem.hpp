#pragma once
#include <vector>
#include "UserReader.hpp"
#include "ItemReader.hpp"
#include "TransactionProcessor.hpp"
#include "TransactionWriter.hpp"
#include <iostream>

using namespace std;
/*
 FrontendSystem
 This is the main program that puts together all relevant classes to run the front end.
 It handles transactions indefinitely and is caleld within main.cpp
*/
class FrontendSystem
{

public:
    // vector stores in all the Users registered
    vector<User> users;

    // reference of which User is currently logged in
    User *currentUser;

    // reads in Users for processing
    UserReader userReader;

    // reads in items for processing
    ItemReader itemReader;

    // starts the transaction process, advertise, bid, refund or addCredit
    TransactionProcessor transactionProcessor;

    // once transactionProcess is done, this outputs different text actions. Will implement writing to output files in future
    TransactionWriter transactionWriter;

    // constant strings to be used for comparison when handling processes
    static const string LOGIN_STRING;
    static const string LOGOUT_STRING;
    static const string CREATE_STRING;
    static const string DELETE_STRING;
    static const string REFUND_STRING;
    static const string BID_STRING;
    static const string ADVERTISE_STRING;
    static const string ADD_CREDIT_STRING;

    // File names 
    string userFileName; 
    string itemFileName;

    // initiates variables to provide necessary functionality for the program.
    FrontendSystem(string itemFileName, string userFileName, string outputFileName);

    // function is used when there is no User currently loggen in.
    void login();

    // function checks to see if user is logged in or not
    void logout();

    ~FrontendSystem();

    // takes the items read in and updates user's items list with the items they own
    void updateUsersItems(string itemsFileName);

    // function handles the typed command from user
    void handleCommand(string command);

    User *findUser(string username);
};
