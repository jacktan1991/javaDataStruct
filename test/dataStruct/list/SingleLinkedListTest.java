package dataStruct.list;

import logout.LogOutStream;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SingleLinkedListTest extends TestCase {
	
	private static SingleLinkedList<String> defaultList;
	
	public SingleLinkedListTest(String name) {
		super(name);
	}	
	
	static
	{
		
		defaultList = new SingleLinkedList<String>();
		
		defaultList.add("A");
		defaultList.add("B");
		defaultList.add("C");
		defaultList.add("D");
		
		/* System.out.println(LogOutStream.DEBUG_LOG + "after adds\n" + defaultList); */
		/* System.out.println(LogOutStream.DEBUG_LOG + "and\n" + defaultList.link()); */
		
	}
		
	public void testToString()
	{
		String expected = "SingleLinkedList=(A,B,C,D)";
		String tested = defaultList.toString();
		
		assertEquals(expected, tested);
		
		System.out.println(LogOutStream.DEBUG_LOG + "SingleLinkedListTest.testToString()");
	}
	
	public void testLink()
	{
		String expected = "link_UnderSingleLinkedList=([A|-]->[B|-]->[C|-]->[D|-]->^)";
		String tested = defaultList.link();
		
		assertEquals(expected, tested);
	}
	
	public void testLen()
	{
		assertEquals(4, defaultList.length());
	}
	
	public void testGet()
	{
		
		System.out.println(LogOutStream.DEBUG_LOG + "SingleLinkedListTest.testGet()");		
		
		assertEquals("A", defaultList.get(0));
		
		boolean result = "C".equals(defaultList.get(2));
		assertTrue("index 2 is C",result);
		
		result = "D".equals(defaultList.get(defaultList.length()-1));
		assertTrue("index 2 is C",result);
		
		String result1 = null;
		try{
			result1 = defaultList.get(-1);
		}catch(Exception ex)
		{
			assertNull(result1);
			System.out.println(ex);
		}
		
	}
	
	SingleLinkedList<String> standardList()
	{
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		
		return list;
	}
	
	String getStr(SingleLinkedList<String> list)
	{
		return list.link() + "\n" + list;
	}
	
	public void testInsert0()
	{
		SingleLinkedList<String> list = standardList();
		
		try{
			list.insert(0, "head");
		}catch(Exception ex)
		{
			assertFalse(ex.toString(), 4==list.length());
		}
		
		assertTrue(getStr(list), 5==list.length());
	}
	
	public void testInsert2()
	{
		SingleLinkedList<String> list = standardList();
		
		try{
			list.insert(2, "three");
		}catch(Exception ex)
		{
			assertFalse(ex.toString(), 4==list.length());
		}
		
		assertTrue(getStr(list), "three".equals(list.get(2)));
	}
	
	public void testInsert()
	{
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		
		try{
			list.insert(-1, "str");
		}catch(Exception ex)
		{
			assertTrue(ex.toString(), 0==list.length());
			System.out.println(ex);
		}
		
		try{
			list.insert(0, null);
		}catch(Exception ex)
		{
			assertTrue(ex.toString(), 0==list.length());
			System.out.println(ex);
		}
		
		try{
			list.insert(4, "str");
		}catch(Exception ex)
		{
			assertTrue(ex.toString(), 0==list.length());
			System.out.println(ex);
		}
		
		String link = list.link();
		assertTrue(link,"link_UnderSingleLinkedList=(null)".equals(link));
		
		list.insert(list.length(), "1");
		list.insert(list.length(), "2");
		list.insert(list.length(), "3");
		
		list.insert(3, "four");
		
		assertTrue("此时长度为4",4==list.length());
		
		list.insert(2, "three");
		
		assertFalse(getStr(list), 4==list.length());
		
	}
	
	public void remove0()
	{
		SingleLinkedList<String> list = standardList();
		
		String str = list.remove(0);
		
		assertTrue(getStr(list), str.equals("1"));
	}
	
	public void remove3()
	{
		SingleLinkedList<String> list = standardList();
		
		String str = list.remove(3);
		
		assertTrue(getStr(list), str.equals("4"));
	}
	
	public void remove4()
	{
		SingleLinkedList<String> list = standardList();
		
		String str = list.remove(5);
		
		assertTrue(getStr(list), "5".equals(str));
	}
	
	public void testInsertAndRemove()
	{
		SingleLinkedList<String> list = standardList();
		
		String str = list.remove(0);
		list.insert(0,str);
		
		str = list.remove(0);
		list.insert(0,str);
		str = list.remove(0);
		list.insert(0,str);
		str = list.remove(0);
		list.insert(0,str);
		
		assertTrue(list.length() + getStr(list), 4==list.length());
	}
	public void testRemoveAndInsert()
	{
		SingleLinkedList<String> list = standardList();
		
		list.insert(0,"head");
		String str = list.remove(0);
		
		list.insert(0,str);		
		str = list.remove(0);
		list.insert(0,str);
		str = list.remove(0);
		list.insert(0,str);
		str = list.remove(0);
		
		assertTrue(list.length() + getStr(list), 4==list.length());
	}
	
	public void testSearchTail()
	{
		SingleLinkedList<String> list = standardList();
		
		int i = list.search("4");
		
		assertTrue(getStr(list), 3==i);
		
		i = list.search("2");
		
		assertTrue(getStr(list), 1==i);
		
		i = list.search("two");
		
		assertTrue(getStr(list), -1==i);
		
		list.insert(2, "three");
		list.remove(4);
		
		i = list.search("4");
		
		assertTrue(getStr(list), -1==i);
		//fail(getStr(list));
	}
	
	public void testUpdate()
	{
		SingleLinkedList<String> list = standardList();
		
		list.update(3, "four");
		
		assertTrue(list.get(3).equals("four"));
		
		//fail(getStr(list));
	}
	
	public void testClear()
	{
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		
		System.out.println("271" + list);
		System.out.println("272" + list.link());
		
		list.clear();
		
		System.out.println("276" + list);
		System.out.println("277" + list.link());
		
		boolean test = "link_UnderSingleLinkedList=([null|-]->^)".equals(list.link());
		assertTrue(test);
		
		list.add("one");
		list.add("two");
		list.add("three");
		
		System.out.println("286" + list);
		System.out.println("287" + list.link());
		
		list.clear();
		
		System.out.println("291" + list);
		System.out.println("292" + list.link());
		
		assertTrue(test);
		
	}
	public static TestSuite suite()
	{
		TestSuite suite = new TestSuite("singlelistlistTest");
		
		suite.addTest(new SingleLinkedListTest("testClear"));
		/* suite.addTestSuite(SingleLinkedListTest.class); */
		
		
		
		return suite;
	}
}