package com.farm.barrenland

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class FertileLandCalculatorSpec extends Specification {

    def "calculate fertile land - field width: #xWidth, field length: #yLength, barren land coordinates: #input, fertile land area(s): #output"() {

        expect:
        FertileLandCalculator calculator = new FertileLandCalculator(xWidth, yLength)
        String[] barrenAreas = calculator.processInput(input)
        calculator.fillBarrenLand(barrenAreas)
        calculator.calculateFertileLand(barrenAreas) == output

        where:
        xWidth | yLength | input                                                                      || output
        1      | 1       | '{“0 0 0 0”}'                                                              || '0'
        2      | 2       | '{“0 0 0 0”}'                                                              || '3'
        4      | 6       | '{“0 0 0 0”}'                                                              || '23'
        4      | 6       | '{“0 0 2 5”}'                                                              || '6'
        4      | 6       | '{“0 0 1 2”}'                                                              || '18'
        4      | 6       | '{“0 2 3 3”}'                                                              || '8 8'
        8      | 12      | '{“1 0 2 11”}'                                                             || '12 60'
        8      | 12      | '{“1 0 2 11”, "4 0 5 11"}'                                                 || '12 12 24'
        8      | 12      | '{“1 0 2 11”, "4 0 5 11", "6 0 7 11"}'                                     || '12 12'
        8      | 12      | '{“1 0 2 5”, "4 0 5 11", "6 0 7 11"}'                                      || '36'
        8      | 12      | '{“1 0 1 11”}'                                                             || '12 72'
        40     | 60      | '{“0 0 0 0”}'                                                              || '2399'
        40     | 60      | '{“0 22 39 37”}'                                                           || '880 880'
        400    | 600     | '{“0 0 0 0”}'                                                              || '239999'
        400    | 600     | '{“0 0 399 598”}'                                                          || '400'
        400    | 600     | '{“0 0 199 299”}'                                                          || '180000'
        400    | 600     | '{“0 0 398 599”}'                                                          || '600'
        400    | 600     | '{“0 0 399 599”}'                                                          || '0'
        400    | 600     | '{“0 292 399 307”}'                                                        || '116800 116800'
        400    | 600     | '{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}' || '22816 192608'
    }


    def "calculate fertile land - field width: #xWidth, field length: #yLength, barren land coordinates: #input - exception: 'Input is not formatted correctly'"() {

        when:
        FertileLandCalculator calculator = new FertileLandCalculator(xWidth, yLength)
        String[] barrenAreas = calculator.processInput(input)
        calculator.fillBarrenLand(barrenAreas)

        then:
        RuntimeException re = thrown()
        re.message == 'Input is not formatted correctly'

        where:
        xWidth | yLength | input
        400    | 600     | '“0 0 100 100”}'
        400    | 600     | '{0 0 100 100”}'
        400    | 600     | '{“0 0 100 100”'
        400    | 600     | '{“0 0 100 100}'
        400    | 600     | '{“0 0 100 100” }'
        400    | 600     | '{“0     0 100 100”}'
        400    | 600     | '{“0 0 100   100”}'
        400    | 600     | '{“-5 0 100 100”}'
        400    | 600     | '{“0 0 100 100 200 200 300 300”}'
        400    | 600     | '{“0 0 100 100 200 200 300”}'


    }

    def "calculate fertile land - field width: #xWidth, field length: #yLength, barren land coordinates: #input - exception: 'Invalid coordinates'"() {

        when:
        FertileLandCalculator calculator = new FertileLandCalculator(xWidth, yLength)
        String[] barrenAreas = calculator.processInput(input)s
        calculator.fillBarrenLand(barrenAreas)

        then:
        RuntimeException re = thrown()
        re.message == 'Invalid coordinates'

        where:
        xWidth | yLength | input
        400    | 600     | '{“0 0 400 200”}'
        400    | 600     | '{“0 0 100 600”}'
        400    | 600     | '{“300 500 100 550”}'
        400    | 600     | '{“300 500 350 499”}'
    }

}
