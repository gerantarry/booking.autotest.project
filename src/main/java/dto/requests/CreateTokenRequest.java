package dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTokenRequest {
    private String username;
    private String password;
}
