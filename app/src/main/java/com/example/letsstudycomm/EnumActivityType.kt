package com.example.letsstudycomm

import com.example.letsstudycomm.activity.OkHttp3Activity
import com.example.letsstudycomm.activity.Retrofit2Activity
import kotlin.reflect.KClass

enum class EnumActivityType(val kClass: KClass<*>) {
    OkHttp3(OkHttp3Activity::class),
    Retrofit2(Retrofit2Activity::class)
}