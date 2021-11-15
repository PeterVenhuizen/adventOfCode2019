import java.util.*;

class Port {
    private int x;
    private int y;
    private List<String> wireSaysHi;

    public Port(int x, int y) {
        this.x = x;
        this.y = y;
        this.wireSaysHi = new ArrayList<String>();
    }

    public Port tracePath(String wireName, Character wireDirection, int stepsLeftToTake) {

        this.wireSaysHi.add(wireName);
        
        if (this.checkIfBothWiresHaveCrossedHere()) {
            this.imACrossing();
        }

        boolean theWireGoesOn = stepsLeftToTake > 0;
        if (theWireGoesOn) {

            int x = calcNextX(this.x, wireDirection);
            int y = calcNextY(this.y, wireDirection);
            String xAndY = String.format("%d-%d", x, y);

            Port neighbourPort;
            if (CrossedWires.ALL_PORTS.get(xAndY) instanceof Port)
                neighbourPort = CrossedWires.ALL_PORTS.get(xAndY);
            else {
                neighbourPort = new Port(x, y);
                CrossedWires.ALL_PORTS.put(xAndY, neighbourPort);
            }

            return neighbourPort.tracePath(wireName, wireDirection, --stepsLeftToTake);
        } else {
            return this;
        }

    }

    private int calcNextX(int x, Character wireDirection) {
        switch (wireDirection) {
            case 'L':
                return x - 1;
            case 'R':
                return x + 1;
            default: 
                return x;
        }
    }

    private int calcNextY(int y, Character wireDirection) {
        switch (wireDirection) {
            case 'U':
                return y + 1;
            case 'D': 
                return y - 1;
            default:
                return y;
        }
    }
    
    public boolean checkIfBothWiresHaveCrossedHere() {
        return this.wireSaysHi.stream().distinct().count() == 2;
    }

    public void imACrossing() {
        CrossedWires.CROSSINGS.add(this);
    }

    public int[] getPosition() {
        return new int[] { this.x, this.y };
    }
}