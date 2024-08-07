package Backend.CCT.Model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateCoin {

    String coinName;

    BigDecimal coinPrice;
}
