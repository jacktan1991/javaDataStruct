package dataStruct.list;

import logout.LogOutStream;
/**
* 线性表的单链结构实现
* @version 1.1
* 相比于1.0，用nodeBeforeHead替代head，所以1.0中所有引用到head的语句CTRL+F成nodeBeforeHead.next
* @startting time 2014.2.18
* @author jack
* 
*/

public class SingleLinkedList<N> implements LinearList<N>{
	
	/* 线性表的头节点 */
	protected Node<N> nodeBeforeHead;
	
	/* 线性表的尾节点，因为线性表add方法操作频繁的特性，所以为有利于提高add效率有必要时刻保存尾节点（双链表无此必要）*/
	protected Node<N> tail;
	
	/* 记录线性表当前长度 */
	protected int len;
	
	public SingleLinkedList()
	{
		nodeBeforeHead = new Node<N>(null, null);
		tail = nodeBeforeHead;
		len = 0;
	}
	
	@Override
	public boolean isEmpty()
	{
		return len == 0;
	}
	
	@Override
	public int length()
	{
		return len;
	}
	
	@Override
	public N get(int i)throws IndexOutOfBoundsException
	{
		if(!validateIndex(i))
		{
			throw new IndexOutOfBoundsException("错误的索引值：" + i);
		}
		
		return getIndexNode(i).element;
	}
	
	@Override
	public SingleLinkedList<N> add(N node) throws IllegalArgumentException
	{
		if(!validateNode(node))
		{
			throw new IllegalArgumentException("待添加的元素node值为null");
		}
		
		/* TODO 有何算法可以用不到这个判断语句 AFTER testAll 
		   解决的办法是用v1.1中的nodeBeforeHead替代head，
		   所有含head语句将head直接改成nodeBeforeHead.next。
		*/
		/* if(len == 0)
		{
			head.node = node;
		}else{ */
		tail.next = new Node<N>(node, null);
		tail = tail.next;
		
		len ++;
		return this;
	}
	
	@Override
	public SingleLinkedList<N> insert(int i, N node) throws IndexOutOfBoundsException, IllegalArgumentException
	{
		if(i == len)
		{
			System.out.println(LogOutStream.EXPECT_LOG + "add(node)");
			return add(node);
		}
		if(!validateNode(node))
		{
			throw new IllegalArgumentException("待插入的元素node值为null");
		}
		if(!validateIndex(i))
		{
			throw new IndexOutOfBoundsException("错误的索引值：" + i);
		}
		/* 注意边界值0 */
		Node<N> node_before_i = getIndexNode(i-1);
		
		/* 分解动作如下：
		Node<N> node_tobe_i = new Node(N)(node, null);
		
		Node<N> node_now_i = node_before_i.next;
		node_tobe_i.next = node_now_i;
		node_before_i.next = node_tobe_i;
		*/
		
		node_before_i.next = new Node<N>(node, node_before_i.next);
		
		len ++;
		
		return this;
	}
	
	@Override
	public N remove(int i) throws IndexOutOfBoundsException
	{
		if(!validateIndex(i))
		{
			throw new IndexOutOfBoundsException("错误的索引值：" + i);
		}
		/* 注意边界值0 */
		Node<N> node_before_i = getIndexNode(i-1);
		Node<N> node_now_i = node_before_i.next;
		
		/* 分解动作
		
		Node<N> node_now_i+1Tobe_i = node_now_i.next;
		node_before_i.next = node_now_i+1Tobe_i;
		*/
		node_before_i.next = node_before_i.next.next;
		
		len--;
		return node_now_i.element;
	}
	
	@Override
	public SingleLinkedList<N> clear()
	{
		len = 0;
		
		nodeBeforeHead.next = null;
		/* tail = nodeBeforeHead.next; //出错 */
		tail = nodeBeforeHead;
		
		return this;
	}
	
	@Override
	public SingleLinkedList<N> update(int i, N node) throws IndexOutOfBoundsException, IllegalArgumentException
	{
		if(!validateNode(node))
		{
			throw new IllegalArgumentException("元素不允许修改为null值");
		}
		if(!validateIndex(i))
		{
			throw new IndexOutOfBoundsException("错误的索引值：" + i);
		}
		
		/* 注意边界值0 */
		Node<N> node_before_i = getIndexNode(i-1);
		
		node_before_i.next = new Node<N>(node, node_before_i.next.next);
		
		return this;
	}
	
	/* 边界值是key在尾节点和尾节点下一个节点 */
	/* 方法体内部不使用for循环加get(i)/getIndexNode(i)的原因是，这将大大增加search的时间复杂度 */
	/* TODO 不妨比较这两种方法的时间复杂度 After allTest */
	@Override
	public int search(N key)
	{
		if(null == key)
		{
			return -1;
		}
		
		int mth_result = -1;
		
		Node<N> p = nodeBeforeHead.next;
		
		/* &&mth_result < len-1可否省略依赖于单链表对“残留”节点访问的约定 */
		
		while(null != p)
		{
			mth_result++;
			if(key.equals(p.element))
			{
				return mth_result;
			}
			p = p.next;
		}
		
		return -1;
	}
	
	protected boolean validateNode(N node)
	{
		if(null == node)
		{
			return false;
		}
		return true;
	}
	
	protected boolean validateIndex(int index)
	{
		if(index<0)
		{
			return false;
		}
		if(index<len)
		{
			return true;
		}
		return  false;
	}
	/* @preCondition 索引值必须提前经过安全检查 */
	private Node<N> getIndexNode(int index)
	{
		Node<N> mth_result = nodeBeforeHead;
		
		while(index!=-1)
		{
			mth_result = mth_result.next;
			index--;
		}
		
		return mth_result;
	}
	
	public String toString()
	{
		if(len == 0)
		{
			return "SingleLinkedList=null";
		}
		
		StringBuilder meth_result = new StringBuilder("SingleLinkedList=(");
		
		Node<N> p = nodeBeforeHead.next;
		meth_result.append(p.element);
		
		while(null != (p=p.next))
		{
			meth_result.append(",").append(p.element);
		}
		meth_result.append(")");
		
		return meth_result.toString();
	}
	
	public String link()
	{
		return "link_UnderSingleLinkedList=(" + nodeBeforeHead + ")";
	}
	
}

class Node<N>{
	N element;
	Node<N> next;
	
	Node(N ele, Node<N> next)
	{
		this.element = ele;
		this.next = next;
	}
	
	public String toString()
	{
		StringBuilder mth_result = new StringBuilder("[");
		
		mth_result.append("" + element).append("|-]->");
		
		if(null == next)
		{
			return mth_result.append("^").toString();
		}
		
		return mth_result.append(next.toString()).toString();
	}
}