package devkdt.devBook.joinRequest.dto;

import devkdt.devBook.joinRequest.domain.JoinRequest;
import lombok.Getter;

@Getter
public class JoinOneResponse {

  Long id;
  TemporaryMemberResponse temporaryMember;

  public JoinOneResponse(JoinRequest joinRequest) {
    this.id = joinRequest.getId();
    this.temporaryMember = new TemporaryMemberResponse(joinRequest.getTemporaryMember());
  }
}
