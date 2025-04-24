# Сервис для поиска N-ного минимального числа из XLSX-файла

## Тестовое задание

### Требования:
1. Реализовать Spring Boot приложение с Swagger UI
2. Единственный контроллер с методом POST
3. Метод принимает:
    - XLSX-файл с числами в первом столбце
    - Целое число N > 0
4. Возвращает N-ное минимальное число
5. Алгоритм должен быть реализован без использования встроенной сортировки
6. Инструкция по запуску

### Реализация:
- **Алгоритм**: Использована max-heap структура для эффективного поиска
- **Оптимизация**: Сложность O(M log N), где M - количество чисел
- **Обработка ошибок**:
    - Пустой файл
    - Некорректный формат данных
    - Недостаточное количество элементов

## Технологии
- Java 21
- Spring Boot 3.2.0
- Apache POI 5.2.3 (работа с Excel)
- Springdoc OpenAPI 2.2.0 (Swagger)
- Maven


## Запуск приложения

### Требования:
- JDK 21+
- Maven 3.9+

### Сборка и запуск:
```bash
# Сборка
mvn clean install

# Запуск
java -jar target/test-for-soft-1.0-SNAPSHOT.jar
```

Приложение будет доступно на порту 8080:

http://localhost:8080

## Работа с API

### Swagger UI
Документация доступна по адресу:  
`http://localhost:8080/swagger-ui.html`

### Пример запроса:
1. В Swagger UI выберите метод `POST /findNthMin`
2. Загрузите XLSX-файл формата:

   | A |
   |---|
   | 5 |
   | 3 |
   | 1 |
   | 4 |
   | 2 |
3. Укажите `N=3`
4. Результат: `3`

### Ответы:
- **Успех**:
```
200 OK
3
```

## Обработка ошибок

| Ошибка                          | HTTP Status | Пример сообщения                     |
|---------------------------------|-------------|---------------------------------------|
| Пустой файл                     | 400         | `File contains no valid numbers`      |
| Некорректный формат файла       | 400         | `Error processing Excel file`         |
| N > количества элементов        | 400         | `N exceeds number of elements in file`|
| Отрицательное значение N        | 400         | `N must be positive`                  |

## Алгоритм

```java
public int findNthMinimum(List<Integer> numbers, int n) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, Comparator.reverseOrder());
    
    for (int num : numbers) {
        if (maxHeap.size() < n) {
            maxHeap.add(num);
        } else if (num < maxHeap.peek()) {
            maxHeap.poll();
            maxHeap.add(num);
        }
    }
    
    return maxHeap.peek();
}
