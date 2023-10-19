# Occurrence Count

## Description

This is a simple program that counts the number of occurrences of a character in a text file with the possibility of running it on multiple files in parallel.

## Implementation

The program is implemented in Java.
We use a BufferedReader to read, line by line, from a support file called 'filestoanalyze.txt,' the paths of the files to be analyzed.
The program employs a thread pool to concurrently perform the character occurrence count.
For each program, we create a ConcurrentHashMap that uses characters as keys and the count as values.
Each thread reads a file, and for each character, it increments the value associated with the corresponding key in the map.
Once the counting for a file is completed, the thread writes it to a dedicated output file named '[filename]_HashMap.txt.'
The program terminates when there are no more files to analyze.
