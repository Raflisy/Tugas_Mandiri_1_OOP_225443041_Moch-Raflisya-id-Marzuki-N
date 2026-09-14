class Library(
    val name: String
) {

    // niympen semua item perpustakaan
    private val items: MutableList<Item> = mutableListOf()

    // nyimpen semua anggota perpustakaan
    private val members: MutableList<Member> = mutableListOf()

    // nyimpen semua transaksi perpustakaan
    private val transactions: MutableList<Transaction> = mutableListOf()


    // =====================================================
    // COMPUTED PROPERTY
    // =====================================================

    // Jumlah semua item
    val totalItems: Int
        get() = items.size

    // Jumlah item yang ada
    val availableItems: Int
        get() = items.count { it.isAvailable }

    // Jumlah semua anggota
    val totalMembers: Int
        get() = members.size

    // Jumlah semua transaksi
    val totalTransactions: Int
        get() = transactions.size


    // tambahin satu item
    fun addItem(item: Item) {
        items.add(item)
        println("Item berhasil ditambahkan: ${item.title}")
    }

    // tambahin banyak item sekaligus
    fun addItems(vararg newItems: Item) {
        for (item in newItems) {
            addItem(item)
        }
    }

    // cari item berdasarkan ID
    fun findItem(id: String): Item? {
        return items.find {
            it.id.equals(id, ignoreCase = true)
        }
    }

    // cari item sesuai ID atau judul
    fun searchItems(keyword: String): List<Item> {
        return items.filter {
            it.id.contains(keyword, ignoreCase = true) ||
                    it.title.contains(keyword, ignoreCase = true)
        }
    }

    // daftarin anggota baru
    fun registerMember(
        id: String,
        name: String,
        email: String,
        phone: String
    ): Boolean {

        // Cek ID udh digunakan atw blum
        if (findMember(id) != null) {
            println("Gagal: ID anggota $id sudah digunakan.")
            return false
        }

        // buat anggota baru
        val member = Member(
            id = id,
            name = name,
            email = email,
            phone = phone
        )

        // tambahin anggota ke daftar
        members.add(member)

        println("Anggota berhasil didaftarkan: $name ($id)")

        return true
    }

    // Mendaftarkan object Member yang sudah dibuat sebelumnya
    fun registerMember(member: Member): Boolean {
        if (findMember(member.id) != null) {
            println("Gagal: ID anggota ${member.id} sudah digunakan.")
            return false
        }

        members.add(member)
        println("Anggota berhasil didaftarkan: ${member.name} (${member.id})")
        return true
    }

    // cri anggota sesuai ID
    fun findMember(id: String): Member? {
        return members.find {
            it.id.equals(id, ignoreCase = true)
        }
    }

    // pinjem item ke ID anggota dan ke ID item
    fun borrowItem(
        memberId: String,
        itemId: String
    ): Transaction? {

        // cari anggota
        val member = findMember(memberId)

        // cari item
        val item = findItem(itemId)

        // Jika anggota gk ditemukan
        if (member == null) {
            println("Gagal: anggota dengan ID $memberId tidak ditemukan.")
            return null
        }

        // Jika item gak ditemukan
        if (item == null) {
            println("Gagal: item dengan ID $itemId tidak ditemukan.")
            return null
        }

        // proses peminjaman ke Member
        val transaction = member.borrowItem(item)

        // Jika berhasil, simpan transaksi di Library
        if (transaction != null) {
            transactions.add(transaction)
        }

        return transaction
    }

    // balikin item ke ID anggota dan ke ID item
    fun returnItem(
        memberId: String,
        itemId: String,
        daysLate: Int = 0
    ): Double {

        // cari anggota
        val member = findMember(memberId)

        // cari item
        val item = findItem(itemId)

        // Jika anggota tidak ditemukan
        if (member == null) {
            println("Gagal: anggota dengan ID $memberId tidak ditemukan.")
            return 0.0
        }

        // Jika item gak ditemuin
        if (item == null) {
            println("Gagal: item dengan ID $itemId tidak ditemukan.")
            return 0.0
        }

        // balikin item ke Member
        return member.returnItem(item, daysLate)
    }

    // tampilin semua item
    fun displayAllItems() {
        println()
        println("=====================================================")
        println("SEMUA ITEM - $name")
        println("=====================================================")

        if (items.isEmpty()) {
            println("Belum ada item.")
        } else {
            items.forEach { item ->
                item.displayInfo()
            }
        }

        println("Total Item      : $totalItems")
        println("Item Tersedia   : $availableItems")
        println("Item Dipinjam   : ${totalItems - availableItems}")
        println("=====================================================")
    }

    // tampilan item yg sdia
    fun displayAvailableItems() {
        println()
        println("=====================================================")
        println("ITEM YANG TERSEDIA")
        println("=====================================================")

        val available = items.filter {
            it.isAvailable
        }

        if (available.isEmpty()) {
            println("Tidak ada item yang tersedia.")
        } else {
            available.forEach { item ->
                println(
                    "${item.id} - ${item.title} " +
                            "(${item.getItemType()})"
                )
            }
        }

        println("Total tersedia: ${available.size}")
        println("=====================================================")
    }


    // Menampilkan semua anggota
    fun displayAllMembers() {
        println()
        println("=====================================================")
        println("SEMUA ANGGOTA")
        println("=====================================================")

        if (members.isEmpty()) {
            println("Belum ada anggota.")
        } else {
            members.forEach { member ->
                member.displayInfo()
            }
        }

        println("Total Anggota: $totalMembers")
        println("=====================================================")
    }

    // tampilin smua transaksi
    fun displayAllTransactions() {
        println()
        println("=====================================================")
        println("SEMUA TRANSAKSI")
        println("=====================================================")

        if (transactions.isEmpty()) {
            println("Belum ada transaksi.")
        } else {
            transactions.forEach { transaction ->
                transaction.displayTransaction()
            }
        }

        println("Total Transaksi: $totalTransactions")
        println("=====================================================")
    }

    // nampilin laporan ringkas perpus
    fun displayReport() {

        // Menghitung jumlah item yang sedang dipinjam
        val borrowedItems = totalItems - availableItems

        // Menghitung total seluruh denda
        val totalFines = members.sumOf {
            it.totalFines
        }

        println()
        println("=====================================================")
        println("LAPORAN PERPUSTAKAAN")
        println("=====================================================")
        println("Nama Perpustakaan : $name")
        println("Total Item        : $totalItems")
        println("Item Tersedia     : $availableItems")
        println("Item Dipinjam     : $borrowedItems")
        println("Total Anggota     : $totalMembers")
        println("Total Transaksi   : $totalTransactions")
        println(
            "Total Denda       : Rp ${"%,.0f".format(totalFines)}"
        )
        println("=====================================================")
    }
}