package edu.uoc.ds.adt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PR0StackTest {

    PR0Stack pr0q;

    private void fillStack() {
        for (int c = 1; c < 16; c++) {
            pr0q.push(c);
        }
    }

    @Before
    public void setUp() {
        this.pr0q = new PR0Stack();

        assertNotNull(this.pr0q.getStack());
        this.fillStack();

    }

    @After
    public void release() {
        this.pr0q = null;
    }


    @org.junit.Test
    public void stackTest() {
        assertEquals(this.pr0q.CAPACITY, this.pr0q.getStack().size());
        Assert.assertEquals(9, pr0q.pop(), 0);
        Assert.assertEquals(4, pr0q.pop(), 0);
        Assert.assertEquals(1, pr0q.pop(), 0);
        Assert.assertEquals(0, pr0q.pop(), 0);
        Assert.assertEquals(9, pr0q.pop(), 0);
        Assert.assertEquals(4, pr0q.pop(), 0);
        Assert.assertEquals(1, pr0q.pop(), 0);
        Assert.assertEquals(0, pr0q.pop(), 0);
        Assert.assertEquals(9, pr0q.pop(), 0);
        Assert.assertEquals(4, pr0q.pop(), 0);
        Assert.assertEquals(1, pr0q.pop(), 0);
        Assert.assertEquals(0, pr0q.pop(), 0);
        Assert.assertEquals(9, pr0q.pop(), 0);
        Assert.assertEquals(4, pr0q.pop(), 0);
        Assert.assertEquals(1, pr0q.pop(), 0);
        assertEquals(0, this.pr0q.getStack().size());
    }
}
