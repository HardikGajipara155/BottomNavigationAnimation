package com.example.bottom_navigation_module.shape

import com.example.bottom_navigation_module.animation.indendshape.ShapeCornerRadius
import com.example.bottom_navigation_module.animation.indendshape.shapeCornerRadius

data class IndentShapeData(
    val xIndent: Float = 0f,
    val height: Float = 0f,
    val width: Float = 0f,
    val cornerRadius: ShapeCornerRadius = shapeCornerRadius(0f),
    val ballOffset: Float = 0f,
)