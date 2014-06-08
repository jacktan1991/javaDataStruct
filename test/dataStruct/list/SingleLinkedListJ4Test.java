package dataStruct.list;

import static org.junit.Assert.*;

import logout.LogOutStream;

import org.junit.*;

public class SingleLinkedListJ4Test {
	
	private static SingleLinkedList<String> defaultList;
	
	static
	{
			
		defaultList = new SingleLinkedList<String>();
		
		defaultList.add("A");
		defaultList.add("B");
		defaultList.add("C");
		defaultList.add("D");
		
		System.out.println(LogOutStream.DEBUG_LOG + "after adds\n" + defaultList);
		System.out.println(LogOutStream.DEBUG_LOG + "and\n" + defaultList.link());
		
	}
	/* 
	@BeforeClass
	public static void setUp()
	{
		System.out.println("BeforeClass setUp()");
	}
	
	@AfterClass
	protected void tearDown()
	{
		System.out.println("AfterClass tearDown()");
	} */
	
	@Test
	public void testToString()
	{
		String expected = "SingleLinkedList=(A,B,C,D)";
		String tested = defaultList.toString();
		
		assertEquals(expected, tested);
	}
	
	@Test
	public void testLink()
	{
		String expected = "link_UnderSingleLinkedList=([A|-]->[B|-]->[C|-]->[D|-]->^)";
		String tested = defaultList.link();
		
		assertEquals(expected, tested);
	}
	
	@Test
	public void testLen()
	{
		assertEquals(4, defaultList.length());
	}
	
	@Test
	public void testGet()
	{
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
}