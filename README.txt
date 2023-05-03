# Java Threads 

@author trong0dn
@version 1.0
@date 28/01/2023
---------------------------------------------------------------------------------

# The Sandwich-Making Chefs Problem

## Problem Description

This problem was first published as the cigarette-smokers problem by S. Patil in
1971, and is one of several classic process coordination problems that are used 
to evaluate facilities for synchronizing concurrent threads and processes.

Consider a system with three chef threads and one agent thread. Each chef 
continuously makes a sandwich and then eats it. But to make and eat a sandwich, 
the chef needs three ingredients: bread, peanut butter, and jam. One of the chef 
threads has an infinite supply of bread, another has peanut butter, and the third
has jam. The agent has an infinite supply of all three ingredients. The agent 
randomly selects two of the ingredients and places them on a table. The chef who
has the remaining ingredient then makes and eats a sandwich, signaling the agent 
on completion. The agent then puts out another two of the three ingredients, and
the cycle repeats.

## Project Structure

lab1
|   .classpath
|   .gitignore
|   .project
|   README.txt
|
+---.settings
|       org.eclipse.jdt.core.prefs
|
+---bin
|   |   module-info.class
|   |
|   \---lab1
|           Agent.class
|           Chef.class
|           Kitchen.class
|
\---src
    |   module-info.java
    |
    \---lab1
            Agent.java
            Chef.java
            Kitchen.java
            
## Requirements and Dependencies

This application was created on Windows 10 OS using Eclipses IDE.
Run on the latest version of jdk-17 using Java 17.

No other external dependencies required.

## Compiling and Running the Application

Download and extract the .zip file. Then import the source code directly and 
run the program in local IDE, otherwise the program can be compiled and 
executed via Command Prompt.

```console
> cd C:\..\..\\lab1\src\			    // Navigate to the src directory	
> javac lab1\*.java						// Compile the source code
> java -cp . lab1.Kitchen				// Set classpath to run application
```

## Concurrency, Multithreading and Synchronization

The following design process develops a Java monitor that synchronizes the agent
the chef classes. Each thread is a program in execution that runs in parallel
independently of each other. This is called concurrent processing. Note that at
any single time, a single CPU is executing from a single thread. This means that 
true concurrency between threads on a uniprocessor is not possible but only to 
create the illusion of currency between the coordination of the scheduler and 
context switcher under the hood.

It was decided to create user-defined class that extends from the 
java.lang.Thread to be able to invoke an override run() method to execute the
threads. 

The synchronized object chosen within this design was for the 
Collections.synchronizedList which is the 'table' that is shared between the 
classes. Thus, when performing certain actions on the synchronized object, it
is wise to apply a lock when performing manipulation. Also permitting a single
thread to accessing the synchronized object such that the request for 'table'
access is under specified conditions, otherwise invoke wait(). Once the
ingredients have been added to the table or clear() from the table, the method
would invoke notifyAll() to alert the threads under wait() to be privy to
'table' access.

The program should run until a specified maximum sandwiches have been made 
and consumed.

## Disclaimer

Copyright disclaimer under section 107 of the Copyright Act 1976, allowance is 
made for “fair use” for purposes such as criticism, comment, news reporting, 
teaching, scholarship, education and research.

Fair use is a use permitted by copyright statute that might otherwise be 
infringing.
