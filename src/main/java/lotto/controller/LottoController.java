package lotto.controller;


import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.LottosGenerator;
import lotto.domain.Money;
import lotto.domain.Prize;
import lotto.domain.LottoResult;
import lotto.domain.WinnerLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    InputView inputView = new InputView();

    public void run() {
        LottosGenerator lottosGenerator = new LottosGenerator();

        Money money = getMoney();
        int numberOfLottos = getNumberOfPurchasedLottos(money);

        Lottos lottos = lottosGenerator.generateLottos(numberOfLottos);
        OutputView.printGeneratedLottos(lottos);

        WinnerLotto winnerLotto = getWinnerLotto();

        Map<Prize, Long> prizeResults = getPrizeResults(lottos, winnerLotto);
        OutputView.printStatistics(prizeResults);
        OutputView.printProfitRate(getProfitRate(money, lottos, winnerLotto));
    }

    private Money getMoney() {
        return new Money(inputView.readMoney());
    }

    private int getNumberOfPurchasedLottos(Money money) {
        int numberOfLottos = money.getNumberOfLottos();
        OutputView.printNumberOfPurchasedLottos(numberOfLottos);
        return numberOfLottos;
    }

    private WinnerLotto getWinnerLotto() {
        Lotto winninglotto = new Lotto(inputView.readLottoNumbers());
        BonusNumber bonusNumber = new BonusNumber(inputView.readBonusNumber());
        return new WinnerLotto(winninglotto, bonusNumber);
    }

    private Map<Prize, Long> getPrizeResults(Lottos lottos, WinnerLotto winnerLotto) {
        LottoResult lottoResult = new LottoResult(lottos, winnerLotto);
        return lottoResult.getPrizeResult();
    }

    private Double getProfitRate(Money money, Lottos lottos, WinnerLotto winnerLotto) {
        LottoResult lottoResult = new LottoResult(lottos, winnerLotto);
        return lottoResult.calculateProfitRate(money.getMoney(), lottoResult.getPrizeResult());
    }
}
