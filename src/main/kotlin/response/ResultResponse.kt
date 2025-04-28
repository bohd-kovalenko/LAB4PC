package response

data class ResultResponse(
    val status: OperationStatus,
    val result: Array<IntArray>?
) : Response {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResultResponse

        if (status != other.status) return false
        if (!result.contentDeepEquals(other.result)) return false

        return true
    }

    override fun hashCode(): Int {
        var result1 = status.hashCode()
        result1 = 31 * result1 + (result?.contentDeepHashCode() ?: 0)
        return result1
    }
}