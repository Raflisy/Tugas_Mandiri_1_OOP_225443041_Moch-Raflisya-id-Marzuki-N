fun main() {

    // 1. Library

    val library = Library("Perpustakaan Kampus")

    println("===========================================")
    println("           SISTEM PERPUSTAKAAN KAMPUS")
    println("===========================================")
    println("Library: ${library.name}")
    println()

    // 2. 6 ITEM ADD
    val buku1 = Book(
        id = "B001",
        title = "Pemograman Kotlin",
        year = 2023,
        author = "Budi Santoso",
        pages = 350,
        genre = "Prgoramming",

    )

    val buku2 = Book(
        id = "B002",
        title = "Dasar-Dasar OOP",
        year = 2022,
        author = "Siti Rahayu",
        pages = 280,
        genre = "Education"
    )

    val jurnal1 = Journal(
        id = "J001",
        title = "Jurnal Teknologi Informasi",
        year = 2023,
        publisher = "ITB",
        volume = 15,
        edition = 2
    )
    val jurnal2 = Journal(
        id = "J002",
        title = "Jurnal Pendidikan",
        year = 2022,
        publisher = "UGM",
        volume = 10,
        edition = 1
    )

    val dvd1 = DVD(
        id = "D001",
        title = "Inception",
        year = 2010,
        director = "Christopher Nolan",
        duration = 148,
        genre = "Sci-Fi"
    )

    val dvd2 = DVD(
        id = "D002",
        title = "The Matrix",
        year = 1999,
        director = "Wachowski",
        duration = 136,
        genre = "Action"
    )

    library.addItems(
        buku1,
        buku2,
        jurnal1,
        jurnal2,
        dvd1,
        dvd2
    )

    // 3. Regis 3 anggota

    val anggota1 = Member(
        id = "M001",
        name = "Ahmad Fauzi",
        email = "ahmad@email.com",
        phone = "08123456789",
    )

    val anggota2 = Member(
        id = "M002",
        name = "Dewi Lestari",
        email = "dewi@email.com",
        phone = "08129876543"
    )

    val anggota3 = Member(
        id = "M003",
        name = "Rizky Pratama",
        email = "rizky@email.com",
        phone = "0812345678901"
    )

    library.registerMember(anggota1)
    library.registerMember(anggota2)
    library.registerMember(anggota3)

    // Menguji pendaftaran dengan data parameter langsung.
    library.registerMember(
        id = "M001",
        name = "Ahmad Fauzi",
        email = "ahmad@email.com",
        phone = "08123456789"
    )

    println("\nPencarian item dengan kata kunci Kotlin:")
    library.searchItems("Kotlin").forEach { item ->
        println("${item.id} - ${item.title}")
    }

    library.displayAllMembers()

    // 4.

    println("\n========================================")
    println("       SEMUA ITEM")
    println("\n========================================")

    library.displayAllItems()

    // 5. Peminjaman SKENA A

    // ahmad minjem pemograman kotlin
    println("\nAhmad meminjam Pemograman Kotlin")
    library.borrowItem("M001", "B001")

    // ahmad minjem intception
    println("\nAhmad meminjam Inception:")
    library.borrowItem("M001", "D001")

    // dewi minjem jurnal teknologi informasi
    println("\nDewi meminjam Jurnal Teknologi Informasi:")
    library.borrowItem("M002", "J001")

    // 6. tAMPILKAN ITEM YANG SEDIA

    println("\n========================================")
    println("       ITEM TERSEDIA")
    println("\n========================================")

    library.displayAvailableItems()

    // 7. Tampilkan transaksi ahmad

    println("\n========================================")
    println("       Transaksi Ahmad Fauzi")
    println("\n========================================")

    library.displayAllTransactions()

    println("\nTransaksi milik Ahmad Fauzi:")
    anggota1.displayTransactions()

    // 8. TAMPILAN TRANSAKSI DEWI

    println("\n========================================")
    println("       Transaksi Dewi Lestari")
    println("\n========================================")

    library.displayAllTransactions()

    // 9. Pengembalian SKENARIO B

    println("\n========================================")
    println("       SKENARIO PENGEMBALIAN")
    println("\n========================================")

    //  ahmad mengembalikan pemograman kotlin tepat waktu
    println("\nAhmad Mengembalikan Pemograman Kotlin")
    library.returnItem("M001", "B001", 0)

    //  dewi mengembalikan pemograman kotlin tepat waktu
    println("\nDewi Mengembalikan Jurnal Teknologi Informasi:")
    library.returnItem("M002", "J001", 3)

    // 10. TRANSAKSI AHMAD SETELAH PENGEMBALIAN

    println("\n========================================")
    println("       TRANSAKSI AHMAD SETELAH PENGEMBALIAN")
    println("\n========================================")

    library.displayAllTransactions()

    println("\n========================================")
    println("       TRANSAKSI DEWI SETELAH PENGEMBALIAN")
    println("\n========================================")

    library.displayAllTransactions()

    // 11. DEMO POLIMORFISME

    println("\n========================================")
    println("       DEMONSTRASI POLIMORFISME")
    println("\n========================================")

    val items: List<Item> = listOf(
        buku1,
        jurnal1,
        dvd1,
    )

    for (item in items) {

        println(
            "${item.getItemType()} - " +
            "Denda/hari: Rp ${"%,.0f".format(item.calculateFinePerDay())}"
        )
    }

    // 12. Demo SEALED CLASS

    println("\n========================================")
    println("       DEMONSTRASI SEALED CLASS")
    println("\n========================================")

    val status1: TransactionStatus = TransactionStatus.Borrowed
    val status2: TransactionStatus = TransactionStatus.Returned
    val status3: TransactionStatus = TransactionStatus.Overdue(5)
    val status4: TransactionStatus = TransactionStatus.Cancelled

    val statuses = listOf(
        status1,
        status2,
        status3,
        status4
    )

    for (status in statuses) {

        when (status) {

            TransactionStatus.Borrowed -> {
                println(status.display())
            }

            TransactionStatus.Returned -> {
                println(status.display())
            }

            is TransactionStatus.Overdue -> {
                println(status.display())
            }

            TransactionStatus.Cancelled -> {
                println(status.display())
            }
        }
    }

    // 13. DEMO SMART CASTING

    println("\n========================================")
    println("       DEMONSTRASI SMART CASTING")
    println("\n========================================")

    // =====================================================
    // 13. DEMONSTRASI SMART CASTING
    // =====================================================

    println("\n=====================================================")
    println("           DEMONSTRASI SMART CASTING")
    println("=====================================================")

    val selectedItem: Item = items[0]

    when (selectedItem) {

        is Book -> {
            println("Item ${selectedItem.id} adalah Buku")
            println("Penulis: ${selectedItem.author}")
        }

        is Journal -> {
            println("Item ${selectedItem.id} adalah Jurnal")
            println("Penerbit: ${selectedItem.publisher}")
        }

        is DVD -> {
            println("Item ${selectedItem.id} adalah DVD")
            println("Sutradara: ${selectedItem.director}")
        }
    }

    // =====================================================
    // 14. DEMONSTRASI SAFE CASTING (as?)
    // =====================================================

    println("\nPercobaan casting B001 menjadi DVD:")

    val dvdResult = selectedItem as? DVD

    if (dvdResult != null) {
        println("Casting berhasil.")
        println("Judul DVD: ${dvdResult.title}")
    } else {
        println("Casting gagal: B001 bukan merupakan DVD.")
    }

    // =====================================================
    // 15. DEMONSTRASI ENKAPSULASI
    // =====================================================

    println("\n=====================================================")
    println("            DEMONSTRASI ENKAPSULASI")
    println("=====================================================")

    println("Properti isAvailable memiliki private set.")
    println("Nilai isAvailable tidak dapat diubah secara langsung")
    println("dari luar class Item.")

    println()

    println("Properti email pada Member memiliki private val.")
    println("Nilai email tidak dapat diubah secara langsung")
    println("dari luar class Member.")

    println()

    println("Contoh kode berikut akan ERROR jika dijalankan:")
    println("// buku1.isAvailable = false")
    println("// anggota1.email = \"emailbaru@email.com\"")

    println()
    println("Enkapsulasi berhasil melindungi data internal object.")

    // =====================================================
    // 16. LAPORAN AKHIR
    // =====================================================

    println("\n=====================================================")
    println("                 LAPORAN AKHIR")
    println("=====================================================")

    library.displayReport()

    // =====================================================
    // PROGRAM SELESAI
    // =====================================================

    println("\n=====================================================")
    println("           DEMONSTRASI SISTEM SELESAI")
    println("=====================================================")


}
