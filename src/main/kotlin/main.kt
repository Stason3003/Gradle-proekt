fun main() {
    val timeSecond = 30000;
    val result = agoToText(timeSecond)
    println(result)

    val cardType = "MasterCard"
    val previousTransfers = 2000.0
    val transferAmount = 500.0

    val commission = calculateCommission(cardType, previousTransfers, transferAmount)
    if (commission >= 0) {
        println("Комиссия: $commission рублей")
    } else {
        println("Произошла ошибка при расчете комиссии!")
    }
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
    cardType: String = "VK Pay",
    previousTransfers: Double = 0.0,
    transferAmount: Double
): Double {
    val monthlyLimit = 75000.0
    val singleLimit = 3000.0

    val commissionRate = when (cardType) {
        "MasterCard", "Maestro" -> {
            if (previousTransfers + transferAmount < monthlyLimit) {
                0.6
            } else {
                0.0
            }
        }
        else -> 0.0
    }

    val commission = transferAmount * commissionRate
    val totalAmount = transferAmount + commission

    if (totalAmount > singleLimit) {

        println("Превышен лимит на операцию!")
        return -1.0
    }

    return commission
}


