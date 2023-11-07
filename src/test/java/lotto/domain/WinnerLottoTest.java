package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinnerLottoTest {

    List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);

    @DisplayName("보너스 숫자가 로또 숫자와 중복되면 예외가 발생한다.")
    @Test
    void createWinnerLottoWithDuplicateBonusNumber() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinnerLotto(lottoNumbers, 6));
    }

    @DisplayName("보너스 숫자가 허용된 범위 밖의 숫자면 예외가 발생한다.")
    @Test
    void createWinnerLottoWithBonusNumberOutOfRange() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinnerLotto(lottoNumbers, 0));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinnerLotto(lottoNumbers, 46));
    }

    @DisplayName("플레이어 로또 생성에 성공한다.")
    @Test
    void createWinnerLottoSuccessfully() {
        Assertions.assertAll(() -> new WinnerLotto(lottoNumbers, 7));
    }

}
