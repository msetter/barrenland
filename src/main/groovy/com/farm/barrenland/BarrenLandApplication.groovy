package com.farm.barrenland

import org.apache.commons.lang3.StringUtils

class BarrenLandApplication {

    static void main(String[] args) {

        println 'Please enter barren land coordinates:'

        String input = StringUtils.stripToEmpty(new Scanner(System.in).nextLine())

        FertileLandCalculator fertileLandCalculator = new FertileLandCalculator(400, 600)

        String[] barrenAreas = fertileLandCalculator.processInput(input)

        String fertileAreas = fertileLandCalculator.calculateFertileLand(barrenAreas)

        println fertileAreas
    }

}
