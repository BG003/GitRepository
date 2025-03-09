/*

You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

Example 1:

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:

Input: list1 = [], list2 = []
Output: []

Example 3:

Input: list1 = [], list2 = [0]
Output: [0]

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.

*/

class ListNode {
    int val;
    ListNode next;
    ListNode() {  
        // expty body.
    }
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val,ListNode next) {
        this.val = val;
        this.next = next;
    }
}



public class mergeTwoSortedList {
    public ListNode mergeTwoList(ListNode list1,ListNode list2) {
        ListNode mergeList = null;
        
        if(list1 == null || list2 == null) 
        {
            if(list1 != null)
                return list1;
            else
                return list2;
        }
        ListNode min = list1, max = list2, lastNode = null;
        while(min != null && max != null)
        {
            if(min.val > max.val)
            {
                ListNode temp = min;
                min = max;
                max = temp;
            }
            ListNode firstNode = min;
            ListNode connector = firstNode;
            while(min.val <= max.val)
            {
                connector = min;
                min = min.next;
                if(min == null) 
                    break;
            }
            if(mergeList == null)
                mergeList = firstNode;
            else
                lastNode.next = firstNode;
            connector.next = max;
            lastNode = max;
            max = max.next;
        }
        if(min != null)
            lastNode.next = min;
        return mergeList;
    }
}



