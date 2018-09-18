package Lesson_1;

public class Lesson_1 {
    public static void main(String[] args) {
        initialize();
        System.out.println("a * (b + (c / d)) = " + calc(2,10,20,5));
        System.out.println(checkSum(5,5));
        checkNum(-10);
        System.out.println(trueIfNegative(-20));
        outputWelcome("Mark");
        determineYear(800);
    }

    //2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
    static void initialize(){
        System.out.println("Задание 2");

        byte b1 = 10;
        short s1 = 15;
        int i1 = 256;
        long l1 = 12312421412L;

        float f1 = 1.6f;
        double d1 = 56.7;

        boolean bool = true;
        char c1 = 'a';

        String str1 =  "Mark";

        System.out.println("Значение для типа byte = " + b1);
        System.out.println("Значение для типа short = " + s1);
        System.out.println("Значение для типа int = " + i1);
        System.out.println("Значение для типа long = " + l1);
        System.out.println("Значение для типа float = " + f1);
        System.out.println("Значение для типа double = " + d1);
        System.out.println("Значение для типа boolean = " + bool);
        System.out.println("Значение для типа char = " + c1);
        System.out.println("Значение для типа String = " + str1);
    }

    //3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат
    // где a, b, c, d – входные параметры этого метода;
    static int calc(int a, int b, int c, int d){
        System.out.println("\nЗадание 3");

        return a * (b + (c / d));
    }

    //4. Написать метод, принимающий на вход два числа, и проверяющий что их сумма
    //лежит в пределах от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
    static boolean checkSum(int a, int b){
        System.out.println("\nЗадание 4");

        int s = a + b;
        return (10 <= s) && (s <= 20);
    }

    //5. Написать метод, которому в качестве параметра передается целое число, метод должен
    //напечатать в консоль положительное ли число передали, или отрицательное; Замечание: ноль
    //считаем положительным числом.
    static void checkNum(int a) {
        System.out.println("\nЗадание 5");

        if (a >= 0) {
            System.out.println("Число " + a + " положительное");
        }
        else {
            System.out.println("Число " + a + " отрицательное");
        }
    }

    //6. Написать метод, которому в качестве параметра передается целое число, метод должен
    // вернуть true, если число отрицательное;

    static  boolean trueIfNegative(int a){
     System.out.println("\nЗадание 6");

        if (a < 0) {
            return true;
        }
        else {
        return false;
        }
    }

    //7. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
    // метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
    static void outputWelcome(String name) {
        System.out.println("\nЗадание 7");


        System.out.println("Привет, " + name + "!");
    }

    //8. * Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
    //Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.

    static void determineYear(int year){
        System.out.println("\nЗадание 8");

        if (!(year % 4 == 0) || (year % 100 == 0) && !(year % 400 == 0) )  {
            System.out.println(year + " год - не високосный ");
        }
        else System.out.println(year + " год - високосный ");
    }
}
