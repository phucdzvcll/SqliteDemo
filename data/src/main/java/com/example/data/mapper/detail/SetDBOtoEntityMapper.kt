package com.example.data.mapper.detail

import com.example.common_jvm.extension.defaultEmpty
import com.example.common_jvm.extension.defaultZero
import com.example.common_jvm.mapper.Mapper
import com.example.data.local.response.SetDBO
import com.example.domain.entities.ChampDetailEntity

class SetDBOtoEntityMapper : Mapper<SetDBO?, ChampDetailEntity.Trait.Set>(){
    override fun map(input: SetDBO?): ChampDetailEntity.Trait.Set {
        return ChampDetailEntity.Trait.Set(
            active = input?.active.defaultEmpty(),
            qty = input?.qty.defaultZero(),
            style = input?.style.defaultEmpty()
        )
    }
}