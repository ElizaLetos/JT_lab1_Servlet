package com.firstlab.lab1tj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Eliza
 */
@WebServlet(name = "OrderLettersBySize", urlPatterns = {"/OrderLettersBySize"})
public class OrderLettersBySize extends HttpServlet {

    private final String FILE_DICT = "C:/Users/Eliza/OneDrive/Documents/NetBeansProjects/Lab1TJ/src/main/java/com/firstlab/lab1tj/resources/dictionary.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        String parameter = request.getParameter("word");
        int size = Integer.parseInt(request.getParameter("size"));

        try {
            pw.print(response(response, parameter, size));
            pw.close();
        } catch (Exception ex) {
            Logger.getLogger(OrderLettersBySize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<String> response(HttpServletResponse response, String message, Integer size)
            throws IOException, Exception {

        List<String> permutationsList = new ArrayList<>();
        List<String> finalList = new ArrayList<>();
        PrintWriter out = response.getWriter();
        getPermutationsOfSize(message, size, permutationsList);
        File file = new File(FILE_DICT);
        try ( Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < permutationsList.size(); i++) {
                while (scanner.hasNextLine()) {
                    String word = scanner.nextLine();
                    if (sameChars(permutationsList.get(i).toLowerCase(), word.toLowerCase())) {
                        finalList.add(word.toLowerCase());
                    }
                }
            }
        }
        return finalList.stream().distinct().collect(Collectors.toList());
    }

    private void getPermutationsOfSize(String message, Integer size, List<String> permutationsList) throws Exception {
        if (size == 0) {
            permutation(message, 0, message.length() - 1, permutationsList);
        } else if (size > 0 && size <= message.length()) {
            permutation(message, 0, size - 1, permutationsList);
        } else if (size > message.length()) {
            throw new Exception("Size bigger than the word's lenght");
        } else if (size < 0) {
            throw new Exception("Size have to be a positive number");
        }
    }

    private void permutation(String message, int start, int finish, List<String> permutationsList) throws FileNotFoundException {
        if (start == finish) {
            permutationsList.add(message);
        } else {
            for (int i = start; i <= finish; i++) {
                message = swapCharcters(message, start, i);
                permutation(message, start + 1, finish, permutationsList);
                message = swapCharcters(message, start, i);
            }
        }
    }

    public String swapCharcters(String message, int i, int j) {
        char temp;
        char[] chArray = message.toCharArray();
        temp = chArray[i];
        chArray[i] = chArray[j];
        chArray[j] = temp;
        return String.valueOf(chArray);
    }

    private boolean sameChars(String firstStr, String secondStr) {
        char[] first = firstStr.toCharArray();
        char[] second = secondStr.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);
    }
}
