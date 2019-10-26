//import static org.junit.Assert.*;
//
//import java.lang.reflect.Array;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//public class InspectorTest {
//	@Test
//	public void testInspect() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPrintTabs() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRecurse() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCheckPrimitiveValue() {
//		ClassA testClass = new ClassA();
//		Class classA = testClass.getClass();
//		Inspector inspector = new Inspector();
//		
//		try {
//			classA.getField("val").setAccessible(true);
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//int test = 0;
//		try {
//			Field test = classA.getField("val");
//			
//			
//			
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		assertEquals(3 , test);
//		
//	
//	}
//	
//	@Test
//	public void testInspectMethod(){
////		ClassB testClass = new ClassB();
////		Class classB = testClass.getClass();
////		Inspector inspector = new Inspector();
////		assertEquals(3 , inspector.inspectMethod(classB,0));
//	}
//
//
//
//}
