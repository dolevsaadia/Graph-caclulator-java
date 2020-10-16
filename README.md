## OOP - Project Ex1: ComplexFunction&Polynom&Monom.
----------------
Editors: Dor Getter & Omeri Rugi
Ariel university -Object-Oriented Programming course task 1.


#  About the project:
This project is built to create and use mathematical function expressions using given interfaces.

The program allows build Complex functions, Polynoms and Monoms objects, either with a String or by creating a new object which stands the characteristics of a valid polynomial expression.

The user of this project can add, subtract, multiply and derivate Monoms and Polynomials expressions.
create a continues operations on functions and save them in a Complex function form.
See the functions he created on graph using GUI window, which can also print to Json and read from Json files. 

Also, the user can find the root of a Polynomial expression in a close interval and sum the blocked area between the polynomial function and the positive x-axis using Riemann's Integral.

# Monom Class

Class info:  This class represents a Monom mathematical expression. Valid Monom objects is in the form of ax^b.
in which:


•	a- Double type coefficient.


•	b- Integer type power.


the class validates that the inputs are forming a valid Monom, and supports the follows Methods.

Methods implemented:

•	Monom(String)-Constructor.

•	Derivative-apply derivative operation.	

•	f(x)-implement && calculate x the Monom.

•	IsValidDouble-check the validation of the coefficient.

•	IsValidInt-check the validation of the Power.

•	add-adds up two Monoms.

•	multiply(num)-multiply the monom by a number.

•	toString-returns the String of the given Monom. 

•	equals-check wither 2 Monoms logically equal.		
________________________________________
# Polynom Class:







Class info: This class represents a Polynom objects that is build from ArraryList data structure of Monom objects.
Polynom class works as follows:


•	Receiving a String containing "Polynom" / Monom OBJ.

•	case 1: Breaks down the String into separate Monoms- and add separately to an ArrayList that will contain the Polynom.

•	case 2: received a Monom OBJ. and adds it to a Polynom ArrayList.

Methods implemented:

•	Polynom(String)-Constructor.

•	f-Place x in the polynom.

•	add(Polynom_able)-Adding two Polynom_able objects into one.

•	add(Monom)-Adding Monom into the Polynom.

•	substruct(Polnom_able) -Substruction of two Polnoms.

•	multiply-Multiplying two Polynoms.

•	equals-Check if two Polynoms are equal- by deviation of Epsilon.

•	isZero-Check if Polynom is equal to zero.			

•	root-Calculate the point which the Polynom==zero between x0,x1 with deviation of Epsilon.

•	Copy-Crates deep copy of Polynom.

•	Derivativ-Derivative the Polynom.

•	area-Calculates the area size between the function to positive x axis.

•	multiply-Multiply Polynom by Monom.

•	Iterator-Returns Iterator from Class Iterator.

•	toString-Returns a String of the Polynom.


________________________________________
# Complex function class



 This class represents ComplexFunction OBJECT.
 ComplexFunction is a object inscence of function interface and have the folllowing fields: 

- **left**:   obtain an instance of function object (Monom,Polynom,Complex Function)
- **right**: obtain an instance of function object (Monom,Polynom,Complex Function)
- **opperation**: between left and right.

 **ComplexFunction class works as following:**
 
 Receiving F(x) ,G(x) which F(x) , G(x) can be any objects iff they are inscence of function interface. (F(x) represents the Left side object , G(x) represents the right side object).
 Recieving Operation to apply between them (G(x) can be null- in that case the operation will be none).
 Complex function will save will show the computes steps of computing and do a mathematicals operations on F(x). 
**Valid operations: **Plus, plus, Times, mul, Divid, div, Max, max, Min, min, Comp , None, Error	
 
 Ex. 
>  CF= comp(mul(x^2,x),none(3x,null))
 


Methods:

 * 1)	ComplexFunction(function p1)-Constructor Num 1.
 * 2)	ComplexFunction(op,left,right)-Constructor Num 2.
 * 3)	ComplexFunction(String func)-Constructor Num 3.
 * 4)	f(double x)-Calculating implement x on function.
 * 5)	initFromString(String s)-Initialized C.F using string.
 * 6)	copy()-Deep copy.
 * 7)	plus(function f1)-implement plus op between two function type objects in C.F.
 * 8)	mult(function f1) -implement mult op between two function type objects in C.F.
 * 9)	div(function f1)-implement div  op between two function type objects in C.F
 * 10)	max(function f1)-implement max  op between two function type objects in C.F.
 * 11)	min(function f1)-implement min  op between two function type objects in C.F.
 * 12)	comp(function f1)-implement comp op between two function type objects in C.F.
 * 13)	left(function f1)-returns the L.H.S of given C.F
 * 14)	right(function f1)-returns the R.H.S of given C.F
 * 15)	getop(function f1)-returns the operation of given C.F.
 * 16)	toString(function f1)-implement plus op between two function type objects in C.F.
 * 17)	equals(function f1)-if the two mathematical object are logicly equal.

---------------------
# Function_GUI class:

This class uses an ArrayList database to store functions objects (Complex function, Polynom, Monom) from which generates a GUI image of functions on a screen. 
Function GUI draw the class uses the StdDraw class, and uses Gson for read/write  Json files  create/write to text java Complex Function, Polynom, objects, Monom objects and presents them on screen.

The class holds the followed methods for draw function from/in different databases:
1) initFromFile(String file) 
2) saveToFile(String file)
3) drawFunctions(String json_file)
4) drawFunctions(int width, int height, Range rx, Range ry, int resolution)
5) drawFunctions(String json_file)
6) drawFunctions()



![Annotation 2019-12-08 185054](https://user-images.githubusercontent.com/57187365/70393434-6f66a780-19f2-11ea-9f8f-541a6343f0b8.jpg)


@author Omer Rugi && Dor Getter

