package com.swarn.weather.repository

/**
 * @author Swarn Singh.
 */
interface Repository<T> {
    fun insert(item: T)

    fun update(item: T)

    fun delete(item: T)
}