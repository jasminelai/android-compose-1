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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jasminelai.petadoption.data.list
import com.jasminelai.petadoption.model.Personality
import com.jasminelai.petadoption.model.Pet
import com.jasminelai.petadoption.model.Sex
import com.jasminelai.petadoption.model.Species
import com.jasminelai.petadoption.theme.MyTheme
import com.jasminelai.petadoption.theme.pastelBlue
import com.jasminelai.petadoption.theme.pastelMauve
import com.jasminelai.petadoption.theme.pastelPurple
import com.jasminelai.petadoption.theme.pastelTeal

@Composable
fun PetListView(context: Context, pets: List<Pet>) {
    PetCardGrid(pets, context)
}

@Composable
fun PetCardColumn(pets: List<Pet>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(pets) { pet ->
            PetCard(pet)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PetCardGrid(pets: List<Pet>, context: Context? = null) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 3)
    ) {
        items(pets) { pet ->
            PetCard(pet, context)
        }
    }
}

@Composable
fun PetCard(pet: Pet, context: Context? = null) {
    Card(
        elevation = 2.dp,
        backgroundColor = getCardBackground(pet.species),
        modifier = Modifier.size(80.dp, 120.dp)
            .clickable { context?.startActivity(PetDetailActivity.newIntent(context, pet)) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            modifier = Modifier.padding(4.dp)
        ) {
            Image(
                painter = painterResource(getIconResource(pet.species)),
                modifier = Modifier.size(45.dp, 45.dp),
                contentDescription = "Animal icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
            )
            Text(
                text = pet.name,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Preview
@Composable
fun PetCardPreview() {
    MyTheme {
        PetCard(
            Pet(
                name = "Goldie",
                species = Species.DOG,
                personality = Personality.NORMAL,
                sex = Sex.FEMALE,
                hobbies = ""
            )
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        PetCardGrid(list)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        PetCardGrid(list)
    }
}

fun getIconResource(species: Species) = when (species) {
    Species.DOG -> R.drawable.ic_dog
    Species.CAT -> R.drawable.ic_cat
    Species.SQUIRREL -> R.drawable.ic_squirrel
    Species.RABBIT -> R.drawable.ic_rabbit
}

private fun getCardBackground(species: Species) = when (species) {
    Species.DOG -> pastelTeal
    Species.CAT -> pastelBlue
    Species.SQUIRREL -> pastelPurple
    Species.RABBIT -> pastelMauve
}
