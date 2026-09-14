class DVD(
    id: String,
    title: String,
    year: Int,
    val director: String,
    val duration: Int,
    val genre: String
) : Item(id, title, year) {

    // hitung denda

    override fun calculateFinePerDay(): Double {
        return 5000.0
    }

    //jenis item

    override fun getItemType(): String {
        return "DVD"
    }

    // maks peminjaman
    override fun getMaxBorrowDays(): Int {
        return 7
    }

    override fun displayInfo() {

        // Menampilkan informasi dasar dari item
        super.displayInfo()

        // Menampilkan informasi khusus DVD
        println("Sutradara        : $director")
        println("Durasi           : $duration menit")
        println("Genre            : $genre")
        println("----------------------------------------------")
    }
}