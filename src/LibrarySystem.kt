class Library<T>(
    val items: MutableList<T> = mutableListOf()
) {
    fun addItem(item: T) {
        items.add(item)
    }
    fun getAllItems(): List<T> {
        return items.toList()
    }
}

data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val isbn: String
)

data class Magazine(
    val title: String,
    val issue: Int,
    val month: String
)

data class DVD(
    val title: String,
    val director: String,
    val duration: Int
)

sealed class LibraryItem {
    class BookItem(val book: Book) : LibraryItem()
    class MagazineItem(val magazine: Magazine) : LibraryItem()
    class DVDItem(val dvd: DVD) : LibraryItem()
}

fun filterByYear(items: List<LibraryItem>, year: Int): List<LibraryItem.BookItem> {
    val result = mutableListOf<LibraryItem.BookItem>()
    for (item in items) {
        if (item is LibraryItem.BookItem) {
            result.add(item)
        }
    }
    return result.filter { it.book.year == year }
}

fun sortByTitle(items: List<LibraryItem>): List<LibraryItem> {
    return items.sortedBy { item ->
        when (item) {
            is LibraryItem.DVDItem -> item.dvd.title
            is LibraryItem.BookItem -> item.book.title
            is LibraryItem.MagazineItem -> item.magazine.title
        }
    }
}

fun groupByAuthor(items: List<LibraryItem>): Map<String, List<LibraryItem.BookItem>> {
    val result = mutableListOf<LibraryItem.BookItem>()
    for (item in items) {
        if (item is LibraryItem.BookItem) {
            result.add(item)
        }
    }
    return result.groupBy { it.book.author }
}

fun calculateTotalDuration(items: List<LibraryItem>): Int {
    val result = mutableListOf<LibraryItem.DVDItem>()
    for (item in items) {
        if (item is LibraryItem.DVDItem) {
            result.add(item)
        }
    }
    return result.fold(0) { total, dvdItem ->
        total + dvdItem.dvd.duration
    }
}

fun main() {
    val book1 = Book("Война и мир", "Лев Толстой", 1869, "978-5-389-07464-0")
    val book2 = Book("Преступление и наказание", "Фёдор Достоевский", 1866, "978-5-17-090484-0")
    val book3 = Book("Мастер и Маргарита", "Михаил Булгаков", 1967, "978-5-17-090234-1")

    val magazine1 = Magazine("Наука и жизнь", 3, "Март")
    val magazine2 = Magazine("National Geographic", 5, "Май")

    val dvd1 = DVD("Интерстеллар", "Кристофер Нолан", 169)
    val dvd2 = DVD("Начало", "Кристофер Нолан", 148)

    val bookItem1 = LibraryItem.BookItem(book1)
    val bookItem2 = LibraryItem.BookItem(book2)
    val bookItem3 = LibraryItem.BookItem(book3)

    val magazineItem1 = LibraryItem.MagazineItem(magazine1)
    val magazineItem2 = LibraryItem.MagazineItem(magazine2)

    val dvdItem1 = LibraryItem.DVDItem(dvd1)
    val dvdItem2 = LibraryItem.DVDItem(dvd2)

    val library = Library<LibraryItem>()

    library.addItem(bookItem1)
    library.addItem(bookItem2)
    library.addItem(bookItem3)
    library.addItem(magazineItem1)
    library.addItem(magazineItem2)
    library.addItem(dvdItem1)
    library.addItem(dvdItem2)

    println()

    val books = library.getAllItems()
    val books1869 = filterByYear(books, 1869)
    println("Книги 1869 года:")
    books1869.forEach { println("- ${it.book.title}") }
    println()

    val sortedItems = sortByTitle(library.getAllItems())
    println("Элементы отсортированные по названию:")
    sortedItems.forEach { item ->
        val title = when (item) {
            is LibraryItem.BookItem -> item.book.title
            is LibraryItem.MagazineItem -> item.magazine.title
            is LibraryItem.DVDItem -> item.dvd.title
        }
        println("- $title")
    }
    println()

    val groupedByAuthor = groupByAuthor(library.getAllItems())
    println("Книги сгруппированные по авторам:")
    groupedByAuthor.forEach { (author, books) ->
        println("$author:")
        books.forEach { println("  - ${it.book.title} (${it.book.year})") }
    }
    println()

    val dvds = library.getAllItems()
    val totalDuration = calculateTotalDuration(dvds)
    println("Общая продолжительность всех DVD: $totalDuration минут")
    println()
}