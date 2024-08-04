package utils;

import java.util.regex.*;

public class Utility {
	public static boolean isEmail(String email) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";  
		Pattern pattern = Pattern.compile(regex);  
		
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        // Regular expressions for different criteria
        String upperCasePattern = ".*[A-Z].*";
        String lowerCasePattern = ".*[a-z].*";
        String digitPattern = ".*\\d.*";
        String specialCharPattern = ".*[!@#$%^&*(),.?\":{}|<>].*";

        // Compile patterns
        Pattern upperCase = Pattern.compile(upperCasePattern);
        Pattern lowerCase = Pattern.compile(lowerCasePattern);
        Pattern digit = Pattern.compile(digitPattern);
        Pattern specialChar = Pattern.compile(specialCharPattern);

        // Match password against patterns
        Matcher matcherUpper = upperCase.matcher(password);
        Matcher matcherLower = lowerCase.matcher(password);
        Matcher matcherDigit = digit.matcher(password);
        Matcher matcherSpecial = specialChar.matcher(password);

        // Return true if all patterns match
        return matcherUpper.find() && matcherLower.find() && matcherDigit.find() && matcherSpecial.find();
    }
	
	public static boolean isValidMobileNo(String str) {  
		Pattern pattern = Pattern.compile("(0/91)?[6-9][0-9]{9}");  
		
		Matcher match = pattern.matcher(str);
	
		return (match.find() && match.group().equals(str));  
	}  
}
