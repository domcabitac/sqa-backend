#include <iostream>
#include "FrontendSystem.hpp"
#include "UserReader.hpp"
#include "ItemReader.hpp"

using namespace std;

// this continously handles transactions commands from a user on standard terminal input
// and provides feedback to the user through the terminal as well
// It requires 2 command line arguemnets specifying the location of current items and current users
// it produces output to a daily-transactions file logging the transactions handled
int main(int argc, char *argv[])
{
  string itemFileName;
  string userFileName;
  string outputFileName;
  
  if (argc >= 4)
  {
    itemFileName = argv[1];
    userFileName = argv[2];
    outputFileName = argv[3];
  }

  FrontendSystem driver = FrontendSystem(itemFileName, userFileName, outputFileName);

  while (true)
  {
    string input;
    cout << "Enter Command: " << endl;
    getline(cin, input);
    if (input == "exit") {
      return 1;
    }
    driver.handleCommand(input);
  }
  return 0;
}