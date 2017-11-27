package com.example.jpmcosta.objectboxtestproject.objectbox.core

import io.objectbox.Box
import io.objectbox.BoxStore

inline fun <reified T : Any> BoxStore.box(): Box<T> = boxFor(T::class.java)

inline fun <reified T : Any> BoxStore.put(obj: T) = box<T>().put(obj)

inline fun <reified T : Any> BoxStore.remove(obj: T) = box<T>().remove(obj)

inline fun <reified T : Any> BoxStore.remove(obj: Collection<T>) = box<T>().remove(obj)