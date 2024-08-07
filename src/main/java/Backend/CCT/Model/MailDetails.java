package Backend.CCT.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String coinName;
    private String coinPrice;
}
