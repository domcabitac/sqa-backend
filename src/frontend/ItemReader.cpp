#include "ItemReader.hpp"
#include <sstream>
#include <fstream>
#include <iostream>
#include <iterator>

ItemReader::ItemReader() {}
ItemReader::~ItemReader() {}

vector<Item> ItemReader::createItems(string itemFileName)
{
    vector<Item> systemItems = vector<Item>();
    ifstream userFile(itemFileName);
    string line;

    while (getline(userFile, line))
    {
        if (line == "END")
        {
            break;
        }

        // get the item name and then remove item name from line
        int itemNameLength = 25;
        string itemName = line.substr(0, itemNameLength);
        line = line.substr(itemNameLength);

        // splits the text up from spaces and stores them as words in a vector
        istringstream iss(line);
        vector<string> tokens{istream_iterator<string>{iss}, istream_iterator<string>{}};

        string sellerName = tokens[0];
        string topBidderName = tokens[1];
        int daysLeft = stoi(tokens[2]);
        float highestBid = stof(tokens[3]);

        Item readItem = Item(itemName, sellerName, highestBid, topBidderName, daysLeft);
        systemItems.push_back(readItem);
    }

    return systemItems;
}
