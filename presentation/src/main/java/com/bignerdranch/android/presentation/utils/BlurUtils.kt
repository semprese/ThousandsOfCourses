//package com.bignerdranch.android.presentation
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.graphics.drawable.BitmapDrawable
//import android.renderscript.Allocation
//import android.renderscript.Element
//import android.renderscript.RenderScript
//import android.renderscript.ScriptIntrinsicBlur
//import android.widget.Button
//
//
//internal object BlurUtils {
//    fun applyBlur(button: Button, context: Context) {
//        // Загрузите фоновое изображение
//        val bitmap =
//            BitmapFactory.decodeResource(context.resources, R.drawable.your_background_image)
//
//        // Создайте размытое изображение
//        val blurredBitmap = blurBitmap(bitmap, context)
//
//        // Установите размытое изображение как фон кнопки
//        button.background = BitmapDrawable(context.resources, blurredBitmap)
//    }
//
//    private fun blurBitmap(bitmap: Bitmap, context: Context): Bitmap {
//        val outputBitmap = Bitmap.createBitmap(bitmap)
//        val rs = RenderScript.create(context)
//        val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
//
//        val tmpIn = Allocation.createFromBitmap(rs, bitmap)
//        val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
//
//        blurScript.setRadius(25f) // Сила размытия
//        blurScript.setInput(tmpIn)
//        blurScript.forEach(tmpOut)
//        tmpOut.copyTo(outputBitmap)
//
//        return outputBitmap
//    }
//}