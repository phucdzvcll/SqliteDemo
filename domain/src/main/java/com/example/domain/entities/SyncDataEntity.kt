package com.example.domain.entities

class SyncDataEntity(val syncDataStatus: SyncDataStatus)

enum class SyncDataStatus{
    Success,Fail
}