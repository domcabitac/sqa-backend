#include "Item.hpp"

Item::Item() {}

Item::Item(string itemName, string sellerName, float highestBid, string topBidderName, int daysLeft)
{
    this->name = itemName;
    this->sellerName = sellerName;
    this->highestBid = highestBid;
    this->topBidderName = topBidderName;
    this->daysLeft = daysLeft;
}

Item::~Item() {}

ostream& operator<<(ostream &os, Item &item) {
  os << "Item name: " << item.name << endl;
  os << "Seller's name: " << item.sellerName << endl;
  os << "Buyer's name: " << item.topBidderName << endl;
  os << "Days left: " << item.daysLeft << endl;
  os << "Highest bid: " << item.highestBid << endl;
  return os;
}
