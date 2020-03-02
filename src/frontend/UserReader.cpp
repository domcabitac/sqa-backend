#include "UserReader.hpp"
#include <sstream>
#include <fstream>
#include <iostream>
#include <iterator>

UserReader::UserReader() {}
UserReader::~UserReader() {}

vector<User> UserReader::createUsers(string usersFileName)
{
    vector<User> systemUsers = vector<User>();
    ifstream userFile(usersFileName);
    string line;

    while (getline(userFile, line))
    {
        if (line == "END")
        {
            break;
        }

        // splits the text up from spaces and stores them as words in a vector
        istringstream iss(line);
        vector<string> tokens{istream_iterator<string>{iss}, istream_iterator<string>{}};

        string username = tokens[0];
        string accountType = tokens[1];
        string credits = tokens[2];
        string password = tokens[3];

        User readUser = User(username, password, accountType);
        readUser.credit = stof(credits);

        systemUsers.push_back(readUser);
    }

    return systemUsers;
}
