package com.example.domain.entities

data class SyncListChampsEntity(val syncStatus: SyncStatus) {
    enum class SyncStatus {
        Success, Fail
    }
}

