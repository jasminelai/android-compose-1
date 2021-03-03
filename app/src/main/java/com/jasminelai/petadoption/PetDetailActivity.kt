/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jasminelai.petadoption

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jasminelai.petadoption.model.Pet
import com.jasminelai.petadoption.theme.MyTheme
import com.jasminelai.petadoption.theme.typography

class PetDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val pet = requireNotNull(intent?.extras?.getParcelable<Pet>(ARG_PET_ITEM))
                PetDetailView(pet)
            }
        }
    }

    companion object {
        private const val ARG_PET_ITEM = "pet_item"

        fun newIntent(context: Context, pet: Pet) =
            Intent(context, PetDetailActivity::class.java).apply {
                putExtra(ARG_PET_ITEM, pet)
            }
    }
}

@Composable
fun PetDetailView(pet: Pet) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
    ) {
        Image(
            painter = painterResource(getIconResource(pet.species)),
            modifier = Modifier.size(100.dp, 100.dp),
            contentDescription = "Animal icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
        )
        Text(text = pet.name, style = typography.h5)
        Text(text = "Species: ${pet.species.name}", style = typography.body1)
        Text(text = "Sex: ${pet.sex.name}", style = typography.body1)
        Text(text = "Personality: ${pet.personality.name}", style = typography.body1)
        Text(text = "Hobbies: ${if (pet.hobbies.isEmpty()) "N/A" else pet.hobbies}", style = typography.body1)
    }
}
