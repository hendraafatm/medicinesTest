package com.medicines.app.ui.medicine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medicines.app.domain.entity.Medicine

@Composable
fun MedicineCard(medicine: Medicine, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Name: ${medicine.name}",
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 15.dp)

            )
            Text(
                text = "Dose: ${medicine.dose}", fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 15.dp)


            )
            Text(
                text = "Strength: ${medicine.strength}", fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 15.dp)

            )
        }
    }
}
