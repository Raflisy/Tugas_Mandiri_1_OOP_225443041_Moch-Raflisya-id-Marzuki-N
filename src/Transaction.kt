import java.time.LocalDate

class Transaction(
    val id: String,
    val item: Item,
    val member: Member,
    val borrowDate: String = LocalDate.now().toString(),
    var status: TransactionStatus = TransactionStatus.Borrowed
) {

    fun returnItem(daysLate: Int = 0): Double {
        if (status.isFinal()) {
            println("Gagal: transaksi $id sudah selesai.")
            return 0.0
        }

        val fine = item.returnItem(daysLate.coerceAtLeast(0))
        status = if (daysLate > 0) {
            TransactionStatus.Overdue(daysLate)
        } else {
            TransactionStatus.Returned
        }

        return fine
    }

    fun cancel() {
        if (status.isFinal()) {
            println("Gagal: transaksi $id sudah selesai.")
            return
        }

        item.returnItem()
        status = TransactionStatus.Cancelled
        println("Transaksi $id berhasil dibatalkan.")
    }

    fun displayTransaction() {
        println("----------------------------------------------")
        println("ID Transaksi    : $id")
        println("Status          : ${status.display()}")
        println("ID Item         : ${item.id}")
        println("Judul Item      : ${item.title}")
        println("Peminjam        : ${member.name}")
        println("ID Anggota      : ${member.id}")
        println("Tanggal Pinjam  : $borrowDate")

        if (status is TransactionStatus.Overdue) {
            val overdue = status as TransactionStatus.Overdue
            println("Keterangan      : Terlambat ${overdue.daysLate} hari")
        }

        println("----------------------------------------------")
    }
}
