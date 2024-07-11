package com.medicines.app.domain.mapper

import com.medicines.app.data.model.DataMedicine
import com.medicines.app.domain.entity.Medicine
import javax.inject.Inject

class MedicineMapper @Inject constructor() {
    fun mapToDomainModel(dataMedicine: DataMedicine): Medicine {
        return Medicine(
            id = dataMedicine.id,
            name = dataMedicine.name,
            dose = dataMedicine.dose,
            strength = dataMedicine.strength
        )
    }

    fun mapToDataModel(domainMedicine: Medicine): DataMedicine {
        return DataMedicine(
            id = domainMedicine.id,
            name = domainMedicine.name,
            dose = domainMedicine.dose,
            strength = domainMedicine.strength
        )
    }
}