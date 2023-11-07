package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    private final int numberOfLottos = 7;
    private final int moneySpent = numberOfLottos * 1000;
    private final WinnerLotto winnerLotto = new WinnerLotto(List.of(1, 2, 3, 4, 5, 6), numberOfLottos);

    List<Lotto> lottos = Arrays.asList(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 7, 8)),
            new Lotto(List.of(1, 2, 3, 7, 8, 9)),
            new Lotto(List.of(1, 2, 7, 8, 9, 10)),
            new Lotto(List.of(1, 7, 8, 9, 10, 11)),
            new Lotto(List.of(7, 8, 9, 10, 11, 12))
    );

    List<Lotto> lottosSmallPrize = Arrays.asList(
            new Lotto(List.of(1, 10, 11, 12, 13, 14)),
            new Lotto(List.of(1, 10, 11, 12, 13, 14)),
            new Lotto(List.of(1, 10, 11, 12, 13, 14)),
            new Lotto(List.of(1, 10, 11, 12, 13, 14)),
            new Lotto(List.of(1, 10, 11, 12, 13, 14)),
            new Lotto(List.of(1, 10, 11, 12, 13, 14)),
            new Lotto(List.of(1, 10, 11, 12, 13, 14))
    );


    @Test
    @DisplayName("당첨 결과를 계산한다.")
    void calculateLottoResults() {
        assertThat(new LottoResult(new Lottos(lottos), winnerLotto).getPrizeResult())
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        Prize.FIRST_PLACE, 1L,
                        Prize.SECOND_PLACE, 1L,
                        Prize.FOURTH_PLACE, 1L,
                        Prize.THIRD_PLACE, 0L,
                        Prize.FIFTH_PLACE, 1L
                ));
    }

    @Test
    @DisplayName("정돈된 당첨 결과를 계산한다.")
    void getLottoResults() {
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

        LottoResult lottoResult = new LottoResult(new Lottos(lottos), winnerLotto);

        assertThat(lottoResult.getPrizeResult())
                .isEqualTo(finalPrizeResults);
    }

    @Test
    @DisplayName("수익률을 계산한다.")
    void calculateProfitRate() {
        LottoResult lottoResult = new LottoResult(new Lottos(lottosSmallPrize), winnerLotto);
        assertThat(lottoResult.calculateProfitRate(moneySpent, Map.of(
                Prize.FIRST_PLACE, 0L,
                Prize.SECOND_PLACE, 0L,
                Prize.FOURTH_PLACE, 0L,
                Prize.FIFTH_PLACE, 7L,
                Prize.NO_PRIZE, 0L
        ))).isEqualTo(500);
    }
}
