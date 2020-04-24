package implementations;

import org.junit.Test;

public class ArrayDequeTest {
   @Test
    public void test(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();

       System.out.println(deque.remove(Integer.valueOf(19)));
   }

}
