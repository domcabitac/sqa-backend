import org.junit.*;
import java.io.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.apache.commons.io.FileUtils;

public class TestJunit {
    public Vector<String> oldItemBuffer = new Vector<String>();
    public Vector<String> oldUserBuffer = new Vector<String>();
    public Vector<String> transactionsBuffer = new Vector<String>();
    public Vector<String> newUserBuffer = new Vector<String>();
    public Vector<String> newItemBuffer = new Vector<String>();
    public String transactionType;
    
    // Test to see if reader is reading and outputing the same data from .txt file
    // @Test
    // public void ReadingUserFileTest() {
    //     OldUserFileReader u = new OldUserFileReader();
    //     Vector<String> testUser = new Vector<String>();
        
    //     testUser.add("Bob             AA 000504.000 password");
    //     testUser.add("Test            FS 0020005.10 passward");
    //     testUser.add("John            FS 000496.000 passward");
    //     testUser.add("Mart            BS 0000000.00 password");
    //     testUser.add("bobby           SS 0000470.00 password");
    //     testUser.add("testerUser      BS 0001000.00 password");
    //     testUser.add("END");

    //     assertEquals(testUser, u.readUserFile("testingFolder/CUA1.txt"));
    // }

    // // Testing for reading invalid file
    // // Should be blank
    // @Test
    // public void ReadingUserFileTest2() {
    //     OldUserFileReader u = new OldUserFileReader();
    //     Vector<String> testUser = new Vector<String>();
        
    //     assertEquals(testUser, u.readUserFile("fakeUserslist.txt"));
    // }

    // // // Test to see if reader is reading and outputing the same data from .txt file
    // @Test
    // public void ReadingItemFileTest() {
    //     OldItemFileReader i = new OldItemFileReader();
    //     Vector<String> testItem = new Vector<String>();

    //         testItem.add("macbook pro 16 i7 silver  John            Bob             898 700.00");
    //         testItem.add("Not a less paul guitaree  John            NULL            058 699.99");
    //         testItem.add("UOIT Water Bottle         John            NULL            018 599.99");
    //         testItem.add("UOIT Backpack             john            Bob             089 700.00");
    //         testItem.add("UOIT Backpack             john            Bob             098 700.00");
    //         testItem.add("END");

    //     assertEquals(testItem, i.readItemFile("testingFolder/AI1.txt"));
    // }

    // // Testing for reading invalid file
    // // Should be blank
    // @Test
    // public void ReadingItemFileTest2() {
    //     OldItemFileReader i = new OldItemFileReader();
    //     Vector<String> testItem = new Vector<String>();

    //     assertEquals(testItem, i.readItemFile("fakeItemsList.txt"));
    // }
    
    // // Test to see if reader is reading and outputing the same data from .txt file
    // @Test
    // public void ReadingTransactionFileTest() {
    //     TransactionReader i = new TransactionReader();
    //     Vector<String> testTrans = new Vector<String>();

    //     testTrans.add("01_testUser________FS_000000500");
    //     testTrans.add("02_testUser________FS_000000500");
    //     testTrans.add("01_testerUser______BS_000000000");
    //     testTrans.add("06_testerUser______BS_000000500");
    //     testTrans.add("03_amazing_guitar____________admin___________050_050.00");
    //     testTrans.add("05_John____________Bob_____________000002.00");
    //     testTrans.add("04_UOIT_Backpack_____________johnsmith_______admin___________010.00");
    //     testTrans.add("00");

    //     assertEquals(testTrans, i.readMergedTransaction("testingFolder/DT1.txt"));
    // }

    // // Testing for reading invalid file
    // // Should be blank
    // @Test
    // public void ReadingTransactionFileTest2() {
    //     TransactionReader i = new TransactionReader();
    //     Vector<String> testTrans2 = new Vector<String>();

    //     assertEquals(testTrans2, i.readMergedTransaction("fakeDailyTrans.txt"));
    // }
    
    // // Test to see if writer is outputing right values
    // @Test
    // public void writeNewUserTest() {
    //     OutputWriter oWriter = new OutputWriter();
    //     Vector<String> testTransBuffer = new Vector<String>();
    //     Vector<String> testUserBuffer = new Vector<String>();

    //     testUserBuffer.add("Bob             AA 0000600.00 password");
    //     testUserBuffer.add("Test            FS 0020005.10 passward");
    //     testUserBuffer.add("John            FS 0000000.00 passward");
    //     testUserBuffer.add("Mart            BS 0000000.00 password");
    //     testUserBuffer.add("bobby           BS 0000500.00 password");
    //     testUserBuffer.add("testerUser      BS 0010000.00 password");
    //     testUserBuffer.add("testUser        FS 0000005.00 password");
        
    //     testTransBuffer.add("01_testUser________FS_000000500");
    
    //     assertEquals(testUserBuffer, oWriter.writeNewUsers(testTransBuffer, testUserBuffer, "testingFolder/CUA2.txt"));
    // }

    // // For testing purposes, make sure avaliable-items-test.txt is open so you can undo after you run the testing code
    // @Test
    // public void writeNewItemTest() {
    //     OutputWriter iWriter = new OutputWriter();
    //     Vector<String> testTransBuffer = new Vector<String>();
    //     Vector<String> testItemBuffer = new Vector<String>();
    //     Vector<String> testUserBuffer = new Vector<String>();

    //     testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
    //     testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
    //     testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
    //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    //     testItemBuffer.add("END");
        
    //     testUserBuffer.add("Bob             AA 0000600.00 password");
    //     testUserBuffer.add("Test            FS 0020005.10 passward");
    //     testUserBuffer.add("John            FS 0000000.00 passward");
    //     testUserBuffer.add("Mart            BS 0000000.00 password");
    //     testUserBuffer.add("bobby           BS 0000500.00 password");
    //     testUserBuffer.add("testerUser      BS 0010000.00 password");
    //     testUserBuffer.add("testUser        FS 0000005.00 password");

    //     testTransBuffer.add("01_testUser________FS_000000500");
    //     testTransBuffer.add("02_testUser________FS_000000500");
    //     testTransBuffer.add("01_testerUser______BS_000000000");
    //     testTransBuffer.add("06_testerUser______BS_000000500");
    //     testTransBuffer.add("03_amazing_guitar____________admin___________050_050.00");
    //     testTransBuffer.add("05_John____________Bob_____________000002.00");
    //     testTransBuffer.add("04_UOIT_Backpack_____________johnsmith_______admin___________010.00");
    //     testTransBuffer.add("00");
    
    //     assertEquals(testItemBuffer, iWriter.writeNewItems(testTransBuffer, testItemBuffer, testUserBuffer, "testingFolder/AI2.txt"));
    // }

    // // Delete User who does not exist
    // @Test
    // public void bufferNewUserDeleteDoesNotExistTest() {
    //     OutputWriter oWriter = new OutputWriter();
    //     OldUserFileReader uReader = new OldUserFileReader();
    //     Vector<String> testUserBuffer = new Vector<String>();
    //     Vector<String> testNewUserBuffer = new Vector<String>();
    //     Vector<String> testItemBuffer = new Vector<String>();
    //     Vector<String> testTransBuffer = new Vector<String>();

    //     Vector<String> oldUserBuffer = uReader.readUserFile("testingFolder/CUA3.txt");
    //     Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);

    //     testTransBuffer.add("02_testUser________FS_000000500");

    //     testUserBuffer.add("Bob             AA 000504.000 password");
    //     testUserBuffer.add("Test            FS 0020005.10 passward");
    //     testUserBuffer.add("John            FS 000496.000 passward");
    //     testUserBuffer.add("Mart            BS 0000000.00 password");
    //     testUserBuffer.add("bobby           SS 0000470.00 password");
    //     testUserBuffer.add("testerUser      BS 0001000.00 password");

    //     testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
    //     testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
    //     testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
    //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    //     testItemBuffer.add("END");
                
    //     oWriter.determineTransactionType(newUserBuffer, testItemBuffer, testTransBuffer);

    //     assertEquals(testUserBuffer, oWriter.writeNewUsers(testTransBuffer, newUserBuffer, "testingFolder/CUA3.txt"));
    // }
    // // // Delete User successfull
    // //     @Test
    // //     public void bufferNewUserDeleteTest() {
    // //         OutputWriter oWriter = new OutputWriter();
    // //         OldUserFileReader uReader = new OldUserFileReader();
    // //         Vector<String> testUserBuffer = new Vector<String>();
    // //         Vector<String> testUserExpectedBuffer = new Vector<String>();
    // //         Vector<String> testNewUserBuffer = new Vector<String>();
    // //         Vector<String> testItemBuffer = new Vector<String>();
    // //         Vector<String> testTransBuffer = new Vector<String>();
        
    // //         testTransBuffer.add("02_testUser________FS_000000500");
    
    // //         testUserExpectedBuffer.add("Bob             AA 000504.000 password");
    // //         testUserExpectedBuffer.add("Test            FS 0020005.10 passward");
    // //         testUserExpectedBuffer.add("John            FS 000496.000 passward");
    // //         testUserExpectedBuffer.add("Mart            BS 0000000.00 password");
    // //         testUserExpectedBuffer.add("bobby           SS 0000470.00 password");
    // //         testUserExpectedBuffer.add("testerUser      BS 0001000.00 password");

    // //         Vector<String> oldUserBuffer = uReader.readUserFile("testingFolder/CUA4.txt");
    // //         Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);

    // //         testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
    // //         testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
    // //         testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
    // //         testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    // //         testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    // //         testItemBuffer.add("END");
                    
    // //         oWriter.determineTransactionType(newUserBuffer, testItemBuffer, testTransBuffer);
    
    // //         assertEquals(testUserExpectedBuffer, oWriter.writeNewUsers(testTransBuffer, newUserBuffer, "testingFolder/CUA4.txt"));

    // //     }

    //         // Trys to make new user but existing already 
    //         @Test
    //         public void bufferNewUserExisitingTest() {
    //             OutputWriter oWriter = new OutputWriter();
    //             OldUserFileReader uReader = new OldUserFileReader();
    //             Vector<String> testUserBuffer = new Vector<String>();
    //             Vector<String> testUserExpectedBuffer = new Vector<String>();
    //             Vector<String> testNewUserBuffer = new Vector<String>();
    //             Vector<String> testItemBuffer = new Vector<String>();
    //             Vector<String> testTransBuffer = new Vector<String>();
            
    //             testTransBuffer.add("01_testUser________FS_000000500");
        
    //             testUserExpectedBuffer.add("Bob             AA 000504.000 password");
    //             testUserExpectedBuffer.add("Test            FS 0020005.10 passward");
    //             testUserExpectedBuffer.add("John            FS 000496.000 passward");
    //             testUserExpectedBuffer.add("Mart            BS 0000000.00 password");
    //             testUserExpectedBuffer.add("bobby           SS 0000470.00 password");
    //             testUserExpectedBuffer.add("testerUser      BS 0001000.00 password");
    //             testUserExpectedBuffer.add("testUser        FS 0000005.00 password");

    //             Vector<String> oldUserBuffer = uReader.readUserFile("testingFolder/CUA5.txt");
    //             Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);
    
    //             testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
    //             testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
    //             testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
    //             testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    //             testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    //             testItemBuffer.add("END");
                        
    //             oWriter.determineTransactionType(newUserBuffer, testItemBuffer, testTransBuffer);
        
    //             assertEquals(testUserExpectedBuffer, oWriter.writeNewUsers(testTransBuffer, newUserBuffer, "testingFolder/CUA5.txt"));
    
    //         }

    //         // Refund user successfull
    //         @Test
    //         public void bufferNewUserRefundTest() {
    //             OutputWriter oWriter = new OutputWriter();
    //             OldUserFileReader uReader = new OldUserFileReader();
    //             Vector<String> testUserBuffer = new Vector<String>();
    //             Vector<String> testUserExpectedBuffer = new Vector<String>();
    //             Vector<String> testNewUserBuffer = new Vector<String>();
    //             Vector<String> testItemBuffer = new Vector<String>();
    //             Vector<String> testTransBuffer = new Vector<String>();
            
    //             testTransBuffer.add("05_John____________Bob_____________000002.00");
        
    //             testUserExpectedBuffer.add("Bob             AA 000506.000 password");
    //             testUserExpectedBuffer.add("Test            FS 0020005.10 passward");
    //             testUserExpectedBuffer.add("John            FS 000494.000 passward");
    //             testUserExpectedBuffer.add("Mart            BS 0000000.00 password");
    //             testUserExpectedBuffer.add("bobby           SS 0000470.00 password");
    //             testUserExpectedBuffer.add("testerUser      BS 0001000.00 password");
    //             testUserExpectedBuffer.add("testUser        FS 0000005.00 password");

    //             Vector<String> oldUserBuffer = uReader.readUserFile("testingFolder/CUA6.txt");
    //             Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);
    
    //             testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
    //             testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
    //             testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
    //             testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    //             testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
    //             testItemBuffer.add("END");
                        
    //             oWriter.determineTransactionType(newUserBuffer, testItemBuffer, testTransBuffer);
        
    //             assertEquals(testUserExpectedBuffer, oWriter.writeNewUsers(testTransBuffer, newUserBuffer, "testingFolder/CUA6.txt"));
    
    //         } 

            // Refund seller cannot afford to fund
            // @Test
            // public void bufferNewUserRefundSellerLowTest() {
            //     OutputWriter oWriter = new OutputWriter();
            //     OldUserFileReader uReader = new OldUserFileReader();
            //     Vector<String> testUserBuffer = new Vector<String>();
            //     Vector<String> testUserExpectedBuffer = new Vector<String>();
            //     Vector<String> testNewUserBuffer = new Vector<String>();
            //     Vector<String> testItemBuffer = new Vector<String>();
            //     Vector<String> testTransBuffer = new Vector<String>();
            
            //     testTransBuffer.add("05_John____________Bob_____________000002.00");
        
            //     testUserExpectedBuffer.add("Bob             AA 000000.000 password");
            //     testUserExpectedBuffer.add("Test            FS 0020005.10 passward");
            //     testUserExpectedBuffer.add("John            FS 000494.000 passward");
            //     testUserExpectedBuffer.add("Mart            BS 0000000.00 password");
            //     testUserExpectedBuffer.add("bobby           SS 0000470.00 password");
            //     testUserExpectedBuffer.add("testerUser      BS 0001000.00 password");
            //     testUserExpectedBuffer.add("testUser        FS 0000005.00 password");

            //     Vector<String> oldUserBuffer = uReader.readUserFile("testingFolder/CUA7.txt");
            //     Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);
    
            //     testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
            //     testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
            //     testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("END");
                        
            //     oWriter.determineTransactionType(newUserBuffer, testItemBuffer, testTransBuffer);
        
            //     assertEquals(testUserExpectedBuffer, oWriter.writeNewUsers(testTransBuffer, newUserBuffer, "testingFolder/CUA7.txt"));
    
            // } 

            // // Refund buyer at max credits
            // @Test
            // public void bufferNewUserRefundBuyerMaxTest() {
            //     OutputWriter oWriter = new OutputWriter();
            //     OldUserFileReader uReader = new OldUserFileReader();
            //     Vector<String> testUserBuffer = new Vector<String>();
            //     Vector<String> testUserExpectedBuffer = new Vector<String>();
            //     Vector<String> testNewUserBuffer = new Vector<String>();
            //     Vector<String> testItemBuffer = new Vector<String>();
            //     Vector<String> testTransBuffer = new Vector<String>();
            
            //     testTransBuffer.add("05_John____________Bob_____________000002.00");
        
            //     testUserExpectedBuffer.add("Bob             AA 0000508.00 password");
            //     testUserExpectedBuffer.add("Test            FS 0020005.10 passward");
            //     testUserExpectedBuffer.add("John            FS 9999999.99 passward");
            //     testUserExpectedBuffer.add("Mart            BS 0000000.00 password");
            //     testUserExpectedBuffer.add("bobby           SS 0000470.00 password");
            //     testUserExpectedBuffer.add("testerUser      BS 0001000.00 password");
            //     testUserExpectedBuffer.add("testUser        FS 0000005.00 password");

            //     Vector<String> oldUserBuffer = uReader.readUserFile("testingFolder/CUA8.txt");
            //     Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);
    
            //     testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
            //     testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
            //     testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("END");
                        
            //     oWriter.determineTransactionType(newUserBuffer, testItemBuffer, testTransBuffer);
        
            //     assertEquals(testUserExpectedBuffer, oWriter.writeNewUsers(testTransBuffer, newUserBuffer, "testingFolder/CUA8.txt"));
    
            // } 

            // // Successfully add credit
            // @Test
            // public void bufferNewUserAddCreditTest() {
            //     OutputWriter oWriter = new OutputWriter();
            //     OldUserFileReader uReader = new OldUserFileReader();
            //     Vector<String> testUserBuffer = new Vector<String>();
            //     Vector<String> testUserExpectedBuffer = new Vector<String>();
            //     Vector<String> testNewUserBuffer = new Vector<String>();
            //     Vector<String> testItemBuffer = new Vector<String>();
            //     Vector<String> testTransBuffer = new Vector<String>();
            
            //     testTransBuffer.add("06_testerUser______BS_000000500");
        
            //     testUserExpectedBuffer.add("Bob             AA 0000508.00 password");
            //     testUserExpectedBuffer.add("Test            FS 0020005.10 passward");
            //     testUserExpectedBuffer.add("John            FS 9999999.99 passward");
            //     testUserExpectedBuffer.add("Mart            BS 0000000.00 password");
            //     testUserExpectedBuffer.add("bobby           SS 0000470.00 password");
            //     testUserExpectedBuffer.add("testerUser      BS 0001500.00 password");
            //     testUserExpectedBuffer.add("testUser        FS 0000005.00 password");

            //     Vector<String> oldUserBuffer = uReader.readUserFile("testingFolder/CUA9.txt");
            //     Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);
    
            //     testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
            //     testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
            //     testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("END");
                        
            //     oWriter.determineTransactionType(newUserBuffer, testItemBuffer, testTransBuffer);
        
            //     assertEquals(testUserExpectedBuffer, oWriter.writeNewUsers(testTransBuffer, newUserBuffer, "testingFolder/CUA9.txt"));
    
            // } 

            // // Add credit to non existant user
            // @Test
            // public void bufferNewUserAddCreditNATest() {
            //     OutputWriter oWriter = new OutputWriter();
            //     OldUserFileReader uReader = new OldUserFileReader();
            //     Vector<String> testUserBuffer = new Vector<String>();
            //     Vector<String> testUserExpectedBuffer = new Vector<String>();
            //     Vector<String> testNewUserBuffer = new Vector<String>();
            //     Vector<String> testItemBuffer = new Vector<String>();
            //     Vector<String> testTransBuffer = new Vector<String>();
            
            //     testTransBuffer.add("06_testARUser______BS_000000500");
        
            //     testUserExpectedBuffer.add("Bob             AA 0000508.00 password");
            //     testUserExpectedBuffer.add("Test            FS 0020005.10 passward");
            //     testUserExpectedBuffer.add("John            FS 9999999.99 passward");
            //     testUserExpectedBuffer.add("Mart            BS 0000000.00 password");
            //     testUserExpectedBuffer.add("bobby           SS 0000470.00 password");
            //     testUserExpectedBuffer.add("testerUser      BS 0001000.00 password");
            //     testUserExpectedBuffer.add("testUser        FS 0000005.00 password");

            //     Vector<String> oldUserBuffer = uReader.readUserFile("testingFolder/CUA10.txt");
            //     Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);
    
            //     testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
            //     testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
            //     testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("END");
                        
            //     oWriter.determineTransactionType(newUserBuffer, testItemBuffer, testTransBuffer);
        
            //     assertEquals(testUserExpectedBuffer, oWriter.writeNewUsers(testTransBuffer, newUserBuffer, "testingFolder/CUA10.txt"));
    
            // }
            
            // // Add credit to user with max credit
            // @Test
            // public void bufferNewUserAddCreditMaxTest() {
            //     OutputWriter oWriter = new OutputWriter();
            //     OldUserFileReader uReader = new OldUserFileReader();
            //     Vector<String> testUserBuffer = new Vector<String>();
            //     Vector<String> testUserExpectedBuffer = new Vector<String>();
            //     Vector<String> testNewUserBuffer = new Vector<String>();
            //     Vector<String> testItemBuffer = new Vector<String>();
            //     Vector<String> testTransBuffer = new Vector<String>();
            
            //     testTransBuffer.add("06_John____________BS_000000500");
        
            //     testUserExpectedBuffer.add("Bob             AA 0000508.00 password");
            //     testUserExpectedBuffer.add("Test            FS 0020005.10 passward");
            //     testUserExpectedBuffer.add("John            FS 9999999.99 passward");
            //     testUserExpectedBuffer.add("Mart            BS 0000000.00 password");
            //     testUserExpectedBuffer.add("bobby           SS 0000470.00 password");
            //     testUserExpectedBuffer.add("testerUser      BS 0001000.00 password");
            //     testUserExpectedBuffer.add("testUser        FS 0000005.00 password");

            //     Vector<String> oldUserBuffer = uReader.readUserFile("testingFolder/CUA11.txt");
            //     Vector<String> newUserBuffer = new Vector<String>(oldUserBuffer);
    
            //     testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
            //     testItemBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
            //     testItemBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            //     testItemBuffer.add("END");
                        
            //     oWriter.determineTransactionType(newUserBuffer, testItemBuffer, testTransBuffer);
        
                // assertEquals(testUserExpectedBuffer, oWriter.writeNewUsers(testTransBuffer, newUserBuffer, "testingFolder/CUA11.txt"));
    
            // }
        // Currently working on it
        @Test
        public void bufferNewItemsTest(){
            OutputWriter oWriter = new OutputWriter();
            OldItemFileReader iReader = new OldItemFileReader();
            Vector<String> testUserBuffer = new Vector<String>();
            Vector<String> testItemExpectedBuffer = new Vector<String>();
            Vector<String> testNewUserBuffer = new Vector<String>();
            Vector<String> testItemBuffer = new Vector<String>();
            Vector<String> testTransBuffer = new Vector<String>();
        
            testTransBuffer.add("03_amazing_guitar____________admin___________050_050.00");
    
            testUserBuffer.add("Bob             AA 0000508.00 password");
            testUserBuffer.add("Test            FS 0020005.10 passward");
            testUserBuffer.add("John            FS 9999999.99 passward");
            testUserBuffer.add("Mart            BS 0000000.00 password");
            testUserBuffer.add("bobby           SS 0000470.00 password");
            testUserBuffer.add("testerUser      BS 0001000.00 password");
            testUserBuffer.add("testUser        FS 0000005.00 password");

            testItemExpectedBuffer.add("macbook pro 16 i7 silver  John            Bob             899 700.00");
            testItemExpectedBuffer.add("Not a less paul guitaree  John            NULL            049 699.99");
            testItemExpectedBuffer.add("UOIT Water Bottle         John            NULL            019 599.99");
            testItemExpectedBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            testItemExpectedBuffer.add("UOIT Backpack             john            Bob             099 700.00");
            testItemExpectedBuffer.add("amazing guitar            John            Bob             050 050.00");
            testItemExpectedBuffer.add("END");

            Vector<String> oldItemBuffer = iReader.readItemFile("testingFolder/AI3.txt");
            Vector<String> newItemBuffer = new Vector<String>(oldItemBuffer);
                    
            oWriter.determineTransactionType(testUserBuffer, newItemBuffer, testTransBuffer);
    
            assertEquals(testItemExpectedBuffer, oWriter.writeNewItems(testTransBuffer, newItemBuffer, testUserBuffer, "testingFolder/AI3.txt"));

        }
}
