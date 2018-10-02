package Lesson_5;
//* Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
//        * Конструктор класса должен заполнять эти поля при создании объекта;
//        * Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
//        * Создать массив из 5 сотрудников
//        Пример:
//        Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
//        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30); // потом для каждой ячейки массива задаем объект
//        persArray[1] = new Person(...);
//        ...
//        persArray[4] = new Person(...);
//        * С помощью цикла вывести информацию только о сотрудниках старше 40 лет;

class Employee {
    String surname, name, patronymic, position, email;
    int salary, age;

    Employee(String surname,
             String name,
             String patronymic,
             String position,
             String email,
             int salary,
             int age) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.position = position;
        this.email = email;
        this.salary = salary;
        this.age = age;
    }

    void getFullInfo() {
        System.out.println("surname: " + surname + " | name: " + name
                + " | patronymic: " + patronymic + " | position: " + position
                + " | email: " + email + " | salary: " + salary + " | age: "
                + age);
    }

    int getAge() {
        return age;
    }
    public static class Lesson_5 {
        public static void main(String[] args) {
            Employee[] employees = new Employee[5];
            employees[0] = new Employee("Gromov", "Daniil", "Efimovich",
                    "director", "gromovdan@mail.ru", 2500000, 42);
            employees[1] = new Employee("Stepanova", "Birgit", "Igorevna",
                    "accountant", "stepanova19@gmail.com", 95000, 31);
            employees[2] = new Employee("Prokhorov", "Gennady", "Gennadyevich",
                    "manager", "gennadyprok111@list.ru", 55000, 25);
            employees[3] = new Employee("Eliseeva", "Lyalya", "Artemovna",
                    "CFO", "lylyaelis2290@mgail.com", 170000, 41);
            employees[4] = new Employee("Khokhlov", "Eric", "Antoninovich",
                    "manager", "khokhloveric23@yandex.ru", 65000, 27);

            for (Employee e : employees) if (e.getAge() > 40) e.getFullInfo();
        }
    }
}