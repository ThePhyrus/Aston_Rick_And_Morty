package roman.bannikov.aston_rick_and_morty.view.mapper

interface Mapper<T, R> {
    fun transform(data: T): R
}