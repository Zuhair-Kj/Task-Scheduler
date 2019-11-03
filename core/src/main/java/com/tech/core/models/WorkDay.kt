package com.tech.core.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel
data class WorkDay @ParcelConstructor constructor(val date: String, val engineers: List<Engineer>)