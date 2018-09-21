package Lesson_2;

public class Lesson_2 {
    public static void main(String[] args) {
        arrayReplacement();
        arrayFilling();
        arrayMultiply();
        fillDiagonal();
        findMinMax();
        System.out.println("\nЗадание 6");
        System.out.println(checkBalance(new int[]{1, 1, 1, 2, 1})); // true
        System.out.println(checkBalance(new int[]{2, 1, 1, 2, 1})); // false
        System.out.println(checkBalance(new int[]{10, 10})); //true
        shiftArray((new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), -5);
    }

    //1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
// С помощью цикла и условия заменить 0 на 1, 1 на 0;
    static void arrayReplacement() {
        System.out.println("Задание 1");

        System.out.println("Исходный массив:");

        int[] mas = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");
        }

        System.out.println("\nОбработанный массив:");

        for (int i = 0; i < mas.length; i++) {
            if (mas[i] == 1) {
                mas[i] = 0;
            } else {
                mas[i] = 1;
            }

            System.out.print(mas[i] + " ");
        }
    }

    //2. Задать пустой целочисленный массив размером 8.
// С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    static void arrayFilling() {
        System.out.println("\n\nЗадание 2");

        int[] mas = new int[8];

        for (int i = 0, j = 0; i < mas.length; i++, j += 3) {
            mas[i] = j;
            System.out.print(mas[i] + " ");
        }
    }

    //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
// пройти по нему циклом, и числа меньшие 6 умножить на 2;
    static void arrayMultiply() {
        System.out.println("\n\nЗадание 3");

        int[] mas = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println("Исходный массив:");

        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");
        }

        System.out.println("\nОбработанный массив:");

        for (int i = 0; i < mas.length; i++) {
            if (mas[i] < 6) {
                mas[i] *= 2;
            }
            System.out.print(mas[i] + " ");
        }
    }

    //4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
// и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    static void fillDiagonal() {
        System.out.println("\n\nЗадание 4");

        int length = 5; // длина массива

        int[][] mas = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if ((i == j) || (i == length - 1 - j)) {
                    mas[i][j] = 1;
                }
            }
        }

        System.out.println("Обработанный массив:");

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(mas[i][j] + " ");
            }
            System.out.println();
        }
    }

    //5. ** Задать одномерный массив и найти в нем минимальный
// и максимальный элементы (без помощи интернета);
    static void findMinMax() {
        System.out.println("\nЗадание 5");

        int[] mas = {15, -3, -10, -1, 20, 6, 7, 19};

        System.out.println("Исходный массив:");

        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");
        }

        int max = mas[0];
        int min = mas[0];

        for (int i = 0; i < mas.length; i++) {
            if (mas[i] > max) {
                max = mas[i];
            } else if (mas[i] < min) {
                min = mas[i];
            }
        }
        System.out.println("\nМаксимальный элемент: " + max + "\nМинимальный элемент: " + min);
    }

//6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true
// если в массиве есть место, в котором сумма левой и правой части массива равны.
// Примеры: checkBalance([1, 1, 1, || 2, 1]) → true,
// checkBalance ([2, 1, 1, 2, 1]) → false,
// checkBalance ([10, || 10]) → true,
// граница показана символами ||, эти символы в массив не входят

    static boolean checkBalance(int[] a) {
        int leftSum, rightSum = 0;

        for (int i = 0; i < a.length + 1; i++) {
            leftSum = 0;
            rightSum = 0;

            for (int j = 0; j < i; j++) {
                leftSum += a[j];
            }

            for (int j = i; j < a.length; j++) {
                rightSum += a[j];
            }

            if (leftSum == rightSum) return true;
        }
        return false;
    }

//    7. **** Написать метод, которому на вход подается одномерный массив и число n
// (может быть положительным, или отрицательным), при этом метод должен
// сместить все элементымассива на n позиций.
// Для усложнения задачи нельзя пользоваться вспомогательными массивами.

    static void shiftArray(int[] mas, int n) {
        System.out.println("\nЗадание 7");

        System.out.print("Исходный массив: ");

        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");
        }

        System.out.print("(n = " + n + ")");

        if (n > 0) {
            for (int i = 0; i < n; i++) {
                // Right
                int tmp = mas[mas.length - 1];

                for (int j = mas.length - 1; j > 0; j--) {
                    mas[j] = mas[j - 1];
                }
                mas[0] = tmp;
            }
        }
        else if (n < 0) {
            for (int i = 0; i < n * (-1); i++) {
                // Left
                int tmp = mas[0];

                for (int j = 0; j < mas.length - 1; j++) {
                    mas[j] = mas[j + 1];
                }
                mas[mas.length - 1] = tmp;
            }
        }

        System.out.print("\nОбработанный массив: ");

        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");
        }
    }
}
