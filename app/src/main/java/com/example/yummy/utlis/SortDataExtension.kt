package com.example.yummy.utlis

import com.example.yummy.data.model.Meal

fun List<Meal>.sortMealByAlphabet() =
    this.sortedWith(Comparator { font, behind -> font.name.compareTo(behind.name) })
