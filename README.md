# GetEasy - Умные заметки для Android

## 📌 Цель
Разработать удобное и функциональное Android-приложение для управления заметками, задачами и напоминаниями с интуитивным интерфейсом и дополнительными возможностями:
- Голосовой ввод
- Настройка персонализации

## 📋 Задача
1. Создать три основных раздела:
   - **Daily** (ежедневные задачи)
   - **Notes** (заметки)
   - **Reminder** (напоминания)
2. Реализовать локальное хранение данных с возможностью облачного расширения
3. Обеспечить современный UI/UX
4. Дополнительные функции:
   - Голосовой ввод
   - Настройки темы
   - Выбор голоса помощника

## 💭 Ожидания в начале
- Простой и понятный интерфейс
- Стабильная работа основных функций
- Надежное хранение данных в БД
- Легкая реализация голосового ввода и настроек

## 🛠 Процесс работы

### 🎨 Проектирование UI
- Созданы макеты экранов в Figma
- Реализованы фрагменты для вкладок
- Настроена навигация между экранами

### 💾 Работа с данными
- Подключена Room Database для Daily
- Реализованы ViewModel и LiveData

### ✨ Дополнительные функции
- В процессе: голосовой ввод (Speech-to-Text API)
- Планируется: настройки темы

## ✅ Результат
| Раздел       | Статус       |
|--------------|-------------|
| UI           | ✅ Готово    |
| Daily (БД)   | ✅ Готово    |
| Навигация    | ✅ Готово    |
| Notes        | ❌ В процессе|
| Reminder     | ❌ В процессе|
| Голосовой ввод | ❌ Не реализовано |
| Настройки темы | ❌ Не реализовано |

## 📚 Использованные технологии
- **Язык**: Kotlin
- **Android Jetpack**:
  - ViewModel & LiveData
  - Room Database
  - Navigation Component
- **Другие**:
  - Coroutines
  - RecyclerView

## 🔴 Неудачи и выводы
### Проблемы
1. Задержка с БД для Notes/Reminder
2. Сложности с голосовым вводом (Google Speech API)
3. Нехватка времени на темную тему
   
## Анализ архитектуры проекта
По анализу кода можно выделить следующие паттерны программирования:

MVVM (Model-View-ViewModel) - Архитектурный паттерн, который явно прослеживается в структуре проекта с разделением на:

Модели данных (entities)

View (активности и фрагменты)

ViewModel (логика представления)

Repository - Используется для абстракции доступа к данным, работа с БД инкапсулирована в репозиториях

Observer - Реализован через LiveData для реакции UI на изменения данных

Singleton - Применяется для доступа к базе данных (Room Database instance)

Adapter - Широко используется для работы с RecyclerView в различных списках

Factory Method - Прослеживается в создании экземпляров ViewModel

Dependency Injection - Используется (хотя можно было бы применить более системно, например с Hilt)

### Выводы
- Лучше планировать этапы работы с БД
- Дробить большие задачи на подзадачи
- Тестировать сторонние API заранее
