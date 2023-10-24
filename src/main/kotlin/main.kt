fun main() {
    val timeSecond = 30000;
    val result = agoToText(timeSecond)
    println(result)

    val cardType = "MasterCard"
    val previousTransfers = 200.0
    val transferAmount = 500.0

    val commission = calculateCommission(cardType, previousTransfers, transferAmount)
    println("Commission: $commission рублей")

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
    minut % 10 == 0 -> "$minut минуту назад"
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
    cardType: String = "VK Pay",
    previousTransfers: Double = 0.0,
    transferAmount: Double
): Double {
    var commission = 0.0

    if (cardType == "MasterCard" || cardType == "Maestro") {
        if (previousTransfers + transferAmount > 300) {
            commission = transferAmount * 0.6 / 100
        }
    }

    return commission
}


