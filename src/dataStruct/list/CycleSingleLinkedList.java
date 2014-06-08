package dataStruct.list;

public abstract class CycleSingleLinkedList<N> implements LinearList<N>{
	
	protected Node<N> nodeBeforeHead;
	
	protected int len;
	
	public CycleSingleLinkedList()
	{
		nodeBeforeHead = new Node<N>(null, null);
		len = 0;
	}
}