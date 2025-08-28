import java.util.Scanner;

public class ContactManager {
    static final int maxContacts = 100;
    static final String[] names = new String[maxContacts];
    static final String[] phoneNumbers = new String[maxContacts];
    static int contactCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- Меню Контакт-Менеджера ---");
            System.out.println("1. Добавить контакт");
            System.out.println("2. Просмотреть все контакты");
            System.out.println("3. Найти контакт");
            System.out.println("4. Удалить контакт");
            System.out.println("5. Выйти");
            System.out.print("Выберите пункт меню (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    displayContacts();
                    break;
                case 3:
                    findContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Выход из программы. До свидания!");
                    break;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите от 1 до 5.");
            }
        }
        scanner.close();
    }

    private static void addContact(Scanner scanner) {
        if (contactCount >= maxContacts) {
            System.out.println("Ошибка! Телефонная книга переполнена.");
            return;
        }
        System.out.print("Введите имя контакта: ");
        String name = scanner.nextLine().trim();
        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine().trim();
        if (name.isEmpty() || phoneNumber.isEmpty()) {
            System.out.println("Ошибка! Имя и номер телефона не могут быть пустыми.");
            return;
        }
        names[contactCount] = name;
        phoneNumbers[contactCount] = phoneNumber;
        contactCount++;
        System.out.println("Контакт '" + name + "' успешно добавлен!");

    }

    private static void displayContacts() {
        if (contactCount == 0) {
            System.out.println("Телефонная книга пуста.");
            return;
        }
        System.out.println("\n--- Список всех контактов ---");
        for (int i = 0; i < contactCount; i++) {
            System.out.println((i + 1) + ". " + names[i] + " - " + phoneNumbers[i]);
        }
    }

    private static void findContact(Scanner scanner) {
        if (contactCount == 0) {
            System.out.println("Телефонная книга пуста.");
            return;
        }
        System.out.print("Введите имя для поиска: ");
        String searchName = scanner.nextLine().trim().toLowerCase();
        boolean found = false;
        System.out.println("\n--- Результаты поиска ---");
        for (int i = 0; i < contactCount; i++) {
            if (names[i].toLowerCase().contains(searchName)) {
                System.out.println("Найдено: " + names[i] + " - " + phoneNumbers[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Контакт с именем '" + searchName + "' не найден.");
        }
    }

    private static void deleteContact(Scanner scanner) {
        if (contactCount == 0) {
            System.out.println("Телефонная книга пуста.");
            return;
        }
        System.out.print("Введите имя контакта для удаления: ");
        String deleteName = scanner.nextLine().trim();
        boolean found = false;
        for (int i = 0; i < contactCount; i++) {
            if (names[i].equalsIgnoreCase(deleteName)) {
                found = true;
                System.out.println("Контакт '" + names[i] + "' найден и будет удален.");
                for (int j = i; j < contactCount - 1; j++) {
                    names[j] = names[j + 1];
                    phoneNumbers[j] = phoneNumbers[j + 1];
                }
                contactCount--;
                names[contactCount] = null;
                phoneNumbers[contactCount] = null;
                System.out.println("Контакт успешно удален.");
                break;
            }
        }
        if (!found) {
            System.out.println("Контакт с именем '" + deleteName + "' не найден.");
        }
    }
}


