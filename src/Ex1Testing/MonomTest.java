package Ex1Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

class MonomTest {
	
	Monom [] monolist;
	String [] str;
	
	@BeforeEach
	public void setUp() throws Exception 
	{

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
			int random = new Random().nextInt(9 + 1)+2;
			str[i] =str[i]+""+random;
		}
		
		for (int i = 0; i < 5; i++) {
			monolist[i] = new Monom(str[i]);
		}
		

	}
	@AfterEach
	public void tearDown() throws Exception 
	{
		for(int i=0;i<monolist.length;i++) 
		{
			monolist[i]=null;
		}
	}

	@Test
	void testMonomDoubleInt() {

		double start = -50;
		double end = 50;
		double [] coo = new double[5];
		
		for (int i = 0; i < 5; i++) {
			double random = new Random().nextDouble();
			double result = start + (random * (end - start));
			coo[i] = result;
		}
		int [] power = new int[5];
		for (int i = 0; i < 5; i++) {
			int random = new Random().nextInt(9 + 1)+1;
			power[i] = random;
		} 
		for (int i = 0; i < power.length; i++) {
			Monom m1 = new Monom(coo[i],power[i]);
		}
		
	}

	@Test
	void testMonomMonom() {
		
		for(int i=0;i<monolist.length;i++) 
		{
			Monom temp=new Monom(monolist[i]);
			assertEquals(monolist[i],temp);
		}
	}

	@Test
	void testGet_coefficient() {

		double start = -50;
		double end = 50;
		double [] coo = new double[5];
		
		for (int i = 0; i < 5; i++) {
			double random = new Random().nextDouble();
			double result = start + (random * (end - start));
			coo[i] = result;
		}
		int [] power = new int[5];
		for (int i = 0; i < 5; i++) {
			int random = new Random().nextInt(9 + 1)+1;
			power[i] = random;
		} 
		for (int i = 0; i < power.length; i++) {
			Monom m1 = new Monom(coo[i],power[i]);
			double temp = coo[i];
			double temp2 = m1.get_coefficient();
			if(temp!=temp2) {fail();}
		}
		
	}

	@Test
	void testGet_power() {
		
		double start = -50;
		double end = 50;
		double [] coo = new double[5];
		
		for (int i = 0; i < 5; i++) {
			double random = new Random().nextDouble();
			double result = start + (random * (end - start));
			coo[i] = result;
		}
		int [] power = new int[5];
		for (int i = 0; i < 5; i++) {
			int random = new Random().nextInt(9 + 1)+1;
			power[i] = random;
		} 
		for (int i = 0; i < power.length; i++) {
			Monom m1 = new Monom(coo[i],power[i]);
			int temp = power[i];
			int temp2 = m1.get_power();
			if(temp!=temp2) {fail();}
		}
		
	}

	@Test
	void testDerivative() {

		String[] test = 	{"12x^2","6.03x^4","x^8","25x^11"};
		String[] result = {"24x", "24.12x^3", "8x^7","275x^10"};
		
		for (int i = 0; i < result.length; i++) {
			Monom m = new Monom(test[i]);
		
			Monom m1= new Monom(result[i]);
			assertEquals(m.derivative(),m1);
			}
	}


	@Test
	void testF() {
		String[] test =    {"12x^2","6.03x^4","x^8","25x^11"};
		double [] result = {48,96.48,256,51200};
		
		for (int i = 0; i < result.length; i++) {
			Monom m = new Monom(test[i]);
			double res= result[i];
			double res2=m.f(2);
			if(res2!=res) {fail();};
			}
	}

	@Test
	void testIsZero() {
		
		Monom [] te = new Monom[5]; 
		Monom [] tx = new Monom[5]; 
		for (int i = 0; i < te.length; i++) {
			te[i]= new Monom (monolist[i]);
			tx[i]= new Monom (-(monolist[i].get_coefficient()),(monolist[i].get_power()));
			te[i].add(tx[i]);
			boolean temp = te[i].isZero();
			assertTrue(temp);
		}
		
	}

	@Test
	void testMonomString() {

		String[] monoms = {"X", "--x","3fx","2+4","4x^x","2^2x","2(x)","2x2","3x^3+2",
				"4x^(3+2)", "4x^3i","4x^3d","4x^3f","x^-3"};

		for(int i=0;i<monoms.length;i++) { // Invalid tests
			try {
			Monom temp = new Monom(monoms[i]);
			}catch (RuntimeException e) {// if get RuntimeException = works ok
				
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		Monom [] test = new Monom[5];
		for (int i = 0; i < test.length; i++) {
			test[i] =new Monom(str[i]);
			assertEquals(test[i], monolist[i]);
		}

	}

	@Test
	void testAdd() {
		
		String[] monoms1 = {"2","x","-x^2", "12.03x^4"};
		String[] monoms2 = {"3","3x","x^2","-13.0043x^4"};
		String[] monoms3 = {"4.999999999","3.9999999x","0","-0.9743x^4"};
		Monom temp;
		Monom [] monom_added = new Monom[4];
		for (int i = 0; i < monom_added.length; i++) {
			monom_added[i] = new Monom(monoms1[i]);
			monom_added[i].add(new Monom(monoms2[i]));
			assertEquals(new Monom(monoms3[i]), monom_added[i]);
		}
		
		///
		
		Polynom_able p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms4 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms5 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		
		for(int i=0;i<monoms4.length;i++) {
			Monom m = new Monom(monoms4[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms5.length;i++) {
			Monom m = new Monom(monoms5[i]);
			p2.add(m);
		}
		
		if (!p1.equals(new Polynom(p1.toString()))) {
			fail();
			
		}
		if (!p1.equals(new Polynom("-4.7x^2-1.0x+6.0"))) {
			fail();
			
		}

	}

	@Test
	void testMultipyMonom() {
		String[] monoms1 = {"2","x","x^2", "x^4"};
		String[] monoms2 = {"3","3x","x^2","x^4"};
		String[] monoms3 = {"5.99999999","3x^2","x^4","x^8"};
		Monom temp;
		Monom [] monom_added = new Monom[4];
		for (int i = 0; i < monom_added.length; i++) {
			monom_added[i] = new Monom(monoms1[i]);
			monom_added[i].multipy(new Monom(monoms2[i]));
			assertEquals(new Monom(monoms3[i]), monom_added[i]);
		}
		
	}

	@Test
	void testMultipyInt() {
		
		String[] monoms1 = {"2","x","x^2", "x^4"};
		int[] monoms2 =	   {3,3,1,4};
		String[] monoms3 = {"5.99999999","3x","x^2","4x^4"};
		Monom temp;
		Monom [] monom_added = new Monom[4];
		for (int i = 0; i < monom_added.length; i++) {
			monom_added[i] = new Monom(monoms1[i]);
			monom_added[i].multipy(monoms2[i]);
			assertEquals(new Monom(monoms3[i]), monom_added[i]);
		}
		
		String[][] polynoms = {{"3x^2","-6x^3","9x","-2"},{"x" , "3x^3"} , 
								{"-18.0x^6" , "9.0x^5" ,"21.0x^4" ,"-3.0x^3",
								"9.0x^2", "-2.0x"}};
		
		
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom();
		Polynom p3 = new Polynom();
		for (int i = 0; i < polynoms[0].length; i++) {
			Monom temp2 = new Monom(polynoms[0][i]);
			p1.add(temp2);
		}		
		for (int i = 0; i < polynoms[1].length; i++) {
			Monom temp2 = new Monom(polynoms[1][i]);
			p2.add(temp2);
		}

		for (int i = 0; i < polynoms[2].length; i++) {
			Monom temp2 = new Monom(polynoms[2][i]);
			p3.add(temp2);
		}
		Polynom multiply = new Polynom();
		multiply = (Polynom) p1.copy();
		multiply.multiply(p2);

	}

	@Test
	void testToString() {

		String [] mono_str = new String[5];
		for (int i = 0; i < mono_str.length; i++) {
			mono_str[i] = str[i];
		}
		assertEquals(mono_str[0], monolist[0].toString());
		assertEquals(mono_str[1], monolist[1].toString());
		assertEquals(mono_str[2], monolist[2].toString());
		assertEquals(mono_str[3], monolist[3].toString());
		assertEquals(mono_str[4], monolist[4].toString());
	}

	@Test
	void testEqualsMonom() {
		
		String[] monoms1 = {"2","x","-x^2", "12.03x^4"};
		String[] monoms2 = {"3","3x","x^2","-13.0043x^4"};
		String[] monoms3 = {"4.999999999","3.9999999x","0","-0.9743x^4"};
		Monom temp;
		Monom [] monom_added = new Monom[4];
		for (int i = 0; i < monom_added.length; i++) {
			monom_added[i] = new Monom(monoms1[i]);
			monom_added[i].add(new Monom(monoms2[i]));
			temp =new Monom(monoms3[i]);
			assertTrue(temp.equals(monom_added[i]));
		}
		String[] monoms4 = {"2","x","x^2", "x^4"};
		String[] monoms5 = {"3","3x","x^2","x^4"};
		String[] monoms6 = {"5.99999999","3x^2","x^4","x^8"};
		Monom temp1;
		Monom [] monom_added2 = new Monom[4];
		
		
		for (int i = 0; i < monom_added.length; i++) {
			monom_added2[i] = new Monom(monoms4[i]);
			monom_added2[i].multipy(new Monom(monoms5[i]));
			temp =new Monom(monoms6[i]);
			assertTrue(temp.equals(monom_added2[i]));
		}
		
		
	}
		
	

	@Test
	void testInitFromString() {

		Monom [] mono = new Monom[5];
		String [] strd = new String[5];
		double start = -50;
		double end = 50;
		for (int i = 0; i < 5; i++) {
			double random = new Random().nextDouble();
			double result = start + (random * (end - start));
			
			strd[i] = result+"x^";
		}
		
		for (int i = 0; i < 5; i++) {
			int random = new Random().nextInt(9 + 1)+2;
			strd[i] =strd[i]+""+random;
		}
		
		for (int i = 0; i < 5; i++) {
			mono[i] = new Monom(strd[i]);
		}
		
		String[] monoms1 = {"2","x","-x^2", "12.03x^4"};
		Monom temp;
		Monom [] monom_added = new Monom[4];
		Monom [] monom_added2 = new Monom[4];
		for (int i = 0; i < monom_added.length; i++) {
			monom_added[i] = new Monom(monoms1[i]);
			monom_added2[i] = new Monom(monoms1[i]);
			if(!monom_added[i].equals(monom_added2[i])) {fail();}
		}
		
	}

	@Test
	void testCopy() {
		
		Monom [] temp = new Monom[5];
		for (int i = 0; i < temp.length; i++) {
			temp[i]=(Monom) monolist[i].copy();
			
			if(!temp[i].equals(monolist[i])) {fail();}
		}
		
		
	}

}
