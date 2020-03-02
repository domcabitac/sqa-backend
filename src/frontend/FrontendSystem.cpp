#include <vector>
#include "FrontendSystem.hpp"
#include "User.hpp"
#include <iostream>
#include <algorithm>

using namespace std;

const string FrontendSystem::LOGIN_STRING = "login";
const string FrontendSystem::LOGOUT_STRING = "logout";
const string FrontendSystem::CREATE_STRING = "create";
const string FrontendSystem::DELETE_STRING = "delete";
const string FrontendSystem::REFUND_STRING = "refund";
const string FrontendSystem::BID_STRING = "bid";
const string FrontendSystem::ADVERTISE_STRING = "advertise";
const string FrontendSystem::ADD_CREDIT_STRING = "addcredit";

// Initiates everything to be used for command processing later
FrontendSystem::FrontendSystem(string itemFileName, string userFileName, string outputFileName)
{
    this->users = vector<User>();
    this->currentUser = NULL;
    this->userReader = UserReader();
    this->users = userReader.createUsers(userFileName);
    this->itemReader = ItemReader();
    this->updateUsersItems(itemFileName);
    this->transactionWriter = TransactionWriter(outputFileName);
    this->transactionProcessor = TransactionProcessor(transactionWriter);
    this->userFileName = userFileName; 
    this->itemFileName = itemFileName;
}

void FrontendSystem::login()
{
    if (currentUser != NULL)
    {
        printf("Cannot login; already logged in\n");
    }
    else
    {
        string username;
        cout << "Enter username: " << endl;
        getline(cin, username);
        if (username == "")
        {
            // could use goto statement to re-ask for username
            cout << "ERROR - username is empty" << endl;
            return;
        }
        User *foundUser = this->findUser(username);
        if (foundUser == NULL)
        {
            cout << "ERROR - username does not exist" << endl;
            return;
        }

        string password;
        cout << "Enter password: " << endl;
        getline(cin, password);
        if (password == "")
        {
            cout << "ERROR - password is empty" << endl;
            // could use goto statement to re-ask for password
            return;
        }
        else if (password != foundUser->password)
        {
            cout << "ERROR - Incorrect password" << endl;
            return;
        }

        this->currentUser = foundUser;
        cout << "Welcome Back!" << endl;
    }
}

void FrontendSystem::logout()
{
    if (currentUser == NULL)
    {
        printf("Must be logged in to logout\n");
    }
    else
    {

        this->transactionWriter.writeLogout(this->currentUser);
        this->currentUser = NULL;
        cout << "Successful Logout" << endl;
        // TODO write transactions to file
    }
}

void FrontendSystem::updateUsersItems(string itemsFileName)
{
    vector<Item> items = this->itemReader.createItems(itemsFileName);
    for (Item item : items)
    {
        User *foundUser = this->findUser(item.sellerName);
        if (foundUser != NULL)
        {
            foundUser->itemsListed.push_back(item);
        }
        else
        {
            cout << "No user found for following item: \n"
                 << item << endl;
        }
    }
    return;
}

void FrontendSystem::handleCommand(string command)
{
    // convert the command to lowercase
    transform(command.begin(), command.end(), command.begin(), ::tolower);


    if (command == FrontendSystem::LOGIN_STRING)
    {
        this->login();
    }
    else if (currentUser == NULL)
    {
        cout << "Must login before issuing commands" << endl;
        return;
    }
    else if (command == FrontendSystem::LOGOUT_STRING)
    {
        this->logout();
    }
    else if (command == FrontendSystem::CREATE_STRING)
    {
        // First checks if current user is Admin.
        if (currentUser->accountType == User::ADMIN_STRING) {
            transactionProcessor.processCreate(this->users);
        } else {
            cout << "Error: Must be an admin to create a user" << endl;
        }
    }
    else if (command == FrontendSystem::DELETE_STRING)
    {
        // First checks if current user is Admin.
        if (currentUser->accountType == User::ADMIN_STRING) {
            transactionProcessor.processDelete(this->users, this->currentUser);
        } else {
            cout << "Error: Must be an admin use the 'delete' code" << endl;
        }
    }
    else if (command == FrontendSystem::ADVERTISE_STRING)
    {
        // Check users account type if transaction is valid. Then send to transactionProcessor
        if (currentUser->accountType != User::BUY_STANDARD_STRING) {
            transactionProcessor.processAdvertise(this->users, this->currentUser);
        } else {
            cout << "ERROR: unable to advertise under buy-standard account type" << endl;
        }
    }
    else if (command == FrontendSystem::BID_STRING)
    {
        if (currentUser->accountType != "SS") {
            transactionProcessor.processBid(this->users, this->currentUser);
        } else {
            cout << "Error: Cannot bid using a Standard-Sell account" << endl;
        }
    }
    else if (command == FrontendSystem::ADD_CREDIT_STRING)
    {
        transactionProcessor.processAddCredit(this->users, this->currentUser);
    }
    else if (command == FrontendSystem::REFUND_STRING)
    {
        if (currentUser->accountType == "AA")
        {
            transactionProcessor.processRefund(this->users, this->currentUser);
        }
        else
        {
            cout << "Error: User is not an admin" << endl;
        }
    }
    else
    {
        cout << "Error: Invalid transaction code entered" << endl;
    }
}

// converts username to lowercase implicitly through overloaded == operator in User
User *FrontendSystem::findUser(string username)
{

    vector<User>::iterator foundUser;
    // create tmp user to use find function
    User tmpUser = User(username, "NULL", "NULL");
    foundUser = find(this->users.begin(), this->users.end(), tmpUser);
    if (foundUser != this->users.end())
    {
        return &foundUser[0];
    }
    else
    {
        return NULL;
    }
}

FrontendSystem::~FrontendSystem() {}