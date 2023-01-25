package devkdt.devBook.joinRequest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class JoinRequest {

  @Id
  @GeneratedValue
  @Column(name = "join_id")
  private Long id;

  @Embedded
  private TemporaryMember temporaryMember;

  public Long getId() {
    return id;
  }

  protected JoinRequest() {
  }

  public TemporaryMember getTemporaryMember() {
    return temporaryMember;
  }

  public JoinRequest(TemporaryMember temporaryMember) {
    this.temporaryMember = temporaryMember;
  }

  @Override
  public String toString() {
    return "JoinRequest{" +
        "id=" + id +
        ", temporaryMember=" + temporaryMember +
        '}';
  }
}
