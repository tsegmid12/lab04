package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
           mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        // TODO: write your own unit test
        for (Integer i : testList) {
            mQueue.enqueue(i);
            assertFalse(mQueue.isEmpty());
        }
    }

    @Test
    public void testPeekNoEmptyQueue() {
        // TODO: write your own unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(Integer.valueOf(testList.get(0)), mQueue.peek());
        }
    }

    @Test
public void testPeekEmptyQueue() {
   
        mQueue.peek();  // Try calling peek() on an empty queue
}


    // @Test
    // public void testPeekEmptyQueue() {
    //     assertNull(mQueue.peek());
    // }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        // TODO: write your own unit test
            for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(i);
            assertFalse(mQueue.isEmpty());
            assertEquals(Integer.valueOf(i), mQueue.dequeue());
            }
            assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testClear() {
    mQueue.enqueue(1);
    mQueue.enqueue(2);
    mQueue.enqueue(3);
    
    mQueue.clear();  // Clear the queue
    
    assertTrue(mQueue.isEmpty());  // Ensure the queue is empty after clear
    assertEquals(0, mQueue.size());  // Ensure the size is reset to 0
}

@Test
public void testDequeueEmptyQueue() {
    assertNull(mQueue.dequeue());  // Dequeue on an empty queue should return null
}

    @Test
    public void testEnsureCapacity() {
    for (int i = 0; i < 20; i++) {
        mQueue.enqueue(i);  // Add elements to trigger resizing
    }
    
    assertEquals(20, mQueue.size());  // Check that the queue contains 20 elements
    assertFalse(mQueue.isEmpty());   // Ensure the queue is not empty
}



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
