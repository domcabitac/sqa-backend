#pragma once
#include <iostream>
#include <vector>
#include "Item.hpp"
/* 
  User
  This class creates the user data
  User requires a username, password, credit,
  takes in a vector of items listed under the userename.
  requires also an account type.
  Depending on the account type, User can adversise, bid and add credit.
*/
using namespace std;

class User
{

public:
  static const string FULL_STANDARD_STRING;
  static const string BUY_STANDARD_STRING;
  static const string SELL_STANDARD_STRING;
  static const string ADMIN_STRING;
  string username;
  string password;
  float credit;
  vector<Item> itemsListed;
  string accountType;
  float creditAddedCurrentSession;

  // This is the default constructor.
  User();

  // Constructor to create a User object and initialize its variables.
  User(string username, string password, string accountType);

  User(string username, string password, string accountType, float initialCredit);

  void updateCredit(float newCredit);

  bool operator==(const User &user) const;
  
  friend ostream& operator<<(ostream &os, User &user);
};
