#pragma once
#include <vector>
#include "User.hpp"

// Provides createUsers function for frontendsystem to create a representation of all user accounts
class UserReader
{
public:
    UserReader();
    ~UserReader();

    // creates all users by reading lines of the given filename
    // Inputs: the name of the file where the users are stored
    vector<User> createUsers(string usersFileName);
};