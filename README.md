# 📊 BackendTest — Поиск N-го минимального значения в Excel-файле

---

### 1. Сборка проекта

Убедитесь, что у вас установлены:
- **Java 17+**
- **Maven**

Выполните в корне проекта:

```bash
mvn clean package
```

### 2. Запуск приложения
После успешной сборки запустите JAR-файл:


```bash
java -jar target/BackendTest-0.0.1-SNAPSHOT.jar
```
- **Сервис будет доступен на порт 8080.**

### 3. Тестирование через Swagger UI
Откройте в браузере:

🔗 http://localhost:8080/swagger-ui/index.html#/controller/getNthMin

### В разделе GET /nth-min укажите два параметра:

filePath — абсолютный путь к вашему .xlsx файлу (например: C:\Users\YourName\Documents\data.xlsx) 

n — порядковый номер минимального значения (например: 3)
