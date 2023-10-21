fun main() {
    val amound = 1_000_000
    val komisia = amound * 0.75 / 100
    if (komisia > 35) println("$komisia рублей") else println("35 рублей")
    val likes = 33
    println("Понравились $likes человеку")

    fun calculateDiscount(purchaseAmount: Int, isRegularCustomer: Boolean): Int {
        var discount = 0
        var totalAmount = purchaseAmount.toDouble()

        if (totalAmount > 1000) {
            if (totalAmount <= 10000) {
                discount = 100
            } else {
                discount = (totalAmount * 0.05).toInt()
            }

            if (isRegularCustomer) {
                val extraDiscount = (totalAmount * 0.01).toInt()
                discount = (discount * (1 - extraDiscount / 100.0)).toInt()
            }
        }

        return discount
    }

    val purchaseAmount = 5000
    val isRegularCustomer = false
    val discount = calculateDiscount(purchaseAmount, isRegularCustomer)
    println("Сумма скидки: $discount рублей")


}







