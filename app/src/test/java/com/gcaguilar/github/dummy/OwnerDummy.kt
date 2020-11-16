package com.gcaguilar.github.dummy

import com.gcaguilar.github.data.db.OwnerDb
import com.gcaguilar.github.data.model.OwnerModel
import com.gcaguilar.github.domain.entity.OwnerEntity
import com.gcaguilar.github.presentation.OwnerMvp

private const val ID = 38892827
private const val OTHER_ID = 38892828
private const val NAME: String = "Guillermo"
private const val OTHER_NAME: String = "Paco"
private const val AVATAR: String = "https://avatars2.githubusercontent.com/u/38892827"
private const val OTHER_AVATAR: String = "https://avatars2.githubusercontent.com/u/38892830"

object OwnerDummy {
    val ownerDb = OwnerDb(ownerId = ID, login = NAME, avatarUrl = AVATAR)
    val otherOwnerDb = OwnerDb(ownerId = OTHER_ID, login = OTHER_NAME, avatarUrl = OTHER_AVATAR)
    val ownerModel = OwnerModel(id = ID, login = NAME, avatarUrl = AVATAR)
    val ownerEntity = OwnerEntity(id = ID, name = NAME, avatarUrl = AVATAR)
    val otherOwnerModel = OwnerModel(id = OTHER_ID, login = OTHER_NAME, avatarUrl = OTHER_AVATAR)
    val ownerMvp = OwnerMvp(id = ID, name = NAME, avatarUrl = AVATAR)
    val otherOwnerEntity = OwnerEntity(id = OTHER_ID, name = OTHER_NAME, avatarUrl = OTHER_AVATAR)
}