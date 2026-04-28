class Kata {
    static int scoreHand(List cards) {

        int constantSum = cards.sum {
            if (it.isInteger()) {
                return it.toInteger()
            }
            else if (it != "A") {
                return 10
            }
            else {
                return 0
            }
        } ?: 0

        int acesNum = cards.count { it == "A" }
        List sums = []
        for (int i=0; i <= acesNum; i++) {
            sums << 11*i + 1*(acesNum-i) + constantSum
        }
        sums.sort(true) { it <= 21 ? 21-it : it }

        return sums ? sums[0] : constantSum
    }
}