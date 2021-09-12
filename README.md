# Railroad Simulation
## About

This is a java program that calculates routes and distances for a railroad system represented by a directed, weighted graph. The program calculates shortest routes, distances of routes, and distances of shortest route.

## Technologies
- Java
- JUnit
## Description
This program works for graphs of 5 vertices, however that can be altered by changing N or V. The program parses through inputs
and creates three representations: a weighted adjacency matrix, an unweighted adjacency matrix, and an adjacency list. The letters
A, B, C, D, E are mapped to numbers using a hash table. I implemented matrix operations and a topological sort algorithm to calculate shortest routes and distances of routes. JUnit was used to test different methods by mocking the input values and using assertion to verify the expected value.

