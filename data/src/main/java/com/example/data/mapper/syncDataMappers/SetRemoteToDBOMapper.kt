package com.example.data.mapper.syncDataMappers

import com.example.common_jvm.extension.defaultEmpty
import com.example.data.local.response.SetDBO
import com.example.data.reponse.TraitResponseItem

class SetRemoteToDBOMapper {
    fun mapper(traitKey: String?, set: TraitResponseItem.Set): SetDBO {
        return SetDBO(
            traitKey = traitKey.defaultEmpty(),
            style = set.style.defaultEmpty(),
            active = set.active,
            qty = set.min
        )
    }

    fun mapList(traitKey: String?, sets: List<TraitResponseItem.Set>): List<SetDBO> {
        val setDBOs: MutableList<SetDBO> = mutableListOf()
        sets.forEach {
            setDBOs.add(mapper(traitKey, it))
        }
        return setDBOs
    }
}