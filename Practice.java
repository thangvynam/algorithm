package org.example;

import java.time.LocalDate;
import java.util.*;

public class Pactice {
    public static void main(String[] args) {

//        System.out.println(task1("BAAABAB"));
//        System.out.println(task1("BBABAA"));
//        System.out.println(task1("AABBBB"));

//        System.out.println(task2(new int[]{2,6,8,5}));
//        System.out.println(task2(new int[]{1,5,5,2,6}));
//        System.out.println(task2(new int[]{1,1}));

//        System.out.println(task3(16));
//        System.out.println(task3(19));
//        System.out.println(task3(7));

//        System.out.println(task4(new int[]{4,2,5,8,7,3,7}));
//        System.out.println(task4(new int[]{14,21,16,35,22}));
//        System.out.println(task4(new int[]{5,5,5,5,5,5}));

//        System.out.println(task5(new int[]{4,7,1,5,3}));
//        System.out.println(task5(new int[]{12,12,12,15,10}));
//        System.out.println(task5(new int[]{18,26,18,25,24,20,22}));

//        System.out.println(task6("><^v"));
//        System.out.println(task6("<<^<v>>"));
//        System.out.println(task6("><><"));

//        System.out.println(task7("acb"));
//        System.out.println(task7("hot"));
//        System.out.println(task7("codility"));
//        System.out.println(task7("aaaa"));

//        System.out.println(task8(new int[]{1,4,1}, new int[]{1,5,1}));
//        System.out.println(task8(new int[]{4,4,2,4}, new int[]{5,5,2,5}));
//        System.out.println(task8(new int[]{2,3,4,2}, new int[]{2,5,7,2}));

//        System.out.println(task9("ayxbx"));
//        System.out.println(task9("xzzzy"));
//        System.out.println(task9("toyxmy"));
//        System.out.println(task9("apple"));

//        System.out.println(task10(new int[]{1, 0, 1, 0, 1, 1}));
//        System.out.println(task10(new int[]{1, 1, 0, 1, 1}));
//        System.out.println(task10(new int[]{0, 1, 0}));
//        System.out.println(task10(new int[]{0, 1, 1, 0}));

//        System.out.println(Arrays.toString(task11("BAABA", new int[]{2, 4, 1, 1, 2})));
//        System.out.println(Arrays.toString(task11("ABAB", new int[]{10, 5, 10, 15})));
//        System.out.println(Arrays.toString(task11("B", new int[]{100})));

    }

    // consisting only of letters 'A' and 'B'. format 'A...AB..B'
    public static int task1(String s) {
        long totalA = s.chars().filter(ch -> ch == 'A').count();
        int countA = 0, countB = 0;
        long result = s.length();

        for (int i = 0; i < s.length() + 1; i++) {
            result = Math.min(result, countB + (totalA - countA));
            if (i < s.length()) {
                if (s.charAt(i) == 'A') {
                    countA++;
                } else {
                    countB++;
                }
            }
        }

        return Math.toIntExact(result);
    }

    // There are N blocks, numbered from 0 to N−1, arranged in a row. A couple of frogs were sitting together on one block when they had a terrible quarrel
    // another answer: https://gist.github.com/manuelgeek/96bbee166a8a8ce63c758f9e22416f53
    public static int task2(int[] blocks) {
        int len = blocks.length;
        int result = 0;

        for (int i = 0; i < len; i++) {
            int left = i;
            int right = i;
            int maxHeight = blocks[i];

            while (left > 0 && blocks[left - 1] >= maxHeight) {
                left--;
                maxHeight = blocks[left];
            }

            // Compute the longest distance to the right of the starting block
            while (right < len - 1 && blocks[right + 1] >= maxHeight) {
                // Increment right by 1
                right++;
                // Assign value at right to maxHeight
                maxHeight = blocks[right];
            }

            // Update the maximum distance if the current distance is greater
            result = Math.max(result, right - left + 1);
        }
        // Return the result
        return result;
    }

    // returns the smallest non-negative integer whose individual digits sum to N
    // https://www.geeksforgeeks.org/find-the-smallest-number-whose-sum-of-digits-is-n/
    public static double task3(int N) {
        return (N % 9 + 1) * Math.pow(10, (N / 9)) - 1;
    }

    public static double task3_2(int N) {
        if (N < 10) {
            return N;
        }

        StringBuilder result = new StringBuilder();
        while (N >= 9) {
            result.append(9);
            N -= 9;
        }

        if (N > 0) {
            result.append(N);
        }

        return Double.parseDouble(result.reverse().toString());
    }

    // You are given N numbers on a circle, described by an array A. Find the maximum number of neighbouring pairs
    public static int task4(int[] arrays) {
        int front = 0;
        int last = arrays.length - 1;
        int result = 0;

        if ((arrays[front] + arrays[last]) % 2 == 0) {
            front++;
            last--;
            result++;
        } else if ((arrays[front] + arrays[front + 1]) % 2 == 0) {
            front += 2;
            result++;
        }

        for (int i = front; i <= last; i++) {
            if (i + 1 <= last && (arrays[i] + arrays[i + 1]) % 2 == 0) {
                i++;
                result++;
            }
        }

        return result;
    }

    // There is an array A made of N integers. Your task is to choose as many integers from A as possible so that, when they are put in ascending order, all of the differences between all pairs of consecutive integers are equal
    public static int task5(int[] arr) {
        List<Integer> data = Arrays.stream(arr)
                .boxed()
                .toList();
        int result = 0;

        for (int step = 1; step <= 99; step++) {
            for (int i = 0; i < data.size(); ++i) {
                int curr = data.get(i);
                int cuLen = 0;
                while (data.contains(curr)) {
                    cuLen++;
                    curr += step;
                }
                result = Math.max(cuLen, result);
            }
        }

        int count = 1;
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i + 1).equals(data.get(i))) {
                count++;
            }
        }
        return Math.max(count, result);
    }

    // There are N players standing in a row, one player on a field. They are numbered from 0 to N−1 from left to right.
    public static int task6(String S) {
        int len = S.length();
        int result = 0;
        int[] count = new int[len];

        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            if (c == '^' || c == 'v') {
                result++;
                count[i] = 1;
            } else if (c == '>' && i == len - 1) {
                result++;
                break;
            } else if (c == '<' && (i == 0 || count[i - 1] == 1)) {
                result++;
                count[i] = 1;
            }
        }

        return result;
    }

    // Write a function solution that, given a string S consisting of N characters, returns the alphabetically smallest string that can be obtained by removing exactly one letter from S. Examples: 1. Given S= "acb"
    public static String task7(String S) {
        int length = S.length();
        int removeIndex = length - 1;
        StringBuilder result = new StringBuilder(S);

        for (int i = 0; i < length; i++) {
            char curChar = S.charAt(i);
            if (i + 1 < length) {
                char nextChar = S.charAt(i + 1);
                if (curChar > nextChar) {
                    removeIndex = i;
                    break;
                }
            }
        }

        return result.deleteCharAt(removeIndex).toString();
    }

    // A group of friends is going on holiday together. They have come to a meeting point(the start of the journey) using N cars
    // https://leetcode.com/discuss/interview-question/2058599/a-group-of-friends-is-going-on-holiday-together-codility-problem
    public static int task8(int[] P, int[] S) {
        Arrays.sort(S);

        // revert array
        for (int i = 0; i < S.length / 2; i++) {
            int t = S[i];
            S[i] = S[S.length - i - 1];
            S[S.length - i - 1] = t;
        }

        int i = 0;
        int totalPass = Arrays.stream(P).sum();
        while (totalPass > 0) {
            totalPass -= S[i];
            i++;
        }

        return i;
    }

    // You are given a string, S, consisting of lowercase English letters. A string is beautiful with respect to S if it can be derived from S by removing exactly 2 characters. Find and print the number of different strings that are beautiful with respect to S.
    public static int task9(String S) {
        int n = S.length();
        int res = 0;

        int[] x = new int[n];
        int[] y = new int[n];

        x[0] = S.charAt(0) == 'x' ? 1 : 0;
        y[0] = S.charAt(0) == 'y' ? 1 : 0;

        for (int i = 1; i < n; i++) {
            x[i] = x[i - 1] + (S.charAt(i) == 'x' ? 1 : 0);
            y[i] = y[i - 1] + (S.charAt(i) == 'y' ? 1 : 0);
        }

        for (int i = 0; i < n-1; i++) {
            if (x[i] == y[i] || x[n-1] - x[i] == y[n-1] - y[i]) {
                res++;
            }
        }

        return res;
    }

    // There are N coins, each showing either heads or tails. We would like all the coins to form a sequence of alternating heads and tails. What is the minimum number of coins that must be reversed to achieve this?
    // https://levelup.gitconnected.com/minimum-number-of-coins-that-must-be-reversed-to-achieve-alternating-sequence-of-heads-and-tails-632b72794964
    public static int task10(int[] A) {
        if (A.length == 0) {
            return 0; // or handle the case when the array is empty
        }

        int count1 = 0;
        int count2 = 0;

        for (int index = 0; index < A.length; index++) {
            if (index % 2 == 0) {
                if (A[index] == 1) {
                    count1 += 1;
                }
                if (A[index] == 0) {
                    count2 += 1;
                }
            } else {
                if (A[index] == 0) {
                    count1 += 1;
                }
                if (A[index] == 1) {
                    count2 += 1;
                }
            }
        }

        return Math.min(count1, count2);
    }

    // You are given a list of N transfers (numbered from 0 to N-1) between two banks: bank A and bank B
    public static int[] task11(String R, int[] V) {
        if (V.length != R.length()) {
            return new int[]{0, 0};
        }

        int[] result = new int[2];
        int bankA = 0, bankB = 0;

        for (int i = 0; i < V.length; i++) {
            if (R.charAt(i) == 'A') {
                bankA -= V[i]; 
                bankB += V[i]; 
                result[1] = Math.max(result[1], bankB);
            } else {
                bankA += V[i]; 
                bankB -= V[i]; 
                result[0] = Math.max(result[0], bankA);
            }
        }
        return result;
    }
}