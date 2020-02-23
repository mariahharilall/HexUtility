/***************************************************

  Name: Mariah Harilall
  Date: February 26th
  
  Program name:        HexUtility
  Program description: Accepts hexadecimal numbers as input.
                       Valid input examples: F00D, 000a, 1010, FFFF, bye, BYE
                       Enter BYE (case insensitive) to exit the program.
  
****************************************************/

import java.util.Scanner;

public class HexUtility {

  public static void main(String[] args) {

    // Maximum length of input string


    final byte INPUT_LENGTH = 4;

    String userInput = "";                   // Initialize to null string
    Scanner input = new Scanner(System.in);

    // Process the inputs until BYE is entered
    do {
      // Input a 4 digit hex number
      System.out.print("\nEnter a hexadecimal string, or enter BYE to quit:  ");
      userInput = input.next().toUpperCase();

      // Process the input
      switch (userInput) {

        case "BYE": break;
            
        default:        if (userInput.length() != INPUT_LENGTH) {
                          // Input length is incorrect
                          System.out.printf("      The input %s is the wrong length, it should be %d characters long.\n", userInput, INPUT_LENGTH);
                          continue;
                        }

                        // Input length is correct
                        if (isValidHex(userInput)) {  //Call method isValidHex
                          // The input is a valid hexadecimal string
                          System.out.printf("      The input %s is a valid hexadecimal value.\n", userInput);
                           // The input is a valid hexadecimal string
                           long decVal = (long) hex2Dec(userInput, INPUT_LENGTH);
                           String binVal = hex2Bin(userInput, INPUT_LENGTH);
                           System.out.printf(" 0x%s = %s in decimal and %s in binary.\n", userInput, decVal, binVal);
                             }
                        

                        else {
                          // String is either the wrong length or is not a valid hexadecimal string
                          System.out.printf("      The input %s is not a valid hexadecimal value.\n", userInput);
                        }
                        break;
        }
    } while (!userInput.equals("BYE"));

    // Exit the program
    System.out.println("\nGoodbye!");
    input.close();
  }

 
  // Method to validate the input.
  // This method returns true or false to the caller (main).
  // This method accepts one parameter - the user input.
  public static boolean isValidHex(String userIn) {
    boolean isValid = false;
        
    // The length is correct, now check that all characters are legal hexadecimal digits
    for (int i = 0; i < 4; i++) {
      char thisChar = userIn.charAt(i);

      // Is the character a decimal digit (0..9)? If so, advance to the next character
      if (Character.isDigit(thisChar)) {
        isValid = true;
      }

      else {
        // Character is not a decimal digit (0..9), is it a valid hexadecimal digit (A..F)?
        if ('A' <= thisChar && thisChar <= 'F') {
          isValid = true;
        }
        else {
          // Found an invalid digit, no need to check other digits, exit this loop
          isValid = false;
          break;
        }
      }
    }
        
    // Returns true if the string is a valid hexadecimal string, false otherwise
    return isValid;

    
  }
 

  public static double hex2Dec(String hexString, double inputLen) {
  double binVal = 0;
  double val = 0;
  for (int i = 3; i >= 0; i--){
    int base = 0;
    char thisChar = hexString.charAt(3 - i);
    if (Character.isLetter(thisChar)) {
      base = thisChar - 'A' + 10;
    }
    else{
      base = thisChar - '0';
    }
    val = base * (int)Math.pow(16,i);
    binVal += val;
  }
    return binVal;

}
// Method to convert the hex number to binary
 public static String hex2Bin(String hexString, byte inputLen) {
 String binString = ""; // Initialize binString to empty string.
 // Use binString to build up your answer:
 // e.g., for input F00D this method will store 1111000000001101 to binString.

 String[] binValue = {"0000", "0001", "0010", "0011",
"0100", "0101", "0110", "0111",
"1000", "1001", "1010", "1011",
"1100", "1101", "1110", "1111"};

 // Convert hexString to a binary string, e.g. F00D = 1111000000001101
 // F 0 0 D
 //
 // Take one hexadecimal character at a time and do a lookup in array binValue[] to find
 // the binary string for that hexadecimal character. For example, if the character is ‘F’,
 // the binary string is "1111".
  int base;
 for (int i = 0; i < inputLen; i++) {
    char thisChar = hexString.charAt(3 - i);
    if (Character.isLetter(thisChar)) {
      base = thisChar - 'A' + 10;
    }
    else{
      base = thisChar - '0';
    }
    binString = binValue[base] + binString;
 }
 return binString;
 }
}

