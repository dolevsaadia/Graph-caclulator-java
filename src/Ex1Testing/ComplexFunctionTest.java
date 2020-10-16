package Ex1Testing;

import Ex1.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.function.Function;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComplexFunctionTest {


	ComplexFunction [] complist;
	Polynom [] polylist;
	Monom [] monolist;
	String [] str;
	
	@BeforeEach
	void setUp() throws Exception
	{
		complist = new ComplexFunction[5];
		polylist = new Polynom[5];
		monolist = new Monom[5];
		str = new String[5];
		double start = -50;
		double end = 50;
		for (int i = 0; i < 5; i++) {
			double random = new Random().nextDouble();
			double result = start + (random * (end - start));
			
			str[i] = result+"x^";
		}
		
		for (int i = 0; i < 5; i++) {
			int random = new Random().nextInt(9 + 1)+1;
			str[i] =str[i]+""+random;
		}
		
		for (int i = 0; i < 5; i++) {
			monolist[i] = new Monom(str[i]);
		}
		
		for (int i = 0; i <5; i++) {
			int random = new Random().nextInt(4 + 1);
			polylist[i] = new Polynom();
			polylist[i].add(monolist[random]);
			polylist[i].add(monolist[random]);
		}
		
		for (int i = 0; i <5; i++) {
			int random = new Random().nextInt(4 + 1);
			complist[i] = new ComplexFunction(polylist[random]);
			complist[i].comp(polylist[random]);
		}
		
		for (int i = 0; i <5; i++) {
			int random = new Random().nextInt(4 + 1);
			complist[i].plus(monolist[random]);
		}
		
	}
	
	@AfterEach
	void tearDown() throws Exception
	{
		for(int i=0;i<monolist.length;i++) {
			polylist[i]=null;
			monolist[i]=null;
			str[i]=null;
		}
			
	}

	@Test
	void testComplexFunctionFunction() {
		
		
		ComplexFunction [] test = new ComplexFunction[5];
		for (int i = 0; i < 5; i++) {
			test[i] = new ComplexFunction(complist[i]);
			assertEquals(test[i], complist[i]);
		}
		
		test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			test[i] = new ComplexFunction(polylist[i]);
			assertEquals(test[i], polylist[i]);
		}
		
		test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			test[i] = new ComplexFunction(monolist[i]);
			assertEquals(test[i], monolist[i]);
		}
		
		
		
		try {
			String[] s3 = {"x+3","x-2", "x-4"};
			Polynom p1 = new Polynom(s3[0]);
			Polynom p2 = new Polynom(s3[1]);
			Polynom p3 = new Polynom(s3[2]);
			ComplexFunction cf1 = new ComplexFunction(p1);
			ComplexFunction cf2 = new ComplexFunction(p2);
			ComplexFunction cf3 = new ComplexFunction(p3);
		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail("Not yet implemented");
		}

	}

	@Test
	void testComplexFunctionStringFunctionFunction() {
		
		ComplexFunction [] test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			Operation op = complist[i].getOp();
			function left = complist[i].left();
			function right = complist[i].right();
			String op_str = ""+op;
			test[i] = new ComplexFunction(op_str,left,right);
		}
		
		for (int i = 0; i < test.length; i++) {
			assertEquals(complist[i], test[i]);
		}
		
		try {
			String s1 = "3.1+2.4x^2-x^4";
			String s2 = "5 +2x-3.3x+0.1x^5";
			Polynom p1 = new Polynom(s1);
			Polynom p2 = new Polynom(s2);
			ComplexFunction cf = new ComplexFunction("Plus", p1, p2);
			ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x+1"), cf);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		try {
			Polynom p2 = new Polynom("0");
			ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x+1"), p2);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	void testComplexFunctionOperationFunctionFunction() {
		
		ComplexFunction [] test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			Operation op = complist[i].getOp();
			function left = complist[i].left();
			function right = complist[i].right();
			test[i] = new ComplexFunction(op,left,right);
		}
		for (int i = 0; i < test.length; i++) {
			assertEquals(complist[i], test[i]);
		}	
			
		try {
			
			Polynom p = new Polynom("x^2+22+11x");
			Monom m1 = new Monom("x^3");
			ComplexFunction temp = new ComplexFunction(Operation.Plus, p, m1);
			
			function f1 = new Monom("x^33");
			ComplexFunction test2 = new ComplexFunction(Operation.Divid, temp,f1);
		
		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}

	}

	@Test
	void testF() {


		ComplexFunction [] test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Plus,polylist[i],polylist[i]);
			double temp = test[i].f(random);
			double temp2 = polylist[i].f(random)+polylist[i].f(random);
			if (temp != temp2) {
				fail();
			}
		}
		
		ComplexFunction [] test2 = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random = new Random().nextInt(4 + 1);
			test2[i] = new ComplexFunction(Operation.Times,polylist[i],polylist[i]);
			double temp = test2[i].f(random);
			double temp2 = polylist[i].f(random)*polylist[i].f(random);
			if (temp != temp2) {
				fail();
			}
		}
		
		ComplexFunction [] test3 = new ComplexFunction[5];
		
		for (int i = 0; i < test3.length; i++) {
			int random = new Random().nextInt(4 + 1);
			test3[i] = new ComplexFunction(polylist[i]);
			for (int j = 0; j < test3.length; j++) {
				test3[i].plus(test2[j]);
				test3[i].div(test[j]);
				
			}
			test3[i].f(1);
		}
		
		
		Polynom p1 = new Polynom("5x");
		Polynom p2 = new Polynom("3x");
		Monom m1 = new Monom("2");
		Monom m2 = new Monom("1");

		try {
			ComplexFunction cf1 = new ComplexFunction(p1);
			cf1.mul(p2);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}

		try {

			ComplexFunction cf1 = new ComplexFunction("mul",p1,m1);
			cf1.mul(p2);

		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}
		try {

			ComplexFunction cf1 = new ComplexFunction(p1);
		
			cf1.div(p2);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}

		try {
			ComplexFunction cf1 = new ComplexFunction("div",p1,m1);
			
			cf1.div(p2);

		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}

	}

	@Test
	void testInitFromString() {
		
		Polynom a = new Polynom("0");
		ComplexFunction cfx = new ComplexFunction(a);
		function cf1x = cfx.initFromString("plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.298x+5.0)");
		ComplexFunction cf2 = new ComplexFunction(cf1x);
		assertEquals(cf2.toString(), cf1x.toString());
		ComplexFunction temp = new ComplexFunction("x");
		assertThrows( RuntimeException.class,() -> temp.initFromString("plus(3.1x,11x^5.5)"));
		assertThrows( RuntimeException.class,() -> temp.initFromString("plus(33x^2+19)"));
		assertThrows( RuntimeException.class,() -> temp.initFromString("plus(x^7),()"));
		assertThrows( RuntimeException.class,() -> temp.initFromString("Divi(-1.0x^4,2)"));
		function temp1 = temp.initFromString("plus(3.1x,+11x^5)");
		function temp2 = temp.initFromString("Max(Max(Max(Max(Plus(-1.0x^4+ 2.4x^2 + 3.1,+0.1x^5-1.2999999999999998x+5.0),Plus(Divid(x+1.0,Times(Times(x+3.0,x-2.0),x-4.0)), +2.0)),Divid(Plus(-1.0x^4+2.4x^2+3.1, 0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)),-1.0x^4+2.4x^2+3.1),0.1x^5-1.2999999999999998x+5.0)");
		
		
	}

	@Test
	void testCopy() {
		
		
		ComplexFunction [] test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			test[i] = (ComplexFunction) complist[i].copy();
			assertEquals(test[i], complist[i]);
		}
		
		Polynom p1 = new Polynom("55.446x");
		Polynom p2 = new Polynom("3x^2");
		Monom m1 = new Monom("2.01");
		Monom m2 = new Monom("1");
		try {
		ComplexFunction cf1 = new ComplexFunction(p1);
		ComplexFunction cf2 = (ComplexFunction) cf1.copy();
		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testPlus() {
		
		
		ComplexFunction [] test = new ComplexFunction[5]; 
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Plus,polylist[random1],polylist[random2]);
			double t1 = test[i].f(10);
			double t2 = polylist[random1].f(10)+polylist[random2].f(10);
			if(t1 != t2) {
				fail();
			}
			
		}
		
	
		Polynom p1 = new Polynom("55.446x");
		Polynom p2 = new Polynom("3x^2");
		Monom m1 = new Monom("2.01");
		Monom m2 = new Monom("1");
	
		try {
			ComplexFunction cf1 = new ComplexFunction(p1);
			cf1.plus(p2);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}

		try {
			ComplexFunction cf1 = new ComplexFunction(Operation.Plus,p1,m1);
			cf1.plus(p2);

		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}


	}

	@Test
	void testMul() {

		ComplexFunction [] test = new ComplexFunction[5]; 
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Times,monolist[random1],monolist[random2]);
			double t1 = test[i].f(10);
			double t2 = monolist[random1].f(10)*monolist[random2].f(10);
			if(t1 != t2) {
				fail();
			}
			
		}
		
		
		test = new ComplexFunction[5]; 
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Times,polylist[random1],polylist[random2]);
			double t1 = test[i].f(10);
			double t2 = polylist[random1].f(10)*polylist[random2].f(10);
			if(t1 != t2) {
				fail();
			}
			
		}
		
		test = new ComplexFunction[5]; 
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Times,complist[random1],complist[random2]);
			double t1 = test[i].f(10);
			double t2 = complist[random1].f(10)*complist[random2].f(10);
			if(t1 != t2) {
				fail();
			}
			
		}
			
		
		Polynom p1 = new Polynom("55.446x");
		Polynom p2 = new Polynom("3x^2");
		Monom m1 = new Monom("2.01");
		Monom m2 = new Monom("1");

		try {

			ComplexFunction cf1 = new ComplexFunction(p1);
			
			cf1.mul(p2);
		

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}

		try {

			ComplexFunction cf1 = new ComplexFunction("mul",p1,m1);

		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testDiv() {
		
		
		ComplexFunction [] test = new ComplexFunction[5]; 
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Divid,monolist[random1],monolist[random2]);
			try {
			double t1 = test[i].f(10);
			double t2 = monolist[random1].f(10)/monolist[random2].f(10);
			if(t1 != t2) {
				fail();
			}}catch (RuntimeException e) {}// if div by zero keep alive
			
			ComplexFunction temp = new ComplexFunction(Operation.Divid,new Polynom("257x^4"),new Polynom("3x^2"));
			ComplexFunction temp2 = new ComplexFunction(new Polynom("85.6666667x^2"));
			if (temp.f(1)!=85.66666666666667) {fail();}
			if(!(temp.equals(temp2))) {fail();}
		}

		
		test = new ComplexFunction[5]; 
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Divid,polylist[random1],polylist[random2]);
			try {
			double t1 = test[i].f(10);
			double t2 = polylist[random1].f(10)/polylist[random2].f(10);
			if(t1 != t2) {
				fail();
			}}catch (RuntimeException e) {} // if div by zero keep alive
			
		}
		
		test = new ComplexFunction[5]; 
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Divid,complist[random1],complist[random2]);
			try {
			double t1 = test[i].f(10);
			double t2 = complist[random1].f(10)/complist[random2].f(10);
			if(t1 != t2) {
				fail();
			}}catch (RuntimeException e) {}// if div by zero keep alive
			
		}
		
		
		
		Polynom p1 = new Polynom("55.446x");
		Polynom p2 = new Polynom("3x^2");
		Monom m1 = new Monom("2.01");
		Monom m2 = new Monom("1");

		try {

			ComplexFunction cf1 = new ComplexFunction(p1);
			cf1.div(p2);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}

		try {
			ComplexFunction cf1 = new ComplexFunction("div",p1,m1);
			cf1.div(p2);

		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testMax() {
		
		
		ComplexFunction c1 = new ComplexFunction("Plus(Times(x,x),8)") ;//9
		ComplexFunction c2 = new ComplexFunction("Times(Plus(Times(x,x),8),3)") ;
		Polynom p1 = new Polynom("55.446x");
		Polynom p2 = new Polynom("3x^2");
		ComplexFunction c3 = new ComplexFunction(Operation.Max,p1,p2);
		double temp1 = c3.f(2);
		if(temp1 != p1.f(2)) { fail();}
		c3= new ComplexFunction(Operation.Max,c1,c2);
		temp1 =c3.f(1); 
		if(temp1 != c2.f(1)) { fail();} 
		Monom m1 = new Monom("2.01");
		Monom m2 = new Monom("1");
		

		try {

			ComplexFunction cf1 = new ComplexFunction(p1);
			cf1.max(p2);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}

		try {
			ComplexFunction cf1 = new ComplexFunction("max",p1,m1);
			cf1.max(p2);
			
		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}	
	}

	@Test
	void testMin() {
		   
		ComplexFunction c1 = new ComplexFunction("Plus(Times(x,x),8)") ;//9
		ComplexFunction c2 = new ComplexFunction("Times(Plus(Times(x,x),8),3)") ;
		Polynom p1 = new Polynom("55.446x");
		Polynom p2 = new Polynom("3x^2");
		ComplexFunction c3 = new ComplexFunction(Operation.Min,p1,p2);
		double temp1 = c3.f(2);
		if(temp1 != p2.f(2)) { fail();}
		c3= new ComplexFunction(Operation.Min,c1,c2);
		temp1 =c3.f(1); 
		if(temp1 != c1.f(1)) { fail();} 
		
		p1 = new Polynom("55.446x");
		p2 = new Polynom("3x^2");
		Monom m1 = new Monom("2.01");
		Monom m2 = new Monom("1");
	
		try {
			
			ComplexFunction cf1 = new ComplexFunction(p1);
			cf1.min(p2);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}

		try {

			ComplexFunction cf1 = new ComplexFunction("min",p1,m1);

			cf1.min(p2);
		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}	
	}

	@Test
	void testComp() {
		
		ComplexFunction [] test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Comp,polylist[random1],polylist[random2]);
			double temp1 = test[i].f(3);
			double temp2 = polylist[random1].f(polylist[random2].f(3));
			if(temp1 != temp2) {fail();}
		}
		
		test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Comp,monolist[random1],monolist[random2]);
			double temp1 = test[i].f(3);
			double temp2 = monolist[random1].f(monolist[random2].f(3));
			if(temp1 != temp2) {fail();}
		}
		
		Polynom p1 = new Polynom("55.446x");
		Polynom p2 = new Polynom("3x^2");
		Monom m1 = new Monom("2.01");
		Monom m2 = new Monom("1");
		try {
			
			ComplexFunction cf1 = new ComplexFunction(p1);

			cf1.comp(p2);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		try {
			ComplexFunction cf1 = new ComplexFunction("comp",p1,m1);
			cf1.comp(p2);
			
		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();}
	}

	@Test
	void testLeft() {
		
		ComplexFunction [] test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Comp,polylist[random1],polylist[random2]);
			assertEquals(test[i].left(), polylist[random1]);
		}		
		test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Comp,complist[random1],complist[random2]);
			assertEquals(test[i].left(), complist[random1]);
		}		
		
	}

	@Test
	void testRight() {
		
		ComplexFunction [] test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Comp,polylist[random1],polylist[random2]);
			assertEquals(test[i].right(), polylist[random2]);
		}		
		test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Comp,complist[random1],complist[random2]);
			assertEquals(test[i].right(), complist[random2]);
		}	
		
		
		test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(complist[random1]);
			assertEquals(test[i].right(), null);
		}
		
		
	}

	@Test
	void testGetOp() {
		
		Operation [] op= new Operation[6];
		op[0] = Operation.Comp;
		op[1] = Operation.Divid;
		op[2] = Operation.Max;
		op[3] = Operation.Min;
		op[4] = Operation.Plus;
		op[5] = Operation.Times;
		
		
		
		ComplexFunction [] test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			int random3 = new Random().nextInt(5 + 1);
			test[i] = new ComplexFunction(op[random3],polylist[random1],polylist[random2]);
			assertEquals(op[random3], test[i].getOp());
		}		
		test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			int random3 = new Random().nextInt(5 + 1);
			test[i] = new ComplexFunction(op[random3],complist[random1],complist[random2]);
			assertEquals(op[random3], test[i].getOp());
			
		}	
		
		//test null; 
		test = new ComplexFunction[5];
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.None,complist[random1],null);
			assertEquals(Operation.None, test[i].getOp());
			
		}
		//test Error; 
		test = new ComplexFunction[5];
		
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			assertThrows( RuntimeException.class,() -> new ComplexFunction(Operation.Error,complist[random1],null));
		}
	}

	@Test
	void testToString() {
		
		ComplexFunction cf = new ComplexFunction("x");
		function [] temp = new ComplexFunction[5]; 
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cf.initFromString(complist[i].toString());
		}
		
		for (int i = 0; i < temp.length; i++) {
			assertEquals(temp[i].toString(), complist[i].toString());
		}
	}
	
	@Test
	void testequals() {
		
		ComplexFunction [] test = new ComplexFunction[5]; 
		ComplexFunction [] test2 = new ComplexFunction[5]; 
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Plus,monolist[random1],monolist[random2]);
			test2[i]= new ComplexFunction(Operation.Plus,monolist[random2],monolist[random1]); 
			if(!(test[i].equals(test2[i]))) {
				fail(); 
			}
				
		}
		
		test = new ComplexFunction[5]; 
		test2 = new ComplexFunction[5]; 
		for (int i = 0; i < test.length; i++) {
			int random1 = new Random().nextInt(4 + 1);
			int random2 = new Random().nextInt(4 + 1);
			test[i] = new ComplexFunction(Operation.Times,polylist[random1],polylist[random2]);
			test2[i]= new ComplexFunction(Operation.Times,polylist[random2],polylist[random1]); 
			if(!(test[i].equals(test2[i]))) {
				fail(); 
			}
				
		}
		try {
		Monom m1 = new Monom("x");
		Polynom p1 = new Polynom("x^2");
		ComplexFunction cf = new ComplexFunction(Operation.Divid,p1,m1);
	
		assertTrue(cf.equals(m1));
		}catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		}
		try {
			Monom m1 = new Monom("x^2");
			Polynom p1 = new Polynom("x");
			ComplexFunction cf = new ComplexFunction(Operation.Times,p1,p1);
			assertTrue(cf.equals(m1));
			}catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
				fail();
			}


			Monom m0 = new Monom("1");
			Monom m1 = new Monom("12x^2");
			Monom m2 = new Monom("12x");
			Monom m3 = new Monom("3");
			Monom m4 = new Monom("x");
			
			
			Polynom p1 = new Polynom("6x");

			ComplexFunction cf1 = new ComplexFunction(Operation.Times,m1,m1);
			cf1.div(m1);//==12x^3
			cf1.div(m4);//==12x suppose to..
			assertTrue(cf1.equals(m2));
			ComplexFunction cf2 = new ComplexFunction(Operation.Divid,p1,p1); //==1;
			
			assertTrue(cf2.equals(m0)); 
			
			cf2.mul(m1);//==12x^2
			cf2.div(m4);//==12x
			assertTrue(cf2.equals(m2));
			
	}
	
	
}
