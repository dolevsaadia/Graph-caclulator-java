package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;

class Functions_GUITest {

	@Test
	void main() 
	{
		functions data = FunctionsFactory();
			int w=1000, h=600, res=200;
			Range rx = new Range(-10,10);
			Range ry = new Range(-5,15);
			data.drawFunctions(w,h,rx,ry,res);
			String file = "function_file.txt";
			String file2 = "function_file2.txt";
			try {
				data.saveToFile(file);
				Functions_GUI data2 = new Functions_GUI();
				data2.initFromFile(file);
				data.saveToFile(file2);
			}
			catch(Exception e) {e.printStackTrace();}
			
			String JSON_param_file = "GUI_params.txt";
			data.drawFunctions(JSON_param_file);
	}


	private functions _data=null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		_data = FunctionsFactory();
	}

	@Test
	void testInitFromFile() throws IOException {
		String Works	= "Works.txt";
		String NotWorks = "NotWorks.txt";
		String NotWrite	= "NotWritable.txt";
		_data.saveToFile(Works);
		Functions_GUI data1 = new Functions_GUI();
		Functions_GUI data2 = new Functions_GUI();
		Functions_GUI data3 = new Functions_GUI();
		data1.initFromFile(Works);
		assertThrows( IOException.class,() -> data2.initFromFile(NotWorks));
		assertThrows( IOException.class,() -> data3.initFromFile(NotWrite));	
	}

	@Test
	void testSaveToFile() throws IOException {
		for (int i = 0; i<5; i++) {
			String file = "sample"+i+".txt";
			PrintWriter writer = new PrintWriter(file);
			_data.saveToFile(file);
		}
		
		for (int i = 5; i < 10; i++) {
			String file = "sample"+i+".txt";
			_data.saveToFile(file);
		}
	}

	@Test
	void testDrawFunctionsIntIntRangeRangeInt() {
		_data.drawFunctions(1080, 1080, new Range(-15, 15), new Range(-25, 25), 200);
		
	}

	@Test
	void testDrawFunctionsString() {
		_data.drawFunctions("GUI_params.json"); // can't find the file will use default
		_data.drawFunctions("GUI_params.txt"); // Will draw from the file 
	}

	@Test
	void testDrawFunctions() {
		Functions_GUI temp = (Functions_GUI) _data;
		temp.drawFunctions();
	}

	public static functions FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}

		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return ans;
	}

}
