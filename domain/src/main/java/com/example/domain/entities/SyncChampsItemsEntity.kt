package com.example.domain.entities

data class SyncChampsItemsEntity(val syncStatus: SyncStatus) {
    enum class SyncStatus {
        Success, Fail
    }
}

