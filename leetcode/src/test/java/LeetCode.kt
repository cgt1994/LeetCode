package com.cgt.leetcode

import org.junit.Test

class LeetCode {

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

    请你将两个数相加，并以相同形式返回一个表示和的链表。

    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/add-two-numbers
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun test1(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) {
            return null
        }
        var carry = 0
        var firstNode = l1
        var secondNode = l2
        var resultNode = ListNode()
        val originNode = resultNode
        while (true) {
            var count = (firstNode?.`val` ?: 0) + (secondNode?.`val` ?: 0) + carry
            carry = count / 10
            resultNode.`val` = count % 10
            firstNode = firstNode?.next
            secondNode = secondNode?.next
            if ((firstNode != null || secondNode != null) || carry != 0) {
                resultNode.next = ListNode()
                resultNode = resultNode.next!!
            } else {
                break
            }
        }
        return originNode
    }

    class ListNode {
        var `val`: Int? = 0
        var next: ListNode? = null

        constructor(`val`: Int? = null, next: ListNode? = null) {
            this.`val` = `val`
            this.next = next
        }
    }

    /**
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。

    进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    0<m
    0<n
    如果是奇数
    中位数的下标为(m+n+1)/2
    如果是偶数
    中位数=(m+n)/2 (m+n)/2+1的平均数

     */

    fun findMedianSortedArrays(nums1: IntArray?, nums2: IntArray?): Double {
        if (nums1?.isEmpty() == true || nums2?.isEmpty() == true) {
            return 0.0
        }

        if ((nums1!!.size + nums2!!.size) % 2 == 1) {
            //奇数

        } else {
            //偶数
        }
        return 0.0
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     */
    fun longestPalindrome(s: String): String {
        var startIndex = 0
        var maxLength = -1
        for (i in s.indices) {
            val startChar = s.get(i)
            var length = 1
            if (length > maxLength) {
                maxLength = length
                startIndex = i
            }
            for (p2 in i + 1 until s.length) {
                length++
                if (s[p2] == startChar) {
                    if (length > maxLength && checkStrIsSymmetry(s.substring(i, i + length))) {
                        maxLength = length
                        startIndex = i
                    }

                }
            }

        }
        return s.substring(startIndex, startIndex + maxLength)
    }

    private fun checkStrIsSymmetry(substring: String): Boolean {
        for (i in 0 until substring.length / 2) {
            if (!substring.get(i).equals(substring[substring.length - 1 - i])) {
                return false
            }
        }
        return true
    }

    @Test
    fun test2() {
        System.out.println(
            longestPalindrome("cbbd")
        )
    }

    /**
    给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

    '.' 匹配任意单个字符
    '*' 匹配零个或多个前面的那一个元素
    所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

     */
    fun isMatch(s: String?, p: String?): Boolean {
        if (p?.contains('.') == false && !p.contains("*")) {
            return p == s
        }
        if (s == null || p == null) {
            return false
        }

        for (i in s.indices) {
            val char = s[i]
            val char2 = p[i]

        }

        return false
    }

    /**
     *  滑动窗口最大值
     *
     *  给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *  返回滑动窗口中的最大值。
     *  输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     *  输出：[3,3,5,5,6,7]
     *  解释：
     *  滑动窗口的位置                最大值
     *  ---------------               -----
     *  [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     */
//    open fun maxSlidingWindow(nums: IntArray?, k: Int): IntArray? {
//        if (nums == null) {
//            return null
//        }
//        val resultSize = if (nums.size - k + 1 > 0) {
//            nums.size - k + 1
//        } else {
//            1
//        }
//        val array = IntArray(resultSize)
//        val deque = ArrayDeque<Int>()
////        for (i in 0 until nums.size) {
////            if ()
////        }
//    }


    /** https://leetcode-cn.com/problems/valid-anagram/description/
    242. 有效的字母异位词
    给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    resolve 用哈希表来存储数组
     */

    /**
     *
    给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

    返回被除数 dividend 除以除数 divisor 得到的商。

    整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     */

//    open fun divide(dividend: Int, divisor: Int): Int {
//        if (divisor==0){
//            return 0
//        }
////       if (divisor)
//    }
//}

    class Test2 {
        /**
         * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

        进阶：你能尝试使用一趟扫描实现吗？
         */
        class ListNode(var `val`: Int) {
            var next: ListNode? = null
        }

        fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
            assert(head != null) { "head为空" }
            if (head == null || n < 0) {
                return null
            }
            var start = 0
            var size = 0

            var firstNode = head
            var secondNode: ListNode? = null

            while (firstNode != null) {
                size++
                firstNode = firstNode.next
                if (firstNode == null) {
                    break
                }
                if (secondNode == null) {
                    start++
                    if (start == n) {
                        System.out.println("second set ${firstNode?.`val`}")
                        secondNode = head
                    }
                } else {
                    secondNode = secondNode.next

                }

            }

            if (size == n) {
                return head.next
            }
            System.out.println("second current ${secondNode?.`val`}")
            secondNode?.next = secondNode?.next?.next
            return head
        }
    }

    @Test
    fun testMax() {
        System.out.println(lengthOfLongestSubstring1("abcabcbb"))
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */

    fun lengthOfLongestSubstring(s: String?): Int {
        if (s == null) return 0
        //key为字符，value为当前下标
        val map = HashSet<Char>()
        var maxLength = 0
        var leftIndex = 0
        var rightIndex = 0
        while (leftIndex < s.length) {
            while (rightIndex < s.length) {
                val rightChar = s[rightIndex]
                if (!map.contains(rightChar)) {
                    map.add(rightChar)
                    rightIndex++
                    if (rightIndex == s.length) {
                        return Math.max(rightIndex - leftIndex, maxLength)
                    }
                } else {
                    maxLength = Math.max(rightIndex - leftIndex, maxLength)
                    map.remove(s.get(leftIndex))
                    leftIndex++
                    break
                }
            }
            if (rightIndex == s.length) {
                return maxLength
            }
        }
        return maxLength
    }

    fun lengthOfLongestSubstring1(s: String): Int {
        // 记录字符上一次出现的位置
        val last = IntArray(128)
        for (i in 0..127) {
            last[i] = -1
        }
        val n = s.length
        var res = 0
        var start = 0 // 窗口开始位置
        for (i in 0 until n) {
            val index = s[i].toInt()
            start = Math.max(start, last[index] + 1)
            res = Math.max(res, i - start + 1)
            last[index] = i
            System.out.println("start == $start res == ${res}  last [index] = $index")
        }
        return res
    }

    /**
     *
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。


    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

    问总共有多少条不同的路径？

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/unique-paths
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    @Test
    fun digui() {
        uniquePaths(7, 3)
    }

    data class ResultVersion(var result: Int)

    fun uniquePaths(m: Int, n: Int) {
        val array = IntArray(m) { 1 }
        for (y in 1 until n) {
            for (x in 1 until m) {
                array[x] = array[x - 1] + array[x]
            }
        }
        System.out.println(array[6])
    }

    fun findPath(cX: Int, cY: Int, limitX: Int, LimitY: Int, result: ResultVersion) {
        if (cX < limitX) {
            findPath(cX + 1, cY, limitX, LimitY, result)
        }
        if (cY < LimitY) {
            findPath(cX, cY + 1, limitX, LimitY, result)
        }
        if (cX == limitX && cY == LimitY) {
            result.result++
        }
    }


    class Two {
        class ListNode(var `val`: Int) {
            var next: ListNode? = null
        }

        @Test
        fun test() {


        }

        /**
        给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

        请你将两个数相加，并以相同形式返回一个表示和的链表。

        你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/add-two-numbers
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode {
            var p1 = l1
            var p2 = l2
            var p3: ListNode? = null
            var hasOver = false
            var head: ListNode? = null
            while (p1 != null || p2 != null) {
                val p3Value = (p1?.`val` ?: 0) + (p2?.`val` ?: 0) + if (hasOver) 1 else 0
                hasOver = p3Value >= 10
                val next = if (hasOver) (ListNode(p3Value - 10)) else ListNode(p3Value)
                if (head == null) {
                    head = next
                }
                p3?.next = next
                p3 = next
                p1 = p1?.next
                p2 = p2?.next
            }
            if (hasOver) {
                p3?.next = ListNode(1)
            }
            return head ?: ListNode(0)
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

//    fun isValidBST(root: TreeNode?): Boolean {
//        test(root, Int.MAX_VALUE, Int.MIN_VALUE)
//
//    }

    public fun isValidBSTa(root: TreeNode?): Boolean {
        return isValidBST(root, Int.MIN_VALUE, Int.MAX_VALUE);
    }

    public fun isValidBST(node: TreeNode?, lower: Int, upper: Int): Boolean {
        if (node == null) {
            return true;
        }
        if (node.`val` <= lower || node.`val` >= upper) {
            return false;
        }
        return isValidBST(
            node.left, lower, node.`val`
        ) && isValidBST(
            node.right, node.`val`, upper
        );
    }


    fun test(root: TreeNode?, maxValue: Int, minValue: Int): Boolean {
        if (root == null) return true
        System.out.println("left ==" + root.left + " rootLeft == ${root.left?.`val`}")
        System.out.println("right ==" + root.right)
        if (root.`val` > maxValue || root.`val` < minValue) {
            return false
        }
        return test(root.left, root.`val`, minValue) && test(root.right, maxValue, root.`val`)
    }

    /**给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

    说明：你不能倾斜容器。

    解题思路：取决于短板的高度，采用双指针


     */
    fun maxArea(height: IntArray): Int {
        if (height.size <= 1) return 0
        var leftP = 0
        var rightP = height.size - 1
        var maxLength = 0
        while (leftP < rightP) {
            maxLength =
                Math.max(maxLength, (rightP - leftP) * Math.min(height[leftP], height[rightP]))
            //左边小于右边，左边指针移动
            if (height[leftP] < height[rightP]) {
                leftP++
            } else {
                rightP--
            }
        }
        return maxLength
    }

    @Test
    fun compare() {
        System.out.println(compareVersion("1.2.3", "1.0058.3"))

    }

    /**
     *
    给你两个版本号 version1 和 version2 ，请你比较它们。

    版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。

    比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。

    返回规则如下：

    如果 version1 > version2 返回 1，
    如果 version1 < version2 返回 -1，
    除此之外返回 0。
     */
    fun compareVersion(version1: String, version2: String): Int {
        var v1Position = IntArray(1) { 0 }
        var v2Position = IntArray(1) { 0 }


        while (version1.length > v1Position.get(0) || version2.length > v2Position.get(0)) {


            val version1Int = getVersion(version1, v1Position)
            val version2Int = getVersion(version2, v2Position)
            if (version1Int < version2Int) {
                return -1
            } else if (version1Int > version2Int) {
                return 1
            }
        }
        return 0
    }

    private fun getVersion(
        version1: String,
        v1Position: IntArray
    ): Int {
        var version = 0
        while (version1.length > v1Position.get(0)) {
            val char = version1.get(v1Position.get(0))
            if ('.' == char) {
                v1Position.set(0, v1Position[0]+1)
                break
            } else {
                version = version * 10 + char.toInt() - '0'.toInt()
                val i = v1Position[0]+1
                v1Position[0] = i
            }
        }
        return version
    }


}




