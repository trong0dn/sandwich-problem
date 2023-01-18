Carleton University 
Department of Systems and Computer Engineering 
SYSC 3303A Real-Time Concurrent Systems Winter 2023 
Assignment 1 — Java Threads 

The Sandwich­Making Chefs Problem

This problem was first published as the cigarette­smokers problem by S. Patil in 1971, and is one 
of several classic process coordination problems that are used to evaluate facilities for 
synchronizing concurrent threads and processes.

Consider a system with three chef threads and one agent thread. Each chef continuously makes a 
sandwich and then eats it. But to make and eat a sandwich, the chef needs three ingredients: bread, 
peanut butter, and jam. One of the chef threads has an infinite supply of bread, another has peanut 
butter, and the third has jam. The agent has an infinite supply of all three ingredients. The agent 
randomly selects two of the ingredients and places them on a table. The chef who has the remaining 
ingredient then makes and eats a sandwich, signaling the agent on completion. The agent then puts 
out another two of the three ingredients, and the cycle repeats.