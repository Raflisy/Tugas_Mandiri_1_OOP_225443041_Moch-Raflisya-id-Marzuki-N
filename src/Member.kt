class Member(
    val id: String,
    val name: String,
    private val email: String,
    private val phone: String
) {

    private val transactions: MutableList<Transaction> = mutableListOf()

    val transactionCount: Int
        get() = transactions.size

    val totalFines: Double
        get() = transactions
            .filter { it.status is TransactionStatus.Overdue }
            .sumOf { transaction ->
                val overdue = transaction.status as TransactionStatus.Overdue
                overdue.daysLate * transaction.item.calculateFinePerDay()
            }

    val activeBorrow: Int
        get() = transactions.count {
            it.status is TransactionStatus.Borrowed
        }

    fun borrowItem(item: Item): Transaction? {
        if (!item.borrow()) {
            return null
        }

        val transaction = Transaction(
            id = "TRX-${transactions.size + 1}",
            item = item,
            member = this
        )

        transactions.add(transaction)
        return transaction
    }

    fun returnItem(item: Item, daysLate: Int = 0): Double {
        val transaction = transactions
            .lastOrNull {
                it.item.id.equals(item.id, ignoreCase = true) &&
                        it.status is TransactionStatus.Borrowed
            }

        if (transaction == null) {
            println("Gagal: ${item.title} tidak sedang dipinjam oleh $name.")
            return 0.0
        }

        return transaction.returnItem(daysLate.coerceAtLeast(0))
    }

    fun displayInfo() {
        println("----------------------------------------------")
        println("ID Anggota       : $id")
        println("Nama             : $name")
        println("Email            : $email")
        println("Telepon          : $phone")
        println("Jumlah Transaksi : $transactionCount")
        println("Pinjaman Aktif   : $activeBorrow")
        println("Total Denda      : Rp ${"%,.0f".format(totalFines)}")
        println("----------------------------------------------")
    }

    fun displayTransactions() {
        if (transactions.isEmpty()) {
            println("$name belum memiliki transaksi.")
            return
        }

        transactions.forEach { transaction ->
            transaction.displayTransaction()
        }
    }
}
