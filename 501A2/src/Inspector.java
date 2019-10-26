
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
//		inspectClass(c, obj, recursive, 0);

		if (obj != null) {
			inspectClass(c, obj, recursive, 0);
			inspectConstructor(c, 0);
			inspectMethod(c, 0);
			inspectField(c, obj, 0, recursive);
			if (recursive) {
				recurse(c, obj, recursive, 0, false);
			}

		}

	}

	public void printTabs(int depth, String toPrint) {

		String tab = "";
		while (depth > 0) {
			tab += "\t";
			depth--;
		}

		System.out.println(tab + toPrint);

	}

	public void recurse(Class c, Object obj, boolean recursive, int depth, boolean checkInterface) {
		if(checkInterface) {
			// want to check interface
			printTabs(depth, "Inspecting Interface: " + c.getName());
			inspectClass(c, obj, recursive, depth);
			inspectConstructor(c, depth);
			inspectMethod(c, depth);
			inspectField(c, obj, depth, recursive);
			
			
			
		}else {
			
		
		Class superClass = c.getSuperclass();
		if (superClass != null) {
			depth++;
			System.out.println("======================================================");
			printTabs(depth, "Inspecting " + c.getName() + "'s superclass: " + superClass.getName());
			
			inspectClass(superClass, obj, recursive, depth);
			inspectConstructor(superClass, depth);
			inspectMethod(superClass, depth);
			inspectField(superClass, obj, depth, recursive);

			recurse(superClass, obj, recursive, depth, false);
			// System.out.println("======================================================");

		} 
		
		}

	}

	private void inspectMethod(Class c, int depth) {
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
			printTabs(depth, "Method Name: " + method.getName());
			// (b) get the exceptions thrown

			Class[] classExceptions = method.getExceptionTypes();
			for (Class except : classExceptions) {
				printTabs(depth, "  Exception Types: " + except.getName());
			}

			// System.out.println();

			// (c) get the parameter types of method
			Class[] parameterTypes = method.getParameterTypes();
			String parameters = "  Parameter(s): ";
			int i = 0;
			for (Class parameter : parameterTypes) {
				if (i == 0) {
					parameters += parameter.getName();
					i++;
				} else {
					parameters += ", " + parameter.getName();
				}

			}

			printTabs(depth, parameters);
			// (d) get the return type of the method
			printTabs(depth, "  Method Return Type: " + method.getReturnType());
			// double check if toString is allowed / read documentation (getName no work)
			printTabs(depth, "  Method Modifier: " + Modifier.toString(method.getModifiers()));
			printTabs(depth, "");

		}
	}

	private void inspectConstructor(Class c, int depth) {
		// (4) Inspect Constructors
		// a) The name constructors the class declares. For each, also find the
		// following:
		// b) The parameter types
		// c) The modifiers

		Constructor[] Classconstructors = c.getConstructors();
		for (Constructor constructor : Classconstructors) {
			printTabs(depth, "Constructor Name: " + constructor.getName());
			Class[] parameterTypes = constructor.getParameterTypes();
			String consParam = "  Parameter(s): ";
			int i = 0;
			for (Class parameter : parameterTypes) {
				if (i == 0) {
					consParam += parameter.getName();
					i++;
				} else {
					consParam += ", " + parameter.getName();
				}

			}

			printTabs(depth, consParam);
			// double check if toString is allowed / read documentation
			printTabs(depth, "  Constructor Modifier: " + Modifier.toString(constructor.getModifiers()) + "\n");

		}
	}

	private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
		if(c.isInterface() == false) {
			printTabs(depth, "");
			
		}
			
		
		//printTabs(depth, "");
		// (1) Get name of the class
		printTabs(depth, "Name of Class: " + c.getName());

		// (2) Get name of the immediate superclass
		if(c.getSuperclass() != null) {
			 System.out.println("Name of Immediate superclass: " +
			 c.getSuperclass().getName() + "\n");
			
		}


		// Constructors that the class declares
		// (3) Examine the interfaces
		Class[] interfaces = c.getInterfaces();
		for (Class inter : interfaces) {
				recurse(inter, obj, recursive, depth, true);
				printTabs(depth, "");
				
		}




	}

	private void inspectField(Class c, Object obj, int depth, boolean recurseFlag) {
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
			printTabs(depth, "Field Name: " + Classfields[k].getName());
			printTabs(depth, "  Field Type: " + Classfields[k].getType());
			// double check if toString is allowed / read documentation
			printTabs(depth, "  Field Modifier: " + Modifier.toString(Classfields[k].getModifiers()));

			try {
				// Need to get arrays and also other objects :<
				Object value = Classfields[k].get(obj);
				if (checkPrimitiveValue(value)) {
					printTabs(depth, "  Field Value: " + value + "\n");

				} else {
					if(recurseFlag == false) {
						printTabs(depth, "  Field Value: " + Classfields[k].getName() +"@"+ Classfields[k].hashCode() +  "\n");
						
					}
					
					// Handle arrays and recursive = True 
					if (Classfields[k].getType().isArray()) {
						String arrayField = "  Field Value: [";

						for (int w = 0; w < Array.getLength(value); w++) {
							Object thing = Array.get(value, w);
							if (checkPrimitiveValue(thing)) {
								// System.out.println("Field Value: " + value + "\n");
								arrayField += " " + thing + ",";
								
							}
							else if (thing == null) {
								if(w < Array.getLength(value) - 1) {
								arrayField += "null, ";}
								else {
									arrayField += "null";
									
								}
							}
							else { // Not primitive
								if(recurseFlag) {
									recurse(thing.getClass(),thing,recurseFlag, depth+1, false);
									
								}
								
							}
						}
						arrayField += "]" + "Length of Array = " + Array.getLength(value);
						printTabs(depth, arrayField);
						
						

					}
					
					
					
					
					
					
					if(Classfields[k].get(obj) != null) {
						recurse(Classfields[k].get(obj).getClass(), value, recurseFlag, depth+1, false);
						
					}else{
						//recurse(Classfields[k].get(obj).getClass(), value, recurseFlag, depth+1, false);
						
						printTabs(depth, "  Field Value: null \n");
					}

				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


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
				//System.out.println("Not primitive type \n");
				return false;
			}

		}
		return false;
	}

}
