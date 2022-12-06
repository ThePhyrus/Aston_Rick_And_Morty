package roman.bannikov.aston_rick_and_morty.presentation.mapper

interface Mapper<T, R> {
    fun transform(data: T): R
}