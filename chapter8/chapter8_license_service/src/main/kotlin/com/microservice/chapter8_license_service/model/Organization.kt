package com.microservice.chapter8_license_service.model

data class Organization(
    var id: String,
    var name: String,
    var contactName: String,
    var contactEmail: String,
    var contactPhone: String,
) {
}