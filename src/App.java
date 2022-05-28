import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        FileReadWrite<Save> frw = new FileReadWrite<>();
        System.out.println(frw.readData("save.bin"));
        ArrayList<String> str = new ArrayList<>();
        str.add("1+21+1231");
        str.add("12412/4124");
        str.add("1231*11312");
        String eq = "12*238";
        Save s = new Save(eq, str);
        frw.writeData("save.bin", s);

        Scanner sc = new Scanner(System.in);
        String equ = sc.nextLine();
        sc.close();
        Equation e = new Equation();
        System.out.println(e.isEquationValid(equ));
        System.out.println("END");
        
    }
}
