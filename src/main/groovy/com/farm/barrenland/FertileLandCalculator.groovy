package com.farm.barrenland

import org.apache.commons.lang3.StringUtils

import java.util.regex.Pattern

class FertileLandCalculator {

    private final int[][] fieldGrid
    private final int X_WIDTH
    private final int Y_LENGTH
    private final int[] startNode
    private final HashMap<Integer, Integer> fertileLandMap
    private final int EMPTY = 0
    private final int FERTILE = 1
    private final int BARREN = 2

    FertileLandCalculator(final int xWidth, final int yLength) {
        // can modify final fields in constructor
        fieldGrid = new int[xWidth][yLength]
        X_WIDTH = xWidth
        Y_LENGTH = yLength
        startNode = [0, 0]
        fertileLandMap = new HashMap<>()
        initFieldGrid fieldGrid, xWidth, yLength
    }

    void initFieldGrid(int[][] fieldGrid, xWidth, yLength) {
        for (x in 0..xWidth - 1) {
            for (y in 0..(yLength - 1)) {
                fieldGrid[x][y] = EMPTY
            }
        }
    }

    String calculateFertileLand(final String[] barrenAreas) {
        fillBarrenLand(barrenAreas)
        LinkedList<int[]> fertileLandList = new LinkedList<>()
        int fertileLandCount = 0

        while (true) {
            if (fertileLandList.isEmpty()) {
                if (!getStartNode()) {
                    break
                }
                fertileLandCount = fertileLandCount + 1
                fertileLandMap.put fertileLandCount, 0
                fertileLandList.add startNode
            }
            if (!fertileLandList.isEmpty()) {
                int[] node = fertileLandList.remove() // returns the head of the list
                addNeighbors(fertileLandCount, node, fertileLandList)
            }
        }

        return processResult()
    }

    String processResult() {
        Iterator<Integer> iterator = fertileLandMap.values().iterator()
        List<Integer> areas = new ArrayList<>()

        while (iterator.hasNext()) {
            areas.add iterator.next()
        }

        Collections.sort(areas)
        String result = ''
        for (Integer area : areas) {
            result = result + area + ' '
        }
        if (areas.size() == 0) {
            result = '0'
        }
        return StringUtils.stripToEmpty(result)
    }

    void addNeighbors(final int fertileLandKey, final int[] node, final LinkedList<int[]> fertileLandList) {
        int fertileLandSize
        int x = node[0]
        int y = node[1]
        int[] rightNode, leftNode, topNode, bottomNode

        if (fieldGrid[x][y] == EMPTY) {
            if (x < X_WIDTH - 1) {
                rightNode = [x + 1, y]
                if (fieldGrid[x + 1][y] == EMPTY) {
                    fertileLandList.add rightNode
                }
            }

            if (x >= 1) {
                leftNode = [x - 1, y]
                if (fieldGrid[x - 1][y] == EMPTY) {
                    fertileLandList.add leftNode
                }
            }

            if (y < Y_LENGTH - 1) {
                topNode = [x, y + 1]
                if (fieldGrid[x][y + 1] == EMPTY) {
                    fertileLandList.add topNode
                }
            }

            if (y >= 1) {
                bottomNode = [x, y - 1]
                if (fieldGrid[x][y - 1] == EMPTY) {
                    fertileLandList.add bottomNode
                }
            }

            fieldGrid[x][y] = FERTILE
            fertileLandSize = fertileLandMap.get(fertileLandKey) + 1
            fertileLandMap.put fertileLandKey, fertileLandSize
        }
    }

    boolean getStartNode() {
        for (x in 0..X_WIDTH - 1) {
            for (y in 0..(Y_LENGTH - 1)) {
                if (fieldGrid[x][y] == EMPTY) {
                    startNode[0] = x
                    startNode[1] = y

                    return true
                }
            }
        }
        return false
    }

    void fillBarrenLand(final String[] barrenAreas) {
        for (String area : barrenAreas) {
            String[] items = area.split ' '
            int x1 = Integer.parseInt(items[0])
            int y1 = Integer.parseInt(items[1])
            int x2 = Integer.parseInt(items[2])
            int y2 = Integer.parseInt(items[3])

            for (x in x1..x2) {
                for (y in y1..y2) {
                    if (fieldGrid[x][y] != BARREN) {
                        fieldGrid[x][y] = BARREN
                    }
                }
            }
        }
    }

    static String[] processInput(String input) {
        def pattern = Pattern.compile('\\{[\"|“]\\d+\\s\\d+\\s\\d+\\s\\d+[\"|”](,\\s[\"|“]\\d+\\s\\d+\\s\\d+\\s\\d+[\"|”])*\\}')
        def matcher = pattern.matcher(input)
        if (!matcher.matches()) {
            throw new RuntimeException("Input is not formatted correctly")
        }
        input = input.replaceAll('\\{|\"|“|”|\\}', '')
        String[] barrenAreas = input.split(',\\s')
        validateBarrenAreas(barrenAreas, 400, 600)
        return barrenAreas
    }

    private static void validateBarrenAreas(final String[] barrenAreas, final xWidth, final yLength) {
        for (area in barrenAreas) {
            String[] items = area.split ' '

            int x1 = Integer.parseInt(items[0])
            int y1 = Integer.parseInt(items[1])
            int x2 = Integer.parseInt(items[2])
            int y2 = Integer.parseInt(items[3])

            if (x1 > x2 || y1 > y2 || x1 < 0 || y1 < 0 || x2 > xWidth - 1 || y2 > yLength - 1) {
                throw new RuntimeException("Invalid coordinates")
            }
        }
    }

}
