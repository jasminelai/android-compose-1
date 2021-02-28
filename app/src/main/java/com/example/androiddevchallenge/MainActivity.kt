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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.androiddevchallenge.data.list
import com.example.androiddevchallenge.model.Personality
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.Sex
import com.example.androiddevchallenge.model.Species
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pastelBlue
import com.example.androiddevchallenge.ui.theme.pastelMauve
import com.example.androiddevchallenge.ui.theme.pastelPurple
import com.example.androiddevchallenge.ui.theme.pastelTeal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                PetCardList(list)
            }
        }
    }
}

@Composable
fun PetCardList(petList: List<Pet>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(petList) { pet ->
            PetCard(pet)
        }
    }
}

@Composable
fun PetCard(pet: Pet) {
    Card(
        elevation = 2.dp,
        backgroundColor = getCardBackground(pet.species),
        modifier = Modifier.size(80.dp, 80.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            modifier = Modifier.padding(4.dp)
        ) {
            Image(
                painter = painterResource(getIconResource(pet.species)),
                modifier = Modifier.size(45.dp, 45.dp),
                contentDescription = "Dog icon",
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
        PetCardList(list)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        PetCardList(list)
    }
}

private fun getIconResource(species: Species) = when (species) {
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