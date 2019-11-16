package com.san4o.just4fun.authenticjobs.repository.network

import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.internal.bind.util.ISO8601Utils
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.text.ParseException
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

class DateTypeAdapter : TypeAdapter<Date>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    override fun read(`in`: JsonReader): Date? {
        if (`in`.peek() == JsonToken.NULL) {
            `in`.nextNull()
            return null
        }
        return deserializeToDate(`in`.nextString())
    }

    private fun deserializeToDate(json: String): Date? {
        try {
            return dateFormat.parse(json)
        } catch (ignored: ParseException) {
        }

        try {
            return ISO8601Utils.parse(
                json,
                ParsePosition(0)
            )
        } catch (e: ParseException) {
            throw JsonSyntaxException(json, e)
        }

    }

    override fun write(out: JsonWriter, value: Date?) {
        if (value == null) {
            out.nullValue()
            return
        }
        val dateFormatAsString = dateFormat.format(value)
        out.value(dateFormatAsString)
    }
}