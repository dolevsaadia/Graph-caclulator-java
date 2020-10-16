package Ex1Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import javax.swing.text.PlainDocument;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;

class PolynomTest {


	Polynom [] polylist;
	Monom [] monolist;
	String [] str;


	@BeforeEach
	public void setUp() throws Exception 
	{

		polylist = new Polynom[5];
		monolist = new Monom[5];
		str = new String[5];
		double start = -1000;
		double end = 1000;
		for (int i = 0; i < 5; i++) {
			double random = new Random().nextDouble();
			double result = start + (random * (end - start));
			str[i] = result+"x^";
		}

		for (int i = 0; i < 5; i++) {
			int random = new Random().nextInt(100 + 1);
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
	void testPolynomString() {
		Polynom [] p = new Polynom [5];

		for (int i = 0; i < p.length; i++) {
			int random1 = new Random().nextInt(5);
			int random2 = new Random().nextInt(5);
			if(str[random2].charAt(0)== '-') {
				p[i] = new Polynom(str[random1]+str[random2]);
				Monom m1 = new Monom(str[random1]);
				Monom m2 = new Monom(str[random2]);
				if(p[i].f(5) != (m1.f(5)+m2.f(5))) {fail();}

			}else {
				p[i] = new Polynom(str[random1]+"+"+str[random2]);
				Monom m1 = new Monom(str[random1]);
				Monom m2 = new Monom(str[random2]);
				if(p[i].f(5) != (m1.f(5)+m2.f(5))) {fail();}	
			}


		}



	}

	@Test
	void testF() {

		Polynom [] polyst = new Polynom [5]; 
		polyst[0]= new Polynom ("2x^4+8x^2-13");
		polyst[1]= new Polynom ("2x^18+8x^1+8x-13");
		polyst[2]= new Polynom ("10x^0+8x^1-x-2");
		polyst[3]= new Polynom ("0x^2+8x^2-13");
		polyst[4]= new Polynom ("-x^4-8x^2-13");
		double [] results_f_2 = {51,524307,22,19,-61};
		double [] results_f_3 = {221,774841013,29,59,-166};

		for(int i=0;i<polyst.length;i++) 
		{
			if((results_f_2[i]!=polyst[i].f(2))){fail();}
			if((results_f_3[i]!=polyst[i].f(3))){fail();}

		}
	}

	@Test
	void testAddPolynom_able() {

		for (int i = 0; i < polylist.length; i++) {
			int random = new Random().nextInt(5);
			Polynom temp = (Polynom) polylist[i].copy();
			polylist[i].add(polylist[random]);
			polylist[random].add(temp);
			assertEquals(polylist[i], polylist[random]);
		}
	}

	@Test
	void testAddMonom() {

		for (int i = 0; i < polylist.length; i++) {		
			int random1 = new Random().nextInt(5);
			int random2 = new Random().nextInt(5);
			Polynom temp2 = new Polynom();
			temp2.add(monolist[random1]);
			assertEquals(temp2, monolist[random1]);
			if(temp2.f(2)!= monolist[random1].f(2)) {fail();}	
			Polynom temp = (Polynom) polylist[i].copy();
			polylist[i].add(monolist[random1]);
			polylist[i].add(monolist[random2]);
			temp.add(monolist[random2]);
			temp.add(monolist[random1]);	
			assertEquals(polylist[i], temp);
			temp = new Polynom(polylist[i].toString());
			Polynom temp3 = (Polynom) temp.copy();
			temp2 = new Polynom();
			temp.add(temp2);
			assertEquals(temp, temp3);
		}

	}

	@Test
	void testSubstract() {
		for (int i = 0; i < polylist.length; i++) {		
			int random1 = new Random().nextInt(5);
			int random2 = new Random().nextInt(5);
			Polynom temp2 = new Polynom();
			temp2.substract(polylist[random1]);
			if(temp2.f(2)*-1!= polylist[random1].f(2)) {fail();}	
			Polynom temp = (Polynom) polylist[i].copy();
			temp.substract(polylist[random1]);
			temp.substract(polylist[random2]);
			temp.add(polylist[random1]);
			temp.add(polylist[random2]);
			assertEquals(temp, polylist[i]);
			temp = new Polynom(polylist[i].toString());
			Polynom temp3 = (Polynom) temp.copy();
			temp2 = new Polynom();
			temp.substract(temp2);
			assertEquals(temp, temp3);	
		}
	}

	@Test
	void testMultiplyPolynom_able() {

		for (int i = 0; i < polylist.length; i++) {		
			int random1 = new Random().nextInt(5);
			int random2 = new Random().nextInt(5);

			Polynom temp2 = new Polynom();
			temp2.multiply(polylist[random1]);
			if (temp2.toString()!="0") {fail();}

			Polynom temp3 = (Polynom) polylist[i].copy();
			Polynom temp4 = (Polynom) polylist[i].copy();

			temp3.multiply(polylist[random1]);
			temp4.multiply(polylist[random1]);

			temp3.add(polylist[random1]);
			temp4.add(polylist[random1]);
			assertEquals(temp3, temp4);

		}

	}

	@Test
	void testEqualsObject() {

		for (int i = 0; i < polylist.length; i++) {		
			int random1 = new Random().nextInt(5);
			int random2 = new Random().nextInt(5);

			Monom m1	= (Monom) monolist[i].copy();
			Monom m2	= (Monom) monolist[i].copy();
			Object o1	= new Object();
			Object o2	= new Object();
			o1 = m1.copy();
			o2 = monolist[i].copy();
			Polynom p = new Polynom();
			p.add(monolist[i]);
			
			if(!(p.equals(m1)&&p.equals(p)&&p.equals(m2)&&p.equals(o1)&&p.equals(o2))) 
			{fail();}
		}
	}

	@Test
	void testIsZero() {
		
		for(int i=0;i<polylist.length;i++) 
		{
			int random1 = new Random().nextInt(5);
			int random2 = new Random().nextInt(5);
			
			Polynom p1 = (Polynom) polylist[random1].copy();
			Polynom p2 = (Polynom) polylist[random1].copy();
			
			p1.substract(p1);
			p2.multiply(new Monom ("0x"));
			if (!p1.isZero()) {fail();}
			if (!p2.isZero()) {fail();}
		}
		
	}

	@Test
	void testRoot() {
		Polynom p0=new Polynom("x^2");//only 0
		Polynom p1=new Polynom("2x^2");// no one exception.
		Polynom p2=new Polynom("-x^3+2x");
		Polynom p3=new Polynom("-2x^2+2");//
		double a =0;
		double b =0;
		
		double diff = a-b;
		if (a == b || (diff < 0.01 && diff > -0.01)) 
		
		
		b=p0.root(0, 1, 0.000001); if( !(b==0 || (b < 0.01 && b > -0.01)) ) {fail();}
		assertThrows( RuntimeException.class,() ->p1.root(-1, 1, 0.0001));
		b=p2.root(-2, 0, 0.0001); 
		if( !(b==-0) ) {fail();}
		assertThrows( RuntimeException.class,() -> p3.root(-0.5,0.5,0.00001)); 
	}

	@Test
	void testCopy() {
		function [] temp =new Polynom_able[5];

		for(int i=0;i<polylist.length;i++) 
		{
			temp[i]=polylist[i].copy();
		}
		assertArrayEquals(polylist,temp);
	}

	@Test
	void testDerivative() {

		Polynom_able [] temp1 = new Polynom[5];
		temp1[0] = new Polynom("0");
		temp1[1] = new Polynom("-x");
		temp1[2] = new Polynom("12x^0");
		temp1[3] = new Polynom("12x^2");
		temp1[4] = new Polynom("36x");
		
		for (int i = 0; i < temp1.length; i++) {
			temp1[i]=temp1[i].derivative();
		}
		
		Polynom [] temp2 = new Polynom[5];
		temp2[0] = new Polynom("0");
		temp2[1] = new Polynom("-1");
		temp2[2] = new Polynom("0");
		temp2[3] = new Polynom("24x");
		temp2[4] = new Polynom("36");
		
		assertArrayEquals(temp2,temp1);
		
	}

	@Test
	void testArea() {

		String test3 = "-2x^8+8x^2+13";// 20.928468752204605
		Polynom p3 = new Polynom(test3);		
		if (p3.area(0, 6, 0.00001)!=20.928468752204605);

		String test4 = "0";;//0.0
		Polynom p4 = new Polynom(test4);
		if (p4.area(0, 6, 0.00001)!=0.0) {fail();};

		String test6 = "x^2";

		Polynom p6 = new Polynom(test6);
		try {
	
		p6.area(0, 6, -0.00001);
		}catch (Exception e) {
		}
		String test7 = "x^2";
		Polynom p7 = new Polynom(test7);
		try {
		p6.area(0, 6, 0.0);
		}catch (Exception e) {
		}
	}

	@Test
	void testMultiplyMonom() {
		
		Polynom p1 [] = {new Polynom("x^2"),
				new Polynom("2x^2"),
				new Polynom("1x^0"),
				new Polynom("0x^3"),
				new Polynom("10x^3")}; 
		Monom m1 = new Monom ("2x"); 
		
		for (int i = 0; i < p1.length; i++) {
			p1[i].multiply(m1);
		}
		
		Polynom p2 [] = { new Polynom("2x^3"),
				new Polynom("4x^3"),
				new Polynom("2x"),
				new Polynom("0"),
				new Polynom("20x^4"),};
		assertArrayEquals(p2, p1);
	}


	@Test
	void testToString() {
		Polynom p1 [] = {new Polynom("x^2"),
				new Polynom("2x^2"),
				new Polynom("1x^0"),
				new Polynom("0x^3"),
				new Polynom("10x^3")};
		Monom m1 = new Monom ("2x"); 
		assertEquals("x^2",p1[0].toString());
		assertEquals("2.0x^2",p1[1].toString());
		assertEquals("1",p1[2].toString());
		assertEquals("0",p1[3].toString());
		assertEquals("10.0x^3",p1[4].toString());
	}

	@Test
	void testInitFromString() {
		Polynom p1 = new Polynom();
		
		function [] polyst = new Polynom [5]; 
		polyst[0]= p1.initFromString("2x^4+8x^2-13");
		polyst[1]= p1.initFromString("2x^18+8x^1+8x-13");
		polyst[2]= p1.initFromString("10x^0+8x^1-x-2");
		polyst[3]= p1.initFromString("0x^2+8x^2-13");
		polyst[4]= p1.initFromString("-x^4-8x^2-13");
		
		Polynom [] temp = new Polynom [5]; 
		temp[0]= new Polynom ("2x^4+8x^2-13");
		temp[1]= new Polynom ("2x^18+8x^1+8x-13");
		temp[2]= new Polynom ("10x^0+8x^1-x-2");
		temp[3]= new Polynom ("0x^2+8x^2-13");
		temp[4]= new Polynom ("-x^4-8x^2-13");
		assertArrayEquals(temp, polyst);


	}

}
