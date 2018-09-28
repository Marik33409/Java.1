package Lesson_4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
// 1 Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку;
////2 Переделать проверку победы, чтобы она не была реализована просто набором условий, например,​​с​​использованием​​циклов.
////3 * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
//// Очень желательно не делать это просто набором условий для каждой из возможных​​ситуаций;
////4 ***​​Доработать​​искусственный​​интеллект,​​чтобы​​он​​мог​​блокировать​​ходы​​игрока.

    // 2 размеры поля
    static int SIZE = 5;

    // 1 создаем двумерный массив
    static char[][] field = new char[SIZE][SIZE];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // 3 обозначение
    static char PLAYER_DOT = 'X';
    static char AI_DOT = 'O';
    static char EMPTY_DOT = '.';

    // 4 метод для заполнения массива точками
    static void initField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    // 5 отображения поля
    static void printField() {
        for (int i = 0; i < SIZE; i++) {
            if(i == 0) {
                System.out.print("   " + (i + 1) + " ");
            } else {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " |");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
    }

    // 6 установка символа
    static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    // 7 ход игрока
    static void playerStep() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X Y (1-3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y,x));
        setSym(y, x, PLAYER_DOT);
    }

    // 9 ход ПК
//    static void aiStep() {
//        int x;
//        int y;
//        do {
//            x = random.nextInt(SIZE);
//            y = random.nextInt(SIZE);
//        } while (!isCellValid(y,x));
//        setSym(y, x, AI_DOT);
//    }
    // 9 ход ПК(2)
   static void aiStep(char c) {
        int x = 0, y = 0, countH = 0, countHNull = 0, countV = 0, countVNull = 0, countDiagonalA = 0, countDiagonalB = 0, countDANull = 0, countDBNull = 0;

        System.out.println("CPU TURN WITH [" + c +"]:");

        // 1. Atack player
        for (int i = 0; i < SIZE; i++) {
            countH = 0;
            countHNull = 0;
            countV = 0;
            countVNull = 0;
            for (int j = 0; j < SIZE; j++) {
                //good vertical move
                if (field[j][i] == c) countV++;
                else if (field[j][i] == EMPTY_DOT) countVNull++;
                if ((countV == SIZE - 1) && (countVNull == 1)) {

                    for (int k = 0; k < SIZE; k++) {
                        if (field[k][i] == EMPTY_DOT) {
                            field[k][i] = c;
                            return;
                        }
                    }
                }
                //good Horizontal move
                if (field[i][j] == c) countH++;
                else if (field[i][j] == EMPTY_DOT) countHNull++;
                if ((countH == SIZE - 1) && (countHNull == 1)) {

                    for (int k = 0; k < SIZE; k++) {
                        if (field[i][k] == EMPTY_DOT) {
                            field[i][k] = c;
                            return;
                        }
                    }
                }

            }

            // good diagonal A "\" move
            if (field[i][i] == c) countDiagonalA++;
            else if (field[i][i] == EMPTY_DOT) countDANull++;
            if ((countDiagonalA == SIZE - 1) && (countDANull == 1)) {

                for (int j = 0; j < SIZE; j++) {
                    if (field[j][j] == EMPTY_DOT) {
                        field[j][j] = c;
                        return;
                    }
                }
            }

            // good diagonal B "/" move
            if (field[i][SIZE - 1 - i] == c) countDiagonalB++;
            else if (field[i][SIZE - 1 - i] == EMPTY_DOT)  countDBNull++;
            if ((countDiagonalB == SIZE - 1) && (countDBNull == 1)) {

                for (int j = 0; j < SIZE; j++) {
                    if (field[j][SIZE - 1 - j] == EMPTY_DOT) {
                        field[j][SIZE - 1 - j] = c;
                        return;
                    }
                }
            }
        }

        countH = 0;
        countHNull = 0;
        countV = 0;
        countVNull = 0;
        countDiagonalA = 0;
        countDiagonalB = 0;
        countDANull = 0;
        countDBNull = 0;

        // 2. Blocking player
        for (int i = 0; i < SIZE; i++) {
            countH = 0;
            countHNull = 0;
            countV = 0;
            countVNull = 0;
            for (int j = 0; j < SIZE; j++) {
                //good vertical move
                if (field[j][i] == PLAYER_DOT) countV++;
                else if (field[j][i] == EMPTY_DOT) countVNull++;
                if ((countV == SIZE - 1) && (countVNull == 1)) {

                    for (int k = 0; k < SIZE; k++) {
                        if (field[k][i] == EMPTY_DOT) {
                            field[k][i] = c;
                            return;
                        }
                    }
                }
                //good Horizontal move
                if (field[i][j] == PLAYER_DOT) countH++;
                else if (field[i][j] == EMPTY_DOT) countHNull++;
                if ((countH == SIZE - 1) && (countHNull == 1)) {

                    for (int k = 0; k < SIZE; k++) {
                        if (field[i][k] == EMPTY_DOT) {
                            field[i][k] = c;
                            return;
                        }
                    }
                }

            }

            // good diagonal A "\" move
            if (field[i][i] == PLAYER_DOT) countDiagonalA++;
            else if (field[i][i] == EMPTY_DOT) countDANull++;
            if ((countDiagonalA == SIZE - 1) && (countDANull == 1)) {

                for (int j = 0; j < SIZE; j++) {
                    if (field[j][j] == EMPTY_DOT) {
                        field[j][j] = c;
                        return;
                    }
                }
            }

            // good diagonal B "/" move
            if (field[i][SIZE - 1 - i] == PLAYER_DOT) countDiagonalB++;
            else if (field[i][SIZE - 1 - i] == EMPTY_DOT)  countDBNull++;
            if ((countDiagonalB == SIZE - 1) && (countDBNull == 1)) {

                for (int j = 0; j < SIZE; j++) {
                    if (field[j][SIZE - 1 - j] == EMPTY_DOT) {
                        field[j][SIZE - 1 - j] = c;
                        return;
                    }
                }
            }
        }

        // 3. Taking center of map
        if (!(SIZE % 2 == 0)) {
            int center = (((SIZE + 1) / 2) - 1);
            if (field[center][center] == EMPTY_DOT) {
                field[center][center] = c;
                return;
            }
        }

        // 4. Taking diagonal points of map
        if (field[0][0] == EMPTY_DOT) { field[0][0] = c; return; }
        if (field[0][field.length - 1] == EMPTY_DOT) { field[0][field.length - 1] = c; return; }
        if (field[field.length - 1][0] == EMPTY_DOT) { field[field.length - 1][0] = c; return; }
        if (field[field.length - 1][field.length - 1] == EMPTY_DOT) { field[field.length - 1][field.length - 1] = c; return; }

        // 5. random move
        //System.out.println("AI random");
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        field[y][x] = c;
        System.out.println("AI X: " + (x + 1) + " Y: " + (y + 1));
    }
    // 8 проверка
    static boolean isCellValid(int y, int x) {
        if(x < 0 || y < 0 || x > SIZE - 1 || y > SIZE - 1) {
            return false;
        }
        return field[y][x] == EMPTY_DOT;
    }


    // 10 проверка на ничью
    static boolean isFieldFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }


    // 11 проверка победы
//    static boolean checkWin(char sym) {
//        if(field[0][0] == sym && field[0][1] == sym &&  field[0][2] == sym) {
//            return true;
//        }
//        if(field[1][0] == sym && field[1][1] == sym &&  field[1][2] == sym) {
//            return true;
//        }
//        if(field[2][0] == sym && field[2][1] == sym &&  field[2][2] == sym) {
//            return true;
//        }
//
//        if(field[0][0] == sym && field[1][0] == sym &&  field[2][0] == sym) {
//            return true;
//        }
//        if(field[0][1] == sym && field[1][1] == sym &&  field[2][1] == sym) {
//            return true;
//        }
//        if(field[0][2] == sym && field[1][2] == sym &&  field[2][2] == sym) {
//            return true;
//        }
//
//        if(field[0][0] == sym && field[1][1] == sym &&  field[2][2] == sym) {
//            return true;
//        }
//        if(field[2][0] == sym && field[1][1] == sym &&  field[0][2] == sym) {
//            return true;
//        }
//        return false;
//    }
    // 11 проверка победы(2)
    static boolean checkWin(char c) {
        int countV;
        int countH;
        int countDiagonalA = 0;
        int countDiagonalB = 0;
        for (int i = 0; i <= SIZE - 1; i++) {
            countH = 0;
            countV = 0;
            for (int j = 0; j <= SIZE - 1; j++) {
                //tested horizontal check
                if (field[i][j] == c) {
                    countH++;
                    if (countH == SIZE) return true;
                }

                //tested vertical check
                if (field[j][i] == c) {
                    countV++;
                    if (countV == SIZE) return true;
                }
            }

            // tested diagonal A "\" check
            if (field[i][i] == c) {
                countDiagonalA++;
                if (countDiagonalA == SIZE) return true;
            } else countDiagonalA = 0;

            // tested diagonal B "/" check
            if (field[i][SIZE - 1 - i] == c) {
                countDiagonalB++;
                if (countDiagonalB == SIZE) return true;
            } else countDiagonalB = 0;
        }
        return false;
    }



    public static void main(String[] args) {
        initField();
        printField();

        while (true) {
            playerStep();
            printField();
            if(checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if(isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }
            aiStep(AI_DOT);
            printField();
            if(checkWin(AI_DOT)) {
                System.out.println("SkyNet WIN!");
                break;
            }
            if(isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }
        }
    }
}
