#pragma once
#include <string>
#include <iostream>
using namespace std;
/*
 Item
 This class creates the item data.
 Item requires sellers name, the highest bid, the top bidders name
 and the number of days left.
*/
class Item
{
public:
    string name;
    string sellerName;
    float highestBid;
    string topBidderName;
    int daysLeft;

    Item();

    //Constructor to create an item object and initialize its variables.
    Item(string itemName, string sellerName, float highestBid, string topBidderName, int daysLeft);

    ~Item();

    friend ostream &operator<<(ostream &os, Item &item);
};
