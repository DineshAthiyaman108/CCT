package Backend.CCT.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {

    @Column(name = "userNumber")
    @JsonProperty("userNumber")
    private String userNumber;

    @Id
    @Column(name = "userName")
    @JsonProperty("userName")
    private String userName;

    @Column(name = "userpass")
    @JsonProperty("userPassword")
    private String userPassword;

    @Column(name = "userEmail")
    @JsonProperty("userEmail")
    private String userEmail;
}
