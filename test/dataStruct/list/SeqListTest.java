package dataStruct.list;

import logout.LogOutStream;

import junit.framework.TestCase;

public class SeqListTest extends TestCase {
	
	private static SeqList<String> defaultList;
	
	public SeqListTest(String name) {
		super(name);
	}	
	
	static
	{
		
		defaultList = new SeqList<String>();
		
		defaultList.add("A");
		defaultList.add("B");
		defaultList.add("C");
		defaultList.add("D");
		
		System.out.println(LogOutStream.DEBUG_LOG + "after adds\n" + defaultList);
		System.out.println(LogOutStream.DEBUG_LOG + "and\n" + defaultList.zhidai());
		
	}
		
	public void testToString()
	{
		String expected = "SeqList=(A,B,C,D)";
		String tested = defaultList.toString();
		
		assertEquals(expected, tested);
		
		System.out.println(LogOutStream.DEBUG_LOG + "SeqListTest.testToString()");
	}
	
	public void testLen()
	{
		assertEquals(4, defaultList.length());
	}
	
	public void testGet()
	{
		
		System.out.println(LogOutStream.DEBUG_LOG + "SeqListTest.testGet()");
		String result1 = null;
		try{
			result1 = defaultList.get(-1);
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		assertNull(result1);
		
		assertEquals("A", defaultList.get(0));
		
		boolean result = "C".equals(defaultList.get(2));
		assertTrue("index 2 is C",result);
		
		result = "D".equals(defaultList.get(defaultList.length()-1));
		assertTrue("index 2 is C",result);
	}
	/* 
	public static TestSuite suite()
	{
		TestSuite suite = new TestSuite("seqlistTest");
		
		suite.addTestSuite(SeqListTest.class);
		
		return suite;
	} */
}