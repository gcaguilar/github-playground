package com.gcaguilar.github.dummy

import com.gcaguilar.github.data.model.OwnerModel
import com.gcaguilar.github.domain.entity.OwnerEntity

private const val ID = 38892827
private const val OTHER_ID = 38892828
private const val NAME: String = "Guillermo"
private const val OTHER_NAME: String = "Paco"
private const val AVATAR: String = "https://avatars2.githubusercontent.com/u/38892827"
private const val OTHER_AVATAR: String = "https://avatars2.githubusercontent.com/u/38892830"

object OwnerDummy {
    val ownerModel = OwnerModel(id = ID, login = NAME, avatarUrl = AVATAR)
    val ownerEntity = OwnerEntity(id = ID, name = NAME, avatarUrl = AVATAR)
    val otherOwnerModel = OwnerModel(id = OTHER_ID, login = OTHER_NAME, avatarUrl = OTHER_AVATAR)
}