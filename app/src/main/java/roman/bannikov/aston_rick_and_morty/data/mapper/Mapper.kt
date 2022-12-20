package roman.bannikov.aston_rick_and_morty.data.mapper

interface Mapper<T, R> {
    fun transform(data: T): R
}