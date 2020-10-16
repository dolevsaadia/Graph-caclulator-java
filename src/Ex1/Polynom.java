package Ex1;
import java.util.*;
import java.util.function.Predicate;

import javax.management.RuntimeErrorException;

import com.google.gson.annotations.SerializedName;

/**
 											**Class Explanation**
* ---------------------------------------------------------------------------------------------------------------------
* This class represents a Polynom OBJ- implemented by String of Monom objects .											|																				|
* 																														|
* Polynom class works as following: 																					|
* *Receiving a String containing "Polynom" / Monom OBJ.																	|
* *case 1: Breaks down the String into separate Monoms- and add separately to a ArrayList that will contain the Polynom.|
* *case 2: recived a Monom OBJ. and adds it to a Polynom ArrayList.														|
*----------------------------------------------------------------------------------------------------------------------																														|
* 											**Methods implemented**
* ---------------------------------------------------------------------------------------------------------------------
* 1) 	Polynom(String)			-Constructor.	 																		|
* 2)	f						-Place x in the polynom.																|
* 3)	add(Polynom_able)	 	-Adding two Polynom_able objects into one.												|
* 4)	add(Monom)				-Adding Monom into the Polynom.															|
* 5)	substruct(Polnom_able) 	-Substruction of two Polnoms.															|
* 6)	multiply				-Multiplying two Polynoms.																|
* 7)	equals					-Check if two Polynoms are equal- by deviation of Epsilon.								|
* 8)	isZero					-Check if Polynom is equal to zero.														|
* 9)	root					-Calculate the point which the Polynom==zero between x0,x1 with deviation of Epsilon.	|
* 10)	Copy					-Crates deep copy of Polynom.															|
* 11)	Derivative				-Derivative the Polynom.																|
* 12)	area					-Calculates the area size between the function to positive x axis.						|
* 13)	multiply				-Multiply Polynom by Monom. 															|
* 14) 	Iterator				-Returns Iterator from Class Iterator.													|
* 15) 	toString				-Returns a String of the Polynom.														|
* ---------------------------------------------------------------------------------------------------------------------
* 										**Legit Polynnom**
* ----------------------------------------------------------------------------------------------------------------------
* Polynom will be only in the form of String containing valid Monoms: ax^b +/- ax^b +/- .... +/- ax^b.					|
* the following cases are not valid: 																					|
* 1) arithmetical operation within the equation that are not +\-.														|
* 2) String containing letters or different characters other then 'x' (small x), '^' if there is a power.				|
* ----------------------------------------------------------------------------------------------------------------------
* 									**class PolyIterator<Monom>**
* ----------------------------------------------------------------------------------------------------------------------
* This class apply Iterator from type Polynom which implements Iterator class. 											|
* 												**Methods**																|
* 1) 	hasNext					-returning boolean true/false if there is a next element (monom) in the Polynom.		|  
* 2)	Next					-returning the pointer to the next monom in the Polynom. 								|
* ----------------------------------------------------------------------------------------------------------------------
* 
* @author DorGetter && OmerRugi
*
*/
public class Polynom implements Polynom_able{
	//			** class variabels **
	
	private static final double EPSILON = 0.0000001;
	
	@SerializedName("PloynomList")
	private ArrayList<Monom> polylist = new ArrayList<Monom>();
	
	public Polynom() {;}
	
	/**
	 * 							Polynom Constructor: 
	 * initialized a Polynom from a given String containing a equation of monoms. 
	 * f(x)= [String s].
	 * 
	 * Valid inputs:	 "x",			 "3+1.4X^3-34x".	
	 * UnValid input: 	"(3-3.4x+1)", 	 "((3.1x-1.2)-(3X^2-3.1))";
	 *  
	 * 							 Way of action:
	 *  *	Breaking down the String to Monoms saving each Monom subString into 'temp'.
	 *  **	Each subString in 'temp' sends to the Monom Constructor which check if the subString is Valid Monom.
	 *      and returns a Monom object back.
	 *  ***	Adding the Monom into the Polynom List.
	 * @param s: Represents a Polynom input.
	 */
	public Polynom(String s) {
		String temp = "";																	//Will contain the monoms during passing on the S- input string
		s = s.replaceAll(" ", "");
		if(s == "") {
			throw new RuntimeException("Empty string");
		}else if(s.charAt(0)== '+') {
			s = s.substring(1,s.length());
		}else if(s.contains("+-") || s.contains("-+") ) {
			throw new RuntimeException("Not a valid polynom");
		}
		
		for (int i = 0; i < s.length(); i++) {

			if(s.charAt(i) == '+') {														//Breaking the String to monoms: searching "+" arithmetic opp.
				try {
					Monom m1 = new Monom(temp);												//creating Monom from substring. 
					this.add(m1);									
					temp ="";																//initialized temp string.
				}
				catch (Exception e) {
					System.out.println(s);
					throw new RuntimeException("Not Valid Poly");}}				//Isnt a Monom or Polynom Valid input.
			else if(s.charAt(i) == '-' && temp!="") 										//same as above but "-" arithmetic opperation 
																							//between the Monoms.
			{
				Monom m1 = new Monom(temp);
				this.add(m1);
				temp ="-";
			}
			else
				temp += s.charAt(i);
		}

		Monom m1 = new Monom(temp);															//creating the Monom:
		this.add(m1);																		//adding it to the Polynom String. 
		Comparator<Monom> monom_comp = new Monom_Comperator();								//Construct new Comparator for Sorting the Monoms 
		polylist.sort(monom_comp);															//in the Polynom.

	}

	/**
	 * 					Polynom calculation-in given x:
	 * The function apply double type x on the Polynom and calculates the result.
	 * Ex. f(x)=2x^2+2x+2 , inputs: =2; , OutCome: 14.
	 *   
	 *  						Way of action:
	 * *	Using a for loop - go over all the Monoms in the polynom.
	 * **	Applying the x input into the equation.  								
	 * ***	Adds the result in ans. 
	 * ****	Return ans.
	 * 
	 * @param x - Value of x.
	 */
	@Override
	public double f(double x) {
		double ans = 0;																	//Holds the sum of the equation.
		for (int i = 0; i < polylist.size(); i++) {
			ans +=  polylist.get(i).f(x);												//Using Monom f-function to calculates the implement of x.
		}																				//and Add+Sum the result.
		return ans;
	}

	/**  						
	 * 				Polynom_able- adding function:
	 * The function receives a Polynom_able (type) p1 and adding it into the the 
	 * polynom object ,"this", in the class.
	 *   
	 * 						Way of action:
	 * *	Using a Iterator of type Monom, go over all the Monoms in the polynom p1.
	 * 		by using a while loop.
	 * **	Add each element in p1 to ,"this", Polynom in the class.
	 * 
	 * @param p1 - Polynom to be added. 				
	 */
	@Override
	public void add(Polynom_able p) {
		
		Polynom_able p1 = (Polynom_able) p.copy();
		Iterator<Monom> new_monom = p1.iteretor() ;										//Construct a Monom Iterator.
		Monom monom1;																	//Contain the Monom in p1. 

		while(new_monom.hasNext())	
		{
			monom1=new_monom.next();													
			this.add(monom1);															//adding the monom. 
		}

	}
	/**
	* 				Add Monom to Polynom function:
	* The function receive a Monom and add it to a Polynom by the following cases: 
	* case 1:	if the Monom's power is equal to a Monom in the Polynom-				sums the Monoms Coefficient.
	* case 2: 	if the Monom's power is not equal to any of the Monoms in the Polynom-	add the Monom to Polynom List.		
	*  
	*						Way of action:
	* *		Using a for loop go over the Polynom.
	* ** 	in any Monom in the Polynom check if its a case 1 or case 2.   
	* ***	if there is a Monom that its power is the same as m1 sum the coefficients and check: 
	* 		+	if the coefficient after adding is close to zero by Epsilon: remove the Monom from Polynom.
	* 		+	if the coefficient after adding is a number greater then 0+/-(Epsilon) add the Coefficients.   		 
	* ****	if there is none such Monoms with same power as m1 in Polynom. 
	* 		+ add the Monom as it is to the Polynom equation.
	* ***** Sort the Polynom for convenient.
	* 
	* 
	* @param m1 - Monom to be added to the Polynom. 
	*/
	@Override
	public void add(Monom m) {
		
		Monom m1 = new Monom(m);
		if(m1.get_coefficient() == 0) {													//if the Monom is zero- no action needed.
			return;
		}

		int i;																			//i= indexes the Monoms in the Polynom list.
		for (i = 0; i < polylist.size(); i++) {

			if(polylist.get(i).get_power() == m1.get_power()) {							//case 1: same powers.
				polylist.get(i).add(m1);												//Using Monom Class add method for adding the two Monoms.
				if(polylist.get(i).get_coefficient() == 0) {							//case 1.1: if after adding coefficient == 0 remove the Monom from the Polynom list.
					polylist.remove(i);
				}
				else if(polylist.get(i).get_coefficient()>0) {							//case 1.2: if after adding coefficient >= 0+-Epsilon (the followed else if as well)  
					if(polylist.get(i).get_coefficient()<0+EPSILON) {					// 			remove coefficient from the Polynom list.
						polylist.remove(i);
					}
				}
				else if(polylist.get(i).get_coefficient()<0) {
					if(polylist.get(i).get_coefficient()>0-EPSILON) {
						polylist.remove(i);
					}
				}
				Comparator<Monom> monom_comp = new Monom_Comperator();					//for convenient Sort the Polynom.
				polylist.sort(monom_comp);
				return;
			}	
		}

		if(i == polylist.size()) {														//case 2: add the Monom to the Polynom list. 
			polylist.add(m1);
		}

		Comparator<Monom> monom_comp = new Monom_Comperator();							//Sort for convenient.
		polylist.sort(monom_comp);

	}

	/**
	* 				subtruct Polynom from Polynom function:
	* The function receive a Polynom and substract it from "this" Polynom Obj. in the class.   
	*
	*						Way of action:
	* *		Construct a new Iterator on p1. 
	* ** 	if p1 is same as the Polynom Obj. (same pointer). the Polynom is initialized. 
	* *** 	Using a while loop- change the coefficients of every Monom in p1 to the opposite sign. (negative <--> Positive.)
	* **** 	using add Monom method adding each Monom of p1 to the Polynom Obj.   
	* @param p1- Polynom for subtraction. 
	*/
	@Override
	public void substract(Polynom_able p) {
		
		Polynom_able p1 = (Polynom_able) p.copy();
		Iterator<Monom> new_monom = p1.iteretor() ;										//creating iterator to travel on p1 Monom's.

		if(this==p1) {																	//same Pointers (same Objects).
			polylist = new ArrayList<Monom>();
		}
		while(new_monom.hasNext())									
		{
			Monom monom0 = new Monom(new_monom.next());									//Holds the next Monom from p1, 
			Monom monom1=new Monom(monom0.get_coefficient()*-1,monom0.get_power());		//and multiply the coefficient by (-).
			this.add(monom1);															//Using add method(monom) to add the monom from p1 to the Polynom Obj. in the class.  
		}
	}
	/**
	* 				Multiplay Polynom * Polynom function:
	* The function receive a Polynom and multiply it by the Polynom Obj. in the class.
	*
	*						Way of action:
	* *		Creating a temp Polynom.
	* **	Using a for loop go over each Monom in Polynom list.   
	* ***	Using nested loop multiply each one of the Monoms in Polynom list. by every Monom in p1.
	* ****	update the Polynom ArrayList pointer.  	
	* @param p1- Polynom for multiply. 
	*/
	@Override
	public void multiply(Polynom_able p) {
		
		Polynom_able p1 = (Polynom_able) p.copy();
		Polynom temp_poly = new Polynom();												//Creating a temp Polynom.

		for (int i = 0; i < polylist.size(); i++) {										//Go over Polynom list.

			for (Iterator<Monom> m1 = p1.iteretor(); m1.hasNext();) {					//Nested loop to go over each Monom in p1.
				Monom m2 = new Monom(m1.next());							
				Monom m3 = new Monom(polylist.get(i));									
				m3.multipy(m2);															//Using multiply Method of Monom class.  
				temp_poly.add(m3);														//Adding the Monom after multiplication to the Polynom temp list.		
			}
		}
		polylist = temp_poly.polylist;													//Update the Polylist.
	}
	
	/**
	* 						Equals function:
	* The function receive a Polynom and check wither it equal Polynom Obj. in the class.
	*
	*						 Way of action:
	* *		Using a for loop go over each Monom in the Polynom Obj. in the class.
	* **	Using a nested loop go over each Monom in p1.
	* ***	Check if there is a Monom in p1 that is equals to Monom in Polynom m1 (Polynom list in the class).  	  	   
	* @param p1- Polynom to check if equal. 
	*/
	@Override
	public boolean equals(Object p1) {

		if(!(p1 instanceof function)) {
			throw new RuntimeException("Not the valid OBJ");
		}else if(p1 instanceof Monom) {

			if(polylist.size() > 1) {
				return false;
			}else {
				String temp = polylist.get(0).toString();
				String temp2 = p1.toString();
				if(temp.equals(temp2)) {
					return true;
				}
				else
					return false;
			}
		}else if(p1 instanceof ComplexFunction) {
			return p1.equals(this);
		}else if(p1 == this) {
			return true;
		}
			
		boolean flag = false;												// flag to know if a Monom from p1 is in this polynom
		Iterator<Monom> m1 = ((Polynom_able) p1).iteretor();
		Iterator<Monom> m2 = this.iteretor();
		while(m2.hasNext() && m1.hasNext()) {
			
			Monom temp1 = new Monom(m1.next());
			Monom temp2 = new Monom(m2.next());
			if (!temp1.equals(temp2)) {
				return false; 
			}
			
		}
		if (m1.hasNext() || m2.hasNext())
			return false; 
					
		return true;
	}
	
	/**
	* 						isZero function:
	* The function check if the Polynom is empty. 
	*
	*						 Way of action:
	* *		check the length of the Polynom.
	* 		case 1: size==0 --> true.
	* 		case 2: size!=0 --> false.  	  	    
	*/
	@Override
	public boolean isZero() {

		if(polylist.size() == 0) {
			return true;
		}
		return false;
	}
	/**
	* 						root function:
	* The function in a given 2 x's points returns the x dot in which the function 
	* cuts the x axis.   
	* 				--Using the Intermediate Value Theorem--
	*  If f(x) is continuous on a closed interval [a,b], and c is any number between f(a) and f(b) inclusive
 	*  then there is at least one number x in the closed interval such that f(x)=c.
 	*  and if f(a)*f(b)<0 then there is at least one number x in the close interval such that f(x)=0.
 	*  
	*
	*						 Way of action:
	* *		Checking if applying x0 and x1 in the Polynom equation and multiply the result of the two, Provides a negative result.
	* **	Using while loop and "binary search" principal to locate the x in which f(x)=0 +/-(Epsilon).  
	* ***	Every iteration advanced one side of the closed Interval according to the result of applying the mid point.
	* **** 	When the result of applying the mid	is close by Epsilon or less to 0. return that mid.  	  	   
	* @param x0- 	left  x dot. 
	* @param x1- 	right x dot.
	* @param eps- 	Epsilon step desired.
	* @return X the point which the function cuts the x axis with offset of a given eps. 
	*/																					
	@Override
	public double root(double x0, double x1, double eps) {
		
		if(eps<=0||eps>x1-x0) {throw new RuntimeException("EPSILON unvalid input");}		//Check valid eps input
		else if(x0>x1) { throw new RuntimeException("x0 cannot be greater then x1");}
		else if (this.f(x0)*this.f(x1)>0) {													//Check if the function cuts the x axis.
			 throw new RuntimeException("Function dosn't rooted in given interval");
		}else if(this.f(x0)==0) {
			return x0;
		}else if(this.f(x1)==0) {
			return x1;
		}
		
		boolean flag = true;																//While loop condition.  
		double mid =((x0+x1)/2);											
		while(flag) {																
			mid = ((x0+x1)/2);																//Update mid.
			if (this.f(mid)<0) {
				if (this.f(mid)>=0-eps) {													//Checking which side of the interval to update.
					return mid;
				}
				else if(this.f(mid)*this.f(x0)<=0) {
					x1 =mid;
				}
				else if(this.f(mid)*this.f(x1)<=0) {
					x0 = mid;
				}
			}
			else if (this.f(mid)>0) {
				if (this.f(mid)<=0-eps) {												
					return mid; 
				}
				else if(this.f(mid)*this.f(x0)<=0) {
					x1 =mid;
				}
				else if(this.f(mid)*this.f(x1)<=0) {
					x0 = mid;
				}
			}
			
		}

		return 0;	
	}
	/**
	* 		    		  Deep copy function:
	* The function creates deep copy of the Polynom Obj.
	* 						 Way of action:
	* *		Copying each Monom from Polynom Obj list to a new Polynom.  
	* @return a Deep copy of Polynom.
	*/
	
	
	public function copy() {
		
		function p1 = new Polynom();
		p1 = p1.initFromString(this.toString());
		return p1;
	}
	
	
	/**
	* 						Derivative function:
	* The function applys a derivative operation on the Polynom.
	* 			
	*						 Way of action:
	* *		Using Polynom Iterator the function go over each Monom and apply the derivative method of Monom.
	* ** 	Adds the new Monom after derivation to the new Polynom.      
	* @return The derivation of the Polynom.     	
	*/
	@Override
	public Polynom_able derivative() {

		Polynom_able deriv_poly = new Polynom();												//Construct a Polynom to contain the changes after derivation.
		Iterator<Monom> m1 = this.iteretor();													//Monom Iterator.		


		while(m1.hasNext()) {																	//Go over the Polynom Monom's. 
			Monom temp = new Monom(m1.next());													//Temp Monom holds the derivative.
			temp = temp.derivative();													
			deriv_poly.add(temp);																
		}
		return deriv_poly;																		//The derivation of the Polynom.     	

	}
	/**
	* 						Area function:
	* Calcuates the area in the close interval that block between the function and the Positive side of the x axis using Riemann Sums role. 
	* 			
	*						 Way of action:
	* *		From x0 to x1 moving in Epsilon steps.
	* **	sum the area if the f(x)>0 (Positive area).
	* 
	* @param eps 	Epsilon step chosen by user.
	* @param x1 	Left hand side of the interval.
	* @param x0		Right hand side of the interval.
	* @return 		The area between the function and the x axis in given close Interval.     	
	*/
	@Override
	public double area(double x0, double x1, double eps) {
		if(x0>x1) {																				//If x0>x1 throw Exception 
			throw new RuntimeException("x0 > x1 unvalid input");
		}
		if(eps<=0) {
			throw new RuntimeException("eps unvalid input");
		}
		double positive_area =0;																//Sum the area- F-->x axis.
		
		for(double i = x0; i<=x1; i+=eps) {														//Go in Epsilon steps. 
			if(this.f(i) > 0) {																	//Only if its a Positive area (above x axis) add to sum.
				positive_area+= this.f(i)*eps;
			}
		}

		return positive_area;
	}
	/**
	* 						Multiply function:
	* The function multiply Polynom by a Monom.
	* 			
	*						 Way of action:
	* *		Using a for loop apply multiplication method of Monoms to each Monom in Polynom by m1.  
	* ** 	Sets the Polynom list Monom in index i to the Monom after multiplication.
	* @param m1- 	the Monom wish to multiply the Polynom with.     	
	*/
	@Override
	public void multiply(Monom m1) {

		for (int i = 0; i < polylist.size(); i++) {									
			Monom temp = new Monom(polylist.get(i));
			temp.multipy(m1);	
			polylist.set(i, temp);
			if(temp.isZero()) {polylist.remove(i);}
		}

	}
	/**
	* 						iterator function:
	*
	* @return new PolyIterator .     	
	*/
	@Override
	public Iterator<Monom> iteretor() {

		return new PolyIterator() ;
	}
	
	/**
	* 						toString function:
	* The function Return a string of the Polynom.
	* 			
	*						 Way of action:
	* *		Using Polynom Iterator the function go over each Monom and Uses the Monom toString Method.
	* ** 	Joint the Monoms by examine the sign of the next Monom coefficient. 
	* 
	* @return String of the Polynom.     	
	*/
	@Override
	public String toString() {

		String poly ="";
		Iterator<Monom> m1 = this.iteretor();

		for (int i = 0; i < polylist.size() && m1.hasNext(); i++) {								//Go over the Polynom Monoms	
			Monom m2 = new Monom(m1.next());													
			if(i == 0) {																		//The first element is enters to the String by default. 
				poly += m2.toString();
			}
			else {																				
				if(m2.get_coefficient()>0) {													//Case of sign of the Monom is Positive. Enter '+' 
					if(m2.get_coefficient()==1) {	
						if(m2.get_power()==0) {
							poly += "+"+m2.get_coefficient();
						}
						else {
						poly += "+"+"x";
						}														//If coefficient == 1 put 'x' in the string.
						if(m2.get_power() != 0 && m2.get_power() != 1) {
							poly +="^"+m2.get_power();											//Put '^' if rhe power of the Monom greater> 0.
						}
					}
					else
						poly += "+"+m2.toString();												//Just put '+' no special case.
				}
				else
					poly += m2.toString();														//The Monom have a (-) sign. 
			}
		}

		if(poly == "") {																		//If the Polynom is empty situation.
			return "0";
		}
		return poly;
	}
	/**
	* 						toString function:
	* The function Return a polynom initialized from string. 
	* 			
	*						 Way of action:
	* *		Using a constructor build from string a new polynom.  
	* @param s: String from which build a Polynom. 
	* @return String of the Polynom.     	
	*/
	@Override
	public function initFromString(String s) {

		function p1 = new Polynom(s);
		return p1;
	}
	
	
	
/**
											**Class Explanation**
* ---------------------------------------------------------------------------------------------------------------------
* This class represents a Polynom Iterator Used in order to travel the Monoms which build the Polynom.					|
* class implements the following methods: 																				|
* 																														|
* Polynom class works as following: 																					|
* *Receiving a String containing "Polynom". 																			|
* *Breaks down the String into separate Monoms- and add separately to a ArrayList that will contain the Polynom.		|
*----------------------------------------------------------------------------------------------------------------------																														|
* 											**Methods implemented**
* ---------------------------------------------------------------------------------------------------------------------
* 1) 	PolyIterator							-Constructor.			index-used to index the Monoms.			        |
* 2)	hasNext									-boolean Method 		true-Polynom has more Monoms -false otherwise.  |         																			|
* 3) 	next									-Pointer Method 		Return pointer to the next Monom in list  		|
* ----------------------------------------------------------------------------------------------------------------------
*/	 																		
	private class PolyIterator<Monom> implements Iterator<Monom>{

		private int index;

		public PolyIterator() {
			index =0;
		}

		public boolean hasNext() {

			return index < polylist.size();
		}


		@SuppressWarnings("unchecked")
		@Override
		public Monom next() {

			return (Monom) polylist.get(index++);
		}

	}




}
