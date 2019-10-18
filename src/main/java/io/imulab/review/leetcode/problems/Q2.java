package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;

@Question(
    index = "2",
    name = "Add Two Numbers",
    links = {"https://leetcode-cn.com/problems/add-two-numbers/", ""},
    tags = {"linked list", "math"},
    level = Difficulty.MEDIUM
)
public class Q2 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + ((next != null) ? " -> " + next : "");
        }

        /**
         * Make a linked list of ListNode and return its head.
         * @param nums values
         * @return the head of the list
         */
        static ListNode make(int[] nums) {
            ListNode head = new ListNode(-1);
            ListNode cursor = head;
            for (int num : nums) {
                cursor.next = new ListNode(num);
                cursor = cursor.next;
            }
            return head.next;
        }
    }

    interface Solution {
        ListNode addTwoNumbers(ListNode l1, ListNode l2);
    }

    /**
     * The idea is to save memory usage by updating l1 directly. If l1 exhausted and l2 has not, link to
     * l2 and continue to update l2. We create at most one new node in this case (the potential carry on the most
     * significant bit in the end).
     */
    static class DefaultSolution implements Solution {
        @Override
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null)
                return l2;
            if (l2 == null)
                return l1;

            // keep track of head of l1 for the return value
            // keep track of tail of either l1 or l2: we might want to link tail of l1 to head of l2
            // in the case that l1 exhausted before l2; we might want to link tail of l2 to a new carry
            // node in the case that the last bit of l2 overflows.
            ListNode head = l1, tail = null;
            boolean carry = false;

            // while l1 has not exhausted
            while (l1 != null) {
                int i = l1.val, j = (l2 == null) ? 0 : l2.val;

                int m = i + j + (carry ? 1 : 0);
                carry = m >= 10;
                l1.val = m % 10;

                if (l2 != null)
                    l2 = l2.next;
                if (l1.next == null)
                    tail = l1;
                l1 = l1.next;
            }

            // if l1 has exhausted, but l2 has not exhausted
            if (l2 != null) {
                tail.next = l2;

                while (l2 != null) {
                    int m = l2.val + (carry ? 1 : 0);
                    carry = m >= 10;
                    l2.val = m % 10;

                    if (l2.next == null)
                        tail = l2;
                    l2 = l2.next;
                }
            }

            if (carry)
                tail.next = new ListNode(1);

            return head;
        }
    }

    public static void main(String[] args) {
        Solution solution = new DefaultSolution();

        ListNode l1 = ListNode.make(new int[]{2, 4, 3});
        System.out.println(l1.toString());

        ListNode l2 = ListNode.make(new int[]{5, 6, 4});
        System.out.println(l2.toString());

        ListNode l3 = solution.addTwoNumbers(l1, l2);
        System.out.println(l3.toString());
    }
}
