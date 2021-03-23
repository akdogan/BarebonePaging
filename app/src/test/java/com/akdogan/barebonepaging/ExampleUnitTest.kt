package com.akdogan.barebonepaging

import com.akdogan.barebonepaging.repository.Sex
import com.akdogan.barebonepaging.util.getRandomSex
import org.junit.Test

import org.junit.Assert.*
import kotlin.math.roundToInt

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @ExperimentalStdlibApi
    @Test
    fun randomSexGenerator_onlyMale() {
        val sexes = buildList<Sex> {
            repeat(1000){
                this.add(getRandomSex(1,0,0))
            }
        }
        val numMales = sexes.count {
            it == Sex.MALE
        }
        assertEquals(1000, numMales)
    }

    @ExperimentalStdlibApi
    @Test
    fun randomSexGenerator_onlyFemale(){
        val sexes = buildList<Sex>{
            repeat(1000){
                this.add(getRandomSex(0, 1,0))
            }
        }
        val numFemales = sexes.count {
            it == Sex.FEMALE
        }
        assertEquals(1000, numFemales)
    }

    @ExperimentalStdlibApi
    @Test
    fun randomSexGenerator_onlyOther(){
        val sexes = buildList<Sex>{
            repeat(1000){
                this.add(getRandomSex(0, 0,1))
            }
        }
        val numOther = sexes.count {
            it == Sex.OTHER
        }
        assertEquals(1000, numOther)
    }

    @ExperimentalStdlibApi
    @Test
    fun randomSexGenerator_distributionFortyFortyTwenty(){
        val sexes = buildList<Sex> {
            repeat(10000){
                this.add(getRandomSex(40, 40,20))
            }
        }
        var numMale = sexes.count{it == Sex.MALE}
        println( "Male Total: $numMale")
        numMale = (numMale / 1000.0).roundToInt()
        println( "Male Div: $numMale")

        var numFemale = sexes.count{it == Sex.FEMALE}
        println( "Female Total: $numFemale")
        numFemale = (numFemale / 1000.0).roundToInt()
        println(  "Female Div: $numFemale")

        var numOther = sexes.count{it == Sex.OTHER}
        println( "Other Total: $numOther")
        numOther = (numOther / 1000.0).roundToInt()
        println( "Other Div: $numOther")
        assertEquals(4, numMale)
        assertEquals(4, numFemale)
        assertEquals(2, numOther)

    }
}