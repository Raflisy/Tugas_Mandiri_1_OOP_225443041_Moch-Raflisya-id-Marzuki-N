class Book(
    id: String,
    title: String,
    year: Int,
    val author: String,
    val pages: Int,
    val genre: String
) : Item(id, title, year) {

    //hitung denda per hari

    override fun calculateFinePerDay(): Double {
        return 2000.0

    }

    //jenis item

    override fun getItemType(): String {
        return "Book"
    }

    // maksimal peminjaman

    override fun getMaxBorrowDays(): Int {
        return 14
    }

    //menampilkan informasi buku

    override fun displayInfo() {

        // menampilkan infromasi dasar dari itme
        super.displayInfo()

        //menampilkan informasi khusus buku
        println("Penulis            :   $author")
        println("Jumlah Halaman     :   $pages")
        println("Genre              :   $genre")
        println("------------------------------------------")
    }
}