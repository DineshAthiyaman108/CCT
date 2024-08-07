package Backend.CCT.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Coin {

    @Id
    @Column(name = "CoinName")
    String Name;

    @Column(name = "userName")
    String userName;

    @Column(name ="Price")
    BigDecimal coinPrice;

}
