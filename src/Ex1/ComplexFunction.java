package Ex1;

import java.util.function.Function;

import javax.management.InstanceAlreadyExistsException;

import com.google.gson.annotations.SerializedName;

/**
 **Class Explanation**
 * ---------------------------------------------------------------------------------------------------------------------
 * This class represents ComplexFunction OBJ. 																			|																				|
 * ComplexFunction is a obj. which contain two obj- Polynom/Monom/complex function and an opperation between them. 		|
 * ComplexFunction class works as following: 																			|
 * *Receiving F(x) ,G(x), and Operation to apply between them (G(x) can be null- in that case Op will be none).			|												|
 *----------------------------------------------------------------------------------------------------------------------																														|
 * 											**Methods implemented**
 * ---------------------------------------------------------------------------------------------------------------------
 * 1) 	ComplexFunction(function p1)			-Constructor Num 1.														|
 * 2)	ComplexFunction(op,left,right)			-Constructor Num 2.														|
 * 3)	ComplexFunction(String func)		 	-Constructor Num 3.														|
 * 4)	f(double x)								-Calculating implement x on function.									|
 * 5)	initFromString(String s)				-Initialized C.F using string. 											|
 * 6)	copy()									-Deep copy.																|
 * 7)	plus(function f1)						-implement plus op between two function type objects in C.F.			|
 * 8)	mult(function f1)						-implement mult op between two function type objects in C.F.			|
 * 9)	div(function f1)						-implement div  op between two function type objects in C.F				|
 * 10)	max(function f1)						-implement max  op between two function type objects in C.F.			|
 * 11)	min(function f1)						-implement min  op between two function type objects in C.F.			|
 * 12)	comp(function f1)						-implement comp op between two function type objects in C.F.			|
 * 13)	left(function f1)						-returns the L.H.S of given C.F											|
 * 14)	right(function f1)						-returns the R.H.S of given C.F											|
 * 15)	getop(function f1)						-returns the operation of given C.F.									|
 * 16)	toString(function f1)					-implement plus op between two function type objects in C.F.			|
 * 17)	equals(function f1)						-if the two mathematical object are logicly equal			 			|
 * ---------------------------------------------------------------------------------------------------------------------
 * 										**Legit ComplexFunction**
 * ----------------------------------------------------------------------------------------------------------------------
 * Legit C.F will Contain only operations from the list below, and will be construct from objects which implements 		|
 * 'Function'.																											|
 * Valid operations: Plus, plus, Times, mul, Divid, div, Max, max, Min, min, Comp , None, Error							|
 * ----------------------------------------------------------------------------------------------------------------------
 * 									
 * @author DorGetter && OmerRugi
 *
 */

public class ComplexFunction implements complex_function {
	
	@SerializedName("CF_LeftFunction")
	private function left;														//left,right are F(x),G(x).															
	@SerializedName("CF_RightFunction")
	private function right;
	@SerializedName("CF_Operation")
	private Operation op=Operation.None;													//Operation.



	/*
	 * default Constructor Creating an empty ComplexFunction.
	 * Not available to User.  
	 */
	private ComplexFunction() {}

	/**
	 * 			ComplexFunction Constructor Num 0: 
	 * initialized a ComplexFunction from F(x) only. 
	 * 
	 * Valid inputs:	Every Obj which implements function interface.	
	 *  
	 * 							 Way of action:
	 *  *	Receiving function implementor.
	 *  **	Declare the Operation as None. 
	 *  *** Set p1 to be the function on the L.H.S of the ComplexFunction obj.  
	 * @param p1: Function implementor obj. 
	 */
	public ComplexFunction(function p) {

		this.op = Operation.None;
		function p1 = p.copy();
		this.left = p1;
	}

	/**
	 * 			ComplexFunction Constructor Num 1: 
	 * initialized a ComplexFunction from F(x), G(X) and String represent operation. 
	 * 
	 * Valid inputs:	+Every Obj which implements function interface- as F(X),G(x).
	 *					+Operation from the Enum class && slight deviations:
	 *				(+) opp: Plus , plus.				
	 *				(*) opp: Times, times, mult, mul.	
	 *				(/) opp: Divid, divid, div.
	 *			   (MAX)opp: Max, max.
	 *			   (MIN)opp: Min, min.
	 *			  (Comp)opp: Comp, comp.   					
	 *  		  (NONE)opp; None, none.
	 * 							 Way of action:
	 *  *		Using switch case on the operation String Routing the function to the specific op case.
	 *  **		creating a new Complex Function. 
	 *  ***		Setting the the op to the 'this' (the object in class) to the specific operation. 
	 *  **** 	Setting the Left of 'this' C.F to the 'left' function obj. 
	 *  ***** 	Setting the Right of 'this' C.F to the 'right' function obj.
	 *  %%      if the op given isn't valid (not from the list above) by default case the function will throw new exception. 
	 * 
	 * @param op   : String containing the Operation type wished between F(x), G(x).
	 * @param left : implementor obj. of function.   
	 * @param right: implementor obj. of function. 
	 */
	public ComplexFunction(String op, function left_org, function right_org) {

		function left  = left_org.copy();
		function right;
		if(right_org == null) {
			right = null;
		}
		else
			right = right_org.copy();


		switch (op) {
		case "plus":
		case "Plus":
			ComplexFunction cf = new ComplexFunction(Operation.Plus, left,right);
			this.left = cf.left;
			this.right = cf.right;
			this.op = cf.op;
			break;

		case "times":
		case "mult":
		case "mul":
		case "Times":
			ComplexFunction cf1 = new ComplexFunction(Operation.Times, left,right);
			this.left = cf1.left;
			this.right = cf1.right;
			this.op = cf1.op;
			break;


		case "divid":
		case "div":
		case "Divid":
			ComplexFunction cf2 = new ComplexFunction(Operation.Divid, left,right);
			this.left = cf2.left;
			this.right = cf2.right;
			this.op = cf2.op;
			break;


		case "max":
		case "Max":
			ComplexFunction cf3 = new ComplexFunction(Operation.Max, left,right);
			this.left = cf3.left;
			this.right = cf3.right;
			this.op = cf3.op;
			break;

		case "min":
		case "Min":
			ComplexFunction cf4 = new ComplexFunction(Operation.Min, left,right);
			this.left = cf4.left;
			this.right = cf4.right;
			this.op = cf4.op;
			break;

		case "none":
		case "None":
			ComplexFunction cf5 = new ComplexFunction(Operation.None, left,right);
			this.left = cf5.left;
			this.right = cf5.right;
			this.op = cf5.op;
			break;

		case "comp":
		case "Comp":
			ComplexFunction cf6 = new ComplexFunction(Operation.Comp, left,right);
			this.left = cf6.left;
			this.right = cf6.right;
			this.op = cf6.op;
			break;


		default:
			throw new RuntimeException("Not a valid op");
		}

	}


	/**
	 			ComplexFunction Constructor Num 2: 
	 * initialized a ComplexFunction from F(x), G(X) and Operation. 
	 * 
	 * Valid inputs:	+Every Obj which implements function interface- as F(X),G(x).
	 *					+Operation from the Enum class && small deviations:
	 *					 opp: Plus, Divide, Times, Comp, None, Max, Min.   	
	 *					+ComplexFunction can receive G(x) (right) only if there is F(x) (left). 
	 *					+ComplexFunction have to receive F(x) ~~ meaning can't be initialized from to null functions. 
	 *
	 *					 Way of action:
	 *  *		Define if the op is None || other op.
	 *  **		None case:  define if the input variables input is valid aka (+ number 3,+ number 4). 
	 *  				    if valid aka there is only F(x) (left) init a new ComplexFunction with F(x) as ComplexFunction.left . 
	 *  *** 	Other case: Check validation (F(x), G(x) are not null and check if Divide is the op input check if G(x) is not '0').
	 *  					if pass create new ComplexFunction and set Left and Right to the F(x) ,G(x) from input.  
	 *  %%      . 
	 * 
	 * @param op   : Operation from the Enum class. 
	 * @param left : implementor obj. of function.   
	 * @param right: implementor obj. of function.
	 */
	public ComplexFunction(Operation op, function left_org, function right_org) {
		
		function right_side;
		function left_side  = left_org.copy();
		if(right_org == null) {
			right_side = null;
		}else {
			right_side = right_org.copy();
		}
		
		if(op == null ) {throw new RuntimeException("Operation null is not valid");}
		if(op == Operation.None) { 

			if(right_side ==null && left_side==null) {
				throw new RuntimeException("No left function");
			}
			if(right_side == null && left_side != null) {
				this.left = left_side;
				return;
			}
			if(left_side == null && right_side != null) {
				throw new RuntimeException("No left function");
			}
			if(left_side != null && right_side != null) {
				throw new RuntimeException("Non op");
			}
		}
		else {
			if(right_side == null || left_side == null) {
				throw new RuntimeException("Null function");
			}

			if(op == Operation.Divid) {
				if(right_side.toString() == "0") {
					throw new RuntimeException("Div by 0");
				}
			}
			else if(op == Operation.Error) {
				throw new RuntimeException("Not a valid op");
			}

			this.left = left_side;
			this.right = right_side;
			this.op = op;
		}


	}
/**
 * 				ComlexFunction Constructor  Num 3: 
 */
	public ComplexFunction(String func) {
		
		ComplexFunction temp = new ComplexFunction();
		ComplexFunction a = temp.initFromString_comp(func);
		this.left = a.left;
		this.right = a.right;
		this.op = a.op;
		
	}

	/**
			f(x) calculating: 

	 * Output the resualt of the Complexfunction by implementing a parameter (x);   
	 * 
	 * 		 * Valid inputs:	double type x. 
	 * 
	 *					 Way of action:  
	 *  *		Init a double type ans (will hold the result of the C.F function (left right). 
	 *  *		Using Switch case examine the op - Go to specific case.
	 *  **		Recursively making sum equal to the result of applying the specific operation on left and right (aka (F(x),G(x)).
	 *  		which left and right are resends to calculate there own x implementation result. 
	 *  ***     Returning ans. 
	 * 
	 * 
	 *@param x:	double input for implementing on the C.F 
	 */
	@Override
	public double f(double x) {


		double ans =0;
		switch (op) {
		case Plus:
			ans = left.f(x)+right.f(x);
			break;

		case Times:
			ans = left.f(x)*right.f(x);
			break;	

		case Divid:
			if ( right.f(x) == 0 ) { 
				throw new RuntimeException("Div by 0");
			}
			ans = left.f(x)/right.f(x);
			break;

		case Max:
			ans = Math.max(left.f(x),right.f(x));
			break;	

		case Min:
			ans = Math.min(left.f(x),right.f(x));
			break;

		case Comp:
			ans = left.f(right.f(x));
			break;

		case None:
			ans = left.f(x);
			break;

		default:
			break;
		}


		return ans;
	}



	/**
			Initialize from string: 

	 * 	Initializing C.F. from given string;   
	 * 
	 * 		* Valid inputs:	any valid Complex Function. 
	 *  
	 * 
	 *					 Way of action:  
	 *
	 *  *		Go over the String (Using i index) from end-1 ( ignoring the first ')' char ) Until the first encounter of ',' char;
	 *  		this char represents the buffer between the left and right (F(x),G(x)) of the first op in the C.F. string given by user.
	 *  ** 		Go over the String (Using j index) from s_char[o] until the first encounter of '(' char.
	 *  		this char represents the buffer between the operation command and the functions that the command apply on.
	 *  *** 	Creating a new C.F that holds the operation and the left and right (F(x),G(x)) and submit them recursively into the C.F
	 *  		by calling the InitFromString function on left (Using substring j+1 until i) and on right (Using substring right_side);   
	 * 
	 * 
	 *@param s:	String contain C.F as a string. 
	 */
	@Override
	public function initFromString(String s) {
		try {
		if(!s.contains(",")) {
			function func;
			if(s.equals("null")) {return null;}
			else if(s.contains(" ")) {
				func = new Polynom(s.replaceAll(" ", ""));
			}else {
				func = new Polynom(s);
			}
			return func;
		}

		String temp ="";
		int i =0;
		i = s.length()-2;
		int j = 0;
		String right_side = "";
		String op_str = "";
		int close = 1;
		int coma =0;
		
		while(close != coma) {
			
			if(s.charAt(i)== ')') {close++;}
			if(s.charAt(i)== ',') {coma++;}
			if(close != coma) {
			right_side = s.charAt(i)+""+right_side;
			}
			i--;
		
		}
		i++;
		while(s.charAt(j) != '(') {
			op_str+=s.charAt(j);
			j++;
		}
		
		String a = s.substring(j+1,i);
		function cf = new ComplexFunction(op_str, initFromString(s.substring(j+1,i)),initFromString(right_side));
		return cf;}catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


	/**
		Deep Copy: 

	 * Initializing new Function OBJ. copy of the C.F that function copy apply on;   
	 * 
	 * 		* Valid inputs:-
	 *  
	 * 
	 *					 Way of action:  
	 *
	 *  *	creating a new 'function' that holds a complex_function which copy the fields of the C.F 
	 *  		which the function "copy" applied on.  
	 *  
	 */	
	@Override
	public function copy() {
		
		function copy_obj;
		if(this.right == null) {
			copy_obj = new ComplexFunction(this.left);
		}
		else
			copy_obj = new ComplexFunction(this.op,this.left,this.right);
		return copy_obj;
	}
	/**

		Plus function: 
	 *	Given a C.F and a function object sets a new C.F which will hold the old C.F on the
    	new function object as left and the new function object to the right.  
	 * 	
	 * 		* Valid inputs: function type obj. 
	 *  
	 * 
	 *					 Way of action:  
	 *
	 *  *	Creating a new function object which will contain a copy of the class C.F
	 *  ** 	Sets the left to the "old" C.F
	 *  *** 	Sets the right to the f1 (input function object)
	 *  **** Sets the operation between left and right to be Plus.  	
	 *  
	 *  @param f: function object to add to the C.F.
	 */	
	@Override
	public void plus(function f1) {

		function new_left = this.copy();
		this.left = new_left;
		this.right = f1;
		this.op = Operation.Plus;

	}
	/**
		mult function: 
	 *	Given a C.F and a function object sets a new C.F which will hold the old C.F on the
		new function object as left and the new function object to the right.  
	 * 	
	 * 		* Valid inputs: function type obj. 
	 *  
	 * 
	 *					 Way of action:  
	 *
	 *  *	Creating a new function object which will contain a copy of the class C.F
	 *  ** 	Sets the left to the "old" C.F
	 *  *** 	Sets the right to the f1 (input function object)
	 *  **** Sets the operation between left and right to be mult/Times.  	
	 *  
	 *  @param f: function object to multiply by.
	 */	
	@Override
	public void mul(function f) {
		function f1 = f.copy();

		function new_left = this.copy();
		this.left = new_left;
		this.right = f1;
		this.op = Operation.Times;
	}
	/**
		div function: 
	 *	Given a C.F and a function object sets a new C.F which will hold the old C.F on the
		new function object as left and the new function object to the right.  
	 * 	
	 * 		* Valid inputs: function type obj. 
	 *  
	 * 
	 *					 Way of action:  
	 *
	 *  *	Creating a new function object which will contain a copy of the class C.F
	 *  ** 	Sets the left to the "old" C.F
	 *  *** 	Sets the right to the f1 (input function object)
	 *  **** Sets the operation between left and right to be div.  	
	 *  
	 *  @param f: function object to divide by.
	 */	
	@Override
	public void div(function f) {
		function f1 = f.copy();

		function new_left = this.copy();
		this.left = new_left;
		this.right = f1;
		this.op = Operation.Divid;

	}
	/**
		max function: 
	 *	Given a C.F and a function object sets a new C.F which will hold the old C.F on the
		new function object as left and the new function object to the right.  
	 * 	
	 * 		* Valid inputs: function type obj. 
	 *  
	 * 
	 *					 Way of action:  
	 *
	 *  *	Creating a new function object which will contain a copy of 'this' C.F
	 *  ** 	Sets the left to the "old" C.F
	 *  *** 	Sets the right to the f1 (input function object)
	 *  **** Sets the operation between left and right to be max.  	
	 *  
	 *  @param f: function object to max between.
	 */
	@Override
	public void max(function f) {
		function f1 = f.copy();
		function new_left = this.copy();
		this.left = new_left;
		this.right = f1;
		this.op = Operation.Max;

	}
	/**
		min function: 
	 *	Given a C.F and a function object sets a new C.F which will hold the old C.F on the
	    new function object as left and the new function object to the right.  
	 * 	
	 * 		* Valid inputs: function type obj. 
	 *  
	 * 
	 *					 Way of action:  
	 *
	 *  *	Creating a new function object which will contain a copy of 'this' C.F
	 *  ** 	Sets the left to the "old" C.F
	 *  *** 	Sets the right to the f1 (input function object)
	 *  **** Sets the operation between left and right to be min.  	
	 *  
	 *  @param f: function object to min with.
	 */
	@Override
	public void min(function f) {
		function f1 = f.copy();

		function new_left = this.copy();
		this.left = new_left;
		this.right = f1;
		this.op = Operation.Min;

	}
	/**
		comp function: 
	 *	Given a C.F and a function object sets a new C.F which will hold the old C.F on the
	    new function object as left and the new function object to the right.  
	 * 	
	 * 		* Valid inputs: function type obj. 
	 *  
	 * 
	 *					 Way of action:  
	 *
	 *  *	Creating a new function object which will contain a copy of 'this' C.F
	 *  ** 	Sets the left to the "old" C.F
	 *  *** 	Sets the right to the f1 (input function object)
	 *  **** Sets the operation between left and right to be comp.  	
	 *  
	 *  @param f: function object to compute with.
	 */
	@Override
	public void comp(function f) {
		function f1 = f.copy();
		function new_left = this.copy();
		this.left = new_left;
		this.right = f1;
		this.op = Operation.Comp;

	}
	/**
		left function: 
	 *
	 *function return the left section of this Complex Function;
	 */
	@Override
	public function left() {	
		return this.left;
	}
	/**
		right function: 
	 *
	 *function return the right section of this Complex Function;
	 */
	@Override
	public function right() {
		return this.right;
	}
	/**
		get_op function: 
	 *
	 *function return the operation of this Complex Function left and right sections;
	 */
	@Override
	public Operation getOp() {
		return this.op;
	}
	/**
	 	toString function: 
	 *
	 *function return String of the Complex Function. 
	 */
	public String toString() { 

		if(op == Operation.None) {
			return ""+this.left;
		}
		if(right == null) {
			return op+"("+left.toString()+","+"0"+")";
		}
		return op+"("+left.toString()+","+right.toString()+")";
	}
	/**

		equals function: 
*	 Given an object(implements of function) the function returns if the two mathematical object are logicly equal. 
*	 The function is not 100 present right, it will check the implementation of x on a range between 
*	-2000 to 2000 and if the number of mismatch results is not more than 10 the function will conceder
*	 those objects to be logically equals.  
* 	
* 		* Valid inputs: function type obj. 
*  		
* 
*					 Way of action:  
*  *		Validates the instance of the input object. 
*  **		Implementing the x values between [-2000,2000] in both objects 
*  			and count the number of same results in Counter.  
*       	
*  *** 		Checking if the the Range (4000 values) - Counter of match is 
*  			less More then 10 ==> return false; 
*  
*  **** 	Return true if the two object are past the test. 
*  @param obj: object(implements function) to be compare with. 
*/	
	@Override
	public boolean equals(Object obj) {

		function x = null;
		if(!(obj instanceof function)){
			throw new RuntimeException("Not a valid OBJ");
		}else if ( obj instanceof Monom) {
			x = new Monom(obj.toString());
		}else if (obj instanceof Polynom_able) {
			x = new Polynom(obj.toString());
		}else if (obj instanceof ComplexFunction) {
			x = this.initFromString(obj.toString());
		}else if(x == null) {
			throw new RuntimeException("Not a valid OBJ 2");
		}

		int Range = 2000; 
		int counter_Of_Match=0;
		double eps = 0.000001;

		for (double i = -2000; i < Range; i=i+0.001) {
			try {
				if((this.f(i) == x.f(i)) || (this.f(i)+eps == x.f(i)) || (this.f(i) == x.f(i)+eps) ) {
					counter_Of_Match ++;
				}else if(this.f(i) <= x.f(i)) {
					
					if(this.f(i) > x.f(i)-eps)
					{counter_Of_Match ++;}
					
					
				}else if(this.f(i) >= x.f(i)) {
					
					if(this.f(i) <= x.f(i)+eps)
					{counter_Of_Match ++;}
				}
			} 
			catch (Exception e) {}
		}
		if ((Range*2)-counter_Of_Match>10)
			return false;

		return true;
	}

	private ComplexFunction initFromString_comp(String s) {
		
		try {
		if(!s.contains(",")) {
			function func;
			if(s.equals("null")) {return null;}
			else if(s.contains(" ")) {
				func = new Polynom(s.replaceAll(" ", ""));
			}else {
				func = new Polynom(s);
			}
			return new ComplexFunction(func);
		}

		String temp ="";
		int i =0;
		i = s.length()-2;
		int j = 0;
		String right_side = "";
		String op_str = "";
		int close = 1;
		int coma =0;
		
		while(close != coma) {
			
			if(s.charAt(i)== ')') {close++;}
			if(s.charAt(i)== ',') {coma++;}
			if(close != coma) {
			right_side = s.charAt(i)+""+right_side;
			}
			i--;
		
		}
		i++;
		while(s.charAt(j) != '(') {
			op_str+=s.charAt(j);
			j++;
		}
		
		String a = s.substring(j+1,i);
		ComplexFunction cf = new ComplexFunction(op_str, initFromString(s.substring(j+1,i)),initFromString(right_side));
		return  cf;}catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
}
