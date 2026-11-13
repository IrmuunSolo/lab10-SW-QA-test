package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusTest {

    @Test
    @DisplayName("B1: all-defs; score(49,54),(54,56)")
    void bonusLongBelowThreshold() {
        int[] attendance = {1, 1, 1, 1, 1, 0, 1};

        assertEquals(3, DataFlowTargets.bonus(attendance, 8));
    }

    @Test
    @DisplayName("B2: all-defs; score(51,56)")
    void bonusZeroThresholdAbsent() {
        int[] attendance = {0};

        assertEquals(5, DataFlowTargets.bonus(attendance, 0));
    }
}