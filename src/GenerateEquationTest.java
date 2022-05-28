import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class GenerateEquationTest {
	Equation equation;
	String generatedEquation;

	@BeforeEach
	void setUp(){
		equation = new Equation();
		generatedEquation = equation.generateEquation();
	}

	@RepeatedTest(10000)
	@DisplayName("Length Between Bounds Test")
	void testLength(){
		int min = 7,max=9;
		int len = generatedEquation.length();
		assertTrue((min <= len && max >=len));
	}
	
	@RepeatedTest(10000)
	@DisplayName("Equation Contains '=' Character Test")
	void testEquationContainsEqCharacter() {
		assertTrue(generatedEquation.contains("="));
	}
	
	@RepeatedTest(10000)
	@DisplayName("Equation Contains '=' Character Only One Test")
	void testEquationContainsMoreThanOneEqCharacter() {
		assertTrue(generatedEquation.split("=").length == 2);
	}
	// metod kullanma sarti: once metodu sabit bir equation ile test et 
	// daha sonra bu metodun dogrulugu kanitlanmis olur
	// bu metodu kullanarak denklemi test et
	
	@Test
	@DisplayName("Validation Method - Valid Equation")
	void testEquationValidationMethod() {
		assertTrue(equation.isEquationValid("4+5*3=19"));
	}
	
	@Test
	@DisplayName("Validation Method - Invalid Equation")
	void testEquationValidationMethoInvalid() {
		assertFalse(equation.isEquationValid("4+5-3"));
	}
	
	@RepeatedTest(10000)
	@DisplayName("Equation Contains At Least One Number")
	void testEquationContainsAtLeastOneOperand() {
		assertTrue(equation.isEquationValid(generatedEquation));
	}
	
	@RepeatedTest(10000)
	@DisplayName("Equation Contains At Least One Operator")
	void testEquationContainsAtLeastOneOperator() {
		// degistir bunu
		assertTrue(equation.isEquationValid(generatedEquation));
	}
	
	@RepeatedTest(10000)
	@DisplayName("Equation Validation Test")
	void testIsEquationValid() {
		assertTrue(equation.isEquationValid(generatedEquation));
	}

}
