<h4>Barren Land Analysis</h4>

You have a farm of 400m by 600m where coordinates of the field are from (0, 0) to (399, 599). 
A portion of the farm is barren, and all the barren land is in the form of rectangles. 
Due to these rectangles of barren land, the remaining area of fertile land is in no particular shape. 
An area of fertile land is defined as the largest area of land that is not covered by any of the rectangles of barren land.

Read input from STDIN. Print output to STDOUT 

<strong>Input</strong> <br/><br/>
You are given a set of rectangles that contain the barren land. 
These rectangles are defined in a string, which consists of four integers separated by single spaces, 
with no additional spaces in the string. 
The first two integers are the coordinates of the bottom left corner in the given rectangle, 
and the last two integers are the coordinates of the top right corner. 

<strong>Output</strong><br/><br/>
Output all the fertile land area in square meters, sorted from smallest area to greatest, separated by a space. 
<p>&nbsp;</p>

<table>
<tr><th align="left">Sample Input</th><th align="left">Sample Output</th></tr>
<tr><td>{“0 292 399 307”}</td><td>116800  116800</td></tr>
<tr><td>{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}</td><td>22816 192608</td></tr>
</table> 

<strong><hr/></strong>
Required to build and run in IDE:  
-- Java<br/>
-- Groovy<br/>
-- Gradle<br/>

Required to run from command line:    
-- Java

To build from command line:  
-- switch to barrenland directory<br/>
-- Mac: <em>./gradlew clean build uberjar</em><br/>
-- Windows: <em>gradlew.bat clean build uberjar</em>

To run from command line:<br/> 
-- switch to barrenland directory<br/>
-- <em>java -jar ./barrenland-1.0-SNAPSHOT.jar</em>

To run in IntelliJ:<br/>
-- right click on BarrenLandApplication<br/>
-- select: <em>Run 'BarrenLandAppl....main()'<br/>
or:<br/> 
-- right click on barrenland-1.0-SNAPSHOT.jar<br/>
-- select: <em>Run 'barrenland-1.0-SNAPS...'</em><br/>




To view test results in browser:  
-- place the following link in a browser, replacing PATH_TO_BARRENLAND<br/>
   with the location of the barrenland directory on your system:<br/>
       <em>file:///PATH_TO_BARRENLAND/build/reports/tests/test/packages/com.farm.barrenland.html</em><br/>
       or specifically:<br/>
       <em>file:///PATH_TO_BARRENLAND/build/reports/tests/test/classes/com.farm.barrenland.FertileLandCalculatorSpec.html</em>


 

