object CurriedFunctionsExample extends App {

    /************** PRZYKŁAD 1 - różne sposoby zapisu curried functions **************/
    def finalProductPriceFullSignature(vat: Double) = (serviceCharge: Double) => (price: Double) => {
        price + price * serviceCharge / 100 + price * vat / 100
    }

    val vatApplied1 = finalProductPriceFullSignature(20)
    val serviceChargeApplied1 = vatApplied1(12.5)
    val finalProductPrice1 = serviceChargeApplied1(120)

    println(finalProductPrice1)

    def finalPriceCurriedSimplifiedSignature(vat: Double) (serviceCharge: Double) (price: Double): Double = {
        price + price * serviceCharge / 100 + price * vat / 100
    }

    val vatApplied2 = finalPriceCurriedSimplifiedSignature(20)_
    val serviceChargeApplied2 = vatApplied2(12.5)
    val finalProductPrice2 = serviceChargeApplied2(120)

    println(finalProductPrice2)

    /**************** PRZYKŁAD 2 - currying dla istniejących funkcji ******************/
    def reduction(discount: Double, price: Double) = (1 - discount / 100) * price

    val curriedReduction = (reduction _).curried
    val discountApplied = curriedReduction(30)
    val reducedPrice1 = discountApplied(240)
    val reducedPrice2 = discountApplied(300)

    println(reducedPrice1)
    println(reducedPrice2)

    /****************PRZYKŁAD 3 - zastosowanie curried functions ****************************/
    def courseAverage(tests: Int*)(assignments: Int*)(quizzes: Int*) = {
        0.2 * tests.sum / tests.length + 0.3 * assignments.sum / assignments.length + 0.5 * quizzes.sum / quizzes.length
    }

    val courseAverageResult = courseAverage(90, 10)(100, 10)(40, 60)

    println(courseAverageResult)

    /***************PRZYKŁAD 4 - zastosowanie curried functions ******************************/
    case class CreditCard(ownerName: String, number: Int)
    object CreditCard {
        def getPremium(totalCards: Int)(creditCard: CreditCard): Double = {
            if(totalCards < 15) 10
            else if(totalCards < 30) 15
            else 20
        }
    }

    def getCreditCards: List[CreditCard] = {
        List(new CreditCard("Kowalski", 3456), new CreditCard("Nowak", 5678), new CreditCard("Zięba", 9876))
    }

    val creditCards: List[CreditCard] = getCreditCards
    val getPremiumWithTotal = CreditCard.getPremium(creditCards.length)_
    val allPremiums = creditCards.map(getPremiumWithTotal).sum

    println(allPremiums)
}
