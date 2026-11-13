package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("B4: attendance(31,32,T)")
    void bonusRejectsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> DataFlowTargets.bonus(null, 1)
        );
    }

    @Test
    @DisplayName("B5: threshold(31,32,T)")
    void bonusRejectsNegativeThreshold() {
        assertThrows(
                IllegalArgumentException.class,
                () -> DataFlowTargets.bonus(new int[]{}, -1)
        );
    }

    @Test
    @DisplayName(
            "B3: attendance(31,38,F); "
                    + "present(35,50,F); best(37,53,F); score(49,56)"
    )
    void bonusEmptyBelowThreshold() {
        assertEquals(
                0,
                DataFlowTargets.bonus(new int[]{}, 1)
        );
    }

    @Test
    @DisplayName(
            "B6: present(40,50,T); best(43,53,F)"
    )
    void bonusMixedAtThreshold() {
        int[] attendance = {1, 0, 1, 1};

        assertEquals(5, DataFlowTargets.bonus(attendance, 3));
    }

    @Test
    @DisplayName(
            "B7: score(51,54),(54,56)"
    )
    void bonusFiveAtThreshold() {
        int[] attendance = {1, 1, 1, 1, 1};

        assertEquals(8, DataFlowTargets.bonus(attendance, 5));
    }

    @Test
    @DisplayName("B8: total attendance >= 5 but longest streak < 5")
    void bonusSplitStreakDoesNotEarnExtraPoints() {
        int[] attendance = {1, 1, 0, 1, 1, 1};

        assertEquals(5, DataFlowTargets.bonus(attendance, 5));
    }
}