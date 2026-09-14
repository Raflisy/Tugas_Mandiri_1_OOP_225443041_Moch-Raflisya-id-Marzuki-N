sealed class TransactionStatus {

    data object Borrowed : TransactionStatus() {
        override fun display(): String = "Dipinjam"
    }

    data object Returned : TransactionStatus() {
        override fun display(): String = "Dikembalikan"
    }

    data class Overdue(val daysLate: Int) : TransactionStatus() {
        override fun display(): String = "Terlambat ($daysLate hari)"
    }

    data object Cancelled : TransactionStatus() {
        override fun display(): String = "Dibatalkan"
    }

    abstract fun display(): String

    fun isFinal(): Boolean = this is Returned || this is Cancelled
}