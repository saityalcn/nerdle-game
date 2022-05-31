
package swingnerdle;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.ArrayList; 

@SuppressWarnings("unused")
public class Equation {
    private String equation = "";
    private static final int upperBoundOfLength = 9;
    private static final int lowerBoundOfLength = 7;
    private int length;    
    private static final char[] operators = {'+', '-', '*', '/'};
    private static final char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    // 7-9 araliginda denklem üretmeyi saglar.
    public String generateEquation(){
        char op, num1, num2;
        int i = 0;
        int rand;
        boolean flag = false, isNumberGenerated = false,divideOperationGenerated = false;
        String result = "", divider;
        this.equation = "";
        
        while((equation.length() < 7 || equation.length() > 9) || !isStringContains(operators,equation)){  // 
            equation = "";
            result = "";
            char num = numbers[new Random().nextInt(numbers.length-1)];
            result += num;
            equation += num;    
            i = 0;
            while(equation.length() + result.length() + 1 < lowerBoundOfLength || isOperator(equation.charAt(i))){    // agirlikli olarak 7 uzunlugunda olmasina sebep olabiliyor.
                char c;
                rand = new Random().nextInt(2);
		// eger bir onceki iterayonda operator uretildiyse tekrar operator uretilmesini engellemek icin flag degiskeni kullanilir.
                if((rand == 0 || !flag) && !divideOperationGenerated){
                	if(equation.charAt(equation.length()-1) == '/') {
				// Bolme operatoru geldiginde daha duzgun sayilar cikmasi icin onceki sayilarin hesap sonucunu tam bolen bir sayi secilmesi saglanir.
                		if(result.isEmpty()) {
                			String temp = equation.substring(0, equation.length()-1);
                      		divider = getRandomDivider(toInt(temp));
                		}
                		else {
                      		divider = getRandomDivider(toInt(result));
                		}
                		equation = equation.concat(divider);
                		i += divider.length();
                		isNumberGenerated = true;
                		flag = true;
                        divideOperationGenerated = true;
                	}
                	else {
	                	if(!flag)
	                        c = numbers[new Random().nextInt(numbers.length-1)];
	                    else 
	                        c = numbers[new Random().nextInt(numbers.length)];
	                    flag = true;    
	                    equation += c;
	                    isNumberGenerated = true;
                        divideOperationGenerated = false;
	                    i++;
                	}
                }
                else if(flag){
                    c = operators[new Random().nextInt(operators.length)];
                    flag = false;   // bir onceki iterasyonda operator uretildiyse tekrar operator uretilmesin diye flag degiskeni false degerine cekilir
                    equation += c;
                    isNumberGenerated = false;
                    divideOperationGenerated = false;
                    i++;
                }
                // her bir iterasyonda deger hesaplamasi dinamik bir sekilde yapilir. Eger bir onceki iterasyonda sayi degil operator uretildiyse bu hesabin yapilmasi engellenir.
                if(isNumberGenerated)
                    result = calculateEquation(equation);
                
            }
           // iterasyon sonucunda elimizde olan denklemin sonuna = simgesi ve denklemin cozulmesi sonucu olusan deger eklenir.
            equation += '=';
            equation = equation.concat(result);
       }

       length = equation.length();

        return equation;
    }
	
	//parametre olarak gelen stringin icindeki sayisal degeri geriye dondurur.
    public int toInt(String s){
        try{
            return Integer.valueOf(s);
        } catch (NumberFormatException e){
            return 0;
        }
    }
	// parametre olarak gelen iki deger ve bir operatore gore hesaplama islemi yaparak sonucu geriye dondurur.
    public int calculateValue(int a, int b, char op){
        switch(op){
            case '+' :
                return a+b;

            case '-' :
                return a-b;

            case '*' : 
                return a*b;

            case '/' :
                return a/b;

            default :
                return 0;
        }
    }
	
	// gelen denklemin sayi ve operatorlerini ayirir ve deger hesaplama metodunu cagirarak denklemin sonuc hesaplanmis halini geriye dondurur
    public String calculateEquation(String eq){
        String num1="",num2="";
        ArrayList<Character> operatorList = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();
        char op = ' ';
        int i = 0;
	
	    // sayi ve operatorleri ayirma islemi
        while(i < eq.length() && eq.charAt(i) != '='){
            num1 = num2;
            num2 = "";
            while(i<eq.length() && !isOperator(eq.charAt(i))){
                num1 += eq.charAt(i);
                i++;
            }

            if(i == eq.length())
                return "";

            else if(isOperator(eq.charAt(i))){
                op = eq.charAt(i++);
                while(i<eq.length() && !isOperator(eq.charAt(i)) && eq.charAt(i) != '='){
                    num2 += eq.charAt(i);
                    i++;
                }
            }
            numbers.add(num1);
            operatorList.add(op);
        }
        if(num2 != "")
        	numbers.add(num2);
        
        if(numbers.size() <= operatorList.size())   
            return null;
        
        calculateValueOfEquation(operatorList, numbers);
        return numbers.get(0);
    }
	
	// gelen bir karakterin operator olup olmadigini geriye dondurur.
    private boolean isOperator(char c){
        int i = 0;
        while(i<operators.length && c != operators[i]){
            i++;
        }

        if(i==operators.length)
            return false;	// operator degil

        return true;		// operator
    }
	
	// gelen operatorler ve sayilara gore denklemi islem onceligini gozeterek hesaplar.
    private void calculateValueOfEquation(ArrayList<Character> operators, ArrayList<String> numbers){
        ArrayList<Character> highPrecedence = new ArrayList<>();
        ArrayList<Character> lowPrecedence = new ArrayList<>();
        int val;

        highPrecedence.add('/');
        highPrecedence.add('*');

        lowPrecedence.add('+');
        lowPrecedence.add('-');
        
        for(int i=0; i<2; i++){
            for(int j=0; j<operators.size(); j++){
                if(numbers.size() > 1){
                    if(isContains(highPrecedence, operators.get(j))){
                        try{
                            val = calculateValue(toInt(numbers.get(j)), toInt(numbers.get(j+1)), operators.get(j));
                        } catch(ArithmeticException e){
                            val = Integer.MAX_VALUE;
                        }
                        numbers.set(j, String.valueOf(val));
                        numbers.remove(j+1);
                        operators.remove(j--);
                    }
                    else if(i == 1){
                        val = calculateValue(toInt(numbers.get(j)), toInt(numbers.get(j+1)), operators.get(j));
                        numbers.set(j, String.valueOf(val));
                        numbers.remove(j+1);
                        operators.remove(j--);
                    }
                }
            }
        }
    }
	
	// parametre olarak gelen bir arraylist'te yine parametre olarak gelen bir karakterin olup olmadigini donderir.
    private boolean isContains(ArrayList<Character> list, char c){
        int i = 0;
        
        while(i<list.size() && list.get(i) != c)
            i++;

        if(i<list.size() && list.get(i) == c)
            return true;		// karakter varsa
	
        return false;		// karakter yoksa
    }

	// denklemin gecerli bir denklem olup olmadigini kontrol eder
    public boolean isEquationValid(String equation){
        String[] arr = new String[2];
        char[] charArr = new char[1];
        String result;
        charArr[0] = '=';
        try{
		
            if(!isStringContains(operators, equation))
                return false;	// denklem operator icermiyorsa

            else if(!isStringContains(numbers, equation))
                return false;	// denklem sayi icermiyorsa
            
            else if(!isStringContains(charArr, equation))
                return false;	// denklem = icermiyorsa

            arr = equation.split("=");
            result = calculateEquation(arr[0]);
            if(result == null)
                return false;	// denklemin sonucu hesaplanamiyorsa

            if(arr[1].equals(result))
                return true;	// denklemin sonucu hesaplaniyor ve girilen sonucla dogruysa

            return false;	// eger denklemin sonucu hesaplaniyor ve girilen sonucla eslesmiyorsa veya diger sartlar saglanmiyorsa
        } catch(IndexOutOfBoundsException e){
            return false;	// eger denklemdeki operatorler hesaplanabilenden fazla oluyorsa IndexOutOfBoundException' a sebep olur	
        }
    }
    
	// parametre olarak verilen sayinin tam bolenlerinden random birini secerek dondurur
    public String getRandomDivider(int num) {
    	String s;
    	ArrayList<Integer> dividers = new ArrayList<>();
	    
	    // tam bolenlerin bulunmmasi
    	for(int i=1; i<=num; i++) {
    		if(num % i == 0)
    			dividers.add(i);
    	}
    	
    	if(dividers.size() > 1) {
    		s = Integer.toString(dividers.get(new Random().nextInt(dividers.size()-1)+1));	// random bir bolenin secilmesi
    		return s;
    	}
    	
    	return "1";	// eger bolen sadece 1 taneyse geriye 1 doner.
    }

	// verilen bir stringde karakter dizisindeki elemanlardan herhangi birinin olup olmadigini geriye dondurur.
    public boolean isStringContains(char[] chars, String str){
        boolean isFound = false;
        int i = 0;
        int j = 0;

        while(i<str.length() && !isFound){
            j=0;
            while(j<chars.length && !isFound){
                if(str.charAt(i) == chars[j])
                    isFound = true;

                j++;
            }       
            i++;     
        }
        return isFound;
    }    
}
