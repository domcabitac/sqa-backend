#include <iostream>
#include <vector>
#include "Item.hpp"
#include "User.hpp"
#include <algorithm>

using namespace std;

const string User::FULL_STANDARD_STRING = "FS";
const string User::BUY_STANDARD_STRING = "BS";
const string User::SELL_STANDARD_STRING = "SS";
const string User::ADMIN_STRING = "AA";

User::User() {}

User::User(string username, string password, string accountType)
{
  this->username = username;
  this->password = password;
  this->accountType = accountType;
  this->creditAddedCurrentSession = 0;
  this->credit = 0.00;
  this->itemsListed = vector<Item>();
}

User::User(string username, string password, string accountType, float initialCredit)
{
  this->username = username;
  this->password = password;
  this->accountType = accountType;
  this->creditAddedCurrentSession = 0;
  this->credit = initialCredit;
  this->itemsListed = vector<Item>();
}

void User::updateCredit(float newCredit) {
  this->credit = newCredit;
}

bool User::operator==(const User &user) const {
  string usernameCopy = this->username;
  string compareCopy = user.username;
  
  // convert the username copies to lowercase without changing original
  transform(usernameCopy.begin(), usernameCopy.end(), usernameCopy.begin(), ::tolower);
  transform(compareCopy.begin(), compareCopy.end(), compareCopy.begin(), ::tolower);

  return usernameCopy == compareCopy;
}

ostream& operator<<(ostream &os, User &user) {
  os << "Username: " << user.username << endl;
  os << "Password: " << user.password << endl;
  os << "Account Type: " << user.accountType << endl;
  os << "Credit: " << user.credit << endl;
  os << "Items listed: " << endl;
  for (Item item : user.itemsListed) {
    os << item << endl;
  }
  return os;
}