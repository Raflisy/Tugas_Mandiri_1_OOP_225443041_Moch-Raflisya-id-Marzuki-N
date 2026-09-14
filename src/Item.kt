abstract class Item(
    val id: String,
    val title: String,
    val year: Int
) {

    var isAvailable: Boolean = true
        private set

    // =====================================================
    // METODE ABSTRAK
    // Wajib di-override oleh Book, Journal, dan DVD
    // =====================================================

    abstract fun calculateFinePerDay(): Double

    abstract fun getItemType(): String

    abstract fun getMaxBorrowDays(): Int


    // =====================================================
    // METODE BORROW
    // =====================================================

    fun borrow(): Boolean {

        if (isAvailable) {
            isAvailable = false

            println("Berhasil meminjam: $title")
            return true
        }

        println("Gagal meminjam: $title sedang tidak tersedia.")
        return false
    }


    // =====================================================
    // METODE RETURN ITEM
    // =====================================================

    fun returnItem(daysLate: Int = 0): Double {

        if (!isAvailable) {

            isAvailable = true

            val fine = daysLate * calculateFinePerDay()

            println("Berhasil mengembalikan: $title")

            if (fine > 0) {
                println(
                    "Terlambat $daysLate hari. " +
                            "Total denda: Rp ${"%,.0f".format(fine)}"
                )
            } else {
                println("Pengembalian tepat waktu. Tidak ada denda.")
            }

            return fine
        }

        println("Peringatan: $title tidak sedang dipinjam.")
        return 0.0
    }


    // =====================================================
    // DISPLAY INFO
    // =====================================================

    open fun displayInfo() {

        println("-----------------------------------------------------")
        println("ID              : $id")
        println("Judul           : $title")
        println("Tahun           : $year")
        println("Jenis           : ${getItemType()}")
        println(
            "Status          : ${
                if (isAvailable) "Tersedia" else "Sedang Dipinjam"
            }"
        )
        println(
            "Denda/Hari      : Rp ${"%,.0f".format(calculateFinePerDay())}"
        )
        println("Maks. Pinjam    : ${getMaxBorrowDays()} hari")
    }
}