package com.example.domain.entities

data class SyncChampsTrainsEntity(val syncStatus: SyncStatus) {
    enum class SyncStatus {
        Success, Fail
    }
}

