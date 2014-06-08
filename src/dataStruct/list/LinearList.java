package dataStruct.list;

/**
* 线性表的顶级接口，实现类应遵循以下约定：
* <ul>
* <ui>线性表本身应无容量的限制（相对于Integer.MAX_VALUE），但必须整数连续</ui>
* <ui>线性表的长度记录在一个变量（如len）中，该变量只需满足时刻记录着线性表当前的长度</ui>
* <ui>线性表的索引值i是从0~len-1的连续整数</ui>
* </ul>
* @author jack
* 
*/

public interface LinearList<N>{
	
	/**
	* 
	* 判断此线性表是否为空。
	* “空”的含义是线性表中不包含有意义的节点元素，和具体实现类的初始化状态无关。
	* 返回true时等效于{@link #length()}=0。
	* @return 如果此线性表不包含元素时，则返回true。
	*/
	public boolean isEmpty();
	
	/**
	*
	* 返回此线性表的长度，即节点的个数。
	* 如果长度大于{@link Integer#MAX_VALUE}时，则返回Integer.MAX_VALUE
	* @return 此线性表的长度。
	*/
	public int length();
	
	/**
	* 获得第i个节点元素，注意此节点元素可为空值，即null。
	* @param 节点索引i
	* @return 如果0<=i<{@link #length()},则返回索引为i的节点元素。
	* @throw IndexOutOfBoundsException 如果i的值小于0或者大于线性表长度。
	*/
	public N get(int i);
	
	/**
	* 在线性表尾部添加元素node
	* @param node 待添加的元素
	* @return 插入后线性表本身
	* @throw ClassCastException 如果待插入的元素和线性表节点不兼容
	*/
	public LinearList<N> add(N node);
	
	/**
	* 在第i处节点插入元素node。
	* 需注意的是在线性表尾部后一位插入（等效于尾部添加）是允许的，等效通过{@link add()}方法实现。
	* @param i 
	* @param node 待插入的元素
	* @return 插入后线性表本身
	* @throw IndexOutOfBoundsException 如果i的值小于0或者大于线性表长度。
	* @throw ClassCastException 如果待插入的元素和线性表节点不兼容
	*/
	public LinearList<N> insert(int i, N node);
	
	/**
	* 移除位于第i处节点的元素
	* @param i 
	* @return 被移除的节点元素
	* @throw IndexOutOfBoundsException 如果i的值小于0或者大于线性表长度。
	*/
	public N remove(int i);
	
	/**
	* 清空整个线性表
	* @return 清空后线性表本身
	*/
	public LinearList<N> clear();
	
	/**
	* 将第i处节点的元素重新设置为node
	* @param i
	* @param node 重设值
	* @return 改动后线性表本身
	* @throw IndexOutOfBoundsException 如果i的值小于0或者大于线性表长度。
	* @throw ClassCastException 如果待插入的元素和线性表节点不兼容
	*/
	public LinearList<N> update(int i, N node);
	
	/**
	* 查找关键字为k的元素第一次出现的索引值
	* @param key 待查找的值
	* @return 元素k第一次出现的索引节点；如果没找到，则返回-1。
	* @throw ClassCastException 如果待查找的元素和线性表节点不兼容
	*/
	public int search(N key);
	
	
}