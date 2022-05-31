package swingnerdle;
        
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class GenerateEquationTest {
    private Equation equation;
    private String generatedEquation;
    private final char[] operators = {'+', '-', '*', '/'};
    private final char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

	@BeforeEach
	public void setUp(){
		equation= new Equation();
		generatedEquation= equation.generateEquation();
	}
       
	@RepeatedTest(100)
	@DisplayName("Length Between Bounds Test")
	public void testLength(){
		int min = 7,max=9;
		int len = generatedEquation.length();
		assertTrue((min <= len && max >=len));
	}
	
	@RepeatedTest(100)
	@DisplayName("Equation Contains '=' Character Test")
	public void testEquationContainsEqCharacter() {
		assertTrue(generatedEquation.contains("="));
	}

	@RepeatedTest(100)
	@DisplayName("Equation Contains  Only One '=' Character Test")
	public void testEquationContainsMoreThanOneEqCharacter() {
		assertTrue(generatedEquation.split("=").length == 2);
	}
	
	@RepeatedTest(1)
	@DisplayName("Validation Method - Valid Equation")
	public void testEquationValidationMethod() {
		assertTrue(equation.isEquationValid("4+5*3=19"));
	}
	
	@RepeatedTest(1)
	@DisplayName("Validation Method - Invalid Equation")
	public void testEquationValidationMethodInvalid() {
		assertFalse(equation.isEquationValid("4+5/15="));
		assertFalse(equation.isEquationValid("4+5/0="));
		assertFalse(equation.isEquationValid("4+5/15=+"));
		assertFalse(equation.isEquationValid("-84+5/15=+"));
	}
	
	@RepeatedTest(100)
	@DisplayName("Equation Contains At Least One Number")
	public void testEquationContainsAtLeastOneOperand() {
		assertTrue(equation.isStringContains(numbers,generatedEquation));
	}

	@RepeatedTest(100)
	@DisplayName("Is Operator Exists Before '=' Character Test")
	public void testIsOperatorExistsBeforeEqCharacter() {
		String temp = generatedEquation.split("=")[0];
		char c = temp.charAt(temp.length()-1);
		assertFalse((c == '+' || c == '-' || c == '*' || c == '/'));
	}

	
	@RepeatedTest(100)
	@DisplayName("Equation Contains At Least One Operator")
	public void testEquationContainsAtLeastOneOperator() {
		assertTrue(equation.isStringContains(operators,generatedEquation));
	}
	
	@RepeatedTest(100)
	@DisplayName("Equation Validation Test")
	public void testIsEquationValid() {
		assertTrue(equation.isEquationValid(generatedEquation));
	}
        
}
