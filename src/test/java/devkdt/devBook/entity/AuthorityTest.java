package devkdt.devBook.entity;

import devkdt.devBook.member.domain.Authority;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthorityTest {

  @Test
  void selectAuthority() {
    Authority admin = Authority.selectAuthority("admin");
    Authority mentor = Authority.selectAuthority("mentor");
    Authority graduate = Authority.selectAuthority("graduate");

    Assertions.assertThat(admin).isEqualTo(Authority.ADMIN);
    Assertions.assertThat(mentor).isEqualTo(Authority.MENTOR);
    Assertions.assertThat(graduate).isEqualTo(Authority.GRADUATE);

  }

}