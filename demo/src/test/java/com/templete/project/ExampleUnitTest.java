package com.templete.project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test1() {
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine().trim();

        String s = "f fssdfsdfsdf";
        int size;
        if (s.contains(" ")) {
            int index = s.lastIndexOf(" ");
            size = s.substring(index + 1).length();
        } else {
            size = s.length();
        }
        System.out.println("last words size=" + size);
    }
}