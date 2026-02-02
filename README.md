# Лабораторная работа №13-14
Коллекции, обобщения и функциональный стиль в Kotlin
## Описание
Данная лабораторная работа посвящена изучению продвинутых возможностей языка Kotlin,
которые активно используются при разработке Android-приложений.
В рамках работы рассматриваются:
- обобщённые типы (Generics);
- коллекции Kotlin;
- функции высшего порядка;
- extension-функции;
- enum-классы;
- data-классы;
- singleton и companion object;
- scope-функции;
- интерфейсы;
- функции для работы с коллекциями: `forEach`, `map`, `filter`, `groupBy`, `fold`, `sortedBy.`
  Все примеры ориентированы на практическое применение и подготовку к разработке
  мобильных приложений.

## Примеры

### 1. Generics & Data-классы
```kotlin
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)
```

### 2. Enum-классы
```kotlin
enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}
```

### 3. Функции для работы с коллекциями
```kotlin
val fullMenu = cookies.map {
    "${it.name} - $${it.price}"
}
val softBakedMenu = cookies.filter {
    it.softBaked
}
val groupedMenu = cookies.groupBy { it.softBaked }
val totalPrice = cookies.fold(0.0) { total, cookie -> 
    total + cookie.price
}
val alphabeticalMenu = cookies.sortedBy { it.name }
```

## Как запустить проект
1. Клонируйте репозиторий:
```bash
git clone https://github.com/vfedotova418-png/Kotlin_Advanced_Lab_13_14_Fedotova.git
```
2. Откройте проект в IntelliJ IDEA.

3. Запустите любой файл с функцией main().

## Автор
Федотова В.С.
## Лицензия
Проект создан в учебных целях.