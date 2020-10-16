
package Ex1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.management.RuntimeErrorException;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

/**
 * @author Dor Getter && Omer Rugi. 
 */
public class Functions_GUI implements functions, Serializable {

	@SerializedName("FunctionArry")
	ArrayList<function> list = new ArrayList<function>();

/**
 * adding a function to the Draw list array;
 */
	@Override
	public boolean add(function arg0) {
		try {
			list.add(arg0);
			return true;
		}
		catch (Exception e) {
			throw new RuntimeException("Unable to add " + arg0.toString());
		}
	}
	
/**
 * add all the functions in a collection to Draw list array;
 * returns true if all the collections is successfully transfer into the Draw list array; 
 */
	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		if (list.addAll(arg0)) return true;

		return false;
	}
/**
 * clear the Draw list array; 
 */
	@Override
	public void clear() {
		list.clear();

	}
/**
 * check if an function is contained in the Draw list array; 
 * return true if function in Draw list; 
 */
	@Override
	public boolean contains(Object arg0) {
		if(list.contains(arg0)) return true;
		return false;
	}
/**
 * check if all the collection is contain within the Draw list array; 
 * return true if contain;
 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		if(list.containsAll(arg0)) return true;
		return false;
	}
/**
 * check if the Draw list array is empty; 
 * return true if the list is empty;
 */
	@Override
	public boolean isEmpty() {
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

/**
 * Draw list iterator; 
 * return the list array iterator; 
 */
	@Override
	public Iterator<function> iterator() {
		return list.iterator();
	}
/**
 * Removing an object from Draw list array; 
 * return true if the object successfully removed.
 */
	@Override
	public boolean remove(Object arg0) {
		try {
		if(list.remove(arg0)) return true ;
		}catch (Exception e) {
			throw new RuntimeException("the object does not on list.");
		}
		return false;
	}
/**
 * remove all the input collection functions from the Draw list array; 
 * return true if the object successfully removed.
 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		if(list.removeAll(arg0)) return true; 

		return false;
	}
/**
 * removes from this collection all of its elements that are not contained in the specified collection; 
 * return true if successfully retain. 
 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		if(list.retainAll(arg0)) return true; 

		return false;
	}
/**
 * return the Draw list size. 
 */
	@Override
	public int size() {
		return list.size();
	}
/**
 * Returns an array containing all of the elements in this list in proper sequence.
 */
	@Override
	public Object[] toArray() {
		return list.toArray();
	}
/**
 * Returns an array containing all of the elements in this list in propersequence (from first to last element); 
 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		return list.toArray(arg0);
	}
/**
 * 							init from file: 
 * In given file name (by string) the function build Function objects to print in graph. 
 * Valid inputs:	 full file name as written in the depositors.,  .txt files Only.  	
 * 
 *  
 * 							 Way of action:
 *  *		Creating FileReader obj. 	 --> Convenience class for reading character files.
 *  
 *  **		creating a buffer  		 	 --> Reads text from a character-input stream, buffering characters so 
 *  										 as to provide for the efficient reading of characters, arrays, and lines.
 *  
 *  ***		Using temp string 'readline' --> Reads a line of text. A line is considered to be terminated
 *  										 by any one of a line feed. covering all lines using while loop.
 *  
 *  ****	saving all the lines read by the readline as list (list_temp).
 *  
 *  ***** 	save this Draw list array to be the list_temp array read from the file. 
 * @param file: the file containing objects parameters to print.
 */
	@Override
	public void initFromFile(String file) throws IOException {

		try {
			File tempFile = new File(file);
			boolean exists = tempFile.exists();
			if(!exists) {throw new IOException();}							// if file not exists.
			FileReader reader = new FileReader(file);						//reader.
			BufferedReader buffer = new BufferedReader(reader);				//buffer. 
			ArrayList <function> list_temp =new ArrayList<function>();		//hold the list of elements from file. 
			String readline;												//hold lines from file. 
			readline=buffer.readLine();							
			while(readline!=null) 
			{
				list_temp.add(new ComplexFunction(readline));			
				readline=buffer.readLine();
			}
			this.list=list_temp;											//save this list to be the list from file. 
			buffer.close();
			reader.close();
		}catch (Exception e) {
			throw new IOException();
		}
	}

/**
 * 							save to file: 
 * In given file name (by string) the function save Function objects file. 
 * Valid inputs:	 full file name as written in the depositors.   	
 * 
 * 
 *  
 * 							 Way of action:
 *  *		Creating FileWriter  obj. 	 --> Convenience class for writing character files.
 *  
 *  **		creating PrintWriter obj. 	 --> Prints formatted representations of objects to a text-output stream.
 *  										 
 *  
 *  ***		Using for loop go over all the objects in Draw list array and print them into .txt file. 
 *  
 * @param file: the file to write the obj array into.
 */	
	@Override
	public void saveToFile(String file) throws IOException {
		
		try {
			File tempFile = new File(file);
			FileWriter f_writer = new FileWriter(file);			
			PrintWriter p_writer = new PrintWriter(file);
			for(int i=0;i<list.size();i++) {
				p_writer.println(list.get(i).toString());
			}
			p_writer.close(); 
			f_writer.close();
		}catch (Exception e) {
			throw new IOException();
		}
	}	
/**
 * 					DrawFunctions (User setting): 
 * 
 * Setting GUI window to the user specifications and print the functions contained in the Draw list array.
 *  
 * 							 Way of action:
 *  *		Setting canvas size. 
 *  **		setting the x and y scale. 
 *  ***		draws the x,y axis and the x,y measurements numbers.
 *  ****	draws the functions from the Draw list array - each with specific random color. 
 *  
 * @param width		: window width.
 * @param height	: window height. 
 * @param rx		: Range of the x axis. (contain [min,max]);
 * @param ry		: Range of the y axis. (contain [min,max]);
 * @param resulotion: sets the length between each line while drawing. 
 */	
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {


		StdDraw.setCanvasSize(width,height);												
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());

		StdDraw.setPenRadius(0.001);					
		StdDraw.setPenColor(Color.LIGHT_GRAY);									//color of slots.
		
						//creating slots\\
		
		for (int i = (int) rx.get_min(); i < rx.get_max(); i++) {				 

			StdDraw.line(i,(int) ry.get_max(), i,(int) ry.get_min());
			
		}
		for (int i =(int) ry.get_min() ; i < ry.get_max(); i++) {
			StdDraw.line((int)rx.get_max(), i, (int) rx.get_min(), i);
			
		}
					//draw texts numbers on axis\\
		
		StdDraw.setPenRadius(0.00000001);
		StdDraw.setPenColor(Color.BLACK);
		for (int i = (int) Math.min(rx.get_min(), ry.get_min()); i <(int)  Math.max(rx.get_max(), ry.get_max()); i++) {
			StdDraw.text(i, -0.5, ""+i);
			if (i==0) {}
			else
				StdDraw.text(0.5, i, ""+i);

		}
		StdDraw.setPenRadius(0.004);
		StdDraw.setPenColor(StdDraw.BLACK);
		
					//creating the x,y axis\\
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		StdDraw.setPenRadius(0.002);

			//give random color to function using RGB\\
		for (int j = 0; j < list.size(); j++) {
			int r= (int) (Math.random()*244);
			int g= (int) (Math.random()*244);
			int b= (int) (Math.random()*244);

			System.out.println("java.awt.Color[r="+r+",g="+g+",b="+b+"] f(x)="+list.get(j));
			//draw the functions from the Draw list- according to scale && resolution\\ 
			for (double i = rx.get_min(); i < rx.get_max()+1; i+=rx.get_max()/resolution) {
				StdDraw.setPenColor(r, g, b);
				try {	
					if(ry.isIn(list.get(j).f(i)) ||ry.isIn(list.get(j).f(i+0.1)) ) {
						StdDraw.line(i, list.get(j).f(i), i+0.1, list.get(j).f(i+0.1));
					}
				}catch (Exception e) {}
			}

		}

	}

/**
 * 				DrawFunctions (settings Window GUI from file): 
 * 
 * Setting GUI window specifications from file and print the functions contained in the Draw list array.
 *  
 * 							 Way of action:
 *  *		Creating a Gson obj 	--> Read Json files. 
 *  **		Creating Filereader obj --> Convenience class for reading character files.
 *  ***		Creating GUI_Window obj -->  This method deserializes the Json read from the specified
 *  									 parse tree into an object of the specified type. 
 *  ****	Setting the window range fields using the Json file parameters. 
 *  *****	Setting the window width height and resolution.  
 * @param Json_File	: Json text file contain object to get window GUI specifications from.
 */	
	@Override
	public void drawFunctions(String json_file) {

		Gson gson = new Gson();
 
		try 
		{
			File tempFile = new File(json_file);
			boolean exists = tempFile.exists();
			if(!exists) {
				System.out.println( json_file+" Dosn't exists");
				this.drawFunctions();
				return;
				}									//if file not exists.
			FileReader reader = new FileReader(json_file);
			GUI_Window window = gson.fromJson(reader,GUI_Window.class);
			Range rx=new Range(window.Range_X[0],window.Range_X[1]);
			Range ry=new Range(window.Range_Y[0],window.Range_Y[1]);
			this.drawFunctions(window.Width, window.Height, rx, ry, window.Resolution);
		} 
		catch (FileNotFoundException e) {	
			e.printStackTrace();
		}
	}
/**
 * 					DrawFunctions (defualt printing): 
 * 
 * Printing the functions in the Draw list array in default settings. 
 *  
 * 							 Way of action:
 *	*		Setting canvas size. 
 *  **		setting the x and y scale. 
 *  ***		draws the x,y axis and the x,y measurements numbers.
 *  ****	draws the functions from the Draw list array - each with specific random color. 
 */
	public void drawFunctions() {
		//setting canvas size and x,y axis parameters\\ 
		int width = 1000;
		int height = 600;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		int resolution = 200;
		
		StdDraw.setCanvasSize(width,height);
		
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());

		StdDraw.setPenRadius(0.001);
		StdDraw.setPenColor(Color.LIGHT_GRAY);
			
			//creating slots\\
		

		for (int i = (int) rx.get_min(); i < rx.get_max(); i++) {				 
			StdDraw.line(i,(int) ry.get_max(), i,(int) ry.get_min());
		}
		for (int i =(int) ry.get_min() ; i < ry.get_max(); i++) {
			StdDraw.line((int)rx.get_max(), i, (int) rx.get_min(), i);	
		}

		//draw texts numbers on axis\\
		
		StdDraw.setPenRadius(0.00000001);
		StdDraw.setPenColor(Color.BLACK);
		for (int i = (int) Math.min(rx.get_min(), ry.get_min()); i <(int)  Math.max(rx.get_max(), ry.get_max()); i++) {
			StdDraw.text(i, -0.5, ""+i);
			if (i==0) {}
			else
				StdDraw.text(0.5, i, ""+i);

		}


		StdDraw.setPenRadius(0.004);
		StdDraw.setPenColor(StdDraw.BLACK);
		
		//draw the x,y axis\\
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());


		
		StdDraw.setPenRadius(0.002);
		//give random color to function using RGB\\
		for (int j = 0; j < list.size(); j++) {
			int r= (int) (Math.random()*244);
			int g= (int) (Math.random()*244);
			int b= (int) (Math.random()*244);
			
			System.out.println("java.awt.Color[r="+r+",g="+g+",b="+b+"] f(x)="+list.get(j));
			//printing functions\\
			for (double i = rx.get_min(); i < rx.get_max()+1; i+=rx.get_max()/resolution) {
				StdDraw.setPenColor(r, g, b);
				try {
					
				if(ry.isIn(list.get(j).f(i)) ||ry.isIn(list.get(j).f(i+0.1)) ) {
					StdDraw.line(i, list.get(j).f(i), i+0.1, list.get(j).f(i+0.1));
				}}catch (Exception e) {System.out.println("de");}
			}
		}

	}
/**
 * 							Get i:
 * 
 * * Function returns the object in the Draw list array as a ComplexFunction type.  
 * 			
 * 			Valid inputs:	 int in range of the Draw list array. 
 * 
 *  
 * 							 Way of action:
 *  *		Checking the object type. 
 *  ** 		According to type returning a ComplexFunction object. 
 *  
 * @param i :	index in list array.
 * @return 	:	ComplexFunction in specific index. 
 */
	public ComplexFunction get(int i) {
		try {
			if (list.get(i) instanceof Polynom_able || list.get(i) instanceof Monom ) {
				ComplexFunction cf = new ComplexFunction(list.get(i));
				return cf;
			}else if ( list.get(i) instanceof ComplexFunction) {
				ComplexFunction cf = new ComplexFunction(list.get(i));
				return cf;
			}
		}catch (Exception e) {
			throw new RuntimeException("Index out of bound!");
		}
		return null;
	}
////////////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * Class contains the Gui_Function parameters. 
	 * @author Dor Getter && Omer Rugi
	 */
	public class GUI_Window 
	{

		public int Width;
		public int Height;
		public int Resolution;
		public double []Range_X;
		public double []Range_Y;

		public GUI_Window(int width,int height,int resolution,double []Range_X,double []Range_Y)
		{
			Range_Y=new double[2];
			for (int i = 0; i < 3; i++) {
				this.Range_X[i]=Range_X[i];
				this.Range_Y[i]=Range_Y[i];
			}
			
			this.Height=height;
			this.Width=width;
			this.Resolution=resolution;
		}

	}

}
