import java.util.Scanner;

class Main {

    public static void main(String[] args) throws Exception{
        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Expression:");
            String input = console.nextLine();
            if (input.equals("exit")) break;
            System.out.println(calc(input));
        }
    }

    public static String calc(String input) throws Exception{
        int x, y;
        char operation;
        boolean isRomeNum, Correct;
        String answer;
        input = input.replaceAll(" ", "");
        String[] inputData = input.split("[+\\-*/]");

        if (inputData.length != 2) throw new Exception("Incorrect Input");
        if ((isRome(inputData[0])) && (isRome(inputData[1]))) {
            x = RomanEnum.valueOf(inputData[0]).ordinal();
            y = RomanEnum.valueOf(inputData[1]).ordinal();
            operation = input.charAt(inputData[0].length());
            isRomeNum = true;
            } else if ((isArabian(inputData[0])) && (isArabian(inputData[1]))) {
                x = Integer.parseInt(inputData[0]);
                y = Integer.parseInt(inputData[1]);
                operation = input.charAt(inputData[0].length());
                isRomeNum = false;
            } else {
            throw new Exception("Incorrect Input");
         }

        Correct = ((x > 0 && x <= 10) && (y > 0 && y <= 10));
        if (!Correct) throw new Exception("Incorrect Input! Values must be between 1 and 10 inclusive");
        answer = arabCalculation(x,y,operation);

        if (isRomeNum) {
            if (Integer.parseInt(answer) <= 0) {
                throw new Exception("Incorrect Out! Rome answer cannot be <= 0");
            }
            answer = arabConvertToRome(answer);
        }

        return answer;

        }

    static boolean isRome (String value) {
        try {
            RomanEnum.valueOf(value);
            return true;
            }
        catch (IllegalArgumentException e) {
            return false;
            }
        }
    static boolean isArabian (String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException n) {
//            System.out.println("NotArabianNumbers");
            return false;
        }
    }
    static String arabCalculation (int a, int b, char op) throws Exception{
        switch (op)
            {
            case '+': return String.valueOf(a + b);
            case '-': return String.valueOf(a - b);
            case '*': return String.valueOf(a * b);
            case '/': return String.valueOf(a / b);
            default: throw new Exception("Invalid Operator"); //return "Invalid Operator";
            }
    }
    static String arabConvertToRome (String s) {
        int P = Integer.parseInt(s);
        RomanEnum[] RomeList = RomanEnum.values();
        for (RomanEnum N : RomeList) {
            if (P == N.ordinal()) return N.name();
        }
        return s;
    }

}