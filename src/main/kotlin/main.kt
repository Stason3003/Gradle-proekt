fun main() {
    val cardType = "Mastercard"
    val previousTransfers = 8000
    val transferAmount = 100000
    val commission = calculateCommission(cardType, previousTransfers, transferAmount)
    if (commission == -1.0) {
        println("У вас лимит")
    } else {
        println("Коммисия: $commission рублей")
    }
}
fun calculateCommission(
    cardType: String = "VK Pay",
    previousTransfers: Int = 0,
    transferAmount: Int
): Double {
    val commissionRate: Double = when (cardType) {
        "Mastercard", "Maestro" -> if (transferAmount in 300..75000) 0.0 else 0.006
        "Visa", "Мир" -> 0.0075
        else -> 0.0
    }

    val minimumCommission: Double = when (cardType) {
        "Mastercard", "Maestro" -> if (transferAmount in 300..75000) 0.0 else 20.0
        "Visa", "Мир" -> 35.0
        else -> 0.0
    }

    val commission: Double = transferAmount * commissionRate

    val dailyLimit: Int = when (cardType) {
        "VK Pay" -> 15000
        else -> 150000
    }

    val monthlyLimit: Int = 600000

    val totalTransfers: Int = previousTransfers + transferAmount

    return when {
        (totalTransfers > dailyLimit || totalTransfers > monthlyLimit) -> -1.0
        (commission < minimumCommission) -> minimumCommission
        else -> commission
    }
}

