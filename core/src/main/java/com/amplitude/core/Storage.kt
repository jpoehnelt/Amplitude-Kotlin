package com.amplitude.core

import com.amplitude.core.events.BaseEvent
import com.amplitude.core.platform.EventPipeline
import com.amplitude.core.utilities.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

interface Storage {

    enum class Constants(val rawVal: String) {
        LAST_EVENT_ID("last_event_id"),
        PREVIOUS_SESSION_ID("previous_session_id"),
        LAST_EVENT_TIME("last_event_time"),
        OPT_OUT("opt_out"),
        Events("events")
    }

    suspend fun writeEvent(event: BaseEvent)

    suspend fun write(key: Constants, value: String)

    suspend fun rollover()

    fun read(key: Constants): String?

    fun readEventsContent(): List<Any>

    fun getEventsString(content: Any): String

    fun getResponseHandler(storage: Storage, eventPipeline: EventPipeline, configuration: Configuration, scope: CoroutineScope, dispatcher: CoroutineDispatcher, events: Any, eventsString: String): ResponseHandler
}

interface StorageProvider {
    fun getStorage(amplitude: Amplitude): Storage
}