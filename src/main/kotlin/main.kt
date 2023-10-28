fun main() {
    val timeSecond = 30000;
    val result = agoToText(timeSecond)
    println(result)
    val commission = calculateCommission("MasterCard", 50000, 1000)
    println("Комиссия: $commission рублей")

}

fun agoToText(timeSecond: Int) = when {
    timeSecond in 0..60 -> "Был(а) только что"
    timeSecond in 61..(60 * 60) -> minutAgo(timeSecond / 60)
    timeSecond in (60 * 60) + 1..(24 * 60 * 60) -> hourAgo(timeSecond / (60 * 60))
    timeSecond in (24 * 60 * 60) + 1..(48 * 60 * 60) -> "Вчера"
    timeSecond in (48 * 60 * 60) + 1..(72 * 60 * 60) -> "Позавчера"
    else -> {
        "Давно"
    }
}

fun minutAgo(minut: Int) = when {
    minut in 5..20 || minut % 10 in 5..9 || minut % 10 == 0 -> "$minut минут назат "
    minut % 10 == 1 -> "$minut минуту назад"
    minut % 10 in 2..4 -> "$minut минуты назад"
    else -> ""
}

fun hourAgo(hour: Int) = when {
    hour in 5..20 || hour % 10 in 5..9 || hour % 10 == 0 -> "$hour часов назад"
    hour % 10 == 1 -> "$hour час назад"
    hour % 10 in 2..4 -> "$hour часа назад"
    else -> ""
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

    val commission: Double = when (accountType) {
        "MasterCard", "Maestro" -> {
            if (previousTransfers >= mastercardMaestroMinLimit && previousTransfers <= mastercardMaestroMaxLimit) {
                0.0
            } else {
                (mastercardMaestroCommissionRate / 100.0) * transferAmount + mastercardMaestroFixedFee
            }
        }
        "Visa", "Мир" -> (visaMirCommissionRate / 100.0) * transferAmount.coerceAtLeast(visaMirMinFee)
        "VK Pay" -> 0.0
        else -> 0.0
    }

    return commission
}

