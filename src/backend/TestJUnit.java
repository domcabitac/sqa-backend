import org.junit.*;
import java.io.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestJunit {
    public Vector<String> oldItemBuffer = new Vector<String>();
    public Vector<String> oldUserBuffer = new Vector<String>();
    public Vector<String> transactionsBuffer = new Vector<String>();
    public Vector<String> newUserBuffer = new Vector<String>();
    public Vector<String> newItemBuffer = new Vector<String>();
    public String transactionType;

    @Test
    public void ReadingUserFileTest() {
        OldUserFileReader u = new OldUserFileReader();
        Vector<String> testUser = new Vector<String>();
        
        testUser.add("Bob             AA 0000005.00 password");
        testUser.add("Test            FS 0020005.10 passward");
        testUser.add("John            FS 0000000.00 passward");
        testUser.add("Mart            BS 0000000.00 password");
        testUser.add("bobby           SS 0000000.00 password");
        testUser.add("END");

        assertEquals(testUser, u.readUserFile("current-user-accounts.txt"));
    }

    @Test
    public void ReadingItemFileTest() {
        OldItemFileReader i = new OldItemFileReader();
        Vector<String> testItem = new Vector<String>();

        testItem.add("macbook pro 16 i7 silver  John            Bob             900 700.00");
        testItem.add("Not a less paul guitaree  John            NULL            050 699.99");
        testItem.add("UOIT Water Bottle         John            NULL            020 599.99");
        testItem.add("UOIT Backpack             john            Bob             100 700.00");
        testItem.add("UOIT Backpack             john            Bob             100 700.00");
        testItem.add("END");

        assertEquals(testItem, i.readItemFile("available-items-test.txt"));
    }

    @Test
    public void ReadingTransFileTest() {
        TransactionReader t = new TransactionReader();
        Vector<String> testTrans = new Vector<String>();

        testTrans.add("04 macbook pro 16 i7 silver  john            Bob             700.00");
        testTrans.add("03 UOIT Backpack             john            Bob             100 700.00");
        assertEquals(testTrans, t.readMergedTransaction("daily-transactions.txt"));
    }

    // Not complete yet in OutputWriter.java
    // @Test
    // public void BuffingNewUserTest() {
    //     OutputWriter w = new OutputWriter();

    //     assertEquals("", w.bufferNewUsers());
    // }

    // @Test
    // public void BuffingNewItemTest() {
    //     OutputWriter w = new OutputWriter();
    //     Vector<String> newItemBuffer = new Vector<String>();

    //     newItemBuffer.add("macbook pro 16 i7 silver  John            Bob             900 700.00");
    //     String currentTransaction;
    //     assertEquals("", w.bufferNewItems(newItemBuffer, currentTransaction));
    // }

    // Testing, not working yet
    @Test
    public void determineTransactionTypeTest() {
        OutputWriter oWriter = new OutputWriter();
        Vector<String> testTransBuffer = new Vector<String>();
        Vector<String> testItemBuffer = new Vector<String>();
        Vector<String> testUserBuffer = new Vector<String>();

        testUserBuffer.add("Bob             AA 0000005.00 password");
        testUserBuffer.add("Test            FS 0020005.10 passward");
        testUserBuffer.add("John            FS 0000000.00 passward");
        testUserBuffer.add("Mart            BS 0000000.00 password");
        testUserBuffer.add("bobby           SS 0000000.00 password");
        testUserBuffer.add("END");
        
        testItemBuffer.add("macbook pro 16 i7 silver  John            Bob             900 700.00");
        testItemBuffer.add("Not a less paul guitaree  John            NULL            050 699.99");
        testItemBuffer.add("UOIT Water Bottle         John            NULL            020 599.99");
        testItemBuffer.add("UOIT Backpack             john            Bob             100 700.00");
        testItemBuffer.add("UOIT Backpack             john            Bob             100 700.00");
        testItemBuffer.add("END");

        testTransBuffer.add("04 macbook pro 16 i7 silver  john            Bob             700.00");
        testTransBuffer.add("03 UOIT Backpack             john            Bob             100 700.00");
    
        assertSame("",oWriter.determineTransactionType(testUserBuffer, testItemBuffer, testTransBuffer));
    }
    
    // @Test
    // public void WritingNewItemsTest() {
    //     OldItemFileReader i = new OldItemFileReader();
    //     OutputWriter w = new OutputWriter();

    //     Vector<String> oldItemBuffer = i.readItemFile("available-items-test.txt");


    //     assertEquals("Writing new items...", w.writeNewItems(oldItemBuffer, "available-items-test.txt"));
    // }

    // @Test
    // public void WritingNewUsersTest() {
    //     OldUserFileReader u = new OldUserFileReader();
    //     OutputWriter w = new OutputWriter();

    //     Vector<String> oldItemBuffer = u.readUserFile("current-user-accounts.txt");


    //     assertEquals("Writing new users...", w.writeNewUsers(oldUserBuffer, "current-user-accounts.txt"));
    // }
}