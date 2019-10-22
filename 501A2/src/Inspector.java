
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	System.out.println();
    	// (1) Get name of the class
    	System.out.println("Name of Class: " + c.getName());
    	
    	// (2) Get name of the immediate superclass 
    	System.out.println("Name of Immediate superclass: " + c.getSuperclass().getName());
    	System.out.println(c.getMethods());
    	
    	
    	// Constructors that the class declares 
    	
    	// (3) Examine the interfaces 
    	Class[] interfaces = c.getInterfaces();
    	System.out.println();
    	// (4) Inspect Constructors 
//    	a) The name constructors the class declares. For each, also find the following:
//    	b) The parameter types
//    	c) The modifiers
    	
    	
    	Constructor[] Classconstructors = c.getConstructors();
    	
    	for(Constructor constructor : Classconstructors) {
    		System.out.println("Constructor Name: " + constructor.getName());
    		Class[] parameterTypes = constructor.getParameterTypes();
    		String consParam = "Parameter(s): ";
    		int i = 0;
    		for(Class parameter : parameterTypes) {
    			if(i == 0) {
    			consParam += parameter.getName();
    			i++;
    			}else {
    				consParam += ", " + parameter.getName();
    			}
    			
    		}
    		
    		System.out.println(consParam);
			// double check if toString is allowed / read documentation 
			System.out.println("Constructor Modifier: " + Modifier.toString(constructor.getModifiers()) + "\n");
    		
    		
    	}
    	
    	
    	
    	
    	
    	// (5) Methods 
		//  a) The name
		//  b) The exceptions thrown
		//  c) The parameter types
		//  d) The return type
		//  e) The modifiers
		// Finding all info about materials 
    	Method[] classMethods = c.getMethods();
    	
    	for(Method method: classMethods) {
    		// (a) get name of the method 
    		System.out.println("Method Name: " + method.getName());
    		// (b) get the exceptions thrown
    		
    		// Class[] classExceptions = c.
    		
    		// (c) get the parameter types of method 
    		Class[] parameterTypes = method.getParameterTypes();
    		String parameters = "Parameter(s): ";
    		int i = 0;
    		for(Class parameter : parameterTypes) {
    			if(i == 0) {
    			parameters += parameter.getName();
    			i++;
    			}else {
    				parameters += ", " + parameter.getName();
    			}
    			
    		}
    		
    		System.out.println(parameters);
    		// (d) get the return type of the method 
    		System.out.println("Method Return Type: " + method.getReturnType());
    		// double check if toString is allowed / read documentation (getName no work) 
    		System.out.println("Method Modifier: " + Modifier.toString(method.getModifiers()));
    		System.out.println();
    		 		
    	}
    	
// (6) Inspect Fields
//		a) The name
//		b) The type
//		c) The modifiers
//		d) The current value of each field.
//		i) If the field is an object reference, and recursive is set to false, then simply print out the “reference value
//		
		Field[] Classfields = c.getDeclaredFields();
		
		for(Field field : Classfields) {
			System.out.println("Field Name: " + field.getName());
			System.out.println("Field Type: " + field.getType().getName());
			// double check if toString is allowed / read documentation 
			System.out.println("Field Modifier: " + Modifier.toString(field.getModifiers()) + "\n");
			
			
			
			// How to get the values 
			// System.out.println(field.get);
			
			//  The recursive stuff 
			
		}
    	
    	
    	
    	
    	
    	
    	
    	
    }

}
