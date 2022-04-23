package com.example.domain.entities

data class ChampDetailEntity(
    val detailHeader: DetailHeader,
    val traits: List<Trait>,
) {

    data class DetailHeader(
        val id: String,
        val name: String,
        val imgUrl: String,
        val coverUrl: String,
        val coverPath: String,
        val cost: Int,
        val imgPath: String,
        val items: List<Item>,
    ) {
        data class Item(
            val imgPath: String,
            val imgUrl: String,
            val itemName: String,
            val itemId: Int
        )
    }

    data class Trait(
        val traitKey: String,
        val description: String,
        val innate: String,
        val name: String,
        val type: String,
        val imgUrl: String,
        val imgPath: String,
        val sets: List<Set>,
        val champLeagues: List<ChampLeague>
    ) {
        data class Set(
            val active: String?,
            val qty: Int?,
            val style: String
        )
        data class ChampLeague(
            val id: String,
            val cost: Int,
            val imgUrl: String,
            val imgPath: String,
        )
    }


}