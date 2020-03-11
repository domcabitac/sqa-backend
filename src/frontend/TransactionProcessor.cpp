#include "User.hpp"
#include "Item.hpp"
#include "TransactionProcessor.hpp"
#include <algorithm>

using namespace std;

TransactionProcessor::TransactionProcessor(TransactionWriter transactionWriter)
{
    this->transactionWriter = transactionWriter;
}

TransactionProcessor::TransactionProcessor() {}

void TransactionProcessor::processCreate(vector<User> &systemUsers)
{

    string username, password, accountType, floatInput;

    cout << "Enter Username: " << endl;
    getline(cin, username);
    if (!this->usernameValid(systemUsers, username))
    {
        return;
    }

    cout << "Enter Password: " << endl;
    getline(cin, password);

    cout << "Enter Account Type for new user: " << endl;
    getline(cin, accountType);
    if (!this->accountTypeValid(accountType))
    {
        return;
    }

    cout << "Enter initial credit amount: " << endl;
    getline(cin, floatInput);
    if (!this->initialCreditValid(floatInput))
    {
        return;
    }
    float amount = stof(floatInput);

    User newUser = User(username, password, accountType, amount);
    systemUsers.push_back(newUser);
    this->transactionWriter.writeCreate(newUser);

    cout << accountType << " user '" << username << "' (" << amount << " credit) created successfully!" << endl;
}

void TransactionProcessor::processDelete(vector<User> &systemUsers, User *currentUser)
{
    string username;

    cout << "Enter Username: " << endl;
    getline(cin, username);

    vector<User>::iterator foundUser;
    // create tmp user to use find user to delete
    User tmpUser = User(username, "NULL", "NULL");
    foundUser = find(systemUsers.begin(), systemUsers.end(), tmpUser);

    if (foundUser->username == currentUser->username)
    {
        cout << "Error: You cannot delete your own account" << endl;
        return;
    }
    else if (foundUser != systemUsers.end())
    {   
        User *userToDelete = this->findUser(systemUsers, username);
        this->transactionWriter.writeDelete(userToDelete);
        systemUsers.erase(foundUser);
        cout << "Successfully deleted " << username << endl;
        return;
    }
    else
    {
        cout << "Error: " << username << " does not exist" << endl;
        return;
    }
}

void TransactionProcessor::processAdvertise(vector<User> &systemUsers, User *currentUser){
    string itemName, floatInput, intInput;

    cout << "Enter item name: " << endl;
    getline(cin, itemName);
    if (!this->itemNameValid(itemName))
    {
        return;
    }

    cout << "Enter minimum bid amount: " << endl;
    getline(cin, floatInput);
    if (!this->minBidValid(floatInput))
    {
        return;
    }
    float amount = stof(floatInput);

    cout << "Enter number of days: " << endl;
    getline(cin, intInput);
    if (!this->numDaysValid(intInput))
    {
        return;
    }
    int numDays = stoi(intInput);

    Item newItem = Item(itemName, currentUser->username, amount, "", numDays);
    User *current = this->findUser(systemUsers, currentUser->username);
    current->itemsListed.push_back(newItem);

    this->transactionWriter.writeAdvertise(itemName, currentUser->username, amount, numDays);
    cout << "Successful Post" << endl;
}

void TransactionProcessor::processBid(vector<User> &systemUsers, User *currentUser)
{
    string sellerName, itemName, floatInput;

    cout << "Enter the user who listed the item:" << endl;
    getline(cin, sellerName);

    User *foundUser = this->findUser(systemUsers, sellerName);
    if (foundUser == NULL)
    {
        cout << "Error: Seller username does not exist." << endl;
        return;
    }
    else if (foundUser->username == currentUser->username)
    {
        cout << "Error: You cannot bid on your own item" << endl;
        return;
    }

    cout << "Enter the item you would like to bid on:" << endl;
    getline(cin, itemName);
    
    bool itemFound = false;
    Item *foundItem;
    int index = 0;
    for (Item item : foundUser->itemsListed)
    {
        if (itemName == item.name)
        {
            foundItem = &item;
            itemFound = true;
        }
        else
        {
            index++;
        }
    }

    if (!itemFound)
    {
        cout << "Error: '" << itemName << "' is not a valid item to bid on" << endl;
        return;
    }

    cout << "Current highest bid is " << foundItem->highestBid << " credits. "<< "Enter your bid:" << endl;
    getline(cin, floatInput);
    if (!this->bidAmountValid(floatInput, foundItem))
    {
        return;
    }
    float amount = stof(floatInput);

    User *current = this->findUser(systemUsers, currentUser->username);
    current->credit -= amount;

    Item updatedItem = Item(itemName, sellerName, amount, currentUser->username, foundItem->daysLeft);
    foundUser->itemsListed[index] = updatedItem;

    this->transactionWriter.writeBid(sellerName, currentUser->username, itemName, amount);
    cout << "You successfully bid " << updatedItem.highestBid << " credits for " << updatedItem.name << "from " << updatedItem.sellerName << endl;
}

void TransactionProcessor::processRefund(vector<User> &systemUsers, User *currentUser)
{

    string buyerName, sellerName, floatInput;
    User *foundBuyer, *foundSeller = NULL;

    cout << "Enter Buyers Name:" << endl;
    getline(cin, buyerName);

    foundBuyer = this->findUser(systemUsers, buyerName);
    if (foundBuyer == NULL)
    {
        cout << "Error: invalid buyers name" << endl;
        return;
    }

    cout << "Enter Sellers Name:" << endl;
    getline(cin, sellerName);

    foundSeller = this->findUser(systemUsers, sellerName);
    if (foundSeller == NULL)
    {
        cout << "Error: invalid sellers name" << endl;
        return;
    }

    cout << "Enter refund amount:" << endl;
    getline(cin, floatInput);
    if (!this->refundAmountValid(floatInput))
    {
        return;
    }
    float amount = stof(floatInput);

    // check if seller has sufficient funds
    if (amount > foundSeller->credit)
    {
        cout << "Error: Seller does not have sufficient funds for refund" << endl;
        return;
    }

    foundSeller->updateCredit(foundSeller->credit - amount);
    foundBuyer->updateCredit(foundBuyer->credit + amount);

    this->transactionWriter.writeRefund(sellerName, buyerName, amount);
    cout << "Refund to '" << foundBuyer->username << "' from '" << foundSeller->username << "' for " << amount << " credit successful!" << endl;
}

void TransactionProcessor::processAddCredit(vector<User> &systemUsers, User *currentUser)
{
    string username, floatInput;
    User *foundUser = NULL;

    if (currentUser->accountType == "AA")
    {
        cout << "Enter Username: " << endl;
        getline(cin, username);

        foundUser = this->findUser(systemUsers, username);
        if (foundUser == NULL)
        {
            cout << "Error: Invalid username. Does not exist. " << endl;
            return;
        }
    }

    cout << "Enter credit amount: " << endl;
    getline(cin, floatInput);
    if (!this->creditValid(floatInput))
    {
        return;
    }
    float amount = stof(floatInput);

    if (foundUser != NULL)
    {
        if (foundUser->creditAddedCurrentSession + amount > 1000)
        {
            cout << "Error: Amount entered exceeds amount addable per session." << endl;
            return;
        }
        foundUser->credit += amount;
        foundUser->creditAddedCurrentSession += amount;
        this->transactionWriter.writeAddCredit(foundUser);
        cout << "Successfully added " << amount << " credits to user '" << foundUser->username << "'" << endl;
    }
    else
    {
        if (currentUser->creditAddedCurrentSession + amount > 1000)
        {
            cout << "Error: Amount entered exceeds amount addable per session." << endl;
            return;
        }
        currentUser->credit += amount;
        currentUser->creditAddedCurrentSession += amount;
        this->transactionWriter.writeAddCredit(currentUser);
        cout << "Successfully added " << amount << " credits to your account!" << endl;
    }
}

// -----------------------------------------------------------------------------------------


User *TransactionProcessor::findUser(vector<User> &systemUsers, string username)
{
    vector<User>::iterator foundUser;
    // create tmp user to use find function
    User tmpUser = User(username, "NULL", "NULL");
    foundUser = find(systemUsers.begin(), systemUsers.end(), tmpUser);
    if (foundUser != systemUsers.end())
    {
        return &foundUser[0];
    }
    else
    {
        return NULL;
    }
}

bool TransactionProcessor::usernameValid(vector<User> &systemUsers, string username)
{
    string usernameLower = username;
    transform(usernameLower.begin(), usernameLower.end(), usernameLower.begin(), ::tolower);

    if (usernameLower == "login" || usernameLower == "logout" || usernameLower == "create" || usernameLower == "delete" || usernameLower == "refund" || usernameLower == "bid" || usernameLower == "advertise" || usernameLower == "addcredit")
    {
        cout << "Error: Invalid username - cannot be a transaction code" << endl;
        return false;
    }
    else if (!(username.find_first_not_of(' ') != std::string::npos))
    {
        cout << "Error: Username is empty - it must be between 1-15 characters and cannot be only spaces" << endl;
        return false;
    }
    else if (username.empty())
    {
        cout << "Error: Username is empty - it must be between 1-15 characters" << endl;
        return false;
    }
    else if (username.length() > 15)
    {
        cout << "Error: Username too long - must be between 1-15 characters" << endl;
        return false;
    }

    // if all tests pass it is possible for user to exist. check first
    User *foundUser = this->findUser(systemUsers, username);
    if (foundUser != NULL)
    {
        cout << "Error: Username " << username << " already exists" << endl;
        return false;
    }

    return true;
}

bool TransactionProcessor::accountTypeValid(string accountType)
{
    if (!(accountType.find_first_not_of(' ') != std::string::npos))
    {
        cout << "Error: Account type emtpy - a user must have an account type" << endl;
        return false;
    }
    else if ((accountType != "FS") && (accountType != "BS") && (accountType != "SS") && (accountType != "AA"))
    {
        cout << "Error: '" << accountType << "' is not a valid user account type" << endl;
        return false;
    }
    return true;
}

bool TransactionProcessor::initialCreditValid(string floatInput)
{
    if (!(floatInput.find_first_not_of(' ') != std::string::npos))
    {
        cout << "Error: Credit must be a numerical value. For no credit given, enter '0'" << endl;
        return false;
    }
    else if (floatInput.find_first_not_of("0123456789.") != std::string::npos)
    {
        cout << "Error: Credit cannot contain non-numeric characters and must be of the format #.## or #" << endl;
        return false;
    }

    float amount = stof(floatInput);
    if (amount > 999999)
    {
        cout << "Error: Credit cannot exceed 999999" << endl;
        return false;
    }

    return true;
}

bool TransactionProcessor::itemNameValid(string itemName)
{
    if (!(itemName.find_first_not_of(' ') != std::string::npos))
    {
        cout << "Error - Name cannot be NULL" << endl;
        return false;
    }
    else if (itemName.length() > 25)
    {
        cout << "Error - Max length of name is 25 characters" << endl;
        return false;
    }
    return true;
}

bool TransactionProcessor::minBidValid(string floatInput)
{
    if (!(floatInput.find_first_not_of(' ') != std::string::npos))
    {
        cout << "ERROR - Bid must be numeric" << endl;
        return false;
    }
    else if (floatInput.find_first_not_of("0123456789.") != std::string::npos)
    {
        cout << "ERROR - bid must be a numeric value" << endl;
        return false;
    }

    float amount = stof(floatInput);
    if (amount > 999.99)
    {
        cout << "ERROR - Maximum item price exceeded" << endl;
        return false;
    }

    return true;
}

bool TransactionProcessor::numDaysValid(string intInput)
{
    int numDays = stoi(intInput);
    if (numDays > 100)
    {
        cout << "ERROR - Max number of auction days is 100" << endl;
        return false;
    }
    return true;
}

bool TransactionProcessor::bidAmountValid(string floatInput, Item *item)
{

    if (!(floatInput.find_first_not_of(' ') != std::string::npos))
    {
        cout << "Error: Bid must be numeric and in the form of ### or ###.##" << endl;
        return false;
    }
    else if (floatInput.find_first_not_of("0123456789.") != std::string::npos)
    {
        cout << "Error: Bid must be numeric and in the form of ### or ###.##" << endl;
        return false;
    }

    float amount = stof(floatInput);
    if (amount > 999.00)
    {
        cout << "Error: Bid must not exceed 999.00 credits" << endl;
        return false;
    }
    else if (amount <= item->highestBid)
    {
        cout << "Error: Bid must be higher than current highest bid" << endl;
        return false;
    }
    else if (amount < (item->highestBid + (item->highestBid * 0.05)))
    {
        cout << "Error: Bid must be 5% higher than current highest bid" << endl;
        return false;
    }

    return true;
}

bool TransactionProcessor::refundAmountValid(string floatInput) {
    if (!(floatInput.find_first_not_of(' ') != std::string::npos))
    {
        cout << "Error: invalid credit amount" << endl;
        return false;
    }
    else if (floatInput.find_first_not_of("0123456789.") != std::string::npos)
    {
        cout << "Error: invalid credit amount" << endl;
        return false;
    }

    return true;
}


bool TransactionProcessor::creditValid(string floatInput)
{
    float amount = stof(floatInput);
    if (amount > 1000.00)
    {
        cout << "Error: Max amount exceeded per session. " << endl;
        return false;
    }
    // session amount check?

    return true;
}

TransactionProcessor::~TransactionProcessor() {}
