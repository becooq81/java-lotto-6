package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.DuplicateNumberException;
import lotto.exception.NumberOutOfRangeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinnerLottoTest {

    private static Lotto lotto;
    private static List<Integer> numbers;
    private static int duplicate, overRange, valid, zero, negative;

    @BeforeAll
    static void setup() {
        numbers = List.of(1, 2, 3, 4, 5, 6);
        lotto = new Lotto(numbers);
        duplicate = numbers.get(3);
        overRange = 46;
        valid = 7;
        zero = 0;
        negative = -1;
    }


    @DisplayName("보너스 숫자가 로또 숫자와 중복되면 예외가 발생한다.")
    @Test
    void createWinnerLottoWithDuplicateBonusNumber() {
        assertThatThrownBy(() -> new WinnerLotto(lotto, new BonusNumber(duplicate)))
                .isInstanceOf(DuplicateNumberException.class);
    }

    @DisplayName("보너스 숫자가 허용된 범위 밖의 숫자면 예외가 발생한다.")
    @Test
    void createWinnerLottoWithBonusNumberOutOfRange() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinnerLotto(lotto, new BonusNumber(zero)));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinnerLotto(lotto, new BonusNumber(overRange)));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinnerLotto(lotto, new BonusNumber(negative)));
    }

    @DisplayName("플레이어 로또 생성에 성공한다.")
    @Test
    void createWinnerLottoSuccessfully() {
        Assertions.assertAll(() -> new WinnerLotto(lotto, new BonusNumber(valid)));
    }

}
