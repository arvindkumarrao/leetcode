/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// O(nLogk) space O(k) and O(n) dfor priority queue
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //min heap
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            new Comparator<ListNode>()
            {
                @Override
                public int compare(ListNode a, ListNode b) 
                { 
                    return a.val - b.val; 
                } 
            }
        );
        for(ListNode list: lists){
          if(list!=null)
              pq.add(list);  
        } 
        ListNode res = new ListNode(0);
        
        ListNode cur = res;
        while(!pq.isEmpty())
        {
            cur.next = pq.poll();
            cur = cur.next;
            if(cur.next!=null) 
                pq.add(cur.next);
        }
        return res.next;
    }
}

//Divide and conqure
// O(Nlogk) , space O(1) 
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int interval =1;
        if(lists.length == 0) return null;
        while(interval < lists.length)
        {
            for(int i = 0; i+interval < lists.length; i = i + interval*2 )
            {
                lists[i] = mergeList(lists[i], lists[i+interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }
    
    public ListNode mergeList(ListNode l1, ListNode l2)
    {
        ListNode l3 = new ListNode(0);
        ListNode result = l3;
        while(l1 != null && l2 != null)
        {
            if(l1.val < l2.val)
            {
                l3.next = l1;
                l3 = l3.next;
                l1 = l1.next;
            }
            else
            {
                l3.next = l2;
                l3 = l3.next;
                l2 = l2.next;
                
            }
        }
        if(l1 != null)
        {
            l3.next = l1;
        }
        if(l2 != null)
        {
            l3.next = l2;
        }
        return result.next;
    }
}