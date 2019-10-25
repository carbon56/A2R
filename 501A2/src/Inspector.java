
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// CPSC 501 Assignment 2

public class Inspector {
	// just use a while loop thats like while != object class, print stuff then move
	// down etc.
	public void inspect(Object obj, boolean recursive) {
		Class c = obj.getClass();
		inspectClass(c, obj, recursive, 0);
	}
	
	
	private void inspectMethod(Class c) {
		// (5) Methods
		// a) The name
		// b) The exceptions thrown
		// c) The parameter types
		// d) The return type
		// e) The modifiers
		// Finding all info about materials
		Method[] classMethods = c.getMethods();

		for (Method method : classMethods) {
			// (a) get name of the method
			System.out.println("Method Name: " + method.getName());
			// (b) get the exceptions thrown

			Class[] classExceptions = method.getExceptionTypes();
			for (Class except : classExceptions) {
				System.out.println("Exception Types: " + except.getName());
			}

			// System.out.println();

			// (c) get the parameter types of method
			Class[] parameterTypes = method.getParameterTypes();
			String parameters = "Parameter(s): ";
			int i = 0;
			for (Class parameter : parameterTypes) {
				if (i == 0) {
					parameters += parameter.getName();
					i++;
				} else {
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
	}
	private void inspectConstructor(Class c) {
	// (4) Inspect Constructors
	//    	a) The name constructors the class declares. For each, also find the following:
	//    	b) The parameter types
	//    	c) The modifiers

		Constructor[] Classconstructors = c.getConstructors();
		for (Constructor constructor : Classconstructors) {
			System.out.println("Constructor Name: " + constructor.getName());
			Class[] parameterTypes = constructor.getParameterTypes();
			String consParam = "Parameter(s): ";
			int i = 0;
			for (Class parameter : parameterTypes) {
				if (i == 0) {
					consParam += parameter.getName();
					i++;
				} else {
					consParam += ", " + parameter.getName();
				}

			}

			System.out.println(consParam);
			// double check if toString is allowed / read documentation
			System.out.println("Constructor Modifier: " + Modifier.toString(constructor.getModifiers()) + "\n");

		}
	}

	private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		System.out.println();
		// (1) Get name of the class
		System.out.println("Name of Class: " + c.getName());

		// (2) Get name of the immediate superclass
		System.out.println("Name of Immediate superclass: " + c.getSuperclass().getName() + "\n");
		// System.out.println(c.getMethods());

		// Constructors that the class declares
		// (3) Examine the interfaces
		Class[] interfaces = c.getInterfaces();

		for (Class inter : interfaces) {
			System.out.println("Interface Name: " + inter.getName());
		}
		System.out.println();
		
		inspectConstructor(c);
		inspectMethod(c);
		inspectField(c, obj);
		
		
		
		






	}
	
	private void inspectField(Class c, Object obj) {
		// (6) Inspect Fields
//		a) The name
//		b) The type
//		c) The modifiers
//		d) The current value of each field.
//		i) If the field is an object reference, and recursive is set to false, then simply print out the “reference value
//		
		Field[] Classfields = c.getDeclaredFields();
		for (int k = 0; k < Classfields.length; k++) {
			Classfields[k].setAccessible(true);
			System.out.println("Field Name: " + Classfields[k].getName());
			System.out.println("Field Type: " + Classfields[k].getType().getName());
			// double check if toString is allowed / read documentation
			System.out.println("Field Modifier: " + Modifier.toString(Classfields[k].getModifiers()));

			try {
				// Need to get arrays and also other objects :<
				Object value = Classfields[k].get(obj);
				if (checkPrimitiveValue(value)) {
					System.out.println("Field Value: " + value + "\n");

				} else {
					if (Classfields[k].getType().isArray()) {
						String wow = "[";

						for (int w = 0; w < Array.getLength(value); w++) {
							Object thing = Array.get(value, w);
							if (checkPrimitiveValue(thing)) {
								// System.out.println("Field Value: " + value + "\n");
								wow += " " + thing + ",";

							}

						}
						wow += " ]";
						System.out.println(wow);

						// System.out.println("NO");
					}
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//				
//			}
			// The recursive stuff

		}
	}

	public boolean checkPrimitiveValue(Object value) {
		if (value != null) {
			if (value.getClass().isPrimitive() || value.getClass() == java.lang.Long.class
					|| value.getClass() == java.lang.String.class || value.getClass() == java.lang.Integer.class
					|| value.getClass() == java.lang.Boolean.class || value.getClass() == java.lang.Double.class
					|| value.getClass() == java.lang.Character.class || value.getClass() == java.lang.Byte.class
					|| value.getClass() == java.lang.Short.class || value.getClass() == java.lang.Float.class) {
				// System.out.println("Field Value: " + value + "\n");
				return true;

			}

			else {
				System.out.println("Not primitive type \n");
				return false;
			}

		}
		return false;
	}

}
