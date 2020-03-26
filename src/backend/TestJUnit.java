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
        String testUser = "[Bob             AA 0000005.00 password, Test            FS 0020005.10 passward, John            FS 0000000.00 passward, Mart            BS 0000000.00 password, bobby           SS 0000000.00 password, END]";

        assertEquals(testUser, u.readUserFile("current-user-accounts.txt").toString());
    }

    @Test
    public void ReadingItemFileTest() {
        OldItemFileReader i = new OldItemFileReader();
        String testItem = "[macbook pro 16 i7 silver  John            Bob             900 700.00, Not a less paul guitaree  John            NULL            050 699.99, UOIT Water Bottle         John            NULL            020 599.99, UOIT Backpack             john            Bob             100 700.00, UOIT Backpack             john            Bob             100 700.00, END]";

        assertEquals(testItem, i.readItemFile("available-items-test.txt").toString());
    }

    @Test
    public void ReadingTransFileTest() {
        TransactionReader t = new TransactionReader();
        String testTrans = "[04 macbook pro 16 i7 silver  john            Bob             700.00, 03 UOIT Backpack             john            Bob             100 700.00]";
        
        assertEquals(testTrans, t.readMergedTransaction("daily-transactions.txt").toString());
    }

    // Not complete yet in OutputWriter.java
    // @Test
    // public void BuffingNewUserTest() {
    //     OutputWriter w = new OutputWriter();

    //     assertEquals("", w.bufferNewUsers());
    // }

    @Test
    public void BuffingNewItemTest() {
        OutputWriter w = new OutputWriter();

        assertEquals("", w.bufferNewItems());
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