fun main() {
    val commission = calculateCommission("MasterCard", 50000, 1000)
    println("Комиссия: $commission рублей")
}

fun calculateCommission(
    accountType: String = "VK Pay",
    previousTransfers: Int = 0,
    transferAmount: Int
): Double {
    val mastercardMaestroMinLimit = 300
    val mastercardMaestroMaxLimit = 75000
    val mastercardMaestroCommissionRate = 0.6
    val mastercardMaestroFixedFee = 20

    val visaMirCommissionRate = 0.75
    val visaMirMinFee = 35

    val vkPayMaxTransferAmount = 15000
    val vkPayMonthlyLimit = 40000

    val visaDailyLimit = 150000
    val visaMonthlyLimit = 600000
    val mastercardMaestroDailyLimit = 150000
    val mastercardMaestroMonthlyLimit = 600000

    val commission: Double = when (accountType) {
        "MasterCard", "Maestro" -> {
            val totalTransfers = previousTransfers + transferAmount
            if (totalTransfers >= mastercardMaestroMinLimit && totalTransfers <= mastercardMaestroMaxLimit) {
                0.0
            } else {
                (mastercardMaestroCommissionRate / 100.0) * transferAmount + mastercardMaestroFixedFee
            }
        }
        "Visa", "Мир" -> {
            if (transferAmount <= vkPayMaxTransferAmount && previousTransfers <= vkPayMonthlyLimit) {
                0.0
            } else {
                val commissionAmount = (visaMirCommissionRate / 100.0) * transferAmount
                if (commissionAmount < visaMirMinFee) {
                    visaMirMinFee.toDouble()
                } else {
                    commissionAmount
                }
            }
        }
        "VK Pay" -> 0.0
        else -> 0.0
    }

    return commission
}