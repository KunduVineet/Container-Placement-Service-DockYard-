package service;

import model.Container;
import model.PlacementRequest;
import model.PlacementResponse;
import model.Slot;
import org.springframework.stereotype.Service;

@Service
public class ContainerPlacementService {
    private static final int INVALID_PENALTY = 10_000;

    public PlacementResponse placeContainer (PlacementRequest request){
        Container container = request.getContainer();
        Slot bestslot = null;
        double bestScore = Double.MAX_VALUE;

        for(Slot slot: request.getYardMap()){
            double score = calculateScore(container, slot);
            if(score < bestScore){
                bestScore = score;
                bestslot = slot;
            }
        }

        if(bestScore >= INVALID_PENALTY){
            return new PlacementResponse("No suitable slot");
        }
        return new PlacementResponse(container.getId(), bestslot.getX(), bestslot.getY());
    }

    // Calculate score for a container-slot pair
    private double calculateScore(Container container, Slot slot) {
        // Manhattan distance: |x1-x2| + |y1-y2|
        double distance = Math.abs(container.getX() - slot.getX()) +
                Math.abs(container.getY() - slot.getY());

        // Size penalty: 0 if fits, 10,000 if not
        int sizePenalty = isSizeCompatible(container.getSize(), slot.getSizeCap())
                ? 0 : INVALID_PENALTY;

        // Cold penalty: 0 if needs met, 10,000 if not
        int coldPenalty = (!container.isNeedCold() || slot.isHasColdUnit())
                ? 0 : INVALID_PENALTY;

        // Occupied penalty: 10,000 if taken, 0 if free
        int occupiedPenalty = slot.isOccupied() ? INVALID_PENALTY : 0;

        return distance + sizePenalty + coldPenalty + occupiedPenalty;
    }

    // Check if container size fits slot capacity
    private boolean isSizeCompatible(String containerSize, String slotSizeCap) {
        if (containerSize.equals("small")) {
            return true; // Small fits in small or big
        }
        return slotSizeCap.equals("big"); // Big only fits in big
    }
}
