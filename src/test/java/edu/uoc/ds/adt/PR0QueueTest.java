package edu.uoc.ds.adt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PR0QueueTest {

    PR0Queue pr0q;

    private void fillQueue() {
        for (int c = 1; c < 16; c++) {
            pr0q.add(c);
        }
    }

    @Before
    public void setUp() {
        this.pr0q = new PR0Queue();

        assertNotNull(this.pr0q.getQueue());
        fillQueue();
    }

    @After
    public void release() {
        this.pr0q = null;
    }


    @org.junit.Test
    public void queueTest() {
        assertEquals(this.pr0q.CAPACITY, this.pr0q.getQueue().size());
        Assert.assertEquals(1, pr0q.poll(), 0);
        Assert.assertEquals(4, pr0q.poll(), 0);
        Assert.assertEquals(9, pr0q.poll(), 0);
        Assert.assertEquals(0, pr0q.poll(), 0);
        Assert.assertEquals(1, pr0q.poll(), 0);
        Assert.assertEquals(4, pr0q.poll(), 0);
        Assert.assertEquals(9, pr0q.poll(), 0);
        Assert.assertEquals(0, pr0q.poll(), 0);
        Assert.assertEquals(1, pr0q.poll(), 0);
        Assert.assertEquals(4, pr0q.poll(), 0);
        Assert.assertEquals(9, pr0q.poll(), 0);
        Assert.assertEquals(0, pr0q.poll(), 0);
        Assert.assertEquals(1, pr0q.poll(), 0);
        Assert.assertEquals(4, pr0q.poll(), 0);
        Assert.assertEquals(9, pr0q.poll(), 0);
        assertEquals(0, this.pr0q.getQueue().size());
    }
}
