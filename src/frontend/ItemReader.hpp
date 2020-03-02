#pragma once
#include "Item.hpp"
#include <vector>
/*
 ItemReader
 This class has a vector called createItems which takes the
 itemFileName and stores them in systemItems
*/

class ItemReader
{
public:
    // This is the default constructor.
    ItemReader(/* args */);
    ~ItemReader();

    // function will read item name and create an Item and places it in a vector
    vector<Item> createItems(string itemFileName);
};
