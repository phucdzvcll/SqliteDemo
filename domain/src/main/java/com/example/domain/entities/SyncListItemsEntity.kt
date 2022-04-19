package com.example.domain.entities

data class SyncListItemsEntity(val syncStatus: SyncStatus) {
    enum class SyncStatus {
        Success, Fail
    }
}

