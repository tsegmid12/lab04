package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
/**
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

  /**
 * This object constraction.

 */
    private IntQueue mQueue;
    /**
     * List.
     */
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
        mQueue = new ArrayIntQueue();

        final int num1 = 1;
        final int num2 = 2;
        final int num3 = 3;
        testList = new ArrayList<>(List.of(num1, num2, num3));
    }
    /**
     * Testing qeue is Empty.
     */
    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    /**
     * Testing qeue not empty.
     */
    @Test
    public void testNotEmpty() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertFalse(mQueue.isEmpty());
        }
    }
    /**
     * Testing peek is empty.
     */
    @Test
    public void testPeekEmptyQueue() {
       assertNull(mQueue.peek());
    }

    /**
     * Testing peek is not empty.
     */
    @Test
    public void testPeekNoEmptyQueue() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
        }
    }

    /**
     * Testing giving value in qeue.
     */
    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    /**
     * Testing take value in qeue.
     */
    @Test
    public void testDequeue() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(i), mQueue.dequeue());
        }
    }

    /**
     * Testing.
     */
    @Test
    public void testClear() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
        }
        mQueue.clear();

        assertTrue(mQueue.isEmpty());
        assertEquals(0, mQueue.size());
    }

    /**
     * testing size.
     */
    @Test
    public void testEnsureCapacity() {
    for (int i = 0; i < 20; i++) {
        mQueue.enqueue(i);

    }
    assertEquals(20, mQueue.size());
    assertFalse(mQueue.isEmpty());
}

    /**
     * test.
     */
    @Test
    public void testEmptyDeqeue() {
        assertNull(mQueue.dequeue());
    }

    /**
     * @throws IOException
     */
    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }
}
