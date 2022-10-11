# JT_lab1_Servlet

Java Servlet Technology

Compulsory
- Create a servlet that receives a word and returns an HTML page containing the letters of that word presented as an ordered list.
The location of the solution: src/main/java/com/firstlab/lab1tj/OrderLetters.java

Homework
- It receives an integer as a parameter, called size, and it returns all the permutations of length size of the given letters. If the size is 0 (default), it will return all the sequences.
- If the servlet has access to a server-side file containing a list of acceptable words (a dictionary), it will return only the sequences forming valid words.
This list should be large enough; you may use aspell to generate a text file containing English words, or anything similar: WordNet, dexonline, etc.
For example, if the servlet receives the leters a,a,j,v and the size is 0, it may return the list aa, ava, java (assuming it uses an en english dictionary).
- The servlet invocation will be done using a simple HTML form. The servlet will return the response as an HTML page.
