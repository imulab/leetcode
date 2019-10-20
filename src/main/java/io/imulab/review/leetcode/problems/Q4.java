package io.imulab.review.leetcode.problems;

import io.imulab.review.leetcode.support.Difficulty;
import io.imulab.review.leetcode.support.Question;
import io.imulab.review.leetcode.support.Tag;

@Question(
    index = "4",
    name = "Median of two sorted arrays",
    links = {
        "https://leetcode-cn.com/problems/median-of-two-sorted-arrays/",
        "classpath:/resources/problems/Q4.png"
    },
    level = Difficulty.HARD,
    tags = {
        Tag.ARRAY,
        Tag.BINARY_SEARCH,
        Tag.DIVIDE_AND_CONQUER
    }
)
public class Q4 {

    interface Solution {
        double findMedianSortedArrays(int[] nums1, int[] nums2);
    }

    // For A and B with length m and n respectively, we would like to find A[i] and B[j], such
    // that i + j = m + n - i - j. This could be converted to j = (m + n) / 2 - i when m < n.
    // What we need to do is that to find index i within [0, m] so that B[j-1] <= A[i]
    // and A[i-1] <= B[j].
    static class DefaultSolution implements Solution {
        @Override
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // Assign A and B so that A.length <= B.length
            int[] A, B;
            if (nums1.length < nums2.length) {
                A = nums1;
                B = nums2;
            } else {
                A = nums2;
                B = nums1;
            }

            int m = A.length, n = B.length;
            int lower = 0, upper = m, halfLen = (m + n + 1) / 2;

            while (lower <= upper) {
                int i = (lower + upper) / 2;
                int j = halfLen - i;

                if (i < upper && B[j-1] > A[i]) {
                    // A[i] is too small, must increase i and (naturally) decrease B[j]
                    lower = i + 1;
                } else if (i > lower && A[i-1] > B[j]) {
                    // A[i] is too big, must decrease i and (naturally) increase B[j]
                    upper = i - 1;
                } else {
                    // i and consequently j is correct. Now we need to find the median which
                    // the number that equally divides A[i-1],B[j-1] and A[i],B[j].

                    // Find the maximum to the left of the median division. In case of odd number
                    // of total elements, maxLeft would be the median
                    int maxLeft = 0;
                    if (i == 0) {
                        // There is no A[i-1], and remember B[j-1] <= A[i]
                        maxLeft = B[j-1];
                    } else if (j == 0) {
                        // There is no B[j-1], and remember A[i-1] <= B[j]
                        maxLeft = A[i-1];
                    } else {
                        maxLeft = Math.max(A[i-1], B[j-1]);
                    }
                    if ((m + n) % 2 == 1)
                        return maxLeft;

                    // Find the minimum to the right of the median division.
                    int minRight = 0;
                    if (i == m) {
                        // There is no A[i], hence B[j] is the minimum to the right
                        minRight = B[j];
                    } else if (j == n) {
                        // There is no B[j], hence A[i] is the minimum to the right
                        minRight = A[i];
                    } else {
                        minRight = Math.min(A[i], B[j]);
                    }

                    return (maxLeft + minRight) / 2.0;
                }
            }

            return 0.0;
        }
    }
}
