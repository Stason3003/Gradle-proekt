fun main() {
    val amound = 1_000_000
    val komisia = amound * 0.75 / 100
    if (komisia > 35) println("$komisia рублей") else println("35 рублей")
    val likes = 1
    if (likes == 1) println("Понравились $likes человеку") else println("Понравились $likes людям")

    fun calculateDiscount(purchaseAmount: Int, isRegularCustomer: Boolean): Int {
        var discount = 0
        var totalAmount = purchaseAmount.toDouble()

        if (totalAmount > 1000){
            if (totalAmount <= 10000) {
                discount = 100
                println("Скидка 100 рублей")
            } else{
                    discount = (totalAmount * 0.05).toInt()
                    println("Скидка 5% ")
                }
                totalAmount = totalAmount - discount.toDouble()
        }

        if (isRegularCustomer) {
            val extraDiscount = ((totalAmount * 1) / 100).toInt()
            discount = discount + extraDiscount
            println("Постояному клиенту скидка 1%: $extraDiscount рублей")
        }

        return discount

    }
        val purchaseAmount = 6000
        val isRegularCustomer = true
        val discount = calculateDiscount(purchaseAmount, isRegularCustomer)
        println("Общая сумма скидки: $discount рублей")



}






