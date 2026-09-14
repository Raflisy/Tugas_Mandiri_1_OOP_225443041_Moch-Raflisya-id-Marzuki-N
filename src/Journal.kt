class Journal(
    id: String,
    title: String,
    year: Int,
    val publisher: String,
    val volume: Int,
    val edition: Int
) : Item(id, title, year) {

    // hitung denda per hari

    override fun calculateFinePerDay(): Double {
        return 3000.0
    }

    // jenis item

    override fun getItemType(): String {
        return "Journal"
    }

    // maks peminjaman
    override fun getMaxBorrowDays(): Int {
        return 7
    }

    // menampilkan informasi jurnal

    override fun displayInfo() {

        // Menampilkan informasi dasar dari item
        super.displayInfo()

        //menampilkan informasi khusus jurnal

        println("Penerbit        : $publisher")
        println("Volume          : $volume")
        println("Edisi           : $edition")
        println("---------------------------------------------")
    }
}