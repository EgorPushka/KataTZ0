import java.util.Scanner;

class Main {

    public static void main(String[] args) throws Exception{
        Scanner console = new Scanner(System.in);
        System.out.println("Enter Expression:");
        String input = console.nextLine();
//        System.out.println(RomanEnum.valueOf("XC").ordinal());
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception{
        int x, y;
        boolean isRomeNum, Correct;
        String operator, answer;

        String[] inputData = (input.trim()).split(" "); //String[] inputData = input.split("[+\\-*/]");
        if (inputData.length != 3) throw new Exception("Incorrect Input");
        if ((isRome(inputData[0])) && (isRome(inputData[2])))
            {
            x = RomanEnum.valueOf(inputData[0]).ordinal();
            y = RomanEnum.valueOf(inputData[2]).ordinal();
            operator = inputData[1];
            isRomeNum = true;
            } else if ((isArabian(inputData[0])) && (isArabian(inputData[2])))
                {
                x = Integer.parseInt(inputData[0]);
                y = Integer.parseInt(inputData[2]);
                operator = inputData[1];
                isRomeNum = false;
                }
         else {
            throw new Exception("Incorrect Input");
         }

        Correct = ((x > 0 && x <= 10) && (y > 0 && y <= 10)) ? true : false;
        if (!Correct) throw new Exception("Incorrect Input! Values must be between 1 and 10 inclusive");
        answer = arabCalculation(x,y,operator);

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
    static String arabCalculation (int a, int b, String op) throws Exception{
        switch (op)
            {
            case "+": return String.valueOf(a + b);
            case "-": return String.valueOf(a - b);
            case "*": return String.valueOf(a * b);
            case "/":
                if (b == 0) return "Devision by ZERO!";
                else return String.valueOf(a / b);
            default:
//                return "Invalid Operator";
                throw new Exception("Invalid Operator");
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