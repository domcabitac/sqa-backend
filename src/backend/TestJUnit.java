// Currently does not work, not running on Mac OSX

import org.junit.Test;
import java.io.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestJUnit {
    public Vector<String> oldItemBuffer = new Vector<String>();
    public Vector<String> oldUserBuffer = new Vector<String>();
    public Vector<String> transactionsBuffer = new Vector<String>();
    public Vector<String> newUserBuffer = new Vector<String>();
    public Vector<String> newItemBuffer = new Vector<String>();
    public String transactionType;

    @Test
    public void ReadingUserFileTest() {
        OldUserFileReader u = new OldUserFileReader();

        assertEquals("", u.readUserFile("current-user-accounts.txt"));
    }

    @Test
    public void ReadingItemFileTest() {
        OldItemFileReader i = new OldItemFileReader();

        assertEquals("", i.readItemFile("available-items-test.txt"));
    }

    @Test
    public void ReadingTransFileTest() {
        TransactionReader t = new TransactionReader();

        assertEquals("", t.readMergedTransaction("daily-transactions.txt"));
    }

    @Test
    public void BuffingNewUserTest() {
        OutputWriter w = new OutputWriter();

        assertEquals("", w.bufferNewUsers());
    }

    @Test
    public void BuffingNewItemTest() {
        OutputWriter w = new OutputWriter();

        assertEquals("", w.bufferNewItems());
    }

    @Test
    public void WritingNewItemsTest() {
        OldItemFileReader i = new OldItemFileReader();
        OutputWriter w = new OutputWriter();

        Vector<String> oldItemBuffer = i.readItemFile("available-items-test.txt");


        assertEquals("Writing new items...", w.writeNewItems(oldItemBuffer, "available-items-test.txt"));
    }

    @Test
    public void WritingNewUsersTest() {
        OldUserFileReader u = new OldUserFileReader();
        OutputWriter w = new OutputWriter();

        Vector<String> oldItemBuffer = u.readUserFile("current-user-accounts.txt");


        assertEquals("Writing new users...", w.writeNewUsers(oldUserBuffer, "current-user-accounts.txt"));
    }
}