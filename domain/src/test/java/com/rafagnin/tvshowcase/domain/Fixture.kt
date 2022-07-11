package com.rafagnin.tvshowcase.domain

import com.rafagnin.tvshowcase.domain.model.ShowDetailModel

object Fixture {

    val showDetailModel = ShowDetailModel(
        1,
        "name",
        "image",
        "description",
        10F,
        "action",
        60,
        "ended",
        "AMC",
        emptyList(),
        "10",
        emptyMap(),
        true
    )
}
