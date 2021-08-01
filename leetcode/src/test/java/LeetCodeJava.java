


import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;


public class LeetCodeJava {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node = createNode(new int[]{1, 2, 5, 8, 11, 20, 30});
        ListNode node2 = createNode(new int[]{4, 5, 5, 8, 11, 20, 30});
        printNode(node);

        System.out.println(divide(2147483647, 3));
    }

    @Test
    public void test() {

    }

    public static ListNode createNode(int[] array) {
        ListNode head = new ListNode(0);
        ListNode c = head;
        for (int i = 0; i < array.length; i++) {
            c.next = new ListNode(array[i]);
            c = c.next;
        }
        return head;
    }

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     * <p>
     * 示例1：
     * <p>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * <p>
     * [1,2,4]
     * [1,3,4]
     */

    public static void printNode(ListNode l1) {
        System.out.println("printNode " + l1.toString());
        while (l1 != null) {
            System.out.println(l1.val);
            l1 = l1.next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode result = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                result.next = l2;
                l2 = l2.next;
            } else {
                result.next = l1;
                l1 = l1.next;
            }
            result = result.next;
        }

        if (l1 != null) {
            result.next = l1;
        } else {
            result.next = l2;
        }
        return head.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode temp;
        while (head != null) {
            temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     *  
     * <p>
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue queue = new ArrayDeque();
        if (root != null) {
            queue.add(root);
        }
        visit(lists, queue);
        return lists;
    }

    private void visit(List<List<Integer>> lists, Queue<TreeNode> queue) {
        List<Integer> integers = new ArrayList<>();
        int n = queue.size();
        for (int i = 0; i < n; i++) {
            TreeNode poll = queue.poll();
            if (poll != null) {
                integers.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }

        if (integers.size() > 0) {
            lists.add(integers);
        }
        if (n > 0) {
            visit(lists, queue);
        }

    }

    public int minMutation(String start, String end, String[] bank) {
        return getArray(start, end, bank);
    }

    //"AACCGGTT"
//        "AAACGGTA"
//        ["AACCGGTA","AACCGCTA","AAACGGTA"]
//
//            "AACCTTGG"
//            "AATTCCGG"
//            "AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"]
    private int getArray(String start, String end, String[] bank) {
        StringBuilder startSb = new StringBuilder(start);
        HashSet set = new HashSet<String>();
        for (int i = 0; i < bank.length; i++) {
            set.add(bank[i]);
        }
        int min = -1;
        int count = 0;
        for (int j = 0; j < startSb.length(); j++) {
            if (startSb.charAt(j) == end.charAt(j)) {
                continue;
            }
            if (end.charAt(j) != startSb.charAt(j)) {
                startSb.replace(j, j + 1, String.valueOf(end.charAt(j)));
                System.out.println("replace" + startSb + " " + !set.contains(startSb.toString()));
                if (!set.contains(startSb.toString())) {
                    count = -1;
                    break;
                }
                count++;
            }

        }
        System.out.println("min== " + min + " count " + count);
        if (min == -1) {

            min = count;
        } else {
            if (count != -1) {
                min = Math.min(count, min);
            }
        }


        return min;
    }

    /**
     * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
     * <p>
     * 进阶：你可以在 O(n) 的时间解决这个问题吗
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

//    public int findMaximumXOR(int[] nums) {
//
//    }


    /**
     * 找出数组中重复的数字。
     * <p>
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 示例 1：
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int findRepeatNumber(int[] nums) {
        int i = 0;
        int temp;
        while (i < nums.length) {
            if (nums[i] != i) {
                temp = nums[nums[i]];
                if (temp == nums[i]) {
                    return temp;
                }
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
        return 0;
    }


    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */
    public List<String> letterCombinations(String digits) {
        ArrayList<String> list = new ArrayList<>();
        char[] resultChars = new char[digits.length()];
        append(digits, 0, resultChars, list);
        return list;
    }

    String str = new String();

    public void append(String digits, int index, char[] currentString, ArrayList<String> list) {
        if (index >= digits.length()) {

            if (!str.isEmpty()) {
                list.add(str);
            }
            return;
        }
        char c = digits.charAt(index);
        char[] chars = change2CharArray(c);
        for (char currentC : chars) {
            int currentIndex = index;
            currentString[currentIndex] = currentC;
            append(digits, ++currentIndex, currentString, list);
        }
    }

    private char[] two = new char[]{'a', 'b', 'c'};
    private char[] three = new char[]{'d', 'e', 'f'};
    private char[] four = new char[]{'g', 'h', 'i'};
    private char[] five = new char[]{'j', 'k', 'l'};
    private char[] six = new char[]{'m', 'n', 'o'};
    private char[] seven = new char[]{'p', 'q', 'r', 's'};
    private char[] eight = new char[]{'t', 'u', 'v'};
    private char[] nine = new char[]{'w', 'x', 'y', 'z'};

    public char[] change2CharArray(char c) {
        switch (c) {
            case '2':
                return two;
            case '3':
                return three;
            case '4':
                return four;
            case '5':
                return five;
            case '6':
                return six;
            case '7':
                return seven;
            case '8':
                return eight;
            case '9':
                return nine;

        }
        return null;

    }

    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     * <p>
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * <p>
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/target-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
//    public int findTargetSumWays(int[] nums, int target) {
//        int[][] exNums = new int[nums.length][2];
////        for (int i = nums.length - 1; i > 0; i--) {
////            for ()
////
////        }
//
//    }


    /**
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     */

    public int change(int amount, int[] coins) {
        int[] result = new int[1];
        Arrays.sort(coins);
        choseCoin(amount, coins, 0, result);
        return result[0];
    }

    private void choseCoin(int amount, int[] coins, int index, int[] result) {
        if (amount == 0) {
            result[0] = result[0] + 1;
            return;
        }
        if (amount - coins[index] < 0) {
            return;
        }

        for (int i = index; i < coins.length; i++) {
            choseCoin(amount - coins[i], coins, i, result);
        }

    }

    /**
     * 29. 两数相除
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * <p>
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     * <p>
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * *
     */

    public static int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == 0) {
            return 0;
        }
        if (dividend == -2147483648 && divisor == -1) {
            return 2147483647;
        }
        boolean isFushu = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            isFushu = true;
        }
        int a = dividend > 0 ? -dividend : dividend;
        int b = divisor > 0 ? -divisor : divisor;

        int tmpB = b;

        int div = test(a, b);
        System.out.println("a== " + a + " , b== " + b);
        return isFushu ? -div : div;
    }

    public static int test(int a, int b) {  // 似乎精髓和难点就在于下面这几句
        if (a > b) return 0;
        int count = 1;
        int tb = b; // 在后面的代码中不更新b
        while ((tb + tb) > a) {
            if (tb > 2147483647 / 2 + 1) {
                break;
            }
            count = count + count; // 最小解翻倍
            tb = tb + tb; // 当前测试的值也翻倍
        }
        System.out.println("a1== " + a + " , b1== " + b);
        return count + test(a - tb, b);
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     */


    /***
     * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
     *
     * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
     *
     * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
     *
     *
     *
     * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：label = 14
     * 输出：[1,3,4,14]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<Integer> pathInZigZagTree(int label) {
        if (label < 0) {
            return null;
        }
        int current = label;
        List<Integer> list = new ArrayList<>();
        double a = Math.log(label + 1) / Math.log(2);
        int row = (int) Math.ceil(a);
        boolean currentIdDouble = row % 2 == 0;
        System.out.println("start --" + current + " row " + row + " current " + currentIdDouble);
        list.add(current);
        while (true) {
            if (row == 1) {
                break ;
            }

            System.out.println("real --" + getRealNum(currentIdDouble, current, row));
            current = (int) Math.floor(getRealNum(currentIdDouble, current, row) / 2);
            row = row - 1;
            currentIdDouble = !currentIdDouble;
            System.out.println("current --" + current);
            int realNum = getRealNum(currentIdDouble, current, row);
            System.out.println("realNum --" + realNum);
            list.add(realNum);
        }
        Collections.reverse(list);
        return list;
    }

    private int getRealNum(boolean isDouble, int label, int row) {
        System.out.println("isDouble");
        if (isDouble) {-
            int pow = (int) Math.pow(2, row);
            return pow + (pow >> 1) -1 -label;
        } else {
            return label;
        }
    }
}
