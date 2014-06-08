package dataStruct.list;

import logout.LogOutStream;
/**
* 线性表的顺序表实现
* @version 1.0
* @startting time 2014.2.10
* @endding time 2014.2.18
* @author jack
* 
*/

public class SeqList<N> implements LinearList<N>{
	
	/** 未指定情况下“容器”的默认容量*/
	private static final int DEFAULT_SIZE = 16;
	
	/** 实际用来盛放节点元素的内部“容器”*/
	protected N[] elements;
	
	/** 记录线性表当前长度 */
	protected int len;
	
	/** 构造函数提取的公共部分*/
	private void init(int initSize)
	{
		elements = (N[])new Object[initSize];
		len = 0;
	}
	
	public SeqList()
	{
		init(DEFAULT_SIZE);
	}
	
	public SeqList(int initialSize)
	{
		if(initialSize<0)
		{
			throw new IllegalArgumentException("初始容量错误" + initialSize);
		}
		init(initialSize);
	}
		
	/**
	* 保证内部容器elements的容量大于size，具体的说是至少要确保对象elements[size-1]有效，能被赋值不会产生NullPointException。
	* 这意味着若size == elements.length，则恰处在安全的边界；
	* size再往上(即elements.length+1)，则超过了边界，故扩容的条件为size>lements.length
	* 若不够，则调用#expandElementsSize()方法进行扩容
	* @param size 确保容器容量的下限值。
	* 
	*/
	
	protected void ensureElemtSize(int size)
	{	
		
		if(size>elements.length)
		{
			expandElementsSize();
		}
		
	} 
	
	/**
	* 另一种更好的设计是保证数组的下标，保证下标不会越界；
	* 此时扩容的条件为index==lements.length || index>lements.length
	*/
	 
	protected void ensureElemtIndex(int index)
	{
		if(index<elements.length)
		{
			//Do Nothing
			return;
		}
		expandElementsSize();
		
		System.out.println(LogOutStream.EXPECT_LOG + "ensureElemtIndex end and elements.length=" + elements.length);
	}
	
	/**
	* 目的同ensureCapacity()，不同的是本方法属于未雨绸缪型
	* 若不够，则调用#()方法来
	*/
	/* //TODO 完成这个方法体的设计 BEFORE 已有代码经测试无错。
	protected abstract void ensureSize_weiyuchoumou(); 
	*/
	
	/**
	* 以一定策略来增加容器的容量，保证容器足够大；
	* 默认扩展策略是新数组长度在原数组长度1.5倍的基础上+1，即newSize = (oldSize * 3)/2 + 1。
	*/
	protected void expandElementsSize()
	{
		/* 也可以直接用len代替oldSize */
		int oldSize = elements.length;
		N[] newElemt = (N[])new Object[(oldSize*3)/2+1];
		System.arraycopy(elements, 0, newElemt, 0, oldSize);
		
		elements = newElemt;
	}
	/**
	* insert、remove、update三个方法中都对线性表的索引值敏感，都可能抛出{IndexOutOfBoundsException}，故抽出公共代码。
	* 注意是线性表的索引值，不是容器数组的下标索引。
	* 这里只做判断，不抛出错误。
	*/
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
	
	/**
	* insert、add、update、search四个方法中都对元素类型和值敏感，都可能抛出{ClassCastException}或{IllegalArgumentException}，故抽出公共代码
	*/
	protected boolean validateNode(N node)
	{
		if(null == node)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean isEmpty()
	{
		return len==0; 
		
	}
	
	@Override
	public int length()
	{
		return len;
	}
	
	/**
	* 时间复杂度为O(1)
	*/
	
	@Override
	public N get(int i) throws IndexOutOfBoundsException
	{
		if(!validateIndex(i))
		{
			throw new IndexOutOfBoundsException("错误的索引值：" + i);
		}
		
		return elements[i];
	}
	
	/**
	* 不允许插入空值，放置恶意插入null元素。时间复杂度为O(1)
	* @param node 待添加的元素，不允许为空值null。
	* @throw IllegalArgumentException 若待添加的元素为空。
	*/
	
	@Override
	public SeqList<N> add(N node) throws IllegalArgumentException
	{
		if(!validateNode(node))
		{
			throw new IllegalArgumentException("待添加的元素node值为null");
		}
		/*在将元素添加至容器之前，必须保证容器容量不低于添加后线性表长度len+1*/
		/* ensureElemtSize(len + 1); */
		
		/*或是保证elements的len下标可引*/
		ensureElemtIndex(len);		
		elements[len] = node;
		
		len++;
		return this;
	}
	
	/**
	* 在第i处节点插入元素node。
	* 需注意的是在线性表尾部后一位插入（等效于尾部添加）是允许的，等效通过{@link add()}方法实现。
	* 最坏情况下，时间复杂度为O(n)
	* @param i 
	* @param node 待插入的元素
	* @return 插入后线性表本身
	* @throw IndexOutOfBoundsException 如果i的值小于0或者大于线性表长度。
	* @throw IllegalArgumentException 如果node为null。
	* @throw ClassCastException 如果待插入的元素和线性表节点不兼容
	*/
	
	@Override
	public SeqList<N> insert(int i, N node) throws IndexOutOfBoundsException, IllegalArgumentException
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
		System.out.println(LogOutStream.EXPECT_LOG + "add(elements[len-1])");
		/* 方案一，借助add方法 */
		add(elements[len-1]);
		
		int p = len - 2;
		do{
			elements[p] = elements[p-1];
			p--;
		}while(p > i);
		
		elements[i] = node;
		/*TODO 方案二，传统方法 BEFORE 大O分析*/
		
		return this;
	}
	
	/**
	* 时间复杂度为O(n)
	*/
	@Override
	public N remove(int i) throws IndexOutOfBoundsException
	{
		if(!validateIndex(i))
		{
			throw new IndexOutOfBoundsException("错误的索引值：" + i);
		}
		
		N mth_result = elements[i];
		
		do{
			elements[i] = elements[i+1];
			i++;
		}while(i < len-1);
			
		len--;
		
		return mth_result;
	}
	
	@Override
	public SeqList<N> clear()
	{
		len = 0;
		return this;
	}
	
	/* 时间复杂度为O(1) */
	@Override
	public SeqList<N> update(int i, N node) throws IndexOutOfBoundsException, IllegalArgumentException
	{
		if(!validateNode(node))
		{
			throw new IllegalArgumentException("元素不允许修改为null值");
		}
		if(!validateIndex(i))
		{
			throw new IndexOutOfBoundsException("错误的索引值：" + i);
		}
		
		elements[i] = node;
		
		return this;
	}
	
	/**
	* 基本的顺序查找法。对元素的等价比较是以equals()方法为依据。
	* 时间复杂度为O(n)
	*/
	@Override
	public int search(N key)
	{
		if(null == key)
		{
			return -1;
		}
		for(int i=0; i<len; i++)
		{
			if(key.equals(elements[i]))
			{
				return i;
			}
		}
		return -1;
	}
	
	/* 时间复杂度为O(n) */
	public String toString()
	{
		if(len == 0)
		{
			return "SeqList=null";
		}
		
		StringBuilder meth_result = new StringBuilder("SeqList=(");
		
		meth_result.append(elements[0]);
		
		for(int i=1; i<len; i++)
		{
			meth_result.append(",").append(elements[i]);
		}
		
		meth_result.append(")");
		
		return meth_result.toString();
	}
	
	public String zhidai()
	{
		StringBuilder meth_result = new StringBuilder("zhidai_UnderSeqList=([");
		
		meth_result.append(elements[0]);
		
		for(int i=1; i<elements.length; i++)
		{
			meth_result.append("],[").append(elements[i]);;
		}
		
		return meth_result.append("])").toString();
	}
}