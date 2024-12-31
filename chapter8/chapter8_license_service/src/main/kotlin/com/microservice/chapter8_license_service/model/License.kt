package com.microservice.chapter8_license_service.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.hateoas.RepresentationModel

@Entity
@Table(name = "licenses")
data class License(

    @Id
    @Column(name = "license_id", nullable = false)
    var licenseId: String = "", // 기본값 추가

    var description: String? = null,

    @Column(name = "organization_id", nullable = false)
    var organizationId: String = "",

    @Column(name = "product_name", nullable = false)
    var productName: String = "",

    @Column(name = "license_type", nullable = false)
    var licenseType: String = "",

    @Column(name = "comment")
    var comment: String = "",

    @Transient
    var organizationName: String? = null,
    @Transient
    var contactName: String? = null,
    @Transient
    var contactPhone: String? = null,
    @Transient
    var contactEmail: String? = null,
) : RepresentationModel<License>() {

    fun withComment(comment: String): License {
        this.comment = comment
        return this
    }
}
