package devkdt.devBook.joinRequest.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class JoinManagementResponse {

  public List<JoinOneResponse> joinResponses;

  public JoinManagementResponse(List<JoinOneResponse> joinResponses) {
    this.joinResponses = joinResponses;
  }
}
