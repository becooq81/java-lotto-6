package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeResultTest {

    private final int numberOfLottos = 7;
    private final int moneySpent = numberOfLottos * 1000;
    private final PlayerLotto playerLotto = new PlayerLotto(List.of(1, 2, 3, 4, 5, 6), numberOfLottos);

    List<Lotto> lottos = Arrays.asList(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 7, 8)),
            new Lotto(List.of(1, 2, 3, 7, 8, 9)),
            new Lotto(List.of(1, 2, 7, 8, 9, 10)),
            new Lotto(List.of(1, 7, 8, 9, 10, 11)),
            new Lotto(List.of(7, 8, 9, 10, 11, 12))
    );


    @Test
    @DisplayName("당첨 결과를 계산한다.")
    void calculatePrizeResults() {
        assertThat(PrizeResult.calculatePrizeResults(new Lottos(lottos), playerLotto))
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        Prize.FIRST_PLACE, 1L,
                        Prize.SECOND_PLACE, 1L,
                        Prize.FOURTH_PLACE, 1L,
                        Prize.FIFTH_PLACE, 1L,
                        Prize.NO_PRIZE, 3L
                ));
    }

    @Test
    @DisplayName("정돈된 당첨 결과를 계산한다.")
    void getPrizeResults() {
        Map<Prize, Long> prizeResults = Map.of(
                Prize.FIRST_PLACE, 1L,
                Prize.SECOND_PLACE, 1L,
                Prize.FOURTH_PLACE, 1L,
                Prize.FIFTH_PLACE, 1L,
                Prize.NO_PRIZE, 3L
        );
        Map<Prize, Long> finalPrizeResults = Map.of(
                Prize.FIFTH_PLACE, 1L,
                Prize.FOURTH_PLACE, 1L,
                Prize.THIRD_PLACE, 0L,
                Prize.SECOND_PLACE, 1L,
                Prize.FIRST_PLACE, 1L
        );

        assertThat(PrizeResult.getPrizeResults(prizeResults)).isEqualTo(finalPrizeResults);
    }

    @Test
    @DisplayName("수익률을 계산한다.")
    void calculateProfitRate() {
        assertThat(PrizeResult.calculateProfitRate(moneySpent, Map.of(
                Prize.FIRST_PLACE, 1L,
                Prize.SECOND_PLACE, 1L,
                Prize.FOURTH_PLACE, 1L,
                Prize.FIFTH_PLACE, 1L,
                Prize.NO_PRIZE, 3L
        ))).isEqualTo(290006.9);
    }
}
