public class Snail {
    enum Direction {
        RIGHT, LEFT, UP, DOWN;
    }

    int[][] array;
    int wallRight, wallLeft, wallTop, wallBottom;
    int x = 0, y = 0;
    Direction direction = Direction.RIGHT;

    public Snail(int[][] array) {
        wallLeft = wallTop = -1;
        wallRight = wallBottom = array.length;
        this.array = array;
    }

    public int getNext() {
        int element = array[y][x];
        switch(direction) {
            case RIGHT:
                if(wallRight-x == 1) {
                    direction = Direction.DOWN;
                    wallTop += 1;
                    y += 1;
                }
                else {
                    x += 1;
                }
                break;
            case DOWN:
                if(wallBottom-y == 1) {
                    direction = Direction.LEFT;
                    wallRight -= 1;
                    x -= 1;
                }
                else {
                    y += 1;
                }
                break;
            case LEFT:
                if(x-wallLeft == 1) {
                    direction = Direction.UP;
                    wallBottom -= 1;
                    y -= 1;
                }
                else {
                    x -= 1;
                }
                break;
            case UP:
                if(y-wallTop == 1) {
                    direction = Direction.RIGHT;
                    wallLeft += 1;
                    x += 1;
                }
                else {
                    y -= 1;
                }
                break;
        }
        return element;
    }

    public static int[] snail(int[][] array) {
        if(array.length == 0 || array[0].length == 0) {
            return new int[] {};
        }
        
        Snail snail = new Snail(array);
        int[] res = new int[array.length*array.length];

        for(int i=0; i < res.length; i++) {
            res[i] = snail.getNext();
        }

        return res;
    }
}